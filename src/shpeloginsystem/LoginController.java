/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shpeloginsystem;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author UTA CSE JJR
 */
public class LoginController extends AnchorPane implements Initializable {
    
    @FXML
    private Label isConnected;
    private Main application;
    private Authenticator connectionReady = new Authenticator();
    
    public void setApp(Main application){
        this.application = application;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(connectionReady.isConnected()){
            isConnected.setText("Connected"); //label indicates successfulconnection or not
        }
        else{
            isConnected.setText("Not Connected");
        }
    } 
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        application.userSignUp();
    }
        
      
}
