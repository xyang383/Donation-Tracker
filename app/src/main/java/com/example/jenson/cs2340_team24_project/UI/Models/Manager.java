package com.example.jenson.cs2340_team24_project.UI.Models;

/**
 * A class representing a Manager object
 */
class Manager extends User {

    /**
     * The constructor for a Manager object
     * @param email the manager's email
     * @param username the manager's username
     */
    public Manager(String email, String username) {
        super(email, username, Responsibility.MANAGER);
    }
}
