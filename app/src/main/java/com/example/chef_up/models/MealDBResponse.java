package com.example.chef_up.models;

import java.util.List;

public class MealDBResponse {
    private List<Meal> meals;

    // This method returns the first meal in the list
    public Meal getFirstMeal() {
        if (meals != null && !meals.isEmpty()) {
            return meals.get(0);
        }
        return null;
    }

    // Getters and setters
    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
