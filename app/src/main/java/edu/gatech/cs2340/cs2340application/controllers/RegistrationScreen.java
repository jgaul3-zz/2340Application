package edu.gatech.cs2340.cs2340application.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import edu.gatech.cs2340.cs2340application.R;

import edu.gatech.cs2340.cs2340application.model.*;

public class RegistrationScreen extends AppCompatActivity {

    // UI references.
    private EditText mUsernameView;
    private EditText pass;
    private Spinner roleSpin;
    private FirebaseAuth mAuth;

    Model model = Model.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set up the login form.
        mUsernameView = findViewById(R.id.input_name);
        pass = findViewById(R.id.input_password);
        roleSpin = findViewById(R.id.typeSpinner);
        mAuth = FirebaseAuth.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Role.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSpin.setAdapter(adapter);

        TextView logButton = findViewById(R.id.link_login);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent toLogin = new Intent(RegistrationScreen.this, LoginActivity.class);
                RegistrationScreen.this.startActivity(toLogin);
            }
        });

        final Button button = findViewById(R.id.btn_signup);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = mUsernameView.getText().toString();
        String password = pass.getText().toString();
        Role role = (Role) roleSpin.getSelectedItem();

        boolean nameCheck = !username.isEmpty() && (model.getUserByUsername(username) == model.nullUser);
        boolean passCheck = !password.isEmpty() && password.length() >= 6;

        if (!nameCheck)
        {
            Toast.makeText(RegistrationScreen.this, "Username is taken", Toast.LENGTH_SHORT).show();
        }
        else if (!passCheck)
        {
            Toast.makeText(RegistrationScreen.this, "Password too short", Toast.LENGTH_SHORT).show();
        }
        else
        {
            DatabaseTools tools = new FirebaseTools();
            boolean success = tools.createUserEmail(username, password, role);
            if (success)
            {
                startActivity(new Intent(RegistrationScreen.this, LoginActivity.class));
            }
            else
            {
                Toast.makeText(RegistrationScreen.this, "Error creating user", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent toOpen = new Intent(RegistrationScreen.this, OpeningScreen.class);
        RegistrationScreen.this.startActivity(toOpen);
    }
}
