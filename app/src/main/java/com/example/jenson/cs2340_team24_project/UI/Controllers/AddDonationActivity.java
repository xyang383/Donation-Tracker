package com.example.jenson.cs2340_team24_project.UI.Controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.jenson.cs2340_team24_project.R;
import com.example.jenson.cs2340_team24_project.UI.Models.Database;
import com.example.jenson.cs2340_team24_project.UI.Models.Donation;
import com.example.jenson.cs2340_team24_project.UI.Models.DonationType;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Arrays;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

/**
 * Add a donation to the database
 */
@SuppressWarnings("ALL")
public class AddDonationActivity extends AppCompatActivity {
    private EditText mShortDescription;
    private EditText mFullDescription;
    private EditText mValue;
    private EditText mComment;
    private Spinner sLocation;
    private Spinner sCategory;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);
        mShortDescription = findViewById(R.id.shortDescriptionEditText);
        mFullDescription = findViewById(R.id.fullDescriptionEditText);
        mValue = findViewById(R.id.valueEditText);
        mComment = findViewById(R.id.commentEditText);
        sLocation = findViewById(R.id.locationSpinner);
        sCategory = findViewById(R.id.categorySpinner);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                Database.getLegalLocations());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sLocation.setAdapter(adapter);

        String name = getIntent().getStringExtra("location_name");

        ArrayAdapter myAdap = (ArrayAdapter) sLocation.getAdapter();
        int position = myAdap.getPosition(name);
        sLocation.setSelection(position);


        //noinspection unchecked
        ArrayAdapter<String> adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                Arrays.asList(DonationType.values()));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategory.setAdapter(adapter1);


        final Button addButton = findViewById(R.id.addButton);
        final Button cancelButton = findViewById(R.id.addDonationCancel);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loc = sLocation.getSelectedItem().toString();
                String sd = mShortDescription.getText().toString();
                String fd = mFullDescription.getText().toString();
                Double value = Double.parseDouble(mValue.getText().toString());
                String com = mComment.getText().toString();
                DonationType type = (DonationType) sCategory.getSelectedItem();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String timenow = dtf.format(now);
                Donation d = new Donation(loc, sd, fd, value, com, type, timenow);

                databaseReference.child("donations").child(sd).setValue(d);

                Intent i = new Intent(AddDonationActivity.this, ViewDonationActivity.class);
                i.putExtra("location_name", loc);
                startActivity(i);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loc = sLocation.getSelectedItem().toString();
                Intent i = new Intent(AddDonationActivity.this, ViewDonationActivity.class);
                i.putExtra("location_name", loc);
                startActivity(i);
            }
        });
    }
}
