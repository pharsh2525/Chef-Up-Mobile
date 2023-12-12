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
import com.example.chef_up.models.Meal;
import com.example.chef_up.models.MealDBResponse;
import com.example.chef_up.models.Recipe;
import com.example.chef_up.models.TheMealDBService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Callback;
import retrofit2.Response;

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
        fetchRandomMeals();
    }
    private void fetchRandomMeals() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMealDBService service = retrofit.create(TheMealDBService.class);
        popularRecipes = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            service.getRandomMeal().enqueue(new Callback<MealDBResponse>() {
                @Override
                public void onResponse(Call<MealDBResponse> call, Response<MealDBResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Meal meal = response.body().getFirstMeal();
                        if (meal != null) {
                            popularRecipes.add(convertMealToRecipe(meal));
                            updateRecyclerView();
                        }
                    }
                }

                @Override
                public void onFailure(Call<MealDBResponse> call, Throwable t) {
                    // Handle failure
                }
            });
        }
    }

    private Recipe convertMealToRecipe(Meal meal) {
        // Convert Meal object to Recipe object
        return new Recipe("", meal.getStrMeal(), meal.getStrInstructions(), "", "", meal.getStrMealThumb(), "");
    }

    private void updateRecyclerView() {
        RecipeAdapter adapter = new RecipeAdapter();
        adapter.setRecipeList(popularRecipes);
        binding.rvHighLight.setAdapter(adapter);
    }

//    private void loadHighLightRecipes() {
//        binding.rvHighLight.setAdapter(new RecipeAdapter());
//        popularRecipes = new ArrayList<>();
//        popularRecipes.add(new Recipe("1", "Popular One","null", "Itallian", "quick", "recipe4", "chef-Up"));
//        popularRecipes.add(new Recipe("1", "Popular Two","null", "Itallian", "quick", "recipe2", "chef-Up"));
//        popularRecipes.add(new Recipe("1", "Popular Three","null", "Itallian", "quick", "recipe3", "chef-Up"));
//        popularRecipes.add(new Recipe("1", "Popular Four","null", "Itallian", "quick", "recipe1", "chef-Up"));
//
//        RecipeAdapter adapter = (RecipeAdapter) binding.rvHighLight.getAdapter();
//        if(adapter != null){
//            adapter.setRecipeList(popularRecipes);
//            adapter.notifyDataSetChanged();
//        }
//
//    }

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