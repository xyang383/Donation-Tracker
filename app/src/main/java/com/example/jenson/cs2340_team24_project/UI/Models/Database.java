package com.example.jenson.cs2340_team24_project.UI.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Store legal locations
 */
public class Database {
    private static final List<String> legalLocations = new ArrayList<>();

    public Database() {
    }
    public static void addLocation(Location l) {
        legalLocations.add(l.getName());
    }
    public static List<String> getLegalLocations() {
        return legalLocations;
    }
}
