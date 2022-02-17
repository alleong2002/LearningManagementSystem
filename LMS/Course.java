/**
 * The Course class creates an instance of Course. Each Course has a department, a number,
 * and a semester. Contains getters and setters for each of these data fields.
 * Also contains a toString method for easier printing.
 *
 * @author Alexander Leong
 */
package LMS;

import java.io.Serializable;
import java.util.ArrayList;

//TODO: Implement students arraylist to keep track of the students in each course.

public class Course implements Serializable {
    private String department;
    private int number;
//    private String semester;
    private String name;
    private Instructor instructor;
    private ArrayList<Student> students = new ArrayList<Student>(); //TODO

    /**
     * Constructor for Course.
     * @param department
     *      The department of the Course.
     * @param number
     *      The number of the Course.
     * @param name
     *      The official course title.
     */
    public Course(String department, int number, String name) {
        this.department = department;
        this.number = number;
        this.name = name;
    }

    /**
     * Setter for department.
     * @param department
     *      The String to set department to.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Setter for number.
     * @param number
     *      The number to set the course to.
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Setter for name.
     * @param name
     *      The name of the course.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for instructor.
     */
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    /**
     * Getter for instructor.
     */
    public Instructor getInstructor() {
        return this.instructor;
    }

    /**
     * Getter for department.
     * @return
     *      Returns the String reference of the department of a Course.
     */
    public String getDepartment() {
        return this.department;
    }

    /**
     * Getter for number.
     * @return
     *      Returns the number of a Course.
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Getter for name.
     * @return
     *      Returns a String representing the course name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Adds a Student to the ArrayList of all Students. Keeps track of which Students are enrolled in a course.
     */
    public void addStudent(Student student) {
        students.add(student);
    }

    /**
     * The toString() method is used for printing a Course's information.
     * @return
     *      Returns a String containing the department and number of a Course.
     */
    public String toString() {
        String s = department + " " + number;
        return s;
    }
}
