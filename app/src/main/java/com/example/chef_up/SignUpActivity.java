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

public class SignUpActivity extends AppCompatActivity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Button signUpButton = findViewById(R.id.btnSignUp);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(signUp()){
                    createNewUser(null,null,email,password);
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