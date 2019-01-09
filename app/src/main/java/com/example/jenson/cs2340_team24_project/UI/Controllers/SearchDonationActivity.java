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
import com.example.jenson.cs2340_team24_project.UI.Models.DonationType;

import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("ALL")
/**
 * The activity where you can search a specific donation.
 */
public class SearchDonationActivity extends AppCompatActivity {
    private String name;
    private String location;
    private DonationType category;
    private EditText eName;
    private Spinner sCategory;
    private Spinner sLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_donation);
        eName = findViewById(R.id.searchDonationNameEdit);
        sCategory = findViewById(R.id.searchDonationCategorySpinner);
        sLocation = findViewById(R.id.searchDonationLocationSpinner);

        //noinspection unchecked
        ArrayAdapter<String> adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Arrays.asList(DonationType.values()));
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sCategory.setAdapter(adapter1);

        ArrayList<String> locations = new ArrayList<>(Database.getLegalLocations());
        locations.add(0, "ALL LOCATIONS");
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, locations);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sLocation.setAdapter(adapter);

        Button search = findViewById(R.id.searchDonationSearchButton);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = eName.getText().toString();
                location = sLocation.getSelectedItem().toString();
                category = (DonationType) sCategory.getSelectedItem();
                Intent i = new Intent(SearchDonationActivity.this, SearchDonationResultActivity.class);
                i.putExtra("name", name);
                i.putExtra("location", location);
                i.putExtra("category", category);
                startActivity(i);
            }
        });

        Button cancel = findViewById(R.id.searchDonationCancelButton);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchDonationActivity.this, ApplicationActivity.class);
                startActivity(i);
            }
        });
    }
}
