package edu.gatech.cs2340.cs2340application;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.gatech.cs2340.cs2340application.controllers.AppScreen;

public class RegistrationScreen extends AppCompatActivity {

    EditText pass = findViewById(R.id.input_password);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        final Button button = findViewById(R.id.btn_signup);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String password = pass.getText().toString();
                if (password.isEmpty() || password.length() < 6) {  pass.setError("Password cannot be less than 6 characters!");
                }
                else {
                    pass.setError(null);
                    startActivity(new Intent(RegistrationScreen.this, AppScreen.class));
                }
            }
        });
    }
}
