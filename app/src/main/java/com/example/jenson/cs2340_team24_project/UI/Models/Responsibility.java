package com.example.jenson.cs2340_team24_project.UI.Models;

/**
 * An enum of user's responsibility
 */
public enum Responsibility {
    USER("user"),
    LOCATION_EMPLOYEE("employee"),
    ADMIN("admin"),
    MANAGER("manager");

    final String responsibility;
    Responsibility(String r) {
        this.responsibility = r;
    }
}
