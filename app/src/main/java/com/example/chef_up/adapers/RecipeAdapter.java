package com.example.chef_up.adapers;

import static com.example.chef_up.databinding.ItemRecipeBinding.inflate;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chef_up.R;
import com.example.chef_up.databinding.ItemRecipeBinding;
import com.example.chef_up.models.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHoler> {
    List<Recipe> recipeList;

    public void setRecipeList(List<Recipe> recipeList){
        this.recipeList = recipeList;
    }
    @NonNull
    @Override
    public RecipeAdapter.RecipeHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecipeHoler(inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.RecipeHoler holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.onBind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public static class RecipeHoler extends RecyclerView.ViewHolder {
        ItemRecipeBinding binding;
        public RecipeHoler(@NonNull ItemRecipeBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
        public void onBind(Recipe recipe) {
            String imageName = recipe.getImage();
            Context context = itemView.getContext();
            int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

            if (imageResId != 0) {
                binding.imgRecipe.setImageResource(imageResId);
            } else {
                binding.imgRecipe.setImageResource(R.drawable.logo); // A default image
            }
            binding.tvRecipeName.setText(recipe.getName());
        }
    }
}
