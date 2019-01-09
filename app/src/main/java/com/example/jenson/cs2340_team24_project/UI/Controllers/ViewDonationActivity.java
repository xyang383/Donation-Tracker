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
import com.example.jenson.cs2340_team24_project.UI.Models.Donation;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The activity where you view all the donations at a specific location.
 */
public class ViewDonationActivity extends AppCompatActivity {

    private final ArrayList<String> donations = new ArrayList<>();
    private DatabaseReference databaseDonations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donation);
        databaseDonations = FirebaseDatabase.getInstance().getReference("donations");

        Button addDonation = findViewById(R.id.addDonationButton);
        addDonation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewDonationActivity.this, AddDonationActivity.class);
                String name = getIntent().getStringExtra("location_name");
                i.putExtra("location_name", name);
                startActivity(i);
            }
        });

        Button back = findViewById(R.id.viewDonationBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewDonationActivity.this, DetailLocationActivity.class);
                String name = getIntent().getStringExtra("location_name");
                i.putExtra("location_name", name);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ValueEventListener donationListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donations.clear();
                String name = getIntent().getStringExtra("location_name");
                for (DataSnapshot donationSnapshot : dataSnapshot.getChildren()) {
                    Donation d = donationSnapshot.getValue(Donation.class);
                    if (Objects.requireNonNull(d).getLocation().equals(name)) {
                        donations.add(d.getShortDescription());
                    }
                }
                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseDonations.addValueEventListener(donationListener);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view1);
        DonationAdapter adapter = new DonationAdapter(this, donations);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
