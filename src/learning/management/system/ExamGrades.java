/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.management.system;

/**
 *
 * @author Haider Qazi, Daniel Gasperini, Javier Blanco, David Dinh, Christian Francois
 */
public class ExamGrades {
    public String Exam_Name, Exam_Weight, Score_Recieved;
    
    public ExamGrades(String Exam_Name, String Exam_Weight, String Score_Recieved){
        this.Exam_Name = Exam_Name;
        this.Exam_Weight = Exam_Weight;
        this.Score_Recieved = Score_Recieved;
    }
       public String getExamName(){
           return Exam_Name;
       }
       public String getExamWeight(){
           return Exam_Weight;
       }
       public String getScoreReceived(){
           return Score_Recieved;
       }
}
