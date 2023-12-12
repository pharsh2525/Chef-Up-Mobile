package com.example.chef_up.models;

import com.example.chef_up.models.MealDBResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TheMealDBService {
    @GET("api/json/v1/1/random.php")
    Call<MealDBResponse> getRandomMeal();
}
