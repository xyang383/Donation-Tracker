package com.example.jenson.cs2340_team24_project.UI.Controllers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import com.example.jenson.cs2340_team24_project.R;
import com.example.jenson.cs2340_team24_project.UI.Models.Location;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;
import java.util.Objects;


/**
 * Maps page
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Location location;
    private DatabaseReference databaseLocations;
    private ArrayList<Location> mLocations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mLocations = new ArrayList<>();
        databaseLocations = FirebaseDatabase.getInstance().getReference("locations");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ValueEventListener locationListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    Location l = locationSnapshot.getValue(Location.class);
                    mLocations.add(l);
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.7490, -84.3880), 10.1f));

                for (Location l : mLocations) {
                    double latitude = l.getLatitude();
                    double longitude = l.getLongtitude();
                    LatLng geo = new LatLng(latitude, longitude);
                    mMap.addMarker(new MarkerOptions().position(geo).title(l.getName()));
                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            return false;
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseLocations.addValueEventListener(locationListener);

        if (mMap != null) {
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                @Override
                public View getInfoWindow(Marker marker) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {
                    @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.info_window, null);
                    TextView tvName = v.findViewById(R.id.nameView1);
                    TextView tvTel = v.findViewById(R.id.telView1);

                    String name = marker.getTitle();
                    for (Location l : mLocations) {
                        if (name.equals(l.getName())) {
                            location = l;
                        }
                    }
                    String nameS = "Name: " + location.getName();
                    String telS = "Tel: " + location.getPhone();
                    tvName.setText(nameS);
                    tvTel.setText(telS);
                    return v;
                }
            });
        }

        // Add a marker in Sydney, Australia, and move the camera.

    }


}
