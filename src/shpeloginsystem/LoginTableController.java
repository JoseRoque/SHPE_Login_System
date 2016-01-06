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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author JoseRoque
 */
public class LoginTableController extends TableView implements Initializable{

    private Main application;
    private Authenticator connection= new Authenticator();
    private ObservableList<Student> students= FXCollections.observableArrayList();
    
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student,String> firstNameCol;
    @FXML
    private TableColumn<Student,String> lastNameCol;
    @FXML
    private TableColumn<Student,String> emailCol;
    @FXML
    private TableColumn<Student,String> phoneCol;
    @FXML
    private TableColumn<Student,String> majorCol;
    @FXML
    private TableColumn<Student,String> classificationCol;

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
    
}
