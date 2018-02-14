package edu.gatech.cs2340.cs2340application.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Jordan on 2/13/2018.
 *
 * Information Holder - represents a single user in model
 *
 *
 */

public class User {

    /** allows each user to have a unique id number */
    private static int Next_Id = 0;

    /** this user's id number */
    private int _id;

    /** this user's name */
    private String _name;

    /** this user's gender */
    private Gender _gender;

    /** this user's username */
    private String _username;

    /** this user's password */
    private String _password;

    /* **********************
     * Getters and setters
     */
    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    public int getId() { return _id; }

    public Gender getGender() { return _gender; }
    public void setGender(Gender gender) { _gender = gender; }

    public String getUsername() { return _username; }
    public void setUsername(String username) { _username = username; }

    public String getPassword() { return _password; }
    public void setPassword(String password) { _password = password; }

    /**
     * Makes a new user
     * @param username  the user's username
     * @param password  the user's password
     * @param name      the user's name
     * @param gender    the user's gender
     */
    public User(String username, String password, String name, Gender gender) {
        _username = username;
        _password = password;
        _name = name;
        _gender = gender;
        _id = User.Next_Id++;
    }

    /**
     * Makes a new user
     * @param username  the user's username
     * @param password  the user's password
     * @param name      the user's name
     */
    public User(String username, String password, String name) {
        this(username, password, name, Gender.OTHER);
    }

    /**
     * Makes a new user
     * @param username  the user's username
     * @param password  the user's password
     */
    public User(String username, String password) {
        this(username, password, "enter your name");
    }

    /**
     * Makes a new user
     */
    public User() { this("enter a username", "enter a password"); }

    /**
     *
     * @return the display string representation
     */
    @Override
    public String toString() {
        return _name + " " + _gender;
    }


}
