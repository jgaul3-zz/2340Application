package edu.gatech.cs2340.cs2340application.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JGaul on 2/20/18.
 *
 */

public class Shelter {

    /** allows each shelter to have a unique key number */
    private static int Next_key = 0;

    /** this shelter's key number */
    private int _key;

    /** this shelter's name */
    private String _name;

    /** this shelter's max capacity */
    private int _capacity;

    /** this shelter's current occupancy */
    private int _occupancy;

    /** this shelter's latitude  */
    private double _latitude;

    /** this shelter's longitude */
    private double _longitude;

    /** this shelter's address */
    private String _address;

    /** this shelter's phone number */
    private String _phoneNumber;

    /** this shelter's special notes */
    private String _notes;

    //TODO replace with proper enum for restrictions.
    /** this shelter's restrictions */
    private List<String> _restrictions;

    /** currently sheltered homeless people here */
    private List<User> _homeless;

    /** employees here */
    private List<User> _employees;


    /* **********************
     * Getters and setters
     */
    public int getKey() { return _key; }

    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    public int getCapacity() { return _capacity; }
    public void setCapacity(int capacity) { _capacity = capacity; }

    public int getOccupancy() { return _occupancy; }
    public void setOccupancy(int occupancy) { _occupancy = occupancy; }

    public double getLatitude() { return _latitude; }
    public void setLatitude(double latitude) { _latitude = latitude; }

    public double getLongitude() { return _longitude; }
    public void setLongitude(double longitude) { _longitude = longitude; }

    public String getAddress() { return _address; }
    public void setAddress(String address) { _address = address; }

    public String getPhoneNumber() { return _phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { _phoneNumber = phoneNumber; }

    public String getNotes() { return _notes; }
    public void setNotes(String notes) { _notes = notes; }

    public List<String> getRestrictions() { return _restrictions; }
    public void setRestrictions(List<String> restrictions) { _restrictions = restrictions; }

    public List<User> getHomeless() { return _homeless; }
    public void setHomeless(List<User> homeless) { _homeless = homeless; }

    public List<User> getEmployees() { return _employees; }
    public void setEmployees(List<User> employees) { _employees = employees; }


    /**
     * Makes a new shelter
     */
    public Shelter(String name, int capacity, double latitude, double longitude, String address,
                   String phoneNumber, String notes) {
        _key = Shelter.Next_key++;
        _name = name;
        _capacity = capacity;
        _occupancy = 0;
        _latitude = latitude;
        _longitude = longitude;
        _address = address;
        _phoneNumber = phoneNumber;
        _notes = notes;
        _restrictions = new ArrayList<>();
        _homeless = new ArrayList<>();
        _employees = new ArrayList<>();
    }

    /**
     *
     * @return the display string representation
     */
    @Override
    public String toString() {
        return _name;
    }

    public boolean equals(Shelter shelter) { return _key == shelter.getKey(); }
}
