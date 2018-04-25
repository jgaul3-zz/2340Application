package edu.gatech.cs2340.cs2340application;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.junit.Test;

import static org.junit.Assert.*;
import edu.gatech.cs2340.cs2340application.model.Model;
import edu.gatech.cs2340.cs2340application.model.Shelter;

import static org.junit.Assert.*;

/**
 * Created by charl on 4/16/2018.
 */
public class AttemptLoginTest {

    private Model model;
    private Shelter shelter1;
    private Shelter testShelter;

    @Before
    public void setUp() throws Exception {
        model = Model.getInstance();
        shelter1 = new Shelter( 8, "", 0, 5.0, 5.0, "",
                "", "");
        testShelter = new Shelter ( 7, "", 0, 5.0, 5.0, "",
                "", "");
        model.addShelter(shelter1);
    }


    @Test
    public void attemptLogin() throws Exception {
        assertTrue(model.addShelter(testShelter));
        assertFalse(model.addShelter(testShelter));
    }

}