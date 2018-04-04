package edu.gatech.cs2340.cs2340application.controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.*;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupActionBar();

        TextView regButton = findViewById(R.id.regLink);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toReg = new Intent(LoginActivity.this, RegistrationScreen.class);
                LoginActivity.this.startActivity(toReg);
            }
        });

        // Set up the login form.
        mUsernameView = findViewById(R.id.username);
        mPasswordView = findViewById(R.id.password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mUsernameSignInButton = findViewById(R.id.email_sign_in_button);
        mUsernameSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        final Model model = Model.getInstance();
        DatabaseTools tools = new FirebaseTools();

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Field is blank", Toast.LENGTH_SHORT).show();
        }
        else if (tools.loginUserEmail(username, password))
        {
            if (model.getShelters().isEmpty())
            {
                Log.e("Login", "DATABSE NOT LOADED CORRECTLY");
                readSDFile();
            }
            Intent toApp = new Intent(LoginActivity.this, AppScreen.class);
            LoginActivity.this.startActivity(toApp);
        }
        else
        {
            Toast.makeText(LoginActivity.this, "Incorrect Info", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent toOpen = new Intent(LoginActivity.this, OpeningScreen.class);
        LoginActivity.this.startActivity(toOpen);
    }


    /**
     * Open the sample.csv file in the /res/raw directory
     * Line Entry format:
     *   [0] - name
     *   [1] - id as a string
     *
     */
    private void readSDFile() {

        try {
            DatabaseTools tools = new FirebaseTools();
            Model model = Model.getInstance();

            // Opens a file using a Scanner object
            Scanner scanner = new Scanner(getResources().openRawResource(R.raw.homeless_shelter_database));

            //Skips over the first line
            scanner.nextLine();


            while (scanner.hasNext()) {
                List<String> line = parseLine(scanner.nextLine());

                int key = Integer.parseInt(line.get(0));
                Log.e("id", line.get(0));

                String name = line.get(1);
                Log.e("name", line.get(1));

                int capacity = Integer.parseInt(line.get(2));
                Log.e("capacity", line.get(2));

                //restrictions is 3 (string)
                double longitude = Double.parseDouble(line.get(4));
                Log.e("longitude", line.get(4));

                double latitude = Double.parseDouble(line.get(5));
                Log.e("latitude", line.get(5));

                String address = line.get(6);
                Log.e("address", line.get(6));

                String phoneNumber = line.get(8);
                Log.e("phoneNumber", line.get(8));

                String notes = line.get(7);
                Log.e("notes", line.get(7));

                Shelter rick = new Shelter(key, name, capacity, latitude, longitude, address, phoneNumber, notes);

                rick.setRestrictions(parseLine(line.get(3), '/', 'w'));
                model.addShelter(rick);
                tools.addShelterDatabase(rick);
            }
            scanner.close();
        } catch (Exception e) {
            Log.e("dopnt", "error reading assets");
        }

    }

    public static List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, ',', '"');
    }

    public static List<String> parseLine(String cvsLine, char separators, char customQuote) {

        List<String> result = new ArrayList<>();

        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
            return result;
        }

        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;

        char[] chars = cvsLine.toCharArray();

        for (char ch : chars) {

            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {

                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }

                }
            } else {
                if (ch == customQuote) {

                    inQuotes = true;

                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }

                } else if (ch == separators) {

                    result.add(curVal.toString());

                    curVal = new StringBuffer();
                    startCollectChar = false;

                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }

        }

        result.add(curVal.toString());

        return result;
    }
}

