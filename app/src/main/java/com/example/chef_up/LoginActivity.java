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
 * Activity for handling user login in the Chef Up application.
 * This class provides UI and logic for users to log in with their email and password.
 * It also provides a link to the SignUpActivity for users who need to create an account.
 */
public class LoginActivity extends AppCompatActivity {

    private String email;
    private String password;
    private TextInputEditText emailInput;
    private  TextInputEditText passwordInput;

    /**
     * Called when the activity is starting.
     * It sets up the user interface for the login screen from the layout resource.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this contains the data it most recently supplied in onSaveInstanceState(Bundle). Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize UI components and set up event listeners
        Button loginButton = findViewById(R.id.btnLogin);
        Button signUpButton = findViewById(R.id.btnSignUp);

        // Login button click event
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){
                    login(email, password);
                }
            }
        });

        // Sign-up button click event
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });
    }

    /**
     * Validates the user's email and password input.
     * Checks if the email and password fields are not empty and if the email is in a valid format.
     * @return true if the input is valid, false otherwise.
     */
    private boolean isValid() {
        emailInput = findViewById(R.id.inEmail);
        passwordInput = findViewById(R.id.inPassword);
        boolean isValid = true;

        email = emailInput.getText().toString().trim();
        password = passwordInput.getText().toString().trim();

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
        }
        else{
            passwordInput.setError(null);
        }

        return isValid;
    }

    /**
     * Attempts to log in the user with the provided email and password.
     * Initializes Firebase, then uses FirebaseAuth to sign in with email and password.
     * @param email The user's email address.
     * @param password The user's password.
     */
    private void login(String email, String  password){
        FirebaseApp.initializeApp(this);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(LoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "Invalid email or password.", Toast.LENGTH_LONG).show();
            }
        });
    }
}