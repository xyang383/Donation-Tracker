package com.example.jenson.cs2340_team24_project.UI.Models;

class Admin extends User{
    public Admin(String email, String username) {
        super(email, username, Responsibility.ADMIN);
    }
}
