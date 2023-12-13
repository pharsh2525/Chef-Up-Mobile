package com.example.chef_up.models;

/**
 * Represents a category in the Chef-Up application.
 * This class provides a structure for storing and retrieving category data,
 * including the category's unique identifier, name, and associated image.
 */
public class Category {
    // Unique identifier for the category
    private String id;

    // Name of the category
    private String name;

    // URL or path to the category's image
    private String image;

    /**
     * Default constructor for instantiating a new Category object.
     * Required for Firebase's automatic data mapping.
     */
    public Category() {
    }

    /**
     * Constructor for instantiating a new Category object with specific values.
     * @param id The unique identifier of the category.
     * @param name The name of the category.
     * @param image The URL or path to the category's image.
     */
    public Category(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    /**
     * Gets the unique identifier of the category.
     * @return A string representing the category's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the category.
     * @param id A string containing the new ID for the category.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the name of the category.
     * @return A string representing the name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     * @param name A string containing the new name for the category.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the image URL or path of the category.
     * @return A string representing the URL or path to the category's image.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the image URL or path of the category.
     * @param image A string containing the new URL or path for the category's image.
     */
    public void setImage(String image) {
        this.image = image;
    }
}

