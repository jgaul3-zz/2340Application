package edu.gatech.cs2340.cs2340application.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private final User nullUser = new User("No such user", "No such password");

    /** Null Shelter when shelter isn't found. */
    private final Shelter nullShelter = new Shelter("Shelter doesn't exist",
            0, 0, 0, "", "",
            "");

    private Model() {

        _users = new ArrayList<>();
        _shelters = new ArrayList<>();

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
            if (u.getUsername().equals(name)) return u;
        }

        return nullUser;
    }

    /**
     *  given credentials determines whether they are valid.
     *
     * @param name the username
     * @param pass the password
     * @return the user object or nullUser if incorrect.
     */
    public User verifyUser(String name, String pass) {
        for (User u : _users)
        {
            if (u.getUsername().equals(name) && u.getPassword().equals(pass)) return u;
        }

        return nullUser;
    }
}
