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

public class Authenticator {
    
    //foloowing variables are of those fetched from SQLite query
    private static String email ;
    private static String firstName;
    private static String lastName;
    private static String phone;
    private static String major;
    private static String classification;
    private static Student loggedInStudent;
    
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
    
    ////////////////////////////////////////////////////////////////////////////////////
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
    
    
    
         
   ///////////////////////////////////////////////////////////////////////////////////// 
}