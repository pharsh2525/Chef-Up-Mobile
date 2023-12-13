package com.example.chef_up.models;

import java.util.List;

/**
 * Represents a response from TheMealDB API in the Chef-Up application.
 * This class is designed to encapsulate the list of meals returned from an API call.
 */
public class MealDBResponse {
    // A list to hold meal objects
    private List<Meal> meals;

    /**
     * Retrieves the first meal from the list.
     * This method is useful when the API response contains a single meal item.
     *
     * @return The first Meal object in the list if available, null otherwise.
     */
    public Meal getFirstMeal() {
        // Check if the list is not null and not empty
        if (meals != null && !meals.isEmpty()) {
            // Return the first meal in the list
            return meals.get(0);
        }
        // Return null if the list is empty or null
        return null;
    }

    // Standard getter method for retrieving the list of meals
    public List<Meal> getMeals() {
        return meals;
    }

    // Standard setter method for setting the list of meals
    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}

