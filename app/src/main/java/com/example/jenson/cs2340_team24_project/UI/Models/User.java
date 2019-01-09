package com.example.jenson.cs2340_team24_project.UI.Models;

import java.util.Arrays;
import java.util.List;

/**
 * A User object.
 */
public class User {
    public static final List<Responsibility> legalResponsibilities = Arrays.asList(Responsibility.values());

    private String _email;
    private String _username;
    private Responsibility _res;
    protected boolean acctState;

    /**
     * The constructor for a User object
     * @param email the user's email
     * @param username the user's username
     * @param r the user's responsibility
     */
    public User(String email, String username, Responsibility r) {
        _email = email;
        _username = username;
        _res = r;
        acctState = true;
    }

    /**
     * The constructor (full) for a User object
     * @param email the user's email
     * @param username the user's username
     * @param r the user's responsibility
     * @param b the user's account state
     */
    public User(String email, String username, Responsibility r, boolean b) {
        _email = email;
        _username = username;
        _res = r;
        acctState = b;
    }

    //Responsibility setter and getter

    /**
     * The setter for user's responsibility
     * @param r the new responsibility
     */
    public void setResponsibility(Responsibility r) {
        this._res = r;
    }

    /**
     * The getter for user's responsibility
     * @return the user's responsibility
     */
    public Responsibility getResponsibility() {
        return this._res;
    }

    //Username setter and getter

    /**
     * The setter for user's username
     * @param s the new username
     */
    public void setUsername(String s) {
        this._username = s;
    }

    /**
     * The getter for user's username
     * @return the user's username
     */
    public String getUsername() {
        return _username;
    }

    //Email getter and setter

    /**
     * The setter for user's email
     * @param s the new email
     */
    public void setEmail(String s) {
        this._email = s;
    }

    /**
     * The getter for user's email
     * @return the user's email
     */
    public String getEmail() {
        return _email;
    }

    //Account state setter and getter

    /**
     * The setter for user's account state
     * @param b the new account state
     */
    public void setAcctState(boolean b) {
        this.acctState = b;
    }

    /**
     * The getter for user's account state
     * @return the user's account state
     */
    public boolean getAcctState() {
        return acctState;
    }
}
