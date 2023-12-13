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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start RecipeDetailActivity and pass the selected recipe
                RecipeDetailActivity.start(holder.itemView.getContext(), recipe);
            }
        });
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
            Glide.with(context)
                    .load(recipe.getImage())
                    .centerCrop()
                    .placeholder(R.drawable.logo)
                    . into(binding.imgRecipe);
            binding.tvRecipeName.setText(recipe.getName());
        }
    }
}
