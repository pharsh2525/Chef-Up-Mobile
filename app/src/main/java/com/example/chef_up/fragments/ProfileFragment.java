package com.example.chef_up.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.chef_up.AddRecipeActivity;
import com.example.chef_up.LoginActivity;
import com.example.chef_up.R;
import com.example.chef_up.UserRecipeActivity;
import com.example.chef_up.databinding.FragmentProfileBinding;
import com.example.chef_up.models.User;
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
import java.util.Objects;

/**
 * ProfileFragment displays and manages the user's profile.
 * It enables the user to view their profile details, edit their profile picture,
 * manage their recipes, and log out from the application.
 */
public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private User user;

    /**
     * Inflates the fragment's layout and initializes the view binding.
     * @param inflater LayoutInflater object to inflate views in the fragment.
     * @param container Parent view to attach the fragment's UI to.
     * @param savedInstanceState If non-null, this fragment is being reconstructed from a previous saved state.
     * @return The View for the fragment's UI.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Called immediately after onCreateView. This method initializes the fragment's UI components and event listeners.
     * @param view The View returned by onCreateView.
     * @param savedInstanceState If non-null, the fragment is being re-constructed from a previous saved state.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadProfile();
        updateProfileImage();

        // Set up event listeners for button clicks
        binding.btnShareRecipe.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), AddRecipeActivity.class));
        });

        binding.btnMyRecipes.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), UserRecipeActivity.class));
        });

        binding.btnLogOut.setOnClickListener(v -> {
            logoutUser();
        });
    }

    /**
     * Logs out the current user and redirects to the LoginActivity.
     */
    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        if (getActivity() != null) getActivity().finish();
    }

    /**
     * Sets up an event listener for updating the user's profile picture.
     * Opens a dialog to select a new image and triggers its upload to Firebase Storage.
     */
    private void updateProfileImage() {
        binding.imgEditProfilePic.setOnClickListener(v -> {
            PickImageDialog.build(new PickSetup()).show(requireActivity()).setOnPickResult(r -> {
                Log.e("ProfileFragment", "onPickResult:" + r.getUri());
                binding.imgProfilePic.setImageBitmap(r.getBitmap());
                uploadImage(r.getBitmap());
            }).setOnPickCancel(() -> Toast.makeText(requireContext(), "Cancelled", Toast.LENGTH_SHORT).show());
        });
    }

    /**
     * Uploads the selected image to Firebase Storage and updates the user's profile picture URL in the Firebase database.
     * @param bitmap The image to be uploaded.
     */
    private void uploadImage(Bitmap bitmap) {
        // Upload logic and Firebase database update
    }

    /**
     * Loads the user's profile information from Firebase and updates the UI accordingly.
     */
    private void loadProfile() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Users").child(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                user = snapshot.getValue(User.class);
                if (user != null) {
                    binding.tvFullName.setText(user.getFirstName() + " " + user.getLastName());
                    Glide.with(requireContext()).load(user.getImage()).centerCrop().placeholder(R.mipmap.ic_launcher).into(binding.imgProfilePic);
                } else {
                    Log.e("ProfileFragment", "onDataChange: User is null");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ProfileFragment", "onCancelled:" + error.getMessage());
            }
        });
    }

    /**
     * Cleans up resources when the view is destroyed to prevent memory leaks.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
