package edu.gatech.cs2340.cs2340application.model;

/**
 * Created by Jordan on 2/13/2018.
 *
 * This class represents the genders a new user can select from
 */

public enum Gender {
    NA ("N/A"),
    ANYONE ("Anyone "),
    MEN ("Men"),
    WOMEN ("Women"),
    OTHER ("Other");

    /** the full string representation of the user's gender */
    private final String name;

    /**
     * Constructor for the enumeration
     *
     * @param pname the gender selected
     */
    Gender(String pname) { name = pname; }

    public Gender opposite() {
        if (this.equals(Gender.MEN)) {
            return Gender.WOMEN;
        } else if (this.equals(Gender.WOMEN)) {
            return Gender.MEN;
        } else {
            return Gender.ANYONE;
        }
    }

    /**
     *
     * @return the display string representation of the gender
     */
    public String toString() { return name; }
}
