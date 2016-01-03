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
    private Label isConnectedSignUp;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML 
    private TextField major;
    @FXML 
    private ComboBox classification;
    @FXML
    private Hyperlink logout;
    
    private Main application;
    
    private Authenticator connectionReady = new Authenticator(); //used to perform queries

    
    public void setApp(Main application){
        this.application = application;
        //User loggedStudent = application.getLoggedStudent();
        //user.setText(loggedUser.getId());
        //email.setText(loggedUser.getEmail());
        //phone.setText(loggedUser.getPhone());
        //if (loggedUser.getAddress() != null) {
        //    address.setText(loggedUser.getAddress());
        //}
        //success.setOpacity(0);
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
    public void processLogout(ActionEvent event) {
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }
        
        application.userLogout();
    }
    
    @FXML
    private void saveInfo(ActionEvent event) {
        /**
        public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
}
        **/ 
        
        /**
        if (application == null){
            // We are running in isolated FXML, possibly in Scene Builder.
            // NO-OP.
            return;
        }

        if(!(Authenticator.validate(application.getStudents(),email.getText()))){
        
            Student loggedUser = new Student();
            loggedUser.setFirstName(firstName.getText());
            loggedUser.setLastName(lastName.getText());
            loggedUser.setEmail(email.getText());
            loggedUser.setPhone(phone.getText());
            loggedUser.setMajor(major.getText());
            loggedUser.setClassification(classification.getValue().toString());
        }
        else{
            System.out.println("That student id/email already taken");
        }
        **/
        
        System.out.println("clicked the save info button");
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
        
        firstName.setText(" ");
        lastName.setText(" ");
        email.setText(" ");
        phone.setText(" ");
        major.setText(" ");
        classification.setValue(classification.getItems().get(0));
    } 
    
    @FXML
    public void retrieveUserInfo(){
     //retirves the data stored for a particualr user from database   
        
    
    }
    
 
}
