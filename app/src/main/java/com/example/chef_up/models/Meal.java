package com.example.chef_up.models;

public class Meal {
    private String strMeal;
    private String strInstructions;
    private String strMealThumb;

    // Constructors
    public Meal() {}

    public Meal(String strMeal, String strInstructions, String strMealThumb) {
        this.strMeal = strMeal;
        this.strInstructions = strInstructions;
        this.strMealThumb = strMealThumb;
    }

    // Getters and setters
    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }
}
