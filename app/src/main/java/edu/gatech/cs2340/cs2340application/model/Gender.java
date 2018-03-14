package edu.gatech.cs2340.cs2340application.model;

/**
 * Created by Jordan on 2/13/2018.
 *
 * This class represents the genders a new user can select from
 */

public enum Gender {
    ANY ("ANY"),
    MALE ("MALE"),
    FEMALE ("FEMALE"),
    OTHER ("OTHER");

    /** the full string representation of the user's gender */
    private final String name;

    /**
     * Constructor for the enumeration
     *
     * @param pname the gender selected
     */
    Gender(String pname) { name = pname; }

    /**
     *
     * @return the display string representation of the gender
     */
    public String toString() { return name; }
}
