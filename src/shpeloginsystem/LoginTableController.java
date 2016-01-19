/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shpeloginsystem;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import sun.awt.image.ImageWatched.Link;

/**
 * FXML Controller class
 *
 * @author JoseRoque
 */
public class LoginTableController extends TableView implements Initializable{

    private Main application;
    private Authenticator connection= new Authenticator();
    private ObservableList<Student> students= FXCollections.observableArrayList();
    private int rowIndex=-1;
    
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student,String> firstNameCol;
    @FXML private TableColumn<Student,String> lastNameCol;
    @FXML private TableColumn<Student,String> emailCol;
    @FXML private TableColumn<Student,String> phoneCol;
    @FXML private TableColumn<Student,String> majorCol;
    @FXML private TableColumn<Student,String> classificationCol;
    @FXML private Button signUpButton;
    @FXML private Button editButton;
    @FXML private Button signInButton;
    @FXML private Label messageLabel;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }  
    
    public void setApp(Main application){
        this.application = application;
        students = connection.retrieveAllData();
        setupTable(students);

    }
    /**
     * Sets ups up columns of TableView with respective data
     * @param students 
     */
    public void setupTable(ObservableList<Student> students){
        
        firstNameCol.setCellValueFactory( new PropertyValueFactory<Student,String>("firstName") );
        lastNameCol.setCellValueFactory( new PropertyValueFactory<Student,String>("lastName") );
        emailCol.setCellValueFactory( new PropertyValueFactory<Student,String>("email") );
        phoneCol.setCellValueFactory( new PropertyValueFactory<Student,String>("phone") );
        majorCol.setCellValueFactory( new PropertyValueFactory<Student,String>("major") );
        classificationCol.setCellValueFactory( new PropertyValueFactory<Student,String>("classification") );
        studentTable.setItems(students);
        return;
    }
    
    @FXML
    private void goToSignUp(ActionEvent event){
        application.userSignUp();   
    }
    
    @FXML
    private void goToSignIn(ActionEvent event){
        //TODO increment count for point system
        if(rowIndex != -1){
            //display pop up window for confirmation
            application.setLoggedStudent(students.get(rowIndex));
            application.popUp();
        }
        else{
            messageLabel.setTextFill(Color.RED);   
        } 
    }
    
    
    @FXML
    private void goToEdit(ActionEvent event){
        if(rowIndex != -1){
           application.setLoggedStudent(students.get(rowIndex));
           application.userEditProfile();   
        }
        else{
            messageLabel.setTextFill(Color.RED);   
        } 
    }
    
    @FXML
    private void tableRowSelected(){
        
        rowIndex = studentTable.getSelectionModel().getSelectedIndex();
        System.out.println("Clicked on row:"+ rowIndex );
        if(rowIndex != -1){
            System.out.println(students.get(rowIndex).getEmail());
        }
    }
    
    
}
