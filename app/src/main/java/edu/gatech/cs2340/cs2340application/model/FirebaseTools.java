package edu.gatech.cs2340.cs2340application.model;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    public boolean loginUserEmail(String email, String password)
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
    @Override
    public void addShelterDatabase(Shelter shelter)
    {
        mDatabase.child("shelters").child("" + shelter.getKey()).setValue(shelter);
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
                Log.e("database", "Made it again");
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
     *  Checks whether user is valid using "veteran" tag.
     *
     *  @param email user's email
     */
    public boolean checkBanned(String email)
    {
        model.waiting = true;

        mDatabase.child("users").child(email.replace(".","")).child("veteran")
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                model.bannedFlag = dataSnapshot.getValue(Boolean.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                model.bannedFlag = false;
            }
        });

        return model.bannedFlag;
    }

    /**
     *  Checks whether user has been locked out using "age" tag.
     *
     *  @param email user's email
     */
    public boolean checkLockout(String email)
    {
        final String modEmail = email.replace(".", "");

        mDatabase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.child(modEmail).exists()) {
                    int count = snapshot.child(modEmail).child("age").getValue(Integer.class);
                    model.lockoutFlag = count >= 3;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        model.waiting = false;
        return model.lockoutFlag;

    }

    /**
     *  Adds one to "age" tag.
     *
     *  @param email user's email
     */
    public void addLockout(String email)
    {
        final String modEmail = email.replace(".", "");

        mDatabase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.child(modEmail).exists()) {
                    int count = snapshot.child(modEmail).child("age").getValue(Integer.class);
                    mDatabase.child("users").child(modEmail).child("age").setValue(count + 1);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    /**
     *  Resets "age" tag.
     *
     *  @param email user's email
     */
    public void resetLockout(String email)
    {
        final String modEmail = email.replace(".", "");

        mDatabase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.child(modEmail).exists()) {
                    mDatabase.child("users").child(modEmail).child("age").setValue(0);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

}
