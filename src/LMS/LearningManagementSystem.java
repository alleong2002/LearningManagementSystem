package LMS;
/**
 * The LearningManagementSystem provides a user interface to enroll and deregister Student's in courses.
 * Contains methods to distinguish between a Registrar (administrative) user and a generic Student
 * user. Depending on which type of user the user is, the program will offer different options. Utilizes
 * a HashMap database to retrieve and store information for courses, instructors, and students.
 *
 * @author Alexander Leong
 */

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class LearningManagementSystem {
    /**
     * Non-relational database. For all objects, the Key is a String containing either a UserID or a Course Code.
     */
    private static HashMap<String, Student> database = new HashMap<>(); // WebID Key (as String), Student as Value.
    private static HashMap<String, String> password = new HashMap<>(); // WebID Key (as String), Password as Value.
    private static HashMap<String, Course> courses = new HashMap<>(); // Course Code (as String), Course as Value.
    private static HashMap<String, Instructor> instructors = new HashMap<>(); // Instructor WebID (as String), Instructor as Value.
    public static boolean end = false;

    /**
     * Checks if there is a previous save file under the name of Lunar.ser. Indicates to the user whether
     * or not a previous save file was found. Prints the menu, and continues the program until the
     * user enters X or Q, upon which the loop will end. Passes user input to choice().
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the Learning Management System.");
        try {
            FileInputStream file = new FileInputStream("Lunar.ser");
            ObjectInputStream input = new ObjectInputStream(file);
            database = (HashMap)input.readObject();
            input.close();
            System.out.println("Previous data loaded.");
        } catch(Exception e1){
            System.out.println("No previous data found.");
        }
        printMenu();
        String s = promptUser();
        while (!end) {
            choice(s);
            if(end) {
                break;
            }
            s = promptUser();
        }
    }

    /**
     * Loads the database for the LMS GUI. This allows for the retrieval of
     * Student and Instructor information.
     */
    public static void loadDatabase() {
        try {
            FileInputStream file = new FileInputStream("Students.ser");
            ObjectInputStream input = new ObjectInputStream(file);
            database = (HashMap)input.readObject();
            input.close();
            System.out.println("Previous data for students loaded.");
        } catch(Exception e1){
            System.out.println("No previous data found for students.");
        }
        try {
            FileInputStream file = new FileInputStream("Courses.ser");
            ObjectInputStream input = new ObjectInputStream(file);
            courses = (HashMap)input.readObject();
            input.close();
            System.out.println("Previous data for courses loaded.");
        } catch(Exception e1){
            System.out.println("No previous data found for courses.");
        }
        try {
            FileInputStream file = new FileInputStream("Instructors.ser");
            ObjectInputStream input = new ObjectInputStream(file);
            instructors = (HashMap)input.readObject();
            input.close();
            System.out.println("Previous data for instructors loaded.");
        } catch(Exception e1){
            System.out.println("No previous data found for instructors.");
        }
    }

    /**
     * Used when Registrar enrolls a new student. Stores the data for the student
     * into the hashmap databases, with their login info and password included.
     * @param student
     *      The new student to add to the database.
     */
    public static void storeStudentInDatabase(Student student) {
        database.put(student.toString(), student);
        password.put(student.toString(), student.getPassword());
        try {
            FileOutputStream file = new FileOutputStream("Students.ser");
            ObjectOutputStream outStream = new ObjectOutputStream(file);
            outStream.writeObject(database);
            outStream.close();
            System.out.println("Student information successfully saved.");
        } catch(Exception e1) {
            System.out.println("Error!");
        }
    }

    /**
     * Stores the instructor information in the HashMap database.
     * @param instructor
     *      The instructor to store in the database.
     */
    public static void storeInstructorInDatabase(Instructor instructor) {
        instructors.put(instructor.toString(), instructor);
        try {
            FileOutputStream file = new FileOutputStream("Instructors.ser");
            ObjectOutputStream outStream = new ObjectOutputStream(file);
            outStream.writeObject(instructors);
            outStream.close();
            System.out.println("Instructor information successfully saved.");
        } catch(Exception e1) {
            System.out.println("Error!");
        }
    }

    /**
     * Stores a new Course into the HashMap database.
     */
    public static void storeCourseInDatabase(Course course) {
        courses.put(course.toString(), course);
        try {
            FileOutputStream file = new FileOutputStream("Courses.ser");
            ObjectOutputStream outStream = new ObjectOutputStream(file);
            outStream.writeObject(courses);
            outStream.close();
            System.out.println("Instructor information successfully saved.");
        } catch(Exception e1) {
            System.out.println("Error!");
        }
    }

    /**
     * Prints the menu of available options.
     */
    public static void printMenu() {
        System.out.println("Menu: " +
                '\n' + '\t' + "L) Login" +
                '\n' + '\t' + "X) Save state and quit" +
                '\n' + '\t' + "Q) Quit without saving state");
    }

    /**
     * Asks the user for an input and stores it in a String.
     * @return
     *      Returns a String input from the user.
     */
    public static String promptUser() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please select an option: ");
        String s = sc.nextLine().toUpperCase();
        return s;
    }

    /**
     * Checks if the login username is valid.
     * @return
     *      Returns true if the student is in the database and the password is correct.
     *      Returns false otherwise.
     */
    public static boolean validStudentLogin(String student) {
        if(database.containsKey(student)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the login username is valid.
     * @return
     *      Returns true of the username matches the WebID of an instructor.
     *      Returns false otherwise.
     */
    public static boolean validInstructorLogin(String instructor) {
        if(instructors.containsKey(instructor)) {
            return true;
        } return false;
    }

    /**
     * Getter for database.
     * @return
     *      Returns a reference to the database storing all student information (webID, and their references).
     */
    public static HashMap<String, Student> getDatabase() {
        return database;
    }

    public static HashMap<String, Course> getCourses() {
        return courses;
    }

    public static HashMap<String, Instructor> getInstructors() { return instructors; }
    
    public void addInstructor(Instructor instructor) {
        instructors.put(instructor.getName(), instructor);
    }

    /**
     * The choice method acts differently depending on what the user input is. Checks if the user
     * is a Registrar / administrative user; if so, calls the registrar() function. Else, it will call
     * the generic student() method. In the case that the user inputs "X" or "Q", the program will terminate.
     * If "X" is chosen, then objects will be serialized and stored as files.
     * @param s
     *      A String containing the user input.
     */
    public static void choice(String s) {
        Scanner sc = new Scanner(System.in);
        s = s.toUpperCase();
        switch(s) {
            case "L":
                System.out.println("Please enter webID: ");
                String webid = sc.nextLine();
                if(webid.equals("registrar")) {
                    webid = webid.substring(0, 1).toUpperCase() + webid.substring(1, webid.length()).toLowerCase();
                }
//                 else {
//                    webid = webid.substring(0, 2).toUpperCase() + webid.substring(2, webid.length()).toLowerCase();
//                }
                System.out.println("Welcome " + webid);
                switch (webid) {
                    case "Registrar":
                        System.out.println("Options: " +
                                '\n' + '\t' + "R) Register a student" +
                                '\n' + '\t' + "D) De-register a student" +
                                '\n' + '\t' + "E) View course enrollment" +
                                '\n' + '\t' + "L) Logout");
                        registrar();
                        break;
                    default:
                        System.out.println("Options: " +
                                '\n' + '\t' + "A) Add a class" +
                                '\n' + '\t' + "D) Drop a class" +
                                '\n' + '\t' + "C) View your classes sorted by course name/department" +
                                '\n' + '\t' + "S) View your courses sorted by semester");
                        student(webid);
                        break;
                }
                break;
            case "Q":
                System.out.println("Program is exiting, data was not saved.");
                end = true;
                break;
            case "X":
                try {
                    FileOutputStream file = new FileOutputStream("Students.ser");
                    ObjectOutputStream outStream = new ObjectOutputStream(file);
                    outStream.writeObject(database);
                    outStream.flush();
                    outStream.close();
                } catch(Exception e1) {
                    System.out.println("Error!");
                }
                try {
                    FileOutputStream file = new FileOutputStream("Courses.ser");
                    ObjectOutputStream outStream = new ObjectOutputStream(file);
                    outStream.writeObject(courses);
                    outStream.flush();
                    outStream.close();
                } catch(Exception e1) {
                    System.out.println("Error!");
                }
                try {
                    FileOutputStream file = new FileOutputStream("Instructors.ser");
                    ObjectOutputStream outStream = new ObjectOutputStream(file);
                    outStream.writeObject(instructors);
                    outStream.flush();
                    outStream.close();
                } catch(Exception e1) {
                    System.out.println("Error!");
                }
                System.out.println("System state saved. Program is exiting.");
                end = true;
                break;
            default:
                System.out.println("Not a valid option!");
                break;
        }
    }

    /**
     * The registrar() method is called when it is indicated that the user is an administrator.
     * Contains options to register and deregister Student's. Also contains an option to list all
     * of the students enrolled in a course.
     */
    public static void registrar() {
        System.out.println("Please select an option: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().toUpperCase();
        switch(input) {
            case "R":
                System.out.println("Please enter a WebID for the new student: ");
                String webid = sc.nextLine();
//                webid = webid.substring(0,2).toUpperCase() + webid.substring(2,webid.length()).toLowerCase();
                Student s = new Student(webid);
                if(database.get(webid) != null) {
                    System.out.println(s.getWebID() + " is already registered.");
                } else {
                    database.put(webid, s);
                    System.out.println(webid + " registered.");
                }
                registrar();
                break;
            case "D":
                System.out.println("Please enter webid for the student to be deregistered:");
                webid = sc.nextLine().toLowerCase();
                webid = webid.substring(0,2).toUpperCase() + webid.substring(2,webid.length()).toLowerCase();
                database.remove(webid);
                System.out.println(webid + " deregistered.");
                registrar();
                break;
            case "E":
                System.out.println("Please enter course: ");
                String courseName = sc.nextLine().toUpperCase();
                int number = Integer.parseInt(courseName.substring(4, courseName.length()));
                courseName = courseName.substring(0,3);
                Course course = new Course(courseName, number, "S");
                System.out.println("Students registered in " + course);
                System.out.printf("%-15s %-15s %n", "Student", "Semester");
                System.out.println("------------------------");
                CourseNameComparator comparator = new CourseNameComparator();
                for(String x : database.keySet()) {
                    ArrayList<Course> c1 = database.get(x).getCourses();
                    for (int i = 0; i < c1.size(); i++) {
                        if(comparator.compare(course, c1.get(i)) == 0) {
                            System.out.printf("%-15s %-15s %n",x, c1.get(i).getName());
                        }
                    }
                }
                registrar();
                break;
            case "Z":
                System.out.println("List of all enrolled students: ");
                for(String x: database.keySet()) {
                    System.out.println(database.get(x).getWebID());
                }
                registrar();
                break;
            case "L":
                System.out.println("Registrar logged out.");
                printMenu();
                break;
            default:
                System.out.println("Not a valid option!");
                registrar();
                break;
        }
    }

    /**
     * This method is called when the user is not an administrative user. Contains options for a user
     * to add a course, drop a course, and view their courses sorted either alphabetically or by
     * semester.
     * @param webid
     *      The webID of the student that is using the program.
     */
    public static void student(String webid) {
        Student s = database.get(webid);
        System.out.println("Please select an option: ");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().toUpperCase();
        switch(input) {
            case "A":
                System.out.println("Please enter course name: ");
                String courseName = sc.nextLine().toUpperCase();
                if(courseName.length() != 7) {
                    System.out.println("That is not a valid course name!");
                    return;
                }
                int number = Integer.parseInt(courseName.substring(4, courseName.length()));
                courseName = courseName.substring(0,3);
                System.out.println("Please select a semester: ");
                String semester = sc.nextLine();
                if(semester.length() != 5) {
                    System.out.println("That is not a valid semester!");
                    printMenu();
                    return;
                }
                Course course = new Course(courseName, number, semester);
                s.addCourse(course);
                System.out.println(course +  " added in " + semester);
                student(webid);
                break;
            case "D":
                System.out.println("Please enter course name:");
                courseName = sc.nextLine().toUpperCase();
                number = Integer.parseInt(courseName.substring(4, courseName.length()));
                courseName = courseName.substring(0,3);
                course = new Course(courseName, number, "S");
                if(s.dropCourse(course)) {
                    System.out.println(course + " dropped.");
                } else {
                    System.out.println("Not enrolled in that course!");
                }
                student(webid);
                break;
            case "C":
//                s.printCourses();
                student(webid);
                break;
            case "S":
//                s.printSortedCourses();
                student(webid);
                break;
            case "L":
                System.out.println(webid + " logged out.");
                printMenu();
                break;
            default:
                System.out.println("Not a valid input!");
                student(webid);
                break;
        }
    }
}
