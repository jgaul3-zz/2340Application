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
import edu.gatech.cs2340.cs2340application.model.Model;
import edu.gatech.cs2340.cs2340application.model.Shelter;

public class Results extends AppCompatActivity {

    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("HEY", "did it 35");
        setContentView(R.layout.activity_app_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Button logOut = findViewById(R.id.logout);
        Button searchUp = findViewById(R.id.search);
        Button mapScreen = findViewById(R.id.mapscreen);
        ListView shelterList = findViewById(R.id.shelterList);

        shelterList.setAdapter(new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                model.getSearchShelters()
        ));
        shelterList.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long jum){
                Shelter item = (Shelter)adapter.getItemAtPosition(position);
                Log.e("test", "User Button Pressed.");

                Intent intent = new Intent(Results.this, ShelterDetailActivity.class);
//based on item add info to intent
                model.setCurrentShelterId(item.getKey());
                startActivity(intent);
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("test", "User Button Pressed.");

                Intent toOpen = new Intent(Results.this, OpeningScreen.class);
                Results.this.startActivity(toOpen);
            }
        });

        searchUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("test", "User Button Pressed.");

                Intent toOpen = new Intent(Results.this, SearchSheltersActivity.class);
                Results.this.startActivity(toOpen);
            }
        });

        mapScreen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i("test", "Map Button Pressed.");

                Intent toOpen = new Intent(Results.this, MapsActivity.class);
                Results.this.startActivity(toOpen);
            }
        });


    }

}
