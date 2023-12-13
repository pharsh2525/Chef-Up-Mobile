package com.example.chef_up.models;

import java.io.Serializable;

/**
 * Represents a recipe in the Chef-Up application.
 * This class is used to encapsulate the details of a recipe.
 * It implements Serializable to allow recipe objects to be passed between activities.
 */
public class Recipe implements Serializable {

    // Unique identifier for the recipe
    private String id;

    // Name of the recipe
    private String name;

    // Detailed description or instructions of the recipe
    private String description;

    // Type of cuisine the recipe belongs to
    private String cuisine;

    // Category of the recipe (e.g., Dessert, Main Course)
    private String category;

    // URL or reference to the image of the recipe
    private String image;

    // Identifier of the user who created or uploaded the recipe
    private String user;

    /**
     * Default constructor required for calls to DataSnapshot.getValue(Recipe.class)
     */
    public Recipe() {
        // Default constructor
    }

    /**
     * Constructs a new Recipe with specified details.
     *
     * @param id          The unique identifier of the recipe.
     * @param name        The name of the recipe.
     * @param description The detailed description or instructions of the recipe.
     * @param cuisine     The type of cuisine the recipe belongs to.
     * @param category    The category of the recipe.
     * @param image       The URL or reference to the image of the recipe.
     * @param user        The identifier of the user who created or uploaded the recipe.
     */
    public Recipe(String id, String name, String description, String cuisine, String category, String image, String user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cuisine = cuisine;
        this.category = category;
        this.image = image;
        this.user = user;
    }

    // Standard getter and setter methods for all fields
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
