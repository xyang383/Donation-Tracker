package com.example.jenson.cs2340_team24_project.UI.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jenson.cs2340_team24_project.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Home page
 */

public class ApplicationActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            Intent i = new Intent(ApplicationActivity.this, LoginActivity.class);
            Toast.makeText(ApplicationActivity.this, "No user is logged in.", Toast.LENGTH_LONG).show();
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();


        Button btnLogout;
        btnLogout = findViewById(R.id.button3);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                Intent i = new Intent(ApplicationActivity.this, HomeActivity.class);
                startActivity(i);
                Toast.makeText(ApplicationActivity.this, "You are logged out.", Toast.LENGTH_LONG).show();
            }
        });

        Button viewLocationButton = findViewById(R.id.button4);
        viewLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ApplicationActivity.this, ViewLocationActivity.class);
                startActivity(i);
            }
        });

        Button searchDonation = findViewById(R.id.applicationSearchDonation);
        searchDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ApplicationActivity.this, SearchDonationActivity.class);
                startActivity(i);
            }
        });

        Button btnMap = findViewById(R.id.mapButton);
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ApplicationActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
    }
}
