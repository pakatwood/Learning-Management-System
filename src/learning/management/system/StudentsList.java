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
public class StudentsList {
    public String Student_ID, First_Name, Last_Name, Student_Status, Residency_Status, Class_Standing, Location, Major, Phone_Number, Email_Address;
    
    public StudentsList(String Student_ID, String First_Name, String Last_Name, String Student_Status, String Residency_Status, String Class_Standing, String Location, String Major, String Phone_Number, String Email_Address){
        this.Student_ID = Student_ID;
        this.First_Name = First_Name;
        this.Last_Name = Last_Name;
        this.Student_Status = Student_Status;
        this.Residency_Status = Residency_Status;
        this.Class_Standing = Class_Standing;
        this.Location = Location;
        this.Major = Major;
        this.Phone_Number = Phone_Number;
        this.Email_Address = Email_Address;
    }

       public String getStudentID(){
           return Student_ID;
       }
       public String getFirstName(){
           return First_Name;
       }
       public String getLastName(){
           return Last_Name;
       }
       public String getStudentStatus(){
           return Student_Status;
       }
       public String getResidencyStatus(){
           return Residency_Status;
       }
       public String getClassStanding(){
           return Class_Standing;
       }
       public String getLocation(){
           return Location;
       }
       public String getMajor(){
           return Major;
       }
       public String getPhoneNumber(){
           return Phone_Number;
       }
       public String getEmailAddress(){
           return Email_Address;
       }
}
