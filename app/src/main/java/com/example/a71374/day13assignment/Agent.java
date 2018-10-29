/*
    CMPP264 Day 13 Assignment
    Author:  Corinne Mullan
    Date:    September 22, 2018

    Agent.java defines the Agent class.
 */
package com.example.a71374.day13assignment;

import java.io.Serializable;

public class Agent implements Serializable{

    // The member variables correspond to the columns in the Agents table in the TravelExperts
    // database
    private int AgentId;
    private String AgtFirstName;
    private String AgtMiddleInitial;
    private String AgtLastName;
    private String AgtBusPhone;
    private String AgtEmail;
    private String AgtPosition;
    private int AgencyId;

    // Constructors
    public Agent() {
    }

    public Agent(int agentId, String agtFirstName, String agtMiddleInitial, String agtLastName,
                 String agtBusPhone, String agtEmail, String agtPosition, int agencyId) {
        AgentId = agentId;
        AgtFirstName = agtFirstName;
        AgtMiddleInitial = agtMiddleInitial;
        AgtLastName = agtLastName;
        AgtBusPhone = agtBusPhone;
        AgtEmail = agtEmail;
        AgtPosition = agtPosition;
        AgencyId = agencyId;
    }

    // Getters and Setters
    public int getAgentId() {
        return AgentId;
    }

    public void setAgentId(int agentId) {
        AgentId = agentId;
    }

    public String getAgtFirstName() {
        return AgtFirstName;
    }

    public void setAgtFirstName(String agtFirstName) {
        AgtFirstName = agtFirstName;
    }

    public String getAgtMiddleInitial() {
        return AgtMiddleInitial;
    }

    public void setAgtMiddleInitial(String agtMiddleInitial) {
        AgtMiddleInitial = agtMiddleInitial;
    }

    public String getAgtLastName() {
        return AgtLastName;
    }

    public void setAgtLastName(String agtLastName) {
        AgtLastName = agtLastName;
    }

    public String getAgtBusPhone() {
        return AgtBusPhone;
    }

    public void setAgtBusPhone(String agtBusPhone) {
        AgtBusPhone = agtBusPhone;
    }

    public String getAgtEmail() {
        return AgtEmail;
    }

    public void setAgtEmail(String agtEmail) {
        AgtEmail = agtEmail;
    }

    public String getAgtPosition() {
        return AgtPosition;
    }

    public void setAgtPosition(String agtPosition) {
        AgtPosition = agtPosition;
    }

    public int getAgencyId() {
        return AgencyId;
    }

    public void setAgencyId(int agencyId) {
        AgencyId = agencyId;
    }

    // The toString() method returns the agent's full name in a readable format.  Only include
    // the middle initial if it is not null.
    @Override
    public String toString() {
        String agtNameString;
        if (AgtMiddleInitial != null) {
            agtNameString = AgtFirstName + " " + AgtMiddleInitial + " " + AgtLastName;
        }
        else {
            agtNameString = AgtFirstName + " " + AgtLastName;
        }
        return agtNameString;
    }
}
