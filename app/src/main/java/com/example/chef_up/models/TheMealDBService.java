package com.example.chef_up.models;

import com.example.chef_up.models.MealDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Defines the Retrofit service for accessing TheMealDB API.
 * This interface declares methods to interact with the API endpoints.
 */
public interface TheMealDBService {
    /**
     * Gets a random meal from TheMealDB API.
     * This method corresponds to the '/random.php' API endpoint.
     *
     * @return A Call object encapsulating a MealDBResponse,
     *         which contains a list of Meal objects.
     */
    @GET("api/json/v1/1/random.php")
    Call<MealDBResponse> getRandomMeal();
}
