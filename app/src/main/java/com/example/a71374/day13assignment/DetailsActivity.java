/*
    CMPP264 Day 13 Assignment
    Author:  Corinne Mullan
    Date:    September 22, 2018

    DetailsActivity.java defines the DetailsActivity class.
 */
package com.example.a71374.day13assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DetailsActivity extends Activity {

    // Member variables for the data source and application objects
    AgentDataSource source;
    AgentApp app;

    Agent selAgent;             // The currently selected agent
    List<Agency> agencies;      // A list of all agencies from the database

    // Declare the variables used to reference the elements on the GUI
    TextView tvAgentId;
    EditText etAgtFirstName, etAgtMiddleInitial, etAgtLastName;
    EditText etAgtBusPhone, etAgtEmail, etAgtPosition;
    Button btnEdit, btnSave;
    Spinner spAgencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Obtain references to the application and the data source
        app = (AgentApp) getApplication();
        source = app.getSource();

        // Obtain references to the text views, edit texts, and buttons on the GUI
        tvAgentId = findViewById(R.id.tvAgentId);
        etAgtFirstName = findViewById(R.id.etAgtFirstName);
        etAgtMiddleInitial = findViewById(R.id.etAgtMiddleInitial);
        etAgtLastName = findViewById(R.id.etAgtLastName);
        etAgtBusPhone = findViewById(R.id.etAgtBusPhone);
        etAgtEmail = findViewById(R.id.etAgtEmail);
        etAgtPosition = findViewById(R.id.etAgtPosition);
        btnEdit = findViewById(R.id.btnEdit);
        btnSave = findViewById(R.id.btnSave);
        spAgencies = findViewById(R.id.spAgencies);

        // Load up the spinner with all possible agencies from the agencies table
        loadSpinnerData();

        // Obtain the selected agent from the agent object passed from the main activity
        Intent intent = getIntent();
        selAgent = (Agent) intent.getSerializableExtra("agent");

        // Set the text of the TextView elements using the fields of the selAgent object
        tvAgentId.setText(Integer.toString(selAgent.getAgentId()));
        etAgtFirstName.setText(selAgent.getAgtFirstName());
        etAgtMiddleInitial.setText(selAgent.getAgtMiddleInitial());
        etAgtLastName.setText(selAgent.getAgtLastName());
        etAgtBusPhone.setText(selAgent.getAgtBusPhone());
        etAgtEmail.setText(selAgent.getAgtEmail());
        etAgtPosition.setText(selAgent.getAgtPosition());

        // Select the spinner item which contains the agency corresponding to the agent's
        // agencyId value.  Do this by iterating through all of the agencies in the agencies list.
        // When the agency's id matches that of the selected Agent, a match has been found.  Save
        // this index value as it will correspond the to the desired spinner item's index.
        int i=0;
        int agencyId;
        for (Agency a : agencies) {
            if(a.getAgencyId() == selAgent.getAgencyId())
                break;
            i++;
        }
        spAgencies.setSelection(i);

        // Disable all of the edit text boxes and the spinner
        etAgtFirstName.setEnabled(false);
        etAgtMiddleInitial.setEnabled(false);
        etAgtLastName.setEnabled(false);
        etAgtBusPhone.setEnabled(false);
        etAgtEmail.setEnabled(false);
        etAgtPosition.setEnabled(false);
        spAgencies.setEnabled(false);

        // Enable the Edit button and disable the Save button
        btnEdit.setEnabled(true);
        btnSave.setEnabled(false);

        // Set the focus to the Edit button, so an edit text field does not automatically
        // get focus and bring up the keyboard
        btnEdit.setFocusable(true);
        btnEdit.setFocusableInTouchMode(true);
        btnEdit.requestFocus();

        // Set an OnClickListener for the Edit button.
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // When the Edit button is clicked, enable all of the edit text fields and the
                // spinner
                etAgtFirstName.setEnabled(true);
                etAgtMiddleInitial.setEnabled(true);
                etAgtLastName.setEnabled(true);
                etAgtBusPhone.setEnabled(true);
                etAgtEmail.setEnabled(true);
                etAgtPosition.setEnabled(true);
                spAgencies.setEnabled(true);

                // Set the focus on the first edit text box
                etAgtFirstName.requestFocus();

                // Disable the Edit button and enable the Save button
                btnEdit.setEnabled(false);
                btnSave.setEnabled(true);
            }
        });

        // Set an OnClickListener for the Save button.
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create the agent object with the updated information from the GUI (the agent ID
                // will be the original agent ID from the selected agent selAgent, as the agency ID
                // is not updatable)
                Agent updatedAgent = new Agent();

                updatedAgent.setAgentId(selAgent.getAgentId());
                updatedAgent.setAgtFirstName(etAgtFirstName.getText().toString());
                updatedAgent.setAgtMiddleInitial(etAgtMiddleInitial.getText().toString());
                updatedAgent.setAgtLastName(etAgtLastName.getText().toString());
                updatedAgent.setAgtBusPhone(etAgtBusPhone.getText().toString());
                updatedAgent.setAgtEmail(etAgtEmail.getText().toString());
                updatedAgent.setAgtPosition(etAgtPosition.getText().toString());

                // Obtain the updated agency ID from the spinner
                // The position of the selection in the spinner will give the index of the agency
                // in the agencies list.  The agency ID can be obtained from this agency object.
                updatedAgent.setAgencyId((agencies.get(spAgencies.getSelectedItemPosition())).getAgencyId());

                // Call the AgentDataSource updateAgent() method to update the database
                int rowsUpdated = source.updateAgent(updatedAgent);

                // Check how many database rows were updated.  1 indicates a successful update;
                // otherwise, the update did not succeed.  Output an appropriate message.
                if (rowsUpdated == 1) {

                    // Output a success message
                    Toast toastMsg = Toast.makeText(getApplicationContext(), "Agent information successfully updated",
                                   Toast.LENGTH_LONG);
                    toastMsg.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                    toastMsg.show();

                    // Disable all of the edit text boxes and the spinner
                    etAgtFirstName.setEnabled(false);
                    etAgtMiddleInitial.setEnabled(false);
                    etAgtLastName.setEnabled(false);
                    etAgtBusPhone.setEnabled(false);
                    etAgtEmail.setEnabled(false);
                    etAgtPosition.setEnabled(false);
                    spAgencies.setEnabled(false);

                    // Enable the Edit button and disable the Save button
                    btnEdit.setEnabled(true);
                    btnSave.setEnabled(false);

                    // Set the focus to the Edit button, so an edit text field does not automatically
                    // get focus and bring up the keyboard
                    btnEdit.requestFocus();
                }
                else {
                    // Output an error message and allow the user to try again
                    Toast toastMsg = Toast.makeText(getApplicationContext(), "Database could not be updated.  Please try again.",
                            Toast.LENGTH_LONG);
                    toastMsg.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
                    toastMsg.show();
                }
            }
        });
    }

    private void loadSpinnerData() {

        agencies = source.getAllAgencies();

        ArrayAdapter<Agency> agenciesAdapter = new ArrayAdapter<Agency>(this,
                android.R.layout.simple_spinner_item, agencies);

        agenciesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        // attaching data adapter to spinner
        spAgencies.setAdapter(agenciesAdapter);
    }
}
