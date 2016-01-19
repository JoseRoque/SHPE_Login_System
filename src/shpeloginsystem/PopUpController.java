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
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JoseRoque
 */
public class PopUpController extends AnchorPane implements Initializable {

    
    @FXML private Button yesButton;
    @FXML private Button noButton;
    @FXML private Label emailLabel;
    private Main application;
    private Authenticator authenticator = new Authenticator();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setApp(Main application){
        this.application = application;
        if(! (authenticator.isConnected())){
            emailLabel.setText("Connected");
        }
        else{
            emailLabel.setText(application.getLoggedStudent().getEmail());
        }
    }
    @FXML 
    private void yesButtonClicked(ActionEvent event) throws SQLException{
        //confirm login and increment point for respective user
        System.out.println("yes clicked");
        authenticator.incrementPoints(application.getLoggedStudent().getEmail());
        //perhaps open a box indicating login successful
        //go back to login table
        application.closePopUp();
        application.userLoginTable();     
    }
    
    @FXML 
    private void noButtonClicked(ActionEvent event){
        //go back to loginTable
        System.out.println("no clicked");
        application.closePopUp();
        application.userLoginTable();     
    }
}
