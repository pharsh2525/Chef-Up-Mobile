package com.example.chef_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button loginButton = findViewById(R.id.btnLogin);
        Button signUpButton = findViewById(R.id.btnSignUp);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(login()){
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private boolean login() {
        TextInputLayout emailLayout = findViewById(R.id.inEmailContainer);
        TextInputEditText emailInput = findViewById(R.id.inEmail);
        TextInputLayout passwordLayout = findViewById(R.id.inPasswordContainer);
        TextInputEditText passwordInput = findViewById(R.id.inPassword);
        boolean isValid = true;

        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();

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
}