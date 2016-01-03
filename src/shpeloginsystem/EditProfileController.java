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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author JoseRoque
 */
public class EditProfileController extends AnchorPane implements Initializable {

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
    private Label isConnectedEdit;
    private Main application;
    private Authenticator connectionReady = new Authenticator();
    
    private Student studentLoggedIn=null;
  
    
    public void setApp(Main application){
        this.application = application;
        //initialize called before setup
        retrieveUserInfo();
           
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(connectionReady.isConnected()){
            isConnectedEdit.setText("Connected"); //label indicates successfulconnection or not
        }
        else{
            isConnectedEdit.setText("Not Connected");
        }
          
    } 

    @FXML
    private void updateInfo(ActionEvent event) {
        /**
        public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

        public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
        }
        **/
        
        System.out.println("clicked the update info button");
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
    private void retrieveUserInfo(){
        studentLoggedIn = application.getLoggedStudent();
       
        firstName.setText(studentLoggedIn.getFirstName());
        lastName.setText(studentLoggedIn.getLastName());
        email.setText(studentLoggedIn.getEmail());
        phone.setText(studentLoggedIn.getPhone());
        major.setText(studentLoggedIn.getMajor());
        //OMITTED
        //classification.setValue(studentLoggedIn.getClassification());
    }
}
