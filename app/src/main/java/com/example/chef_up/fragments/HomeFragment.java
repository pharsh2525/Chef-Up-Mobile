package com.example.chef_up.fragments;

import android.os.Bundle;
import android.util.Log;
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

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * HomeFragment is responsible for displaying lists of popular and favorite recipes on the home screen.
 * It fetches random meals from TheMealDB API and displays them in two separate RecyclerViews.
 */
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private List<Recipe> favouriteRecipes;
    private static List<Recipe> popularRecipes = null;

    /**
     * Inflates the layout for the fragment's UI and initializes view binding.
     *
     * @param inflater LayoutInflater object to inflate views in the fragment.
     * @param container Parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The View for the fragment's UI.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Called immediately after onCreateView. Checks if popular recipes have been previously fetched.
     * If not, it initiates the process to fetch random meals.
     *
     * @param view The View returned by onCreateView.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (popularRecipes == null) {
            fetchRandomMeals();
        } else {
            updateRecyclerViews();
        }
    }

    /**
     * Fetches random meals from TheMealDB API and updates the RecyclerViews for popular and favorite recipes.
     * This method fetches data asynchronously and updates the adapter once data is fetched.
     */
    private void fetchRandomMeals() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TheMealDBService service = retrofit.create(TheMealDBService.class);

        // Assuming you want 4 random meals for each list
        int totalCalls = 8; // 4 for popular and 4 for favorites
        popularRecipes = new ArrayList<>();
        favouriteRecipes = new ArrayList<>();

        for (int i = 0; i < totalCalls; i++) {
            service.getRandomMeal().enqueue(new Callback<MealDBResponse>() {
                @Override
                public void onResponse(Call<MealDBResponse> call, Response<MealDBResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Meal meal = response.body().getFirstMeal();
                        if (meal != null) {
                            if (popularRecipes.size() < 4) {
                                popularRecipes.add(convertMealToRecipe(meal));
                            } else {
                                favouriteRecipes.add(convertMealToRecipe(meal));
                            }

                            if (popularRecipes.size() + favouriteRecipes.size() == totalCalls) {
                                updateRecyclerViews();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<MealDBResponse> call, Throwable t) {
                    // Handle API request failure
                }
            });
        }
    }

    /**
     * Updates the RecyclerView adapters for both popular and favorite recipes lists.
     */
    private void updateRecyclerViews() {
        RecipeAdapter popularAdapter = (RecipeAdapter) binding.rvHighLight.getAdapter();
        if (popularAdapter == null) {
            popularAdapter = new RecipeAdapter();
            binding.rvHighLight.setAdapter(popularAdapter);
        }
        popularAdapter.setRecipeList(popularRecipes);
        popularAdapter.notifyDataSetChanged();

        RecipeAdapter favouriteAdapter = (RecipeAdapter) binding.rvFavorites.getAdapter();
        if (favouriteAdapter == null) {
            favouriteAdapter = new RecipeAdapter();
            binding.rvFavorites.setAdapter(favouriteAdapter);
        }
        favouriteAdapter.setRecipeList(favouriteRecipes);
        favouriteAdapter.notifyDataSetChanged();
    }

    /**
     * Converts a Meal object from the API response to a Recipe object suitable for the adapter.
     *
     * @param meal The Meal object to convert.
     * @return The converted Recipe object.
     */
    private Recipe convertMealToRecipe(Meal meal) {
        // Convert Meal object to Recipe object
        return new Recipe("", meal.getStrMeal(), meal.getFormattedDescription(), "", "", meal.getStrMealThumb(), "");
    }

    /**
     * Called when the view previously created by onCreateView has been detached from the fragment.
     * The binding is set to null to avoid memory leaks.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
