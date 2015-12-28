/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shpeloginsystem;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author JoseRoque
 */
public class SqliteConnection {
    
    public static Connection connector(){
        
        try{
            Class.forName("org.sqlite.JDBC");
            Connection myConnection =DriverManager.getConnection("jdbc:sqlite:/Users/JoseRoque/Documents/Student Database/Students.sqlite");
            return myConnection;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
        
    } 
}
