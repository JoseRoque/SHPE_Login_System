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
import java.sql.SQLException;

public class Authenticator {
    
    private static String storedStudentID;
    private static String storedStudentEmail;
    
    private Connection connection; //used to verify connectivity to database when authenticating

    //key value pairs are studentID and student email
    public Authenticator(){
        connection = SqliteConnection.connector();
        if (connection == null) System.exit(1);
        
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

    public static boolean validate(ArrayList<Student> students, String email){
        //authentication is based upon a student's email since it is unique
        for(Student user:students){
            storedStudentEmail=user.getEmail();
            if(storedStudentEmail.equals(email) ){
                return true;
            }
        }   
            return false; //if email not on record return false    
    }
    
}