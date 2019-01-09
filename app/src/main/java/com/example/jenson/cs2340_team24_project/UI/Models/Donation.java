package com.example.jenson.cs2340_team24_project.UI.Models;


/**
 * Donation class
 */
public class Donation {
    private String location;
    private String shortDescription;
    private String fullDescription;
    private double value;
    private String comments;
    private String time;
    private DonationType type = DonationType.OTHER;

    /**
     * Constructor for donation
     * @param location location
     * @param shortDescription short description
     * @param fullDescription full description
     * @param value value
     * @param comments comments
     * @param type type
     * @param time time
     */
    public Donation(String location, String shortDescription, String fullDescription,
                    Double value, String comments, DonationType type, String time) {
        this.location = location;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.value = value;
        this.comments = comments;
        this.type = type;
        this.time = time;
    }

    /**
     * No-parameter constructor
     */
    public Donation() {}

    //Setters and getters

    /**
     * Get location
     * @return location (String)
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set location
     * @param l location (String)
     */
    public void setLocation(String l) {
        this.location = l;
    }

    /**
     * Get short description
     * @return short description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Set short description
     * @param s short description
     */
    public void setShortDescription(String s) {
        this.shortDescription = s;
    }

    /**
     * Get full description
     * @return full description
     */
    public String getFullDescription() {
        return fullDescription;
    }

    /**
     * Set full description
     * @param s full description
     */
    public void setFullDescription(String s) {
        this.fullDescription = s;
    }

    /**
     * Get value
     * @return value
     */
    public double getValue() {
        return value;
    }

    /**
     * Set value
     * @param v value
     */
    public void setValue(double v) {
        this.value = v;
    }

    /**
     * Get comments
     * @return comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * Set comments
     * @param s comments
     */
    public void setComments(String s) {
        this.comments = s;
    }

    /**
     * Get donation time
     * @return donation time
     */
    public String getDonationTime() {
        return time;
    }

    /**
     * Set donation time
     * @param t donation time
     */
    public void setDonationTime(String t) {
        this.time = t;
    }

    /**
     * Set type
     * @param type type
     */
    public void setType(DonationType type) {
        this.type = type;
    }

    /**
     * Get type
     * @return type
     */
    public DonationType getType() {
        return type;
    }
}
