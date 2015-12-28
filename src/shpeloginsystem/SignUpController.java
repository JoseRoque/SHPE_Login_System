/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shpeloginsystem;

import java.net.URL;
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
    @FXML
    private TextField textField_FirstName;
    @FXML
    private TextField textField_LastName;
    @FXML
    private TextField textField_Email;
    @FXML
    private TextField textField_Phone;
    @FXML
    private TextField textField_StudentID;
    @FXML 
    private TextField textField_Major;
    @FXML 
    private ComboBox comboBox_Classification;
    @FXML
    private Button button_Save;
    @FXML
    private Button button_Reset;
    @FXML
    private Hyperlink logout;
    private Main application;
    
    public void setApp(Main application){
        this.application = application;
        //User loggedUser = application.getLoggedUser();
        //user.setText(loggedUser.getId());
        //email.setText(loggedUser.getEmail());
        //phone.setText(loggedUser.getPhone());
        //if (loggedUser.getAddress() != null) {
        //    address.setText(loggedUser.getAddress());
        //}
        //success.setOpacity(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    @FXML
    public void processLogout(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }
        
        application.userLogout();
    }
    
    @FXML
    public void saveInfo(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }

        if(!(Authenticator.validate(application.getStudents(),textField_Email.getText()))){
        
            Student loggedUser = new Student();
            loggedUser.setFirstName(textField_FirstName.getText());
            loggedUser.setLastName(textField_LastName.getText());
            loggedUser.setEmail(textField_Email.getText());
            loggedUser.setPhone(textField_Phone.getText());
            loggedUser.setMajor(textField_Major.getText());
            loggedUser.setClassification(comboBox_Classification.getValue().toString());
        }
        else{
            System.out.println("That student id/email already taken");
        }
    }
    //if reset button is pressed
    
    @FXML
    public void resetInfo(ActionEvent event){
        if (application == null){
            return;
        }
        textField_FirstName.setText(" ");
        textField_LastName.setText(" ");
        textField_Email.setText(" ");
        textField_Phone.setText(" ");
        textField_Major.setText(" ");
        comboBox_Classification.setValue(" ");
    }
    
}
