/*
    CMPP264 Day 13 Assignment
    Author:  Corinne Mullan
    Date:    September 22, 2018

    AgentApp.java defines the AgentApp class.
    When the application is created, a new AgentDataSource object belonging to the app is created.
 */
package com.example.a71374.day13assignment;

import android.app.Application;

public class AgentApp extends Application {

    AgentDataSource source;

    // Constructor
    @Override
    public void onCreate() {
        super.onCreate();
        source = new AgentDataSource(this);
    }

    // Getter
    public AgentDataSource getSource() {
        return source;
    }
}
