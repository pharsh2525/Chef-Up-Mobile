package com.example.chef_up;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.chef_up.adapers.RecipeAdapter;
import com.example.chef_up.databinding.ActivityUserRecipeBinding;
import com.example.chef_up.models.Recipe;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity to display recipes created by the logged-in user in the Chef Up application.
 * This class handles fetching user-specific recipes from Firebase and displaying them in a RecyclerView.
 */
public class UserRecipeActivity extends AppCompatActivity {
    private ActivityUserRecipeBinding binding; // Define a binding variable
    private RecipeAdapter adapter;
    private List<Recipe> recipes = new ArrayList<>();

    /**
     * Called when the activity is starting.
     * This method sets up the UI, initializes the RecyclerView and its adapter,
     * and invokes the method to fetch user-specific recipes from Firebase.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this contains the data it most recently supplied in onSaveInstanceState(Bundle). Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserRecipeBinding.inflate(getLayoutInflater()); // Initialize the binding
        setContentView(binding.getRoot()); // Set the content view to the root of the binding

        adapter = new RecipeAdapter(); // Assuming your adapter is ready to handle a list
        binding.rvUserRecipes.setAdapter(adapter); // Use binding to reference the RecyclerView
        binding.rvUserRecipes.setLayoutManager(new LinearLayoutManager(this));

        fetchRecipes();
    }

    /**
     * Fetches recipes created by the logged-in user from Firebase.
     * It queries the Firebase database for recipes matching the user's ID,
     * and updates the RecyclerView adapter with the retrieved data.
     */
    private void fetchRecipes() {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Recipes");

        databaseReference.orderByChild("user").equalTo(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recipes.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Recipe recipe = snapshot.getValue(Recipe.class);
                    if (recipe != null) {
                        recipes.add(recipe);
                    }
                }
                adapter.setRecipeList(recipes); // Update the adapter with the new list
                adapter.notifyDataSetChanged(); // Notify the adapter of the data change
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle the error
            }
        });
    }
}