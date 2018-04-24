package edu.gatech.cs2340.cs2340application.model;

/**
 * Created by Jordan on 2/21/2018.
 *
 */

public enum Role {
    HOMELESS ("HOMELESS"),
    EMPLOYEE ("EMPLOYEE"),
    ADMIN ("ADMIN");

    /** the full string representation of the user's role */
    private final String name;

    /**
     * Constructor for the enumeration
     *
     * @param pname the role selected
     */
    Role(String pname) { name = pname; }

    /**
     *
     * @return the display string representation of the role
     */
    public String toString() { return name; }
}