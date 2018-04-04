package edu.gatech.cs2340.cs2340application.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
        Button mapScreen = findViewById(R.id.mapscreen);
        ListView shelterList = findViewById(R.id.shelterList);

        shelterList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                model.getShelters()
        ));
        shelterList.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long jum){
                Shelter item = (Shelter)adapter.getItemAtPosition(position);

                Intent intent = new Intent(AppScreen.this, ShelterDetailActivity.class);
//based on item add info to intent
                model.setCurrentShelterId(item.getKey());
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toOpen = new Intent(AppScreen.this, OpeningScreen.class);
                AppScreen.this.startActivity(toOpen);
            }
        });

        searchUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toOpen = new Intent(AppScreen.this, SearchSheltersActivity.class);
                AppScreen.this.startActivity(toOpen);
            }
        });

        mapScreen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toOpen = new Intent(AppScreen.this, MapsActivity.class);
                AppScreen.this.startActivity(toOpen);
            }
        });


    }

}
