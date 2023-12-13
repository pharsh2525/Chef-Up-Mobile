package com.example.chef_up.models;

/**
 * Represents a user in the Chef Up application.
 * This class contains user-related information like id, first name, last name, email, and image.
 */
public class User {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String image;

    /**
     * Default constructor for creating an instance of User.
     */
    public User() {
    }

    /**
     * Constructs a new User with specified id, first name, last name, email, and image.
     *
     * @param id The unique identifier for the user.
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param email The user's email address.
     * @param image The URL or path to the user's image.
     */
    public User(String id, String firstName, String lastName, String email, String image) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.image = image;
    }

    /**
     * Gets the user's unique identifier.
     *
     * @return A string representing the user's id.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the user's unique identifier.
     *
     * @param id A string containing the user's new id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the user's first name.
     *
     * @return A string representing the user's first name.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstName A string containing the user's new first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the user's last name.
     *
     * @return A string representing the user's last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastName A string containing the user's new last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the user's email address.
     *
     * @return A string representing the user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email A string containing the user's new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the URL or path to the user's image.
     *
     * @return A string representing the URL or path to the user's image.
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets the URL or path to the user's image.
     *
     * @param image A string containing the new URL or path to the user's image.
     */
    public void setImage(String image) {
        this.image = image;
    }
}
