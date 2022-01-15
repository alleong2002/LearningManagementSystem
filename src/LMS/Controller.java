package LMS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private HashMap<String, Student> database = LearningManagementSystem.getDatabase();
    @FXML private TextField username;
    @FXML private TextField password;
    @FXML private Label textlabel;
    @FXML private TextField searchName;
    @FXML private Label displaySearchInformation;
    @FXML private GridPane gridSearchInformation;
    @FXML private GridPane searchGridHeader;
    @FXML private Label enrollStatusMessage;
    @FXML private TextField enrollWebID;
    @FXML private TextField enrollPassword;
    @FXML private TextField enrollCourseWebID;
    @FXML private TextField enrollCourseCode;
    @FXML private Label createInstructorStatusMessage;
    @FXML private Label createCourseStatusMessage;
    @FXML private TextField newCourseCode;
    @FXML private TextField newCourseInstructor;
    @FXML private TextField courseTitle;
    @FXML private TextField createNewInstructor;
    @FXML private TextField newInstructorPassword;
    @FXML private AnchorPane listCoursesAnchorPane;
    @FXML private ScrollPane displayAllCourses;
    @FXML private ScrollPane listStudentsScrollPane;
    @FXML private AnchorPane listStudentsAnchorPane;

    public void checkValidLogin(ActionEvent e) {
        switch(username.getText()) {
            case "r":
                if(password.getText().equals("r")) {
//                    LMS.LearningManagementSystem.registrar();
                    registrarLogIn();
                } else {
                    try {
                        displayInvalidLogin(e);
                    } catch(IOException exception) {
                        exception.printStackTrace();
                    }
                }
                break;
            default:
                if(LearningManagementSystem.validInstructorLogin(username.getText())) {
                    if(LearningManagementSystem.getInstructors().get(username.getText()).getPassword().equals(password.getText())) {
                        goToInstructorPage();
                        return;
                    }
                } else {
                    try {
                        displayInvalidLogin(e);
                        return;
                    } catch(IOException exception) {
                        exception.printStackTrace();
                    }
                }
                if(LearningManagementSystem.validStudentLogin(username.getText())) {
                    if(LearningManagementSystem.getDatabase().get(username.getText()).getPassword().equals(password.getText())) {
                        goToHomePage();
                    }
                } else {
                    try {
                        displayInvalidLogin(e);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                }
        }
    }

    /**
     * Changes the scene to a default student login page.
     */
    public void goToHomePage() {
        GUI g = new GUI();
        try {
            g.changeScene("HomeScreen.fxml");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Changes the scene to a default instructor login page.
     */
    public void goToInstructorPage() {
        GUI g = new GUI();
        try {
            g.changeScene("InstructorHome.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes the scene to the Welcome / Log In Page.
     */
    public void goToLogInScreen() {
        LMS.LearningManagementSystem.choice("X");
        GUI g = new GUI();
        try {
            g.changeScene("LogInScreen.fxml");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Changes the scene to the Registrar Home Page.
     */
    public void registrarLogIn() {
        GUI g = new GUI();
        try {
            g.changeScene("Registrar.fxml");
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void displayInvalidLogin(ActionEvent e) throws IOException {
        System.out.println("Invalid login");
        textlabel.setText("Invalid Login! Try again.");
        textlabel.setMinHeight(50);
        textlabel.setMinWidth(175);
    }

    /**
     * Changes the scene to the Registrar Search page.
     */
    public void goToSearchPage() {
        GUI g = new GUI();
        try {
            g.changeScene("RegistrarSearch.fxml");
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Changes the scene to the Registrar Enrollment page.
     */
    public void goToEnrollPage() {
        GUI g = new GUI();
        try {
            g.changeScene("RegistrarEnroll.fxml");
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Changes the scene to the Registrar Home page.
     */
    public void goToRegistrarHome() {
        GUI g = new GUI();
        try {
            g.changeScene("Registrar.fxml");
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Changes the scene to the Registrar Instructor page.
     */
    public void goToManageInstructors() {
        GUI g = new GUI();
        try {
            g.changeScene("RegistrarInstructors.fxml");
        } catch(IOException exception) {
            exception.printStackTrace();
        }
    }

    public void search() {
        listStudentsScrollPane.setVisible(false);
        gridSearchInformation.setVisible(false);
        searchGridHeader.setVisible(false);
        String student = searchName.getText();
        database = LMS.LearningManagementSystem.getDatabase();
        if(LMS.LearningManagementSystem.getDatabase().containsKey(student)) {
            displaySearchInformation.setText("Displaying Information For " + student);
            gridSearchInformation.setVisible(true);
            searchGridHeader.setVisible(true);
            int rowCount = 0;
            gridSearchInformation.getChildren().clear();
            while(rowCount < 10 && rowCount < LMS.LearningManagementSystem.getDatabase().get(student).getCourses().size()) {
                Label label = new Label(LMS.LearningManagementSystem.getDatabase().get(student).getCourses().get(rowCount).getName());
                label.setFont(Font.font ("Verdana", 12));
                label.setTextFill(Color.WHITE);
                gridSearchInformation.add(label, 0, rowCount);
                label = new Label(LMS.LearningManagementSystem.getDatabase().get(student).getCourses().get(rowCount).toString());
                label.setFont(Font.font ("Verdana", 12));
                label.setTextFill(Color.WHITE);
                gridSearchInformation.add(label, 1, rowCount);
                label = new Label(LMS.LearningManagementSystem.getCourses().get(LMS.LearningManagementSystem.getDatabase().get(student).getCourses().get(rowCount).toString()).getInstructor().toString());
                label.setFont(Font.font ("Verdana", 12));
                label.setTextFill(Color.WHITE);
                gridSearchInformation.add(label, 2, rowCount);
                label = new Label("94");
                label.setFont(Font.font ("Verdana", 12));
                label.setTextFill(Color.WHITE);
                gridSearchInformation.add(label, 3, rowCount);
                rowCount++;
            }
        } else {
            gridSearchInformation.setVisible(false);
            displaySearchInformation.setText("No Search Results Found.");
        }
    }

    public void enrollNewStudent() {
        enrollStatusMessage.setVisible(false);
        if(!enrollWebID.getText().equals("") && !enrollPassword.getText().equals("") && !LMS.LearningManagementSystem.getDatabase().containsKey(enrollWebID.getText())) {
            Student student = new Student(enrollWebID.getText(), enrollPassword.getText());
            LMS.LearningManagementSystem.storeStudentInDatabase(student);
            enrollStatusMessage.setText(enrollWebID.getText() + " successfully enrolled.");
            enrollStatusMessage.setVisible(true);
            enrollWebID.setText("");
            enrollPassword.setText("");
        } else {
            if(enrollWebID.getText().equals("") || enrollPassword.getText().equals("")) {
                enrollStatusMessage.setText("Complete all fields before enrolling!");
            } else {
                enrollStatusMessage.setText("User is already registered in the database!");
            }
            enrollStatusMessage.setVisible(true);
        }
    }

    public void enrollStudentInCourse() {
        enrollStatusMessage.setVisible(true);
        if(!enrollCourseWebID.getText().equals("") && !enrollCourseCode.getText().equals("")) {
            enrollStatusMessage.setVisible(true);
            if(LMS.LearningManagementSystem.getCourses().containsKey(enrollCourseCode.getText())) {
                Course course = LMS.LearningManagementSystem.getCourses().get(enrollCourseCode.getText());
                course.addStudent(database.get(enrollCourseWebID.getText()));
                LMS.LearningManagementSystem.getDatabase().get(enrollCourseWebID.getText()).addCourse(course);
                LMS.LearningManagementSystem.storeCourseInDatabase(LMS.LearningManagementSystem.getCourses().get(enrollCourseCode.getText()));
                LMS.LearningManagementSystem.storeStudentInDatabase(LMS.LearningManagementSystem.getDatabase().get(enrollCourseWebID.getText()));
                enrollCourseWebID.setText("");
                enrollCourseCode.setText("");
                enrollStatusMessage.setText(enrollCourseWebID.getText() + " successfully enrolled in " + enrollCourseCode.getText());
            }
            else {
                if (!LMS.LearningManagementSystem.getCourses().containsKey(enrollCourseCode.getText())) {
                    enrollStatusMessage.setText("Course does not exist!");
                } else {
                    enrollStatusMessage.setText("That WebID is not enrolled as a student.");
                }
            }
        } else {
            enrollStatusMessage.setVisible(true);
            if(enrollCourseWebID.getText().equals("") || enrollCourseWebID.getText().equals("")) {
                enrollStatusMessage.setText("Complete all fields before submitting!");
            }
        }
    }

    /**
     * Method for creating a new Course from the Instructors page of the Registrar screen.
     */
    public void createNewCourse() {
        createCourseStatusMessage.setVisible(false);
        if(newCourseCode.getText().equals("") || courseTitle.getText().equals("") || newCourseInstructor.getText().equals("")) {
            createCourseStatusMessage.setVisible(true);
            createCourseStatusMessage.setText("Please complete all fields before continuing.");
            return;
        }
        if(validCourseCode(newCourseCode.getText()) && LMS.LearningManagementSystem.getInstructors().containsKey(newCourseInstructor.getText()) && !courseTitle.getText().equals("")) { //Every field is valid. Create new course.
            createCourseStatusMessage.setVisible(true);
            Course course = new Course(newCourseCode.getText().substring(0,3), Integer.parseInt(newCourseCode.getText().substring(4)), courseTitle.getText());
            course.setInstructor(LMS.LearningManagementSystem.getInstructors().get(newCourseInstructor.getText()));
            LMS.LearningManagementSystem.storeCourseInDatabase(course);
            newCourseCode.setText("");
            newCourseInstructor.setText("");
            courseTitle.setText("");
            createCourseStatusMessage.setText("New course successfully created.");
        } else {
            createCourseStatusMessage.setVisible(true);
            if(courseTitle.getText().equals("")) {
                createCourseStatusMessage.setText("Please provide a course title to continue.");
            }
            if(!validCourseCode(newCourseCode.getText())) {
                createCourseStatusMessage.setText("Invalid course code.");
            } else {
                createCourseStatusMessage.setText("That instructor does not exist.");
            }
        }
    }

    /**
     * Method to create new instructor (from registrar screen).
     */
    public void createNewInstructor() {
        createInstructorStatusMessage.setVisible(false);
        if(!createNewInstructor.getText().equals("") && !newInstructorPassword.getText().equals("")) {
            createInstructorStatusMessage.setVisible(true);
            if(!LMS.LearningManagementSystem.getInstructors().containsKey(createNewInstructor.getText()) && !database.containsKey(createNewInstructor.getText())) {
                Instructor instructor = new Instructor(createNewInstructor.getText(), newInstructorPassword.getText());
                LMS.LearningManagementSystem.storeInstructorInDatabase(instructor);
                createInstructorStatusMessage.setText("Instructor successfully created!");
                createNewInstructor.setText("");
                newInstructorPassword.setText("");
            } else {
                createInstructorStatusMessage.setText("That WebID already belongs to another user!");
            }
        } else {
            createInstructorStatusMessage.setVisible(true);
            createInstructorStatusMessage.setText("Complete all fields before continuing!");
        }
    }

    /**
     * Checks if the course code is valid. This means the course code follows a 6 digit format: three letters
     * followed by a space and three numbers (i.e. CSE 123).
     * @param s
     *      The course code entered into the text field when registering a new Course.
     * @return
     *      Returns true if the course code is valid.
     *      Returns false otherwise.
     */
    public boolean validCourseCode(String s) {
        int pointer = 0;
        while(pointer<s.length()) {
            if(pointer<=2 && !Character.isLetter(s.charAt(pointer))) {
                return false;
            }
            if(pointer>=4 && !Character.isDigit(s.charAt(pointer))) {
                return false;
            }
            pointer++;
        }
        return true;
    }

    /**
     * Displays all of the courses in the database to the registrar.
     */
    public void listAllCourses() {
        displayAllCourses.setVisible(true);
        listCoursesAnchorPane.getChildren().clear();
        listCoursesAnchorPane.setVisible(true);
        GridPane table = table(LMS.LearningManagementSystem.getCourses().size());
        listCoursesAnchorPane.getChildren().add(table);
        int rowCount = 0;
        int keysIndex = 0;
        ArrayList<String> keys = new ArrayList<String>();
        for(String i : LMS.LearningManagementSystem.getCourses().keySet()) {
            keys.add(i);
        }
        while(keysIndex < LMS.LearningManagementSystem.getCourses().size()) {
            Label label = new Label(LMS.LearningManagementSystem.getCourses().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 0, rowCount);
            Button button = new Button();
            button.setVisible(false);
            table.add(button, 0, rowCount);
            if(keysIndex==LMS.LearningManagementSystem.getCourses().size()) break;

            label = new Label(LMS.LearningManagementSystem.getCourses().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 1, rowCount);
            button = new Button();
            button.setVisible(false);
            table.add(button, 1, rowCount);
            if(keysIndex==LMS.LearningManagementSystem.getCourses().size()) break;

            label = new Label(LMS.LearningManagementSystem.getCourses().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 2, rowCount);
            button = new Button();
            button.setVisible(false);
            table.add(button, 2, rowCount);
            if(keysIndex==LMS.LearningManagementSystem.getCourses().size()) break;

            label = new Label(LMS.LearningManagementSystem.getCourses().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 3, rowCount);
            button = new Button();
            button.setVisible(false);
            table.add(button, 3, rowCount);
            if(keysIndex==LMS.LearningManagementSystem.getCourses().size()) break;

            label = new Label(LMS.LearningManagementSystem.getCourses().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 4, rowCount);
            button = new Button();
            button.setVisible(false);
            table.add(button, 4, rowCount);

            rowCount++;
        }
    }

    /**
     * Displays all instructors in a GridPane.
     */
    public void listAllInstructors() {
        displayAllCourses.setVisible(true);
        listCoursesAnchorPane.getChildren().clear();
        listCoursesAnchorPane.setVisible(true);
        GridPane table = table(LMS.LearningManagementSystem.getCourses().size());
        listCoursesAnchorPane.getChildren().add(table);
        int rowCount = 0;
        int keysIndex = 0;
        ArrayList<String> keys = new ArrayList<String>();
        for(String i : LMS.LearningManagementSystem.getInstructors().keySet()) {
            keys.add(i);
        }
        while(keysIndex < LMS.LearningManagementSystem.getInstructors().size()) {
            Label label = new Label(LMS.LearningManagementSystem.getInstructors().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 0, rowCount);
            if(keysIndex==LMS.LearningManagementSystem.getInstructors().size()) break;
            label = new Label(LMS.LearningManagementSystem.getInstructors().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 1, rowCount);
            if(keysIndex==LMS.LearningManagementSystem.getInstructors().size()) break;
            label = new Label(LMS.LearningManagementSystem.getInstructors().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 2, rowCount);
            if(keysIndex==LMS.LearningManagementSystem.getInstructors().size()) break;
            label = new Label(LMS.LearningManagementSystem.getInstructors().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 3, rowCount);
            if(keysIndex==LMS.LearningManagementSystem.getInstructors().size()) break;
            label = new Label(LMS.LearningManagementSystem.getInstructors().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 4, rowCount);

            rowCount++;
            return;
        }
    }

    /**
     * Displays all Students in a Gridpane.
     */
    public void listAllStudents() {
        displaySearchInformation.setText("Listing all students in the database!");
        gridSearchInformation.setVisible(false);
        searchGridHeader.setVisible(false);
        listStudentsScrollPane.setVisible(true);
        listStudentsAnchorPane.getChildren().clear();
        listStudentsAnchorPane.setVisible(true);
        GridPane table = table(LMS.LearningManagementSystem.getDatabase().size());
        listStudentsAnchorPane.getChildren().add(table);
        int rowCount = 0;
        int keysIndex = 0;
        ArrayList<String> keys = new ArrayList<String>();
        for(String i : LMS.LearningManagementSystem.getDatabase().keySet()) {
            keys.add(i);
        }
        while(keysIndex < LMS.LearningManagementSystem.getDatabase().size()) {
            Label label = new Label(LMS.LearningManagementSystem.getDatabase().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 0, rowCount);
            if(keysIndex==LMS.LearningManagementSystem.getDatabase().size()) break;
            label = new Label(LMS.LearningManagementSystem.getDatabase().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 1, rowCount);
            if(keysIndex==LMS.LearningManagementSystem.getDatabase().size()) break;
            label = new Label(LMS.LearningManagementSystem.getDatabase().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 2, rowCount);
            if(keysIndex==LMS.LearningManagementSystem.getDatabase().size()) break;
            label = new Label(LMS.LearningManagementSystem.getDatabase().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 3, rowCount);
            if(keysIndex==LMS.LearningManagementSystem.getDatabase().size()) break;
            label = new Label(LMS.LearningManagementSystem.getDatabase().get(keys.get(keysIndex++)).toString());
            label.setFont(Font.font ("Verdana", 12));
            label.setTextFill(Color.WHITE);
            table.add(label, 4, rowCount);

            rowCount++;
        }
    }

    /**
     * Used to dynamically adjust the size of a GridPane. Adds rows as necessary; there are 5 columns.
     */
    public GridPane table(int rows){
        GridPane table = new GridPane();

        for(int i=0; i<rows; i++){
            Label labelOne = new Label();
            labelOne.setVisible(false);
            Label labelTwo = new Label();
            labelTwo.setVisible(false);
            Label labelThree = new Label();
            labelThree.setVisible(false);
            Label labelFour = new Label();
            labelFour.setVisible(false);
            Label labelFive = new Label();
            labelFive.setVisible(false);

            table.add(labelOne, 0, i);
            table.add(labelTwo , 1, i);
            table.add(labelThree,2, i);
            table.add(labelFour, 3, i);
            table.add(labelFive, 4, i);

            table.setHgap(95);
            table.setVgap(15);
        }


        return table;
    }
}
