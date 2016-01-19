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
    private Stage popUpStage;

    private Student loggedInStudent=null;
    private boolean studentLoggedIn; //used to declare user in session
    private final double MINIMUM_WINDOW_WIDTH = 750.0;
    private final double MINIMUM_WINDOW_HEIGHT = 500.0;

    //main function sets up entire application including initial stage with scene
    @Override
    public void start(Stage stage) throws Exception {
        try {
            primaryStage = stage;
            primaryStage.setTitle("UTA SHPE Login");
            //primaryStage.setHeight(MINIMUM_WINDOW_HEIGHT);
            //primaryStage.setWidth(MINIMUM_WINDOW_WIDTH);
            //primaryStage.setMinWidth(MINIMUM_WINDOW_WIDTH);
            //primaryStage.setMinHeight(MINIMUM_WINDOW_HEIGHT);       
            primaryStage.show();//basic javafx window
            gotoLoginTable();//go to basic single button login
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
    
    public void setLoggedStudent(Student student){
        this.loggedInStudent = student;    
    }
    public Student getLoggedStudent(){
        return loggedInStudent;
    }
    
    public boolean getStudentLoggedIn() {
        return studentLoggedIn;
    }
    
    public void setStudentLoggedIn(){
        this.studentLoggedIn=true;
    }
    
    public void setStudentLoggedOut(){
        this.studentLoggedIn=false;
    }
    
    void userEditProfile(){
        gotoEditProfile();
    }
    
    void userLogIn(){
        gotoLogin();
    }
    
    void userSignUp(){
        gotoSignUp();
    }
    
    void userLoginTable(){
        gotoLoginTable();
    }
    
    void popUp(){
        gotoPopUp();
    }
    
    void closePopUp(){
        popUpStage.close();
    }
    
   
    private void gotoPopUp(){
        popUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader(); 
        InputStream in = Main.class.getResourceAsStream("PopUp.fxml"); //access input stream of respective fxml file
        loader.setBuilderFactory(new JavaFXBuilderFactory()); 
        loader.setLocation(Main.class.getResource("PopUp.fxml"));
        AnchorPane page=null;
        try {
            page = (AnchorPane)loader.load(in);
            Scene scene = new Scene(page,300,300);
            popUpStage.setScene(scene);
            popUpStage.sizeToScene();
            PopUpController profile = (PopUpController) loader.getController();
            popUpStage.show();
            profile.setApp(this);
            System.out.println("opening pop up");
        } 
        catch(Exception e){
            System.out.println("unable to open pop up");
        }
        finally {
            //in.close();
        }  
    }
    
    private void gotoEditProfile() {
        try {
            System.out.println("going to edit profile");

            EditProfileController profile = (EditProfileController) replaceSceneContent("EditProfile.fxml");
            //setStudentLoggedIn(); //let user session in progress
            profile.setApp(this); //setup  used to acccess the loggedUser
        } catch (Exception ex) {
            System.out.println("unable to go to edit profile");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void gotoSignUp() {
        try {
            System.out.println("going to sign up");
            SignUpController profile = (SignUpController) replaceSceneContent("SignUp.fxml");
            //setStudentLoggedIn(); //let user session in progress
            profile.setApp(this); //setup  used to acccess the loggedUser
        } catch (Exception ex) {
            System.out.println("unable to go to sign up");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void gotoLoginTable() {
        try {
            System.out.println("going to login table");
            LoginTableController profile = (LoginTableController) replaceSceneContent("LoginTable.fxml");
            //setStudentLoggedIn(); //let user session in progress
            profile.setApp(this); //setup  used to acccess the loggedUser
        } catch (Exception ex) {
            System.out.println("unable to go to login table");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void gotoLogin() {
        try {
            System.out.println("going to login");
            LoginController login = (LoginController) replaceSceneContent("Login.fxml");
            login.setApp(this); //setup  used to acccess the loggedUser
        } catch (Exception ex) {
            System.out.println("unable to go to log in");
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
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
            //in.close();
        } 
        Scene scene = new Scene(page,600,400);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        return (Initializable) loader.getController();
   }

       
}
