package edu.gatech.cs2340.cs2340application.model;

/**
 * Created by charleston on 3/16/2018.
 * This class is meant to handle the many age ranges
 */

public enum AgeRange {
    NA ("N/A"),
    ANYONE ("Anyone"),
    FAMILIES ("Families"),
    CHILDREN ("Children"),
    YOUNG_ADULTS ("Young adults");


    private final String ageRange;

    AgeRange(String aR) {ageRange = aR;}
    public String toString() {return ageRange;}
}
