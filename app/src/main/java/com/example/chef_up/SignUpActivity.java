package com.example.chef_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signUpButton = findViewById(R.id.btnSignUp);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signUp()){
                    Intent signUpIntent = new Intent(SignUpActivity.this, MainActivity.class);
                    signUpIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(signUpIntent);
                    finish();
                }
            }
        });
    }

    private boolean signUp() {
        TextInputEditText firstNameInput = findViewById(R.id.inFirstName);
        TextInputEditText lastNameInput = findViewById(R.id.inLastName);
        TextInputEditText emailInput = findViewById(R.id.inEmail);
        TextInputEditText passwordInput = findViewById(R.id.inPassword);
        boolean isValid = true;

        String firstName = firstNameInput.getText().toString().trim();
        String lastName = lastNameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

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
}