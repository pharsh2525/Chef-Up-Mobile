package com.example.chef_up;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.chef_up.databinding.ActivityRecipeDetailBinding;
import com.example.chef_up.models.Recipe;

/**
 * Activity for displaying the details of a selected recipe in the Chef Up application.
 * It shows detailed information about the recipe, including the name, description, and an image.
 */
public class RecipeDetailActivity extends AppCompatActivity {
    private ActivityRecipeDetailBinding binding;
    private static final String EXTRA_RECIPE = "com.example.chef_up.EXTRA_RECIPE";

    /**
     * Static method to start the RecipeDetailActivity.
     * It creates an intent and passes the Recipe object to the activity.
     *
     * @param context The context from which the activity is started.
     * @param recipe  The Recipe object to be displayed.
     */
    public static void start(Context context, Recipe recipe) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(EXTRA_RECIPE, recipe);
        context.startActivity(intent);
    }

    /**
     * Called when the activity is starting.
     * It initializes the UI components and retrieves the Recipe object passed from the previous activity.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this contains the data it most recently supplied in onSaveInstanceState(Bundle). Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve the Recipe object
        Recipe recipe = (Recipe) getIntent().getSerializableExtra(EXTRA_RECIPE);
        displayRecipeDetails(recipe);
    }

    /**
     * Displays the details of the recipe in the UI.
     * It sets the recipe title, instructions, and loads the image using Glide.
     *
     * @param recipe The Recipe object whose details are to be displayed.
     */
    private void displayRecipeDetails(Recipe recipe) {
        if (recipe != null) {
            // Set the recipe details on your views
            binding.txtTitle.setText(recipe.getName());
            binding.txtInstructions.setText(recipe.getDescription());
            Glide.with(this)
                    .load(recipe.getImage()) // 'getImage' should return a URL or a drawable resource
                    .placeholder(R.drawable.logo)
                    .centerCrop()// Placeholder image
                    .into(binding.imgRecipeDetail);
            // Load the image from URL or resource
        }
    }
}
