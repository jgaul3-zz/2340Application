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

    /** this user's username */
    private String _username;

    /** this user's password */
    private String _password;

    /** this user's name */
    private String _name;

    /** this user's role */
    private Role _role;

    /** this user's gender */
    private Gender _gender;

    /** this user's age */
    private int _age;

    /** this user's veteran */
    private boolean _veteran;


    /** this user's contact information */
    private String _contact;

    /* **********************
     * Getters and setters
     */


    public int getId() { return _id; }

    public String getUsername() { return _username; }
    public void setUsername(String username) { _username = username; }

    public String getPassword() { return _password; }
    public void setPassword(String password) { _password = password; }

    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    public Gender getGender() { return _gender; }
    public void setGender(Gender gender) { _gender = gender; }

    public int getAge() { return _age; }
    public void setAge(int age) { _age = age; }

    public boolean getVeteran() { return _veteran; }
    public void setVeteran(boolean veteran) { _veteran = veteran; }

    public Role getRole() { return _role; }
    public void setRole(Role role) { _role = role; }

    /**
     * Makes a new user
     * @param username  the user's username
     * @param password  the user's password
     * @param name      the user's name
     * @param role      the user's role
     * @param gender    the user's gender
     * @param age       the user's age
     * @param veteran   the user's veteran??
     * @param contact   the user's contact information
     */
    public User(String username, String password, String name, Role role, Gender gender, int age, boolean veteran, String contact) {
        _username = username;
        _password = password;
        _name = name;
        _role = role;
        _gender = gender;
        _age = age;
        _veteran = veteran;
        _contact = contact;
        _id = User.Next_Id++;
    }

    /**
     * Makes a new user
     * @param username  the user's username
     * @param password  the user's password
     * @param name      the user's name
     * @param role      the user's role
     * @param gender    the user's gender
     * @param age       the user's age
     * @param veteran   the user's veteran??
     */
    public User(String username, String password, String name, Role role, Gender gender, int age, boolean veteran) {
        this(username, password, name, role, gender, age, veteran, "No phone registered");
    }

    /**
     * Makes a new user
     * @param username  the user's username
     * @param password  the user's password
     * @param name      the user's name
     * @param role      the user's role
     */
    public User(String username, String password, String name, Role role) {
        this(username, password, name, role, Gender.OTHER, 100, false);
    }

    /**
     * Makes a new user
     * @param username  the user's username
     * @param password  the user's password
     * @param name      the user's name
     */
    public User(String username, String password, String name) {
        this(username, password, name, Role.HOMELESS);
    }

    /**
     * Makes a new user
     * @param username  the user's username
     * @param password  the user's password
     * @param role      the user's role
     */
    public User(String username, String password, Role role) {
        this(username, password, "", role, Gender.OTHER, 100, false);
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

    /**
     * Verify the password passed into this user
     *
     * @param pass attempted password
     * @return if they match
     */
    public boolean checkPassword(String pass) {
        return pass.equals(_password);
    }


}
