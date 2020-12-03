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
public class StudentRegisteredCourses {
    public String Class_ID, Course_No, Course_Description, Room_No, Instructor_Name, Days_Time, Start_Date, End_Date;
    
    public StudentRegisteredCourses(String Class_ID, String Course_No, String Course_Description, String Room_No, String Instructor_Name, String Days_Time, String Start_Date, String End_Date){
        this.Class_ID = Class_ID;
        this.Course_No = Course_No;
        this.Course_Description = Course_Description;
        this.Room_No = Room_No;
        this.Instructor_Name = Instructor_Name;
        this.Start_Date = Start_Date;
        this.End_Date = End_Date;
        this.Days_Time = Days_Time;
        //this.Units = Units;
    }
       public String getClassID() {
           return Class_ID;
       }
       public String getCourseNo(){
           return Course_No;
       }
       public String getCourseDescription(){
           return Course_Description;
       }
       public String getRoomNo(){
           return Room_No;
       }
       public String getInstructorName(){
           return Instructor_Name;
       }
       public String getDaysTime() {
           return Days_Time;
       }
       public String getStartDate(){
           return Start_Date;
       }
       public String getEndDate(){
           return End_Date;
       }
       //public String getUnits(){
       //    return Units;
       //}
}