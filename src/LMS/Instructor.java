package LMS;

/**
 * The Instructor class is used to create an instructor for a course. Each instructor
 * has administrative rights over the content of a course, with the ability to
 * add and remove assignments / exams. The GUI also provides data on the statistics
 * for a certain assignment.
 */

import java.io.Serializable;
import java.util.*;

public class Instructor implements Serializable {
    private ArrayList<Course> courses = new ArrayList<Course>();
    private String name;
    private String webID;
    private String password;
    public Instructor() {

    }

    /**
     * Constructor for instance of Instructor.
     * @param webID
     *      The webID of the instructor.
     * @param password
     *      The password for this user.
     */
    public Instructor(String webID, String password) {
        this.webID = webID;
        this.password = password;
    }

    /**
     * Getter for name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for WebID.
     */
    public String getWebID() {
        return this.webID;
    }

    /**
     * Getter for password.
     */
    public String getPassword() { return this.password; }

    /**
     * Getter for Courses.
     */
    public ArrayList<Course> getCourses() {
        return this.courses;
    }

    public String toString() {
        String s = this.getWebID();
        return s;
    }
}
