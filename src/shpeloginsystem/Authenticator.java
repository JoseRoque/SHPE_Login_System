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

public class Authenticator {
    
    private static String storedStudentID;
    private static String storedStudentEmail;

    //key value pairs are studentID and student email
    public Authenticator(){
        
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