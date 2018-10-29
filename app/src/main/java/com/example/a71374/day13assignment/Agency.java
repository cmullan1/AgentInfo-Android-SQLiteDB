/*
    CMPP264 Day 13 Assignment
    Author:  Corinne Mullan
    Date:    September 22, 2018

    Agency.java defines the Agency class which stores information for each agencies in the
    TravelExperts database.
 */
package com.example.a71374.day13assignment;

public class Agency {

    // Only the agency ID, address, and city are used in this application
    private int agencyId;
    private String agncyAddress;
    private String agncyCity;

    // Constructor
    public Agency(int agencyId, String agncyAddress, String agncyCity) {
        this.agencyId = agencyId;
        this.agncyAddress = agncyAddress;
        this.agncyCity = agncyCity;
    }

    // Getters and Setters
    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getAgncyAddress() {
        return agncyAddress;
    }

    public void setAgncyAddress(String agncyAddress) {
        this.agncyAddress = agncyAddress;
    }

    public String getAgncyCity() {
        return agncyCity;
    }

    public void setAgncyCity(String agncyCity) {
        this.agncyCity = agncyCity;
    }

    // The toString() method returns a string with the agency ID, agency address,
    // and agency City in readable format.
    @Override
    public String toString() {
        return "ID:  " + agencyId + ", Address:  " + agncyAddress + ", " + agncyCity;
    }
}
