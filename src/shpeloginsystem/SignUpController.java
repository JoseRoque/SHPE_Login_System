/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shpeloginsystem;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

//import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author UTA CSE JJR
 */
public class SignUpController extends AnchorPane implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private Label isConnectedSignUp;
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField major;
    @FXML private ComboBox classification;
    @FXML private Hyperlink logout;
    
    private Main application;
    private Authenticator connectionReady = new Authenticator(); //used to perform queries

    public void setApp(Main application){
        this.application = application;
        initSignUp();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(connectionReady.isConnected()){
            isConnectedSignUp.setText("Connected"); //label indicates successfulconnection or not
        }
        else{
            isConnectedSignUp.setText("Not Connected");
        }
    } 
    
    @FXML
    public void processLogIn(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }    
        application.userLogIn();
    }
    
    @FXML
    private void saveInfo(ActionEvent event) throws SQLException {
        System.out.println("clicked the save info button");
        //if valid email address is entered and the email is not already in database
        if(Authenticator.validEmail(email.getText()) && !(connectionReady.inDatabase(email.getText())) ){
            //if valid email address
            System.out.println("The email in save info is: " + email.getText());
            Student student = new Student(firstName.getText(),lastName.getText(),email.getText(),phone.getText(),major.getText(),classification.getValue().toString());
            isConnectedSignUp.setText("Valid email");
            connectionReady.saveUserInfo(student);   
        }
        else{    
            isConnectedSignUp.setText("Invalid email");
        }
        application.userLoginTable();
    }
    
    //if reset button is pressed
    /**
     * Resets the information present in the textfields of signup scene
     * @param event 
     */
    @FXML
    private void resetInfo(ActionEvent event){
        System.out.println("clicked the reset info button");

        if (application == null){
            System.out.println("application is null");
            return;
        }  
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        phone.setText("");
        major.setText("");
        classification.setValue(classification.getItems().get(0));
    } 
    
    @FXML
    private void cancelSignUp(){
        application.userLoginTable();
    }
    
    private void initSignUp(){
        firstName.setText("");
        lastName.setText("");
        email.setText("");
        phone.setText("");
        major.setText("");
        classification.setValue(classification.getItems().get(0));
    }

}
