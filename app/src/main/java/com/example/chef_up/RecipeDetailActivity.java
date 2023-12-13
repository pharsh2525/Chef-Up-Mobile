package com.example.chef_up;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.chef_up.databinding.ActivityRecipeDetailBinding;
import com.example.chef_up.models.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {
    private ActivityRecipeDetailBinding binding;
    private static final String EXTRA_RECIPE = "com.example.chef_up.EXTRA_RECIPE";

    public static void start(Context context, Recipe recipe) {
        Intent intent = new Intent(context, RecipeDetailActivity.class);
        intent.putExtra(EXTRA_RECIPE, recipe);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Retrieve the Recipe object
        Recipe recipe = (Recipe) getIntent().getSerializableExtra(EXTRA_RECIPE);
        displayRecipeDetails(recipe);
    }

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
