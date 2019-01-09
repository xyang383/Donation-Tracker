package com.example.jenson.cs2340_team24_project.UI.Controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.jenson.cs2340_team24_project.R;

import java.util.ArrayList;
import java.util.Objects;

import com.example.jenson.cs2340_team24_project.UI.Models.Location;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The activity where you view all the store locations
 */
public class ViewLocationActivity extends AppCompatActivity {

    private static final String TAG = "ViewLocationActivity";

    private ArrayList<String> mLocationNames;

    private DatabaseReference databaseLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);

        mLocationNames = new ArrayList<>();

        databaseLocations = FirebaseDatabase.getInstance().getReference("locations");

        Button back = findViewById(R.id.viewLocationBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewLocationActivity.this, ApplicationActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ValueEventListener locationListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mLocationNames.clear();
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    Location l = locationSnapshot.getValue(Location.class);
                    mLocationNames.add(Objects.requireNonNull(l).getName());
                }
                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseLocations.addValueEventListener(locationListener);
    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mLocationNames);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}