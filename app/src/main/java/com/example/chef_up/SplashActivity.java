package com.example.chef_up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

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
                    startActivity(new Intent(SplashActivity.this, MainActivity2.class));
                }else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }

                finish();
            }
        },3000);


    }
}