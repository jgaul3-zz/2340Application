package edu.gatech.cs2340.cs2340application.model;

/**
 * Created by charl on 3/16/2018.
 */

public enum AgeRange {
    ANYONE ("Anyone"),
    FAMILIES ("Family/Newborns"),
    CHILDREN ("Children"),
    YOUNG_ADULTS ("Young adults");


    private final String ageRange;

    AgeRange(String aR) {ageRange = aR;}
    public String toString() {return ageRange;}
}
