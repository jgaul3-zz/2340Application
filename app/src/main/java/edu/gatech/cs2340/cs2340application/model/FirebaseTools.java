package edu.gatech.cs2340.cs2340application.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Semaphore;

import edu.gatech.cs2340.cs2340application.model.*;

/**
 * Suite of database tools implemented using Firebase.
 *
 * Created by JGaul on 3/13/18.
 */

public class FirebaseTools implements DatabaseTools {

    private final FirebaseAuth mAuth;
    private final DatabaseReference mDatabase;
    private final Model model;

    /**
     * Instantiates tools to be used.
     */
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
    @Override
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
                try
                {
                    String key = curUser.getEmail().replace(".","").toLowerCase();
                    mDatabase.child("users").child(key).setValue(new User(email, password, role));
                }
                catch (NullPointerException e)
                {
                    Log.e("FirebaseRegistration",
                            "Null Pointer Exception: " + e.getMessage());
                    return false;
                }

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
    @Override
    public boolean loginUserEmail(final String email, final String password)
    {
        if (email == null)
        {
            Log.e("FirebaseLogin", "Null User");
            return false;
        }

        if (password == null)
        {
            Log.e("FirebaseLogin", "Null Password");
            return false;
        }

        if (email.isEmpty())
        {
            Log.e("FirebaseLogin", "Empty User");
            return false;
        }

        if (password.isEmpty())
        {
            Log.e("FirebaseLogin", "Empty Password");
            return false;
        }

        try
        {
            Task t = mAuth.signInWithEmailAndPassword(email, password);
            while(!t.isComplete());
            Log.d("FirebaseLogin", "Login Complete");
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
    @Override
    public void addShelterDatabase(Shelter shelter)
    {
        mDatabase.child("shelters").child("" + shelter.getKey()).setValue(shelter);
    }

    /**
     * Populates the user list on opening.
     */
    @Override
    public void loadUsers()
    {
        DatabaseReference shelterRef = mDatabase.child("users");

        shelterRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User toAdd;
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    try
                    {
                        toAdd = new User (ds.child("username").getValue(String.class),
                                ds.child("password").getValue(String.class),
                                ds.child("name").getValue(String.class),
                                ds.child("role").getValue(Role.class),
                                ds.child("gender").getValue(Gender.class),
                                ds.child("age").getValue(Integer.class),
                                ds.child("veteran").getValue(Boolean.class),
                                ds.child("id").getValue(Integer.class));
                    }
                    catch (NullPointerException e)
                    {
                        Log.e("FirebaseRegistration",
                                "Null Pointer Exception: " + e.getMessage());
                        return;
                    }
                    model.addUser(toAdd);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Firebase", "Loading users went wrong: " + databaseError.getMessage());
            }
        });
    }

    /**
     * Populates the shelter list on login.
     */
    @Override
    public void loadShelters()
    {
        DatabaseReference shelterRef = mDatabase.child("shelters");

        shelterRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Shelter toAdd;
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    try
                    {
                        toAdd = new Shelter (ds.child("key").getValue(Integer.class),
                                ds.child("name").getValue(String.class),
                                ds.child("capacity").getValue(Integer.class),
                                ds.child("occupancy").getValue(Integer.class),
                                ds.child("latitude").getValue(Double.class),
                                ds.child("longitude").getValue(Double.class),
                                ds.child("address").getValue(String.class),
                                ds.child("phoneNumber").getValue(String.class),
                                ds.child("notes").getValue(String.class));
                    }
                    catch (NullPointerException e)
                    {
                        Log.e("FirebaseRegistration",
                                "Null Pointer Exception: " + e.getMessage());
                        return;
                    }
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
    @Override
    public void updateShelter(Shelter shelter)
    {
        mDatabase.child("shelters").child("" + shelter.getKey()).setValue(shelter);
    }

    /**
     * Given a user, updates their info in the database.
     *
     * @param user the user to be updated
     */
    @Override
    public void updateUser(User user)
    {
        mDatabase.child("users").child(user.getUsername().replace(".",""))
                .setValue(user);
    }

    /**
     *  Sends password reset to selected email.
     *
     * @param email email to be reset.
     */
    public void resetPassword(String email)
    {
        mAuth.sendPasswordResetEmail(email);
    }

    /**
     * updates user's age tag
     * @param email user's email
     * @param age new age
     */
    public void updateAge(String email, int age)
    {
        mDatabase.child("users").child(email.replace(".",""))
                .child("age").setValue(age);
    }
}
