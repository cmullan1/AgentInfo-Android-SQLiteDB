/*
    CMPP264 Day 13 Assignment
    Author:  Corinne Mullan
    Date:    September 22, 2018

    AgentDataSource.java defines the AgentDataSource class which contains methods for
    retrieving and updating agent data from the TravelExperts database.
 */

package com.example.a71374.day13assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AgentDataSource {

    // Define SQLiteDatabase and DBHelper objects
    SQLiteDatabase db;
    DBHelper helper;

    // Constructor
    // Create a new DBHelper object and a reference to the database.  If the database does not
    // already exist in the device's /data/data/<package name>/databases/ directory, it will
    // be copied from the project's assets folder.
    public AgentDataSource(Context context) {
        helper = new DBHelper(context);
        helper.copyDatabase();
        db = helper.getWritableDatabase();
    }

    // The getAllAgents() method retrieves a list of all agents from the Agents table
    public ArrayList<Agent> getAllAgents()
    {
        ArrayList<Agent> list = new ArrayList<Agent>();
        String sql = "SELECT AgentId, AgtFirstName, AgtMiddleInitial, AgtLastName, " +
                     "AgtBusPhone, AgtEmail, AgtPosition, AgencyId " +
                     "FROM Agents " +
                     "ORDER BY AgentId";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext())
        {
            list.add(new Agent(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getInt(7)));
        }
        cursor.close();
        return list;
    }

    // The getAgent() method retrieves the agent from the Agents table with the given agent ID
    public Agent getAgent(int agentId)
    {
        Agent selAgent = null;
        String sql = "SELECT AgentId, AgtFirstName, AgtMiddleInitial, AgtLastName, " +
                     "AgtBusPhone, AgtEmail, AgtPosition, AgencyId " +
                     "FROM agents " +
                     "WHERE AgentId=? " +
                     "ORDER BY AgentId";
        Cursor cursor = db.rawQuery(sql, new String [] {Integer.toString(agentId)});

        if(cursor.getCount() > 0) {
            cursor.moveToFirst();
            selAgent = new Agent(cursor.getInt(0),
                                 cursor.getString(1),
                                 cursor.getString(2),
                                 cursor.getString(3),
                                 cursor.getString(4),
                                 cursor.getString(5),
                                 cursor.getString(6),
                                 cursor.getInt(7));
        }

        cursor.close();
        return selAgent;
    }

    // The updateAgent() method updates the information in the Agents table using the Agent
    // object passed to it.
    // Last-in concurrency is assumed.
      public int updateAgent(Agent updatedAgent)
    {
        ContentValues values = new ContentValues();
        values.put("AgtFirstName", updatedAgent.getAgtFirstName().toString());
        values.put("AgtMiddleInitial", updatedAgent.getAgtMiddleInitial().toString());
        values.put("AgtLastName", updatedAgent.getAgtLastName().toString());
        values.put("AgtBusPhone", updatedAgent.getAgtBusPhone().toString());
        values.put("AgtEmail", updatedAgent.getAgtEmail().toString());
        values.put("AgtPosition", updatedAgent.getAgtPosition().toString());
        values.put("AgencyId", updatedAgent.getAgencyId());

        String where = "AgentId=?";
        String [] whereArgs = { updatedAgent.getAgentId() + "" };   // convert the agentId to a string
                                                                    // by concatenating with ""
        int numRowsUpdated = db.update("Agents", values, where, whereArgs);

        return numRowsUpdated;
    }

    // The getAllAgencies() method retrieves a list of travel agencies from the Agencies table.
    // This is used to provide a list of possible agency IDs in the spinner on the agent details
    // display activity.
    public ArrayList<Agency> getAllAgencies()
    {
        ArrayList<Agency> list = new ArrayList<Agency>();

        // Only the agency ID, address, and city are required in this application
        String sql = "SELECT AgencyId, AgncyAddress, AgncyCity " +
                "FROM agencies " +
                "ORDER BY AgencyId";
        Cursor cursor = db.rawQuery(sql, null);

        while (cursor.moveToNext())
        {
            list.add(new Agency(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)));
        }
        cursor.close();
        return list;
    }
}
