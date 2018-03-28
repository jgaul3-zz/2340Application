package edu.gatech.cs2340.cs2340application.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JGaul on 2/20/18.
 *
 */

public class Shelter {

    /** this shelter's key number */
    private int _key;

    /** this shelter's name */
    private String _name;

    /** this shelter's max capacity */
    private String _capacity;

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

    /** this shelters's age range */
    private AgeRange userAgeRange;


    public AgeRange getAgeRange() { return userAgeRange; }
    public void setAgeRange(AgeRange uAgeRange) {  userAgeRange = uAgeRange; }

    public int getKey() { return _key; }

    public String getName() { return _name; }
    public void setName(String name) { _name = name; }

    public String getCapacity() { return _capacity; }
    public void setCapacity(String capacity) { _capacity = capacity; }

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

    public int getVacancy() {return Integer.parseInt(_capacity) - _occupancy;}

    /**
     * Makes a new shelter
     */
    public Shelter(int key, String name, String capacity, int occupancy, double latitude, double longitude,
                   String address, String phoneNumber, String notes) {
        _key = key;
        _name = name;
        _capacity = capacity;
        _occupancy = occupancy;
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
     * Makes a new shelter
     */
    public Shelter(int key, String name, String capacity, double latitude, double longitude, String address,
                   String phoneNumber, String notes) {
        this(key, name, capacity, 0, latitude, longitude, address, phoneNumber, notes);
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
