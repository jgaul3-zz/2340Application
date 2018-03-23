package edu.gatech.cs2340.cs2340application.controllers;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SearchSheltersActivity extends AppCompatActivity {

    // UI references.
    private EditText mShelterNameView;
    private Spinner mAgeRangeView;
    private Spinner mGenderView;

    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_shelters);

        // Set up the search form.
        mShelterNameView = findViewById(R.id.shelterName);
        mAgeRangeView = findViewById(R.id.age_spinner);
        mGenderView = findViewById(R.id.gender_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Gender.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenderView.setAdapter(adapter);

        ArrayAdapter<String> ageAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, AgeRange.values());
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mAgeRangeView.setAdapter(ageAdapter);

        Button mResultsButton = findViewById(R.id.results_button);
        mResultsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResults();
                Intent toApp = new Intent(SearchSheltersActivity.this, Results.class);
                SearchSheltersActivity.this.startActivity(toApp);

            }
        });

        //mLoginFormView = findViewById(R.id.login_form);
    }

    /**
     * Attempts to apply the search criteria.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void openResults() {

        final Model model = Model.getInstance();

        // Store values at the time of the login attempt.
        String shelterName = mShelterNameView.getText().toString();
        AgeRange shelterAgeRange = (AgeRange) mAgeRangeView.getSelectedItem();
        Gender shelterGender = (Gender) mGenderView.getSelectedItem();

        Log.e(shelterName, "did it");

        model.clearSearchShelters();
        for (Shelter shelter: model.getShelters()) {

            //Log.e(shelter.getName(), "top of loop");
            if ((shelterName.equals("")) || (shelter.getName().toLowerCase().contains(shelterName.toLowerCase()))) {

                for (String restriction: shelter.getRestrictions()) {
                    if (shelterAgeRange.equals(AgeRange.NA) || restriction.toLowerCase().contains(shelterAgeRange.toString().toLowerCase())) {
                        if (shelterGender.equals(Gender.NA) || shelter.getRestrictions().contains(shelterGender.toString()) || !(shelter.getRestrictions().contains(shelterGender.opposite().toString()))) {
                            model.addSearchShelter(shelter);
                        }
                    }
                }
            }
        }

//        if (model.verifyUser(username, password))
//        {
//            Intent toApp = new Intent(SearchSheltersActivity.this, AppScreen.class); //Results.class);
//            SearchSheltersActivity.this.startActivity(toApp);
//        }
//        else
//        {
//            Toast.makeText(SearchSheltersActivity.this, "Incorrect Info", Toast.LENGTH_SHORT).show();
//        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    @Override
    public void onBackPressed() {
        Intent toOpen = new Intent(SearchSheltersActivity.this, AppScreen.class);
        SearchSheltersActivity.this.startActivity(toOpen);
    }
}