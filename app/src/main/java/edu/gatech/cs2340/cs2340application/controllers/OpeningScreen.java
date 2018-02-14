package edu.gatech.cs2340.cs2340application.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.gatech.cs2340.cs2340application.R;


/** Top level window.
 *
 * Directs the user to either the login screen or the guest screen.
 *
 */
public class OpeningScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_opening_screen);

        //Prep buttons
        Button userButton = findViewById(R.id.loginUser);
        userButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //on a phone, we need to change windows to the detail view
                Context context = v.getContext();

                //create our new intent with the new screen (activity)
                Intent intent = new Intent(context, LoginActivity.class);

                //now just display the new window
                context.startActivity(intent);
                Log.i("test", "User Button Pressed.");
            }
        });

        Button guestButton = findViewById(R.id.loginGuest);
        guestButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("test", "Guest Button Pressed.");
            }
        });






    }
}
