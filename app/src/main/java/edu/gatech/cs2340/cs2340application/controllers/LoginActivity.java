package edu.gatech.cs2340.cs2340application.controllers;

import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.*;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private DatabaseTools mTools;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupActionBar();

        mTools = new FirebaseTools();
        model = Model.getInstance();


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
                if ((id == EditorInfo.IME_ACTION_DONE) || (id == EditorInfo.IME_NULL)) {
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

        View mLoginFormView = findViewById(R.id.login_form);

        TextView resetButton = findViewById(R.id.resetLink);
        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTools.updateAge(mUsernameView.getText().toString(), 0);
                mTools.resetPassword(mUsernameView.getText().toString());
                Toast.makeText(LoginActivity.this, "Sending reset email", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            try
            {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            catch (NullPointerException e)
            {
                Log.e("Login",
                        "Null Pointer Exception: " + e.getMessage());
            }

        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        User attempter = model.getUserByUsername(username);

        boolean banCheck = attempter.getVeteran();
        boolean lockCheck = attempter.getAge() > 2;

        boolean loginResult = mTools.loginUserEmail(username, password);

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Field is blank", Toast.LENGTH_SHORT).show();
        }
        else if (banCheck)
        {
            Toast.makeText(LoginActivity.this, "You've been banned", Toast.LENGTH_SHORT).show();
        }
        else if (lockCheck)
        {
            Toast.makeText(LoginActivity.this, "Too many login attempts", Toast.LENGTH_SHORT).show();
        }
        else if (loginResult)
        {
            attempter.setAge(0);
            mTools.updateAge(username, 0);
            Intent toApp = new Intent(LoginActivity.this, AppScreen.class);
            LoginActivity.this.startActivity(toApp);
        }
        else
        {
            attempter.incrementAge();
            mTools.updateAge(username, attempter.getAge());
            Toast.makeText(LoginActivity.this, "Incorrect info", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent toOpen = new Intent(LoginActivity.this, OpeningScreen.class);
        LoginActivity.this.startActivity(toOpen);
    }

}

