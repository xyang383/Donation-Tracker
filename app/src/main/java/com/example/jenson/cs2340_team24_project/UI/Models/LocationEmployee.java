package com.example.jenson.cs2340_team24_project.UI.Models;

/**
 * A class representing a location employee object
 */
class LocationEmployee extends User {
    /**
     * The constructor for a location employee
     * @param email the employee's email
     * @param username the employee's username
     */
    public LocationEmployee(String email, String username) {
        super(email, username, Responsibility.LOCATION_EMPLOYEE);
    }
}
