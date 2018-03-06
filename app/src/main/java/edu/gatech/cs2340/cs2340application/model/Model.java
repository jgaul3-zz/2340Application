package edu.gatech.cs2340.cs2340application.model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import edu.gatech.cs2340.cs2340application.R;

/**
 * Created by Jordan on 2/13/2018.
 *
 * Information Holder - represents a single user in model
 *
 * We are passing this object in a bundle between intents, so we implement
 * the Parcelable interface.
 *
 */

public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    /** List of users. */
    private List<User> _users;

    /** List of shelters. */
    private List<Shelter> _shelters;

    /**Currently selected user for admin tasks. */
    private User _currentUser;

    /**Currently selected shelter for admin tasks. */
    private Shelter _currentShelter;

    /** Null User when user isn't found. */
    public final User nullUser = new User("No such user", "No such password");

    /** Null Shelter when shelter isn't found. */
    private final Shelter nullShelter = new Shelter(0, "Shelter doesn't exist",
            0, 0, 0, "", "",
            "");

    private Model() {

        _users = new ArrayList<>();
        _shelters = new ArrayList<>();

        dummyData();
        //shelterRead();

    }

    private void dummyData() {
        addUser(new User("Admin", "adminpass", Role.ADMIN));
        addUser(new User("HomelessGuy", "homelesspass", Role.HOMELESS));
        addUser(new User("ShelterWorker", "workerpass", Role.EMPLOYEE));
    }

    private void shelterRead() {
        /*
        try {
            InputStream is = getResources().openRawResource(R.raw.homeless_shelter_database);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String line;
            br.readLine();


        } catch (IOException e) {

        }
        */
    }

    /** Getters */
    public List<User> getUsers() { return _users; }

    public List<Shelter> getShelters() { return _shelters; }

    /**
     * adds a user to the app if they don't already exist.
     *
     * @param user the user to be added
     * @return whether it worked
     */
    public boolean addUser(User user) {
        for (User u : _users )
        {
            if (u.equals(user)) return false;
        }

        _users.add(user);
        return true;
    }

    /**
     * removes the supplied user from the database
     *
     * @param user the user to be removed
     * @return whether the removal was successful
     */
    public boolean removeUser(User user) {
        return _users.remove(user);
    }

    /**
     * adds a shelter to the app if they don't already exist.
     *
     * @param shelter the shelter to be added
     * @return whether the shelter was added
     */
    public boolean addShelter(Shelter shelter) {
        for (Shelter s : _shelters )
        {
            if (s.equals(shelter)) return false;
        }

        _shelters.add(shelter);
        return true;
    }

    /**
     * removes the supplied shelter from the database
     *
     * @param shelter the shelter to be removed
     * @return whether the removal was successful
     */
    public boolean removeShelter(Shelter shelter) {
        return _shelters.remove(shelter);
    }

    /**
     * given a username, returns the user if they exist.
     *
     * @param name the username
     * @return the user or nullUser
     */
    public User getUserByUsername(String name) {
        for (User u : _users)
        {
            if (u.getUsername().toLowerCase().equals(name.toLowerCase())) return u;
        }

        return nullUser;
    }

    /**
     *  given credentials determines whether they are valid.
     *
     * @param name the username
     * @param pass the password
     * @return the result
     */
    public boolean verifyUser(String name, String pass) {
        for (User u : _users)
        {
            if (u.getUsername().toLowerCase().equals(name.toLowerCase())
                    && u.getPassword().equals(pass)) return true;
        }

        return false;
    }
}
