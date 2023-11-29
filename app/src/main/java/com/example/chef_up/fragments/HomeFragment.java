package com.example.chef_up.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.chef_up.adapers.RecipeAdapter;
import com.example.chef_up.databinding.FragmentHomeBinding;
import com.example.chef_up.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    List<Recipe> favouriteRecipes;
    List<Recipe> popularRecipes;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadFavouriteRecipes();
        loadHighLightRecipes();
    }

    private void loadHighLightRecipes() {
        binding.rvHighLight.setAdapter(new RecipeAdapter());
        popularRecipes = new ArrayList<>();
        popularRecipes.add(new Recipe("1", "Popular One","null", "Itallian", "quick", "recipe4", "chef-Up"));
        popularRecipes.add(new Recipe("1", "Popular Two","null", "Itallian", "quick", "recipe2", "chef-Up"));
        popularRecipes.add(new Recipe("1", "Popular Three","null", "Itallian", "quick", "recipe3", "chef-Up"));
        popularRecipes.add(new Recipe("1", "Popular Four","null", "Itallian", "quick", "recipe1", "chef-Up"));

        RecipeAdapter adapter = (RecipeAdapter) binding.rvHighLight.getAdapter();
        if(adapter != null){
            adapter.setRecipeList(popularRecipes);
            adapter.notifyDataSetChanged();
        }

    }

    private void loadFavouriteRecipes() {
        favouriteRecipes = new ArrayList<>();
        favouriteRecipes.add(new Recipe("1", "Favourite One","null", "Itallian", "quick", "recipe1", "chef-Up"));
        favouriteRecipes.add(new Recipe("2", "Favourite Two","null", "Itallian", "quick", "recipe2", "chef-Up"));
        favouriteRecipes.add(new Recipe("1", "Favourite Three","null", "Itallian", "quick", "recipe3", "chef-Up"));
        favouriteRecipes.add(new Recipe("1", "Favourite Four","null", "Itallian", "quick", "recipe4", "chef-Up"));
        binding.rvFavorites.setAdapter(new RecipeAdapter());
        RecipeAdapter adapter = (RecipeAdapter) binding.rvFavorites.getAdapter();
        if(adapter != null){
            adapter.setRecipeList(favouriteRecipes);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}