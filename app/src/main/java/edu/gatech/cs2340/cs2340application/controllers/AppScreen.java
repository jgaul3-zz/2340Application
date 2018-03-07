package edu.gatech.cs2340.cs2340application.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import edu.gatech.cs2340.cs2340application.R;
import edu.gatech.cs2340.cs2340application.model.*;

public class AppScreen extends AppCompatActivity {

    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Button logOut = findViewById(R.id.logout);
        Button searchUp = findViewById(R.id.search);
        ListView shelterList = findViewById(R.id.shelterList);

        shelterList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                model.getShelters()
        ));

        logOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("test", "User Button Pressed.");

                Intent toOpen = new Intent(AppScreen.this, OpeningScreen.class);
                AppScreen.this.startActivity(toOpen);
            }
        });

        searchUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("test", "User Button Pressed.");

                Intent toOpen = new Intent(AppScreen.this, SearchSheltersActivity.class);
                AppScreen.this.startActivity(toOpen);
            }
        });


    }

}
