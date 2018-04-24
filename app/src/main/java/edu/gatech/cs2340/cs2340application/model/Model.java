package edu.gatech.cs2340.cs2340application.model;

import java.util.ArrayList;

/**
 * Created by Jordan on 2/13/2018.
 *
 * Information Holder - represents a single user in model
 *
 * We are passing this object in a bundle between intents, so we implement
 * the Parcelable interface.
 *
 */

public final class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();

    /**
     * gets instance
     * @return the instance
     */
    public static Model getInstance() { return _instance; }

    /** List of users. */
    private final ArrayList<User> _users;

    /** List of shelters. */
    private final ArrayList<Shelter> _shelters;
    private ArrayList<Shelter> _searchShelters;

    /**Currently selected user for admin tasks. */
    private User _currentUser;
    public boolean bannedFlag;
    public boolean lockoutFlag;
    public boolean loggedInFlag;
    public String returnMessage;

    /**Currently selected shelter for admin tasks. */
    private Shelter _currentShelter;

    private int currentShelterId;

    /**
     * shelter id getter
     * @return id
     */
    public int getCurrentShelterId() {
        return currentShelterId;
    }

    /**
     * current shelter id getter
     * @return id
     */
    public void setCurrentShelterId(int ID) {
        currentShelterId = ID;
    }


    /** Null User when user isn't found. */
    public final User nullUser = new User("No such user", "");

    public boolean isNullUser(User user)
    {
        return user.equals(nullUser);
    }

    /** Null Shelter when shelter isn't found. */
    private final Shelter nullShelter = new Shelter(-1, "Shelter doesn't exist",
            0, 0, 0, "", "",
            "");

    public boolean isNullShelter(Shelter shelter)
    {
        return shelter.equals(nullShelter);
    }

    private Model() {

        _users = new ArrayList<>();
        _shelters = new ArrayList<>();

    }

//    public ArrayList<User> getUsers() { return _users; }

    /**
     * gets list of shelter
     * @return list of shelters
     */
    public ArrayList<Shelter> getShelters() { return _shelters; }

//    public Shelter getCurrentShelter() { return _currentShelter; }
//    public void setCurrentShelter(Shelter s) { _currentShelter = s; }
//
//    public User getCurrentUser() { return _currentUser; }
//    public void setCurrentUser(User u) { _currentUser = u; }

    /**
     * getter for searched shelters
     * @return list
     */
    public ArrayList<Shelter> getSearchShelters() { return _searchShelters; }


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
//
//    /**
//     * removes the supplied user from the database
//     *
//     * @param user the user to be removed
//     * @return whether the removal was successful
//     */
//    public boolean removeUser(User user) {
//        return _users.remove(user);
//    }

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
     * adds a shelter to the search list if they don't already exist.
     *
     * @param shelter the shelter to be added
     * @return whether the shelter was added
     */
    public boolean addSearchShelter(Shelter shelter) {
        for (Shelter s : _searchShelters )
        {
            if (s.equals(shelter)) return false;
        }

        _searchShelters.add(shelter);
        return true;
    }

    /**
     * adds a shelter to the app if they don't already exist.
     *
     */
    public void clearSearchShelters() {
        _searchShelters = new ArrayList<>();
    }

//    /**
//     * removes the supplied shelter from the database
//     *
//     * @param shelter the shelter to be removed
//     * @return whether the removal was successful
//     */
//    public boolean removeShelter(Shelter shelter) {
//        return _shelters.remove(shelter);
//    }



    /**
     * Charleston Commit
     *
     * given a username, returns the user if they exist.
     *
     * @param key the id
     * @return the shelter or nullShelter
     */
    public Shelter findShelterById(int key) {

        for (Shelter d : _shelters) {
            if (d.getKey() == key) return d;
        }
        return nullShelter;
    }

    /**
     * given a username, returns the user if they exist.
     *
     * @param name the username
     * @return the user or nullUser
     */
    public User getUserByUsername(String name) {

        if (name == null || name.equals(""))
        {
            return nullUser;
        }

        for (User u : _users)
        {
            if (u.getUsername().toLowerCase().equals(name.toLowerCase())) return u;
        }

        return nullUser;
    }

//    /**
//     *  given credentials, determines whether they are valid
//     *
//     * @param name the username
//     * @param pass the password
//     * @return the result
//     */
//    public boolean verifyUser(String name, String pass) {
//        return getUserByUsername(name).checkPassword(pass);
//    }


}
