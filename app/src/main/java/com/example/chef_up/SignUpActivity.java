package com.example.chef_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Activity for handling user sign-up in the Chef Up application.
 * This class provides UI and logic for users to register with their first name, last name, email, and password.
 */
public class SignUpActivity extends AppCompatActivity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    /**
     * Called when the activity is starting.
     * It sets up the user interface for the sign-up screen from the layout resource.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this contains the data it most recently supplied in onSaveInstanceState(Bundle). Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signUpButton = findViewById(R.id.btnSignUp);

        // Sign-up button click event
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signUp()){
                    createNewUser(null,null,email,password);
                }
            }
        });
    }

    /**
     * Validates the user's input for sign-up.
     * Checks if the first name, last name, email, and password fields are not empty and if the email is in a valid format.
     * @return true if the input is valid, false otherwise.
     */
    private boolean signUp() {
        TextInputEditText firstNameInput = findViewById(R.id.inFirstName);
        TextInputEditText lastNameInput = findViewById(R.id.inLastName);
        TextInputEditText emailInput = findViewById(R.id.inEmail);
        TextInputEditText passwordInput = findViewById(R.id.inPassword);
        boolean isValid = true;

        firstName = firstNameInput.getText().toString().trim();
        lastName = lastNameInput.getText().toString().trim();
        email = emailInput.getText().toString().trim();
        password = passwordInput.getText().toString().trim();

        if(firstName.isEmpty()){
            firstNameInput.setError("First name is required");
            isValid = false;
        } else{
            firstNameInput.setError(null);
        }

        if(lastName.isEmpty()){
            lastNameInput.setError("Last name is required");
            isValid = false;
        } else{
            lastNameInput.setError(null);
        }

        if(email.isEmpty()){
            emailInput.setError("Email is required");
            isValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Please enter valid email");
            isValid = false;
        } else {
            emailInput.setError(null);
        }

        if(password.isEmpty()) {
            passwordInput.setError("Password is required");
            isValid = false;
        } else if (password.length() < 8) {
            passwordInput.setError("Password needs to be at least 8 characters");
        } else{
            passwordInput.setError(null);
        }

        return isValid;
    }

    /**
     * Creates a new user account using Firebase Authentication.
     * It uses the provided first name, last name, email, and password to create a new user.
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param email The user's email address.
     * @param password The user's password.
     */
    private void createNewUser(String firstName, String lastname, String email, String password){
        FirebaseApp.initializeApp(this);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(this, "Account creation failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}