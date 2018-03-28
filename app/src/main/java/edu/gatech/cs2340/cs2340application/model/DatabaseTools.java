package edu.gatech.cs2340.cs2340application.model;

/**
 * Inteface for the things that Firebase will do.
 *
 * Created by JGaul on 3/13/18.
 */

public interface DatabaseTools {

    /**
     * Given an email and password, puts a new user in the database.
     * Caller's responsibility to check whether the email and password are valid.
     *
     * @param email user's email
     * @param password user's password
     * @return whether the user was successfully created.
     */
    public boolean createUserEmail(String email, String password, Role role);

    /**
     * Verifies a given email and password combo.
     * Caller's responsibility to check whether the email and password are valid.
     *
     * @param email user's email
     * @param password user's password
     * @return whether the data matches
     */
    public boolean loginUserEmail(String email, String password);

    /**
     * Adds shelter to database. Should be triggered on every add action
     *
     * @param shelter the shelter to be added
     */
    public void addShelterDatabase(Shelter shelter);

    /**
     * Populates the shelter list on opening.
     */
    public void loadShelters();

    /**
     * Given a shelter, updates its info in the database.
     *
     * @param shelter the shelter to be updated
     */
    public void updateShelter(Shelter shelter);

    /**
     * Given a user, updates their info in the database.
     *
     * @param user the user to be updated
     */
    public void updateUser(User user);

}
