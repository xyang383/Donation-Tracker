package com.example.jenson.cs2340_team24_project.UI.Controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jenson.cs2340_team24_project.R;
import com.example.jenson.cs2340_team24_project.UI.Models.Location;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Detail Location Page
 */
public class DetailLocationActivity extends AppCompatActivity {

    private Location location;
    private DatabaseReference databaseLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_location);
        databaseLocations = FirebaseDatabase.getInstance().getReference("locations");

        Button viewDonations = findViewById(R.id.button7);
        viewDonations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailLocationActivity.this, ViewDonationActivity.class);
                i.putExtra("location_name", location.getName());
                startActivity(i);
            }
        });
        Button back = findViewById(R.id.detailLocationBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailLocationActivity.this, ViewLocationActivity.class);
                i.putExtra("location_name", location.getName());
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
                String name = getIntent().getStringExtra("location_name");
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    Location l = locationSnapshot.getValue(Location.class);
                    assert l != null;
                    if (l.getName().equals(name)) {
                        location = l;
                        break;
                    }
                }
                setInfo(location);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseLocations.addValueEventListener(locationListener);
    }

    private void setInfo(Location location) {
        TextView name = findViewById(R.id.name);
        TextView type = findViewById(R.id.type);
        TextView longitude = findViewById(R.id.longitude);
        TextView latitude = findViewById(R.id.latitude);
        TextView address = findViewById(R.id.address);
        TextView phone = findViewById(R.id.phone);
        TextView website = findViewById(R.id.website);

        String nameS = "Name: " + location.getName();
        String typeS = "Type: " + location.getType();
        String longitudeS = "Longitude: " + location.getLongtitude();
        String latitudeS = "Latitude: " + location.getLatitude();
        String addressS = "Address: " + location.getAddress();
        String phoneS = "Phone: " + location.getPhone();
        String websiteS = "Website: " + location.getWebsite();

        name.setText(nameS);
        type.setText(typeS);
        longitude.setText(longitudeS);
        latitude.setText(latitudeS);
        address.setText(addressS);
        phone.setText(phoneS);
        website.setText(websiteS);
    }





}
