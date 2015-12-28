/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shpeloginsystem;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author UTA CSE JJR
 */
public class Student {

    private static final Map<String, Student> USERS = new HashMap<String, Student>();
    private String firstName="";
    private String lastName ="";
    private String email = "";
    private String phone = "";
    private String major="";
    private String classification="";
           
    public Student( String email){
        this.email=email;  
    }
    
    public Student(){
        
        //default construcctor
    }
    
   
    //used to retrive data of particular user
    public static Student of(String email) {
        Student user = USERS.get(email);
        if (user == null) { //if there is no one with that id add that user to the list
            user = new Student(email);
            USERS.put(email, user);//create new user??
        }
        return user;
    }
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
    
}
