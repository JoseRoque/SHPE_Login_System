/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shpeloginsystem;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author UTA CSE JJR
 */
public class Main extends Application {
    
    private Stage primaryStage;
    private Student loggedUser; //used to declare user in session
    private final double MINIMUM_WINDOW_WIDTH = 390.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;
    private static ArrayList<Student> students= new ArrayList<>();

    
    @Override
    public void start(Stage stage) throws Exception {
        try {
            primaryStage = stage;
            primaryStage.setTitle("UTA SHPE Login");
            primaryStage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            primaryStage.setMinHeight(MINIMUM_WINDOW_HEIGHT);       
            primaryStage.show();//basic javafx window
            gotoLogin();//go to basic single button login
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void setLoggedUser(String email){
        Student newUser = new Student(email);    
    }
    
    public Student getLoggedUser() {
        return loggedUser;
    }
 
    public ArrayList<Student> getStudents(){
        return students;
    }
           
        
    public boolean userLogging(String email){
        if (Authenticator.validate(students, email)) {
            loggedUser = Student.of(email);
            gotoSignUp();
            return true;
        } else {
            return false;
        }
    }
    
    void userLogout(){
        gotoLogin();
    }
    
    void userSignUp(){
        gotoSignUp();
    }
    
    private void gotoSignUp() {
        try {
            SignUpController profile = (SignUpController) replaceSceneContent("SignUp.fxml");
            profile.setApp(this); //setup  used to acccess the loggedUser
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");
            login.setApp(this); //setup  used to acccess the loggedUser
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(); 
        InputStream in = Main.class.getResourceAsStream(fxml); //access input stream of respective fxml file
        loader.setBuilderFactory(new JavaFXBuilderFactory()); 
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        return (Initializable) loader.getController();
   }
      
}
