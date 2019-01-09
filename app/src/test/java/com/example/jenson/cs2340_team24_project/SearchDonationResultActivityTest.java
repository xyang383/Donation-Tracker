package com.example.jenson.cs2340_team24_project;

import com.example.jenson.cs2340_team24_project.UI.Controllers.SearchDonationResultActivity;
import org.junit.Test;
import java.util.ArrayList;


import static org.junit.Assert.*;

public class SearchDonationResultActivityTest {
    private final ArrayList<String> donations = new ArrayList<>();
    private final ArrayList<String> result = new ArrayList<>();


    @Test
    public void fuzzySearch() {
        String name = "afd";
        SearchDonationResultActivity s = new SearchDonationResultActivity();
        result.add("AFDtest333");
        result.add("AFDtest111");
        result.add("afdtest3");
        result.add("afdtest4");
        result.add("afdtest5");

        donations.add("AFDtest333");
        donations.add("AFDtest111");
        donations.add("afdtest3");
        donations.add("afdtest4");
        donations.add("afdtest5");
        donations.add("sample1");
        donations.add("sample2");
        donations.add("sample3");
        donations.add("sample4");

        s.fuzzySearch(name, donations);

        for (int i = 0; i < 5; i++) {
            assertEquals(result.get(i), donations.get(i));
        }
    }
}