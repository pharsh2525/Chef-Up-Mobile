package com.example.chef_up.models;

import java.io.Serializable;

public class Recipe implements Serializable {

    private String id;
    private String name;
    private String description;
    private String cuisine;
    private String category;
    private String image;
    private String user;

    public Recipe() {

    }

    public Recipe(String id, String name, String description, String cuisine, String category, String image, String user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cuisine = cuisine;
        this.category = category;
        this.image = image;
        this.user = user;
    }

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
