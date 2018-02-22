package edu.gatech.cs2340.cs2340application.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.RegistrationScreen;


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
        Button guestButton = findViewById(R.id.loginGuest);

        userButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("test", "User Button Pressed.");

                Intent toLogin = new Intent(OpeningScreen.this, RegistrationScreen.class);
                OpeningScreen.this.startActivity(toLogin);
            }
        }); 

        guestButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("test", "Guest Button Pressed.");

                Intent toApp = new Intent(OpeningScreen.this, AppScreen.class);
                OpeningScreen.this.startActivity(toApp);
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Do Here what ever you want do on back press;
        // Nothing tbh.
    }

}
