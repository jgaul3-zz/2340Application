package edu.gatech.cs2340.cs2340application.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.*;

/** Top level window.
 *
 * Directs the user to either the login screen or the guest screen.
 *
 */
public class OpeningScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);

        //Prep buttons
        Button userButton = findViewById(R.id.loginUser);
        Button registerButton = findViewById(R.id.loginRegister);
        Button guestButton = findViewById(R.id.loginGuest);


        userButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(OpeningScreen.this, LoginActivity.class);
                OpeningScreen.this.startActivity(toLogin);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(OpeningScreen.this, RegistrationScreen.class);
                OpeningScreen.this.startActivity(toLogin);
            }
        });

        guestButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent toApp = new Intent(OpeningScreen.this, AppScreen.class);
                OpeningScreen.this.startActivity(toApp);
            }
        });
    }

    @Override
    public void onBackPressed() {
        //Must exit through menu.
    }

}