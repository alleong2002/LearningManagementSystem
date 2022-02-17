/**
 * The Student class creates an instance of Student. Each Student has a
 * String webID and an array list containing all of the courses they are taking.
 * Contains methods to add and remove courses, as well as methods to print out
 * the courses they are taking.
 *
 * @author Alexander Leong
 */
package LMS;
import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {
    private String webID;
    private ArrayList<Course> courses = new ArrayList<Course>();
    private String password;

    /**
     * Default constructor for Student.
     */
    public Student() {

    }

    /**
     * Constructor for Student.
     * @param webID
     *      String to set webID to.
     *
     * Postconditions:
     *      A new Student object with webID equal to webID has been created.
     */
    public Student(String webID) {
        this.webID = webID;
    }

    /**
     * Constructor for student. Includes webID and password fields.
     */
    public Student(String webID, String password) {
        this.webID = webID;
        this.password = password;
    }

    /**
     * Setter for webID.
     * @param webID
     *      String to set webID to.
     */
    public void setWebID(String webID) {
        this.webID = webID;
    }

    /**
     * Setter for courses.
     * @param courses
     *      ArrayList to set courses to.
     */
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    /**
     * Getter for webID.
     * @return
     *      Returns a String containing the webID.
     */
    public String getWebID() {
        return this.webID;
    }

    /**
     * Getter for courses.
     * @return
     *      Returns a reference to the ArrayList containing all of the Student's courses.
     */
    public ArrayList<Course> getCourses() {
        return this.courses;
    }

    /**
     * Method to add a course to a Student's course list.
     * @param course
     *      Course to add to the student's course list.
     */
    public void addCourse(Course course) {
        courses.add(course);
    }

    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Removes a course from a student's course list.
     * @param course
     *      The course to be removed from the studen't list.
     * @return
     *      Returns true if the course has been located and removed.
     *      Returns false if the course is not in the student's course list.
     */
    public boolean dropCourse(Course course) {
        CourseNameComparator comp = new CourseNameComparator();
        for(int i = 0; i < courses.size(); i++) {
            if(comp.compare(course, courses.get(i)) == 0) {
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Method to print the courses in alphabetical order (by department and class number).
     * Uses an instance of CourseNameComparator to determine order.
     */
//    public void printCourses() {
//        ArrayList<Course> c1 = (ArrayList<Course>)getCourses().clone();
//        CourseNameComparator comparator = new CourseNameComparator();
//        System.out.println("Dept. Course Semester" + '\n' + "---------------------");
//        for(int i = 0; i < courses.size(); i++) {
//            int min = 0;
//            for(int j = 0; j < c1.size(); j++) {
//                if(comparator.compare(c1.get(j), c1.get(min)) < 0) {
//                    min = j;
//                }
//            }
//            System.out.println(c1.get(min).getDepartment() + "   " + c1.get(min).getNumber() +
//                    "    " + c1.get(min).getSemester());
//            c1.remove(min);
//        }
//    }

    /**
     * Method to print a student's courses by semester. Uses an instance of SemesterComparator
     * to determine order.
     */
//    public void printSortedCourses() {
//        ArrayList<Course> c1 = (ArrayList<Course>)getCourses().clone();
//        System.out.println("Dept. Course Semester" + '\n' + "---------------------");
//        SemesterComparator comparator = new SemesterComparator();
//        for(int i = 0; i < courses.size(); i++) {
//            int min = 0;
//            for(int j = 0; j < c1.size(); j++) {
//                if(comparator.compare(c1.get(j), c1.get(min)) < 0) {
//                    min = j;
//                }
//            }
//            System.out.println(c1.get(min).getDepartment() + "   " + c1.get(min).getNumber() +
//                    "    " + c1.get(min).getSemester());
//            c1.remove(min);
//        }
//    }

    public String toString() {
        String s = this.getWebID();
        return s;
    }
}
