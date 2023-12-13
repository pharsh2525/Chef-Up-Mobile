package com.example.chef_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Splash screen activity for the Chef Up application.
 * This activity displays a splash screen for a short duration and then checks if the user is logged in.
 * Depending on the user's authentication status, it redirects to either the MainActivity or LoginActivity.
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Called when the activity is starting.
     * This method sets up the splash screen display and initializes Firebase.
     * After a delay, it checks the user's authentication status and redirects accordingly.
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down, this contains the data it most recently supplied in onSaveInstanceState(Bundle). Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseApp.initializeApp(SplashActivity.this);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }

                finish();
            }
        },3000);
    }
}