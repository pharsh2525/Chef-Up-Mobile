package com.example.chef_up.adapers;

import static com.example.chef_up.databinding.ItemRecipeBinding.inflate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chef_up.R;
import com.example.chef_up.RecipeDetailActivity;
import com.example.chef_up.databinding.ItemRecipeBinding;
import com.example.chef_up.models.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * RecipeAdapter is a RecyclerView adapter used to display a list of recipes.
 * It manages the creation and binding of RecipeHolder instances, which define
 * the appearance and behavior of individual items in the recipe list.
 */
public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {
    private List<Recipe> recipeList = new ArrayList<>();

    /**
     * Updates the recipe list in the adapter and notifies the adapter of data changes.
     *
     * @param recipeList The new list of recipes to display.
     */
    public void setRecipeList(List<Recipe> recipeList){
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }

    /**
     * Called when RecyclerView needs a new RecipeHolder to represent a recipe item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new RecipeHolder that holds a View for a recipe item.
     */
    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeHolder(inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     *
     * @param holder The RecipeHolder which should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecipeHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.onBind(recipe);
        holder.itemView.setOnClickListener(v -> RecipeDetailActivity.start(holder.itemView.getContext(), recipe));
    }

    /**
     * Returns the total number of recipes in the list held by the adapter.
     *
     * @return The total number of recipes in this adapter.
     */
    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    /**
     * RecipeHolder defines the UI and behavior for individual recipe items within the RecyclerView.
     */
    public static class RecipeHolder extends RecyclerView.ViewHolder {
        ItemRecipeBinding binding;

        /**
         * Constructs a RecipeHolder with the specified item binding.
         *
         * @param itemView The binding for the recipe item.
         */
        public RecipeHolder(@NonNull ItemRecipeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        /**
         * Binds a recipe object to the holder, setting the image, name, and other UI elements as needed.
         *
         * @param recipe The recipe to bind to this holder.
         */
        public void onBind(Recipe recipe) {
            Glide.with(binding.getRoot().getContext())
                    .load(recipe.getImage())
                    .centerCrop()
                    .placeholder(R.drawable.logo)
                    .into(binding.imgRecipe);
            binding.tvRecipeName.setText(recipe.getName());
        }
    }
}

