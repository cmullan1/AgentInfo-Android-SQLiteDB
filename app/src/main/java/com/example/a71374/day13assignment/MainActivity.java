/*
    CMPP264 Day 13 Assignment
    Author:  Corinne Mullan
    Date:    September 22, 2018

    MainActivity.java defines the MainActivity class.
 */
package com.example.a71374.day13assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends Activity {

    // Member variables for the data source and application objects
    AgentDataSource source;
    AgentApp app;

    // Define the list view that will display the travel agent names
    ListView lvAgents;

    // Set up an ArrayList and an ArrayAdapter to bind the travel agent data to the list view
    ArrayList<Agent> arrListAgents = new ArrayList<Agent>();
    ArrayAdapter<Agent> arrAdapterAgents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtain references to the application and the data source
        app = (AgentApp) getApplication();
        source = app.getSource();

        // Obtain a reference to the list view GUI element
        lvAgents = findViewById(R.id.lvAgents);

        // Create the ArrayAdapter.  Use the source.getAllAgents() method to obtain all of the
        // agent data from the database and display it in the list view (the agent's toString()
        // method is called implicitly).
        arrAdapterAgents = new ArrayAdapter<Agent>(this,
                           android.R.layout.simple_list_item_1,
                           source.getAllAgents());
        lvAgents.setAdapter(arrAdapterAgents);

        // Set an OnItemClickListener so that, when an agent is selected from the list view,
        // the details activity is called using an intent, and the selected agent is passed to it.
        lvAgents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                Agent selectedAgent = (Agent) lvAgents.getAdapter().getItem(position);
                intent.putExtra("agent", (Serializable) selectedAgent);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        arrAdapterAgents.clear();
        arrAdapterAgents.addAll(source.getAllAgents());
    }

    @Override
    protected void onResume() {
        super.onResume();
        arrAdapterAgents.clear();
        arrAdapterAgents.addAll(source.getAllAgents());
    }
}
