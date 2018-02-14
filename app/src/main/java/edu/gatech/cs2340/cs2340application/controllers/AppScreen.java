package edu.gatech.cs2340.cs2340application.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import edu.gatech.cs2340.cs2340application.R;

public class AppScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        /*

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        */

        Button logOut = findViewById(R.id.logout);

        logOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("test", "User Button Pressed.");

                Intent toOpen = new Intent(AppScreen.this, OpeningScreen.class);
                AppScreen.this.startActivity(toOpen);
            }
        });
    }

}
