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

    private String firstName="";
    private String lastName ="";
    private String email = "";
    private String phone = "";
    private String major="";
    private String classification="";
              
    public Student(String firstName, String lastName, String email, String phone, String major, String classification){
        this.firstName= firstName;
        this.lastName = lastName;
        this.email= email;
        this.phone=phone;
        this.major=major;
        this.classification= classification;
    }
    
    public Student(String firstName, String lastName, String email, String phone, String major){
        this(firstName,lastName, email,phone,major, null);   
    }
    
    public Student(String firstName, String lastName, String email, String phone){
        this(firstName,lastName, email,phone,null, null);   
    }
    
    public Student(String firstName, String lastName, String email){
        this(firstName,lastName, email,null,null, null);   
    }
    
    public Student(String firstName, String email){
        this(firstName,null, email,null,null, null);   
    }
    
    public Student( String email){
        this(null,null, email,null,null, null);   
    }
    
    public Student(){
        //default construcctor
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
