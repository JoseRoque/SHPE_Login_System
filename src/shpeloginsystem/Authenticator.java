/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shpeloginsystem;

/**
 *
 * @author UTA CSE JJR
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Authenticator {
    
    //foloowing variables are of those fetched from SQLite query
    private static String email ;
    private static String firstName;
    private static String lastName;
    private static String phone;
    private static String major;
    private static String classification;
    private static Student loggedInStudent;
    
    //regular expression checking for @ sign in email address
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]", Pattern.CASE_INSENSITIVE);

    private Connection connection; //used to verify connectivity to database when authenticating

    //key is student email
    public Authenticator(){
        connection = SqliteConnection.connector();
        if (connection == null){
            System.exit(1);
        }   
    }
    
    public boolean isConnected(){   
        try{
            //connection is still opened
            return !connection.isClosed();
        }
        catch(SQLException e){
            //connection is closed
            e.printStackTrace();
            return false;
        }     
    }
 
    /**
     * Checks whether some student email has been 
     * registered or not
     **/
    public boolean inDatabase(String email) throws SQLException{
    //the email is a student's unique identifier (KEY)
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query= "select * from student where email = ?";
        
        try{  
            preparedStatement = connection.prepareStatement(query); //takes query string arg
            //1st arg is for email at 1st index
            preparedStatement.setString(1, email); 
            resultSet = preparedStatement.executeQuery();
            //if we have more than one result
            if(resultSet.next()){
                //return all user data
                
                System.out.println(resultSet.getString("first_name"));
                System.out.println(resultSet.getString("last_name"));
                System.out.println(resultSet.getString("phone"));
                System.out.println(resultSet.getString("email"));
                System.out.println(resultSet.getString("major"));
                System.out.println(resultSet.getString("classification"));
                
                return true;
            }
            else{
                //return null
                return false;
            }
        }
        catch(Exception e){
          e.printStackTrace();
          return false;
        }
        finally{
            preparedStatement.close();
            resultSet.close();
        }
    }
    
    public Student retrieveDBInfo(String email) throws SQLException{
    //the email is a student's unique identifier (KEY)
        
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String query= "select * from student where email = ?";
        
        try{  
            preparedStatement = connection.prepareStatement(query); //takes query string arg
            //1st arg is for email at 1st index
            preparedStatement.setString(1, email); 
            resultSet = preparedStatement.executeQuery();
            //if we have more than one result
            if(resultSet.next()){
                
                email = resultSet.getString("email");
                firstName = resultSet.getString("first_name");
                lastName = resultSet.getString("last_name");
                phone = resultSet.getString("phone");
                major = resultSet.getString("major");
                classification=resultSet.getString("classification");
                loggedInStudent = new Student(firstName, lastName, email, phone, major , classification);
                return loggedInStudent;
            }
            else{
                return null;
            }
        }
        catch(Exception e){
          e.printStackTrace();
          return null;
        }
        finally{
            preparedStatement.close();
            resultSet.close();
        }
    }
    
    public void saveUserInfo(Student student) throws SQLException{
        PreparedStatement preparedStatement = null;
        boolean result = false;
        String query= "insert or ignore into student "+ 
        "(email,first_name,last_name,phone,major,classification) "+
        "values (?,?,?,?,?,?);";
                
        System.out.println(query);
        System.out.println(student.getEmail());
        
        try{  
            preparedStatement = connection.prepareStatement(query); //takes query string arg
            preparedStatement.setString(1, student.getEmail());
            preparedStatement.setString(2, student.getFirstName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setString(4, student.getPhone());
            preparedStatement.setString(5, student.getMajor());
            preparedStatement.setString(6, student.getClassification());

            result = preparedStatement.execute();
            //if we have more than one result
            if(result == true){
                System.out.println("Save Successful");
            }
            preparedStatement.close();
        }
        catch(Exception e){
          e.printStackTrace();
          System.out.println("Save Unsuccessful");
        }
    }
    
    public void updateUserInfo(Student student) throws SQLException{
        PreparedStatement preparedStatement = null;
        boolean result = false;
        /**String query= "UPDATE student SET "
                + "first name ="+ student.getFirstName() + "," 
                + "last_name = " + student.getLastName() + "," 
                + "phone =" + student.getPhone() + ","
                + "email=" + student.getEmail()+ ","
                + "major=" + student.getMajor() + ","
                + "classification=" + student.getClassification()
                + " WHERE email ="+ student.getEmail() + ";";
        **/
        String query= "UPDATE student SET first_name =?,last_name =?,phone =?,major=?, classification=? WHERE email = ?;";
        
        System.out.println(query);
        System.out.println(student.getEmail());
        
        try{  
            preparedStatement = connection.prepareStatement(query); //takes query string arg
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getPhone());
            preparedStatement.setString(4, student.getMajor());
            preparedStatement.setString(5, student.getClassification());
            preparedStatement.setString(6, student.getEmail());


            result = preparedStatement.execute();
            //if we have more than one result
            if(result == true){
                System.out.println("Update Successful");
            }
            preparedStatement.close();
        }
        catch(Exception e){
          e.printStackTrace();
          System.out.println("Save Unsuccessful");
        }
    }
     
    public static boolean validEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }
    
    public  ObservableList<Student> retrieveAllData(){
        ObservableList<Student> students = FXCollections.observableArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query= "select * from student";//retrieve all data
        
        try{  
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                String major = resultSet.getString("major");
                String classification = resultSet.getString("classification");
                
                Student newStudent = new Student(firstName,  lastName,  email,  phone,  major,  classification);
                students.add(newStudent);
                
                System.out.println(firstName);
            }  
            
            preparedStatement.close();
        }
        catch(Exception e){
          e.printStackTrace();
          System.out.println("Retrieval of all data was unsuccessful");
        }
        return students;
    }
    
           
}