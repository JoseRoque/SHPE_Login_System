/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shpeloginsystem;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author UTA CSE JJR
 */
public class LoginController extends AnchorPane implements Initializable {
    
    @FXML private Label isConnectedLogin;
    @FXML private Label inDatabase;
    @FXML private TextField emailCheckIn;
    
    private Main application; //tied to main function
    private Authenticator connectionReady = new Authenticator(); //used to perform queries
    
    public void setApp(Main application){
        this.application = application;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(connectionReady.isConnected()){
            isConnectedLogin.setText("Connected"); //label indicates successfulconnection or not
        }
        else{
            isConnectedLogin.setText("Not Connected");
        }
    } 
    
    @FXML
    private void editProfile(ActionEvent event) {
        System.out.println("You clicked edit profile button!");
        try{
            if(connectionReady.inDatabase(emailCheckIn.getText())){
                
                application.setLoggedStudent(connectionReady.retrieveDBInfo(emailCheckIn.getText()));
                //edit users profile
                application.userEditProfile();
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }  
        
    }
    
    @FXML
    private void signUp(ActionEvent event) {
        System.out.println("You clicked sign up button");
        application.userSignUp();
    }
    
    @FXML
    private void checkIn(ActionEvent event){
        try{
            if(connectionReady.inDatabase(emailCheckIn.getText())){
                inDatabase.setText("email is in database");
            }
            else{
                inDatabase.setText("email is not in database");
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }  
    }
    
          
}
