package edu.gatech.cs2340.cs2340application;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.gatech.cs2340.cs2340application.model.Model;
import edu.gatech.cs2340.cs2340application.model.Role;
import edu.gatech.cs2340.cs2340application.model.User;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    /**
     * useAppContext() sample text
     * @throws Exception thrown by this test
     */
    @Test
    public void testRegisterUser() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testModelGetUserByUsername() throws Exception {
        Model model = Model.getInstance();

        //Returns null user when given null.
        assertTrue(model.isNullUser(model.getUserByUsername(null)));

        //Returns null user when given empty string.
        assertTrue(model.isNullUser(model.getUserByUsername("")));

        User JG = new User("Jon Gaul", "12345678", Role.ADMIN);
        User AJG = new User("Also Jon Gaul", "12345678", Role.EMPLOYEE);
        User JGBHTT = new User("Jon Gaul but homeless this time",
                "12345678", Role.HOMELESS);

        model.addUser(JG);
        model.addUser(AJG);
        model.addUser(JGBHTT);

        assertEquals(model.getUserByUsername(JG.getUsername()), JG);
        assertEquals(model.getUserByUsername(AJG.getUsername()), AJG);
        assertEquals(model.getUserByUsername(JGBHTT.getUsername()), JGBHTT);

        assertNotEquals(model.getUserByUsername(JG.getUsername()), AJG);
        assertNotEquals(model.getUserByUsername(AJG.getUsername()), JG);
        assertNotEquals(model.getUserByUsername("Bob"), JGBHTT);
    }
}