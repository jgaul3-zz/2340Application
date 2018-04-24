package edu.gatech.cs2340.cs2340application.model;

/**
 * Interface for the things that Firebase will do.
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
     * @param role user's role
     * @return whether the user was successfully created.
     */
    boolean createUserEmail(String email, String password, Role role);

    /**
     * Verifies a given email and password combo.
     * Caller's responsibility to check whether the email and password are valid.
     *
     * @param email user's email
     * @param password user's password
     * @return whether the data matches
     */
    boolean loginUserEmail(String email, String password);

    /**
     * Adds shelter to database. Should be triggered on every add action
     *
     * @param shelter the shelter to be added
     */
    void addShelterDatabase(Shelter shelter);

    /**
     * Populates the shelter list on opening.
     */
    void loadShelters();

    /**
     * Given a shelter, updates its info in the database.
     *
     * @param shelter the shelter to be updated
     */
    void updateShelter(Shelter shelter);

    /**
     * Given a user, updates their info in the database.
     *
     * @param user the user to be updated
     */
    void updateUser(User user);

    /**
     *  Sends password reset to selected email.
     *
     * @param email email to be reset.
     */
     void resetPassword(String email);

    /**
     *  Checks whether user is valid using "veteran" tag.
     *
     *  @param email user's email
     */
    public boolean checkBanned(String email);

    /**
     *  Checks whether user has been locked out using "age" tag.
     *
     *  @param email user's email
     */
    public boolean checkLockout(String email);

    /**
     *  Adds one to "age" tag.
     *
     *  @param email user's email
     */
    public void addLockout(String email);

    /**
     *  Resets "age" tag.
     *
     *  @param email user's email
     */
    public void resetLockout(String email);
}
