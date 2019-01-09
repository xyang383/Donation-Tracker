package com.example.jenson.cs2340_team24_project.UI.Controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.example.jenson.cs2340_team24_project.R;
import com.example.jenson.cs2340_team24_project.UI.Models.Donation;
import com.example.jenson.cs2340_team24_project.UI.Models.DonationType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

/**
 * The activity where you view the result of a donation search.
 */
public class SearchDonationResultActivity extends AppCompatActivity {
    private String name;
    private String location;
    private DonationType category;
    private final ArrayList<String> donations = new ArrayList<>();

    private DatabaseReference databaseLocations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_donation_result);
        databaseLocations = FirebaseDatabase.getInstance().getReference("donations");

        Button back = findViewById(R.id.resultSearchDonationBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SearchDonationResultActivity.this, SearchDonationActivity.class);
                startActivity(i);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name = (String) extras.get("name");
            location = (String) extras.get("location");
            category = (DonationType) extras.get("category");
        }
        ValueEventListener donationListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                donations.clear();
                for (DataSnapshot donationSnapshot : dataSnapshot.getChildren()) {
                    Donation d = donationSnapshot.getValue(Donation.class);
                    if (location.equals("ALL LOCATIONS")) {
                        if (category.equals(DonationType.NOTYPE) || Objects.requireNonNull(d).getType().equals(category)) {
                            donations.add(d.getShortDescription());
                        }
                    } else {
                        if (d.getLocation().equals(location)) {
                            if (category.equals(DonationType.NOTYPE) || d.getType().equals(category)) {
                                donations.add(d.getShortDescription());
                            }
                        }
                    }
                }
                if (!TextUtils.isEmpty(name)) {
                    fuzzySearch(name, donations);
                }
                initRecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseLocations.addValueEventListener(donationListener);
    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view2);
        SearchDonationAdapter adapter = new SearchDonationAdapter(this, donations);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void fuzzySearch(String name, ArrayList<String> donations) {
        List<ExtractedResult> list = FuzzySearch.extractSorted(name, donations);
        donations.clear();
        //Toast.makeText(SearchDonationResultActivity.this, "No match.", Toast.LENGTH_LONG).show();
        for (ExtractedResult r : list) {
            if (r.getScore() >= 60) {
                donations.add(r.getString());
            }
        }
    }
}
