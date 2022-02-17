package LMS;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
import javafx.stage.Stage;
;import java.io.IOException;

public class GUI extends Application {
    private static Stage stage;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            LMS.LearningManagementSystem.loadDatabase();
            stage = primaryStage;
            Parent root = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image("file:Icon.PNG");
            primaryStage.getIcons().add(icon);
            primaryStage.setTitle("Learning Management System");
            primaryStage.setResizable(false);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeScene(String s) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(s));
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        stage.getScene().setRoot(pane);
    }

//    public void changeToRegistrar(String s) throws IOException {
//        Parent pane = FXMLLoader.load(getClass().getResource(s));
//        Scene scene = new Scene(pane);
//        stage.getScene().setRoot(pane);
//        stage.setScene(scene);
//        stage.show();
//        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//    }
}
