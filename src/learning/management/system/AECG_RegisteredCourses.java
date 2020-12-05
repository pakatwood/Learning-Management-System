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
public class AECG_RegisteredCourses {
    public String Class_ID, Course_No, Course_Description, Instructor_Name;
    
    public AECG_RegisteredCourses(String Class_ID, String Course_No, String Course_Description, String Instructor_Name){
        this.Class_ID = Class_ID;
        this.Course_No = Course_No;
        this.Course_Description = Course_Description;
        this.Instructor_Name = Instructor_Name;
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
       public String getInstructorName(){
           return Instructor_Name;
       }
    
}
