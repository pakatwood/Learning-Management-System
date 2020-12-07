/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.management.system;

/**
 *
 * @author Haider Qazi (Chief Programmer), Daniel Gasperini (Backup Programmer), Javier Blanco (Programmer), David Dinh (Programmer), Christian Francois (Programming Secretary)
 */
public class GPA {
    public String Class_ID, Course_No, Course_Description, Grade, Units;
    
    public GPA(String Class_ID, String Course_No, String Course_Description, String Units, String Grade){
        this.Class_ID = Class_ID;
        this.Course_No = Course_No;
        this.Course_Description = Course_Description;
        this.Units = Units;
        this.Grade = Grade;
    }
       public String getClassID(){
           return Class_ID;
       }
       public String getCourseNo(){
           return Course_No;
       }
       public String getCourseDescription(){
           return Course_Description;
       }
       public String getUnits(){
           return Units;
       }
       public String getGrade(){
           return Grade;
       }
}
