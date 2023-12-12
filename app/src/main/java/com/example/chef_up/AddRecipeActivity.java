package com.example.chef_up;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chef_up.databinding.ActivityAddRecipeBinding;
import com.example.chef_up.models.Category;
import com.example.chef_up.models.Recipe;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class AddRecipeActivity extends AppCompatActivity {

    ActivityAddRecipeBinding binding;
    private Recipe recipe;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddRecipeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadCategories();

        binding.btnSubmitRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRecipe();
            }
        });

        binding.imgUserRecipeUpload.setOnClickListener(view -> {
            PickImageDialog.build(new PickSetup()).show(AddRecipeActivity.this).setOnPickResult(r -> {
                binding.imgUserRecipeUpload.setImageBitmap(r.getBitmap());
                binding.imgUserRecipeUpload.setScaleType(ImageView.ScaleType.CENTER_CROP);
                bitmap = r.getBitmap();
            }).setOnPickCancel(() -> Toast.makeText(AddRecipeActivity.this,"Cancelled",Toast.LENGTH_SHORT).show());
        });
    }

    private void loadCategories() {
        List<String> categories = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        binding.inCategory.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Categories");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() && snapshot.hasChildren()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        categories.add(dataSnapshot.getValue(Category.class).getName());
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void UserRecipe() {
        String name = binding.inDishName.getText().toString();
        String description = binding.inDishDescription.getText().toString();
        String category = binding.inCategory.getText().toString();

        boolean isValid = true;

        if (name.isEmpty()) {
            binding.inDishName.setError("Please name your Recipe");
            isValid = false;
        }
        if (description.isEmpty()) {
            binding.inDishDescription.setError("Please add instructions");
            isValid = false;
        }
        if (category.isEmpty()) {
            binding.inCategory.setError("Please select a Category");
            isValid = false;
        }

        if (isValid) {
            recipe = new Recipe();
            recipe.setName(name);
            recipe.setDescription(description);
            recipe.setCategory(category);
            recipe.setUser(FirebaseAuth.getInstance().getUid());

            uploadImage().addOnSuccessListener(uploadUrl -> {
                recipe.setImage(uploadUrl);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Recipes");
                String id = reference.push().getKey();
                recipe.setId(id);
                if (id != null) {
                    reference.child(id).setValue(recipe).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddRecipeActivity.this, "Recipe added successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(AddRecipeActivity.this, "Error in adding recipe", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }).addOnFailureListener(e -> {
                Toast.makeText(AddRecipeActivity.this, "Error uploading image", Toast.LENGTH_SHORT).show();
            });
        }
    }

    private Task<String> uploadImage() {
        TaskCompletionSource<String> taskCompletionSource = new TaskCompletionSource<>();
        String id = System.currentTimeMillis() + "";

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference().child("images/user_recipes/" + id + "_recipe.png");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        storageReference.putBytes(data).continueWithTask(task -> {
            if (!task.isSuccessful()) {
                throw task.getException();
            }
            return storageReference.getDownloadUrl();
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Uri downloadUri = task.getResult();
                taskCompletionSource.setResult(downloadUri.toString());
            } else {
                taskCompletionSource.setException(task.getException());
            }
        });

        return taskCompletionSource.getTask();
    }

}