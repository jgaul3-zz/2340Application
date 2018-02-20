package edu.gatech.cs2340.cs2340application.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jordan on 2/13/2018.
 *
 * Information Holder - represents a single user in model
 *
 * We are passing this object in a bundle between intents, so we implement
 * the Parcelable interface.
 *
 */

public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    /** List of users. */
    private List<User> _users;

    /** List of shelters. */
    private List<Shelter> _shelters;

    /**Currently selected user for admin tasks. */
    private User _currentUser;

    /**Currently selected shelter for admin tasks. */
    private Shelter _currentShelter;

    /** Null User when user isn't found. */
    private final User nullUser = new User("No such user", "No such password");

    /** Null Shelter when shelter isn't found. */
    private final Shelter nullShelter = new Shelter("Shelter doesn't exist",
            0, 0, 0, "", "",
            "");

    private Model() {

        _users = new ArrayList<>();
        _shelters = new ArrayList<>();

    }

    /** Getters */
    public List<User> getUsers() { return _users; }

    public List<Shelter> getShelters() { return _shelters; }

    /**
     * adds a user to the app if they don't already exist.
     *
     * @param user the user to be added
     * @return whether it worked
     */
    public boolean addUser(User user) {
        for (User u : _users )
        {
            if (u.equals(user)) return false;
        }

        _users.add(user);
        return true;
    }

    /**
     * adds a shelter to the app if they don't already exist.
     *
     * @param shelter the shelter to be added
     * @return whether the shelter was added
     */
    public boolean addUser(Shelter shelter) {
        for (Shelter s : _shelters )
        {
            if (s.equals(shelter)) return false;
        }

        _shelters.add(shelter);
        return true;
    }

//    /**
//     * get the courses
//     * @return a list of the courses in the app
//     */
//    public List<Course> getCourses() { return _courses; }
//
//    /**
//     * add a course to the app.  checks if the course is already entered
//     *
//     * uses O(n) linear search for course
//     *
//     * @param course  the course to be added
//     * @return true if added, false if a duplicate
//     */
//    public boolean addCourse(Course course) {
//        for (Course c : _courses ) {
//            if (c.equals(course)) return false;
//        }
//        _courses.add(course);
//        return true;
//    }
//
//    /**
//     *
//     * @return  the currently selected course
//     */
//    public Course getCurrentCourse() { return _currentCourse;}
//
//    public void setCurrentCourse(Course course) { _currentCourse = course; }
//
//    /**
//     * Return a course that has matching number.
//     * This uses an O(n) linear search.
//     *
//     * @param number the number of the course to find
//     * @return  the course with that number or the NullCourse if no such number exists.
//     *
//     */
//    public Course getCourseByNumber (String number) {
//        for (Course c : _courses ) {
//            if (c.getNumber().equals(number)) return c;
//        }
//        return theNullCourse;
//    }
//
//    /**
//     * Return a course that has the matching id
//     * This uses a linear O(n) search
//     *
//     * @param id the id number of the course
//     * @return the course with this id or theNullCourse if no such id exists.
//     */
//    public Course getCourseById(int id) {
//        for (Course c : _courses ) {
//            if (c.getId() == id) {
//                return c;
//            }
//        }
//        return theNullCourse;
//    }
//
//    /**
//     * add a student to the current course
//     *
//     * @param student the student to add
//     * @return true if student added, false if not added
//     */
//    public boolean addStudent(Student student) {
//        return _currentCourse != null && _currentCourse.addStudent(student);
//    }
//
//    /**
//     * Replace an existing students data with new data
//     *
//     * @param student the student being edited
//     */
//    public void replaceStudentData(Student student) {
//        Student existing = _currentCourse.findStudentById(student.getId());
//
//        //if existing comes back null, something is seriously wrong
//        if (BuildConfig.DEBUG && (existing == null)) { throw new AssertionError(); }
//
//        //update the name
//        existing.setName(student.getName());
//
//        //update the major
//        existing.setMajor(student.getMajor());
//    }
}
