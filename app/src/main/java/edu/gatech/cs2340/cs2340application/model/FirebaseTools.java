package edu.gatech.cs2340.cs2340application.model;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

import edu.gatech.cs2340.cs2340application.controllers.LoginActivity;
import edu.gatech.cs2340.cs2340application.controllers.RegistrationScreen;
import edu.gatech.cs2340.cs2340application.model.*;

/**
 * Suite of database tools implemented using Firebase.
 *
 * Created by JGaul on 3/13/18.
 */

public class FirebaseTools implements DatabaseTools {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private Model model;

    public FirebaseTools()
    {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        model = Model.getInstance();
    }

    /**
     * Given an email and password, puts a new user in the database
     *
     * @param email user's email
     * @param password user's password
     * @return whether the user was successfully created.
     */
    public boolean createUserEmail(String email, String password, Role role)
    {
        try
        {
            Task t = mAuth.createUserWithEmailAndPassword(email, password);
            while(!t.isComplete());
            Log.d("FirebaseRegistration", "Registration Complete");
            if (t.isSuccessful())
            {
                FirebaseUser curUser = mAuth.getCurrentUser();
                String key = curUser.getEmail().replace(".","").toLowerCase();
                mDatabase.child("users").child(key).setValue(new User(email, password, role));
                return true;
            }
            else
            {
                Log.e("FirebaseRegistration", "Failure: " + t.getException());
                return false;
            }
        }
        catch (Exception e)
        {
            Log.e("FirebaseRegistration", "User Creation Exception: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifies a given email and password combo
     *
     * @param email user's email
     * @param password user's password
     * @return whether the data matches
     */
    public boolean loginUserEmail(String email, String password)
    {
        try
        {
            Task t = mAuth.signInWithEmailAndPassword(email, password);
            while(!t.isComplete());
            Log.d("FirebaseLogin", "Login Complete");
            loadShelters();
            return t.isSuccessful();
        }
        catch (Exception e)
        {
            Log.e("FirebaseLogin", "Login Exception: " + e.getMessage());
            return false;
        }
    }

    /**
     * Adds shelter to database. Should be triggered on every add action
     *
     * @param shelter the shelter to be added
     */
    public void addShelterDatabase(Shelter shelter)
    {
        mDatabase.child("shelters").child("" + shelter.getKey()).setValue(shelter);
    }

    /**
     * Populates the shelter list on login.
     */
    public void loadShelters()
    {
        DatabaseReference shelterRef = mDatabase.child("shelters");
        Log.e("database", "Made it");

        shelterRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Shelter toAdd;
                Log.e("database", "Made it again");
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Log.e("LoadShelters", "Adding:" + ds.getValue());
                    toAdd = new Shelter (ds.child("key").getValue(Integer.class),
                            ds.child("name").getValue(String.class),
                            ds.child("capacity").getValue(String.class),
                            ds.child("occupancy").getValue(Integer.class),
                            ds.child("latitude").getValue(Double.class),
                            ds.child("longitude").getValue(Double.class),
                            ds.child("address").getValue(String.class),
                            ds.child("phoneNumber").getValue(String.class),
                            ds.child("notes").getValue(String.class));
                    Log.e("database", "Adding: " + toAdd);
                    model.addShelter(toAdd);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Loading shelters went wrong: " + databaseError.getMessage());
            }
        });
    }

    /**
     * Given a shelter, updates its info in the database.
     *
     * @param shelter the shelter to be updated
     */
    public void updateShelter(Shelter shelter)
    {
        mDatabase.child("shelters").child("" + shelter.getKey()).setValue(shelter);
    }

    /**
     * Given a user, updates their info in the database.
     *
     * @param user the user to be updated
     */
    public void updateUser(User user)
    {
        mDatabase.child("users").child(user.getUsername().replace(".",""))
                .setValue(user);
    }
}
