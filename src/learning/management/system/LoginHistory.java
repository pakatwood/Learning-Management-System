/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.management.system;

/**
 *
 * @author Haider Qazi
 */
public class LoginHistory {
    public String Student_ID, Student_Name, Logged_In, Logged_Out, Date, IP_Address;
    
    public LoginHistory(String Student_ID, String Student_Name, String Logged_In, String Logged_Out, String Date, String IP_Address){
        this.Student_ID = Student_ID;
        this.Student_Name = Student_Name;
        this.Logged_In = Logged_In;
        this.Logged_Out = Logged_Out;
        this.Date = Date;
        this.IP_Address = IP_Address;
    }
       public String getStudentID() {
           return Student_ID;
       }
       public String getStudentName(){
           return Student_Name;
       }
       public String getLoggedIn(){
           return Logged_In;
       }
       public String getLoggedOut(){
           return Logged_Out;
       }
       public String getDate(){
           return Date;
       }
       public String getIPAddress(){
           return IP_Address;
       }
}
