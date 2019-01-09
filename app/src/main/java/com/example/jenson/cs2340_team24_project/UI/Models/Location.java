package com.example.jenson.cs2340_team24_project.UI.Models;

import java.util.ArrayList;

/**
 * Location class
 */
public class Location {
    private String name;
    private String type;
    private double longtitude;
    private double latitude;
    private String address;
    private String phone;
    private String website;
    private final ArrayList<Donation> donations = new ArrayList<>();

    /**
     * Constructor
     * @param name name
     * @param type type
     * @param lontitude longitude
     * @param latitude latitude
     * @param address address
     * @param phone phone
     * @param website website
     */
    public Location(String name, String type, double lontitude,
                    double latitude, String address, String phone, String website) {
        this.name = name;
        this.type = type;
        this.longtitude = lontitude;
        this.latitude = latitude;
        this.address = address;
        this.phone = phone;
        this.website = website;
    }

    /**
     * No-Arg constructor
     */
    public Location() {

    }

    /**
     * Get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Get type
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Get longtitude
     * @return longtitude
     */
    public double getLongtitude() {
        return longtitude;
    }

    /**
     * Get latitude
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Get address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Get phone
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Get website
     * @return website
     */
    public String getWebsite() { return website; }

    /**
     * Get donations
     * @return donations
     */
    public ArrayList<Donation> getDonations() {
        return donations;
    }

    /**
     * Set name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set type
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set longtitude
     * @param longtitude longtitude
     */
    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    /**
     * Set latitude
     * @param latitude latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Set address
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Set phone
     * @param phone phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Set website
     * @param website website
     */
    public void setWebsite(String website) { this.website = website; }

    /**
     * Add donation
     * @param donation donation
     */
    public void addDonation(Donation donation) {
        donations.add(donation);
    }
}
