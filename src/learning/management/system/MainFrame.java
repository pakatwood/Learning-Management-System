/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.management.system;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Haider Qazi, Daniel Gasperini, Javier Blanco, David Dinh, Christian Francois test test (DAVID WAS HERE)
 * Test
 * 
 */
public class MainFrame extends javax.swing.JFrame {
    int mouseposX;
    int mouseposY;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    DefaultTableModel MC_CC_Table;
    DefaultTableModel MS_CS_Table;
    DefaultTableModel AC_CS_Table;
    DefaultTableModel AC_CC_Table;
    String Student_ID = LoginFrame.Current_Student_ID;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        SP_GPA_Table.getTableHeader().setForeground(new Color(0,41,85));
        TableColumnSize();
        tableColumnAlignment();
        HomeTile();
        MP_Class_Schedule_Table();
        Admin_User_Check();
        MC_CC_Table = (DefaultTableModel) MC_Current_Courses_Table.getModel();
        MS_CS_Table = (DefaultTableModel) MS_Current_Students_Table.getModel();
        AC_CS_Table = (DefaultTableModel) SC_Current_Students_Table.getModel();
        AC_CC_Table = (DefaultTableModel) SC_Current_Courses_Table.getModel();
    }
    
    // View Logout Frame
    LogoutFrame ViewLogoutFrame = new LogoutFrame();
    
    // User Check Admin or Student
    private void Admin_User_Check(){
        if (LoginFrame.User_Admin == true){
            Student_Main_Page_Panel.hide();
            Student_SRE_Panel.hide();
            Admin_MC_Panel.hide();
            Admin_MS_Panel.hide();
            Admin_SC_Panel.hide();
            Admin_AECG_Panel.hide();
            SC_Registered_Courses_Panel.hide();
            Admin_Main_Page_Panel.show();
            MC_Show_Courses();
            MS_Show_Students();
            SR_Show_Students();
            AC_Show_Courses();
        }
        else
        {
            Admin_Main_Page_Panel.hide();
            Student_SRE_Panel.hide();
            Admin_MC_Panel.hide();
            Admin_MS_Panel.hide();
            Admin_SC_Panel.hide();
            Admin_AECG_Panel.hide();
            Student_Main_Page_Panel.show();
            Show_RegisteredCourses();
            StudentCoursesComboBox();
            Show_GPA_Table();
        }
    }
    // Student - Class Schedule Table in Main Panel
    private void MP_Class_Schedule_Table(){
        MP_Class_Schedule_Table.getTableHeader().setFont(new Font("Yu Gothic UI Light", Font.BOLD, 12));
        MP_Class_Schedule_Table.getTableHeader().setOpaque(false);
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(0,0,0));
        for (int i = 0; i < MP_Class_Schedule_Table.getModel().getColumnCount(); i++) {
        MP_Class_Schedule_Table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }
        MP_Class_Schedule_Table.getTableHeader().setForeground(new Color(255,255,255));
        MP_Class_Schedule_Table.setRowHeight(40);
        Class_Schedule_ScrollPane.setBorder( null );
        MP_Class_Schedule_Table.setTableHeader(null);
    }
    // Table Column Sizes
    private void TableColumnSize(){
        // Student - GPA Table Column Size
        SP_GPA_Table.getColumnModel().getColumn(0).setPreferredWidth(50);
        SP_GPA_Table.getColumnModel().getColumn(1).setPreferredWidth(125);
        SP_GPA_Table.getColumnModel().getColumn(2).setPreferredWidth(10);
        SP_GPA_Table.getColumnModel().getColumn(3).setPreferredWidth(10);
        SP_GPA_Table.getColumnModel().getColumn(4).setPreferredWidth(15);
        // Student - Registered Courses Table Column Size
        SP_Registered_Courses_Table.getColumnModel().getColumn(0).setPreferredWidth(40);
        SP_Registered_Courses_Table.getColumnModel().getColumn(1).setPreferredWidth(130);
        SP_Registered_Courses_Table.getColumnModel().getColumn(2).setPreferredWidth(0);
        SP_Registered_Courses_Table.getColumnModel().getColumn(3).setPreferredWidth(85);
        SP_Registered_Courses_Table.getColumnModel().getColumn(4).setPreferredWidth(50);
        SP_Registered_Courses_Table.getColumnModel().getColumn(5).setPreferredWidth(40);
        // Student - Class Schedule Table Column Size
        MP_Class_Schedule_Table.getColumnModel().getColumn(0).setPreferredWidth(35);
        MP_Class_Schedule_Table.getColumnModel().getColumn(1).setPreferredWidth(10);
        MP_Class_Schedule_Table.getColumnModel().getColumn(2).setPreferredWidth(110);
        // Student - Exam Grade Table Column Size
        SP_Exam_Grades_Table.getColumnModel().getColumn(0).setPreferredWidth(20);
        SP_Exam_Grades_Table.getColumnModel().getColumn(1).setPreferredWidth(10);
        SP_Exam_Grades_Table.getColumnModel().getColumn(2).setPreferredWidth(70);
        SP_Exam_Grades_Table.getColumnModel().getColumn(3).setPreferredWidth(60);
        // Admin - Courses Table Column Size in Manage Courses Panel
        MC_Current_Courses_Table.getColumnModel().getColumn(0).setPreferredWidth(20);
        MC_Current_Courses_Table.getColumnModel().getColumn(1).setPreferredWidth(30);
        MC_Current_Courses_Table.getColumnModel().getColumn(2).setPreferredWidth(100);
        MC_Current_Courses_Table.getColumnModel().getColumn(3).setPreferredWidth(20);
        MC_Current_Courses_Table.getColumnModel().getColumn(4).setPreferredWidth(80);
        MC_Current_Courses_Table.getColumnModel().getColumn(5).setPreferredWidth(100);
        MC_Current_Courses_Table.getColumnModel().getColumn(6).setPreferredWidth(25);
        MC_Current_Courses_Table.getColumnModel().getColumn(7).setPreferredWidth(20);
        // Admin - Courses Table Column Size in Assign Courses Panel
        SC_Current_Courses_Table.getColumnModel().getColumn(0).setPreferredWidth(25);
        SC_Current_Courses_Table.getColumnModel().getColumn(1).setPreferredWidth(45);
        SC_Current_Courses_Table.getColumnModel().getColumn(2).setPreferredWidth(80);
        SC_Current_Courses_Table.getColumnModel().getColumn(3).setPreferredWidth(0);
        SC_Current_Courses_Table.getColumnModel().getColumn(4).setPreferredWidth(50);
        SC_Current_Courses_Table.getColumnModel().getColumn(5).setPreferredWidth(100);
    }
    // Table Column Alignment
    private void tableColumnAlignment(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        // Student - GPA Table
        SP_GPA_Table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        SP_GPA_Table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        SP_GPA_Table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        // Student - Exam Grades Table
        SP_Exam_Grades_Table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        SP_Exam_Grades_Table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        SP_Exam_Grades_Table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        // Student - Registered Courses Table
        SP_Registered_Courses_Table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        SP_Registered_Courses_Table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        
    }
    // Setting Home Tiles Background Color
    private void HomeTile(){
        // Student Home Page Tiles Background Color
        Student_Center_Panel.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        Student_Comminication_Center_Panel.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        Academic_Advising_Panel.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        Student_Records_Enrollment_Panel.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        Scholarships_Financial_Aid_Panel.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        Student_Financials_Panel.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        // Admin Home Page Tiles Background Color
        Admin_Manage_Courses_Panel.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        Assign_Courses_Panel.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        Admin_Manage_Student_Panel.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        Admin_Assign_Exam_Grades_Panel.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        Admin_Scholarships_Financial_Aid_Panel.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
        Admin_Student_Financials_Panel.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
    }
    // Admin - Courses Table in Manage Courses Panel
    public ArrayList<CoursesList> CoursesList(){
        ArrayList<CoursesList> CoursesList = new ArrayList<>();
        try{
            conn = MySqlConnect.ConnectDB();
            String Query1 = "SELECT * FROM courses";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query1);
            CoursesList newCourse;
            while(rs.next()){
                newCourse = new CoursesList((rs.getString("Class_ID")), rs.getString("Course_No"), rs.getString("Course_Description"), rs.getString("Room_No"), rs.getString("Instructor_Name"), rs.getString("Days_Time"), rs.getString("Start_Date"), rs.getString("End_Date"));
                CoursesList.add(newCourse);
            }
            st.close();
            rs.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return CoursesList;
    }
    // Admin - Show Current Courses Table in Manage Courses Panel
    public void MC_Show_Courses() {
        ArrayList<CoursesList> list = CoursesList();
        DefaultTableModel model = (DefaultTableModel)MC_Current_Courses_Table.getModel();
        Object[] row = new Object[8];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getClassID();
            row[1]=list.get(i).getCourseNo();
            row[2]=list.get(i).getCourseDescription();
            row[3]=list.get(i).getRoomNo();
            row[4]=list.get(i).getInstructorName();
            row[5]=list.get(i).getDaysTime();
            row[6]=list.get(i).getStartDate();
            row[7]=list.get(i).getEndDate();
            model.addRow(row);
        }
    }
    // Admin - Show Current Courses Table in Assign Courses Panel
    public void AC_Show_Courses() {
        ArrayList<CoursesList> list = CoursesList();
        DefaultTableModel model = (DefaultTableModel)SC_Current_Courses_Table.getModel();
        Object[] row = new Object[6];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getClassID();
            row[1]=list.get(i).getCourseNo();
            row[2]=list.get(i).getCourseDescription();
            row[3]=list.get(i).getRoomNo();
            row[4]=list.get(i).getInstructorName();
            row[5]=list.get(i).getDaysTime();
            model.addRow(row);
        }
    }
    
    
    // Admin - Current Student Table
    public ArrayList<StudentsList> StudentsList(){
        ArrayList<StudentsList> StudentsList = new ArrayList<>();
        try{
            conn = MySqlConnect.ConnectDB();
            String Query2 = "SELECT * FROM student";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query2);
            StudentsList newStudent;
            while(rs.next()){
                newStudent = new StudentsList((rs.getString("Student_ID")), rs.getString("First_Name"), rs.getString("Last_Name"), rs.getString("Student_Status"), rs.getString("Residency_Status"), rs.getString("Class_Standing"), rs.getString("Location"), rs.getString("Major"), rs.getString("Phone_Number"), rs.getString("Email_Address"));
                StudentsList.add(newStudent);
            }
            st.close();
            rs.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return StudentsList;
    }
    // Admin - Show Current Students Table in Manage Student Panel
    public void MS_Show_Students() {
        ArrayList<StudentsList> list = StudentsList();
        DefaultTableModel model = (DefaultTableModel)MS_Current_Students_Table.getModel();
        Object[] row = new Object[10];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getStudentID();
            row[1]=list.get(i).getFirstName();
            row[2]=list.get(i).getLastName();
            row[3]=list.get(i).getStudentStatus();
            row[4]=list.get(i).getResidencyStatus();
            row[5]=list.get(i).getClassStanding();
            row[6]=list.get(i).getLocation();
            row[7]=list.get(i).getMajor();
            row[8]=list.get(i).getPhoneNumber();
            row[9]=list.get(i).getEmailAddress();
            model.addRow(row);
        }
    }
    // Admin - Show Students Table in Student Records Panel
    public void SR_Show_Students() {
        ArrayList<StudentsList> list = StudentsList();
        DefaultTableModel model = (DefaultTableModel)SC_Current_Students_Table.getModel();
        Object[] row = new Object[10];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getStudentID();
            row[1]=list.get(i).getFirstName();
            row[2]=list.get(i).getLastName();
            row[3]=list.get(i).getStudentStatus();
            row[4]=list.get(i).getResidencyStatus();
            row[5]=list.get(i).getClassStanding();
            row[6]=list.get(i).getLocation();
            row[7]=list.get(i).getMajor();
            row[8]=list.get(i).getPhoneNumber();
            row[9]=list.get(i).getEmailAddress();
            model.addRow(row);
        }
    }
    // Student - Registered Courses Table in Student Records & Enrollment Panel
        public ArrayList<StudentRegisteredCourses> StudentRegisteredCourses(){
        ArrayList<StudentRegisteredCourses> StudentRegisteredCourses = new ArrayList<>();
        try{
            conn = MySqlConnect.ConnectDB();
            String Query3 = "SELECT c.Class_ID, Course_No, Course_Description, Room_No, Instructor_Name, Days_Time, Start_Date, End_Date FROM registered_courses sc INNER JOIN courses c ON c.class_id = sc.class_id WHERE sc.Student_ID = '" +Student_ID+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query3);
            StudentRegisteredCourses RegisteredCourses;
            while(rs.next()){
                RegisteredCourses = new StudentRegisteredCourses(rs.getString("Class_ID"), rs.getString("Course_No"), rs.getString("Course_Description"), rs.getString("Room_No"), rs.getString("Instructor_Name"), rs.getString("Days_Time"), rs.getString("Start_Date"), rs.getString("End_Date"));
                StudentRegisteredCourses.add(RegisteredCourses);
            }
            st.close();
            rs.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return StudentRegisteredCourses;
    }
    // Student - Show Registered Courses Table in Student Records & Enrollment Panel
    public void Show_RegisteredCourses() {
        ArrayList<StudentRegisteredCourses> list = StudentRegisteredCourses();
        DefaultTableModel model = (DefaultTableModel)SP_Registered_Courses_Table.getModel();
        Object[] row = new Object[6];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getCourseNo();
            row[1]=list.get(i).getCourseDescription();
            row[2]=list.get(i).getRoomNo();
            row[3]=list.get(i).getInstructorName();
            row[4]=list.get(i).getStartDate();
            row[5]=list.get(i).getEndDate();
            model.addRow(row);
        }
    }
    // Student - Registered Courses Table in Student Records & Enrollment Panel
        public ArrayList<StudentRegisteredCourses> SC_StudentRegisteredCourses(){
        ArrayList<StudentRegisteredCourses> StudentRegisteredCourses = new ArrayList<>();
        try{
            String Student_ID = SC_Student_ID_Label.getText();
            conn = MySqlConnect.ConnectDB();
            String Query3 = "SELECT c.Class_ID, Course_No, Course_Description, Room_No, Instructor_Name, Days_Time, Start_Date, End_Date FROM registered_courses sc INNER JOIN courses c ON c.class_id = sc.class_id WHERE sc.Student_ID = '" +Student_ID+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query3);
            StudentRegisteredCourses RegisteredCourses;
            while(rs.next()){
                RegisteredCourses = new StudentRegisteredCourses(rs.getString("Class_ID"), rs.getString("Course_No"), rs.getString("Course_Description"), rs.getString("Room_No"), rs.getString("Instructor_Name"), rs.getString("Days_Time"), rs.getString("Start_Date"), rs.getString("End_Date"));
                StudentRegisteredCourses.add(RegisteredCourses);
            }
            st.close();
            rs.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return StudentRegisteredCourses;
    }
    // Admin - Show Selected Students Registered Courses in Assign Exam & Course Grades Panel
    public void SC_Show_RegisteredCourses() {
        ArrayList<StudentRegisteredCourses> list = SC_StudentRegisteredCourses();
        DefaultTableModel model = (DefaultTableModel)SC_Student_Courses_Table.getModel();
        Object[] row = new Object[6];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getClassID();
            row[1]=list.get(i).getCourseNo();
            row[2]=list.get(i).getCourseDescription();
            row[3]=list.get(i).getRoomNo();
            row[4]=list.get(i).getInstructorName();
            row[5]=list.get(i).getDaysTime();
            model.addRow(row);
        }
    }
    // Student - GPA Table in Student Records & Enrollment Panel
    public ArrayList<GPA> GPA(){
        ArrayList<GPA> GPA = new ArrayList<>();
        try{
            conn = MySqlConnect.ConnectDB();
            String Query4 = "SELECT c.Class_ID, Course_No, Course_Description, Units, Grade FROM registered_courses sc INNER JOIN courses c ON c.class_id = sc.class_id WHERE sc.Student_ID = '" +Student_ID+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query4);
            GPA gpa;
            while(rs.next()){
                gpa = new GPA(rs.getString("Course_No"), rs.getString("Course_Description"), rs.getString("Units"), rs.getString("Grade"));
                GPA.add(gpa);
            }
            st.close();
            rs.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return GPA;
    }
    // Student - Show GPA Table in Student Records & Enrollment Panel
    public void Show_GPA_Table() {
        ArrayList<GPA> list = GPA();
        DefaultTableModel model = (DefaultTableModel)SP_GPA_Table.getModel();
        Object[] row = new Object[4];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getCourseNo();
            row[1]=list.get(i).getCourseDescription();
            row[2]=list.get(i).getUnits();
            row[3]=list.get(i).getGrade();
            model.addRow(row);
        }
    }
    // Student - GPA Point Sum for GPA Table
    public Float getTotalCredits() { 
        int rowsCount = SP_GPA_Table.getRowCount();
        float Total_Credits = 0;
        for (int i = 0; i < rowsCount; i++){
            Total_Credits = Total_Credits + Float.valueOf(SP_GPA_Table.getValueAt(i, 2).toString());
        }
        return Total_Credits;
    }
    // Student - Total Points for GPA Table
    public Float getTotalPoints() {
        int rowsCount = SP_GPA_Table.getRowCount();
        float Total_Points = 0;
        for (int i = 0; i < rowsCount; i++){
            Total_Points = Total_Points + Float.valueOf(SP_GPA_Table.getValueAt(i, 4).toString());
        }
        return Total_Points;
    }
    // Student - Setting Current Course Selection Options
    public void StudentCoursesComboBox() {
        try {
            conn = MySqlConnect.ConnectDB();
            String sql = "SELECT c.Class_ID, Course_No, Course_Description, Room_No, Instructor_Name, Start_Date, End_Date FROM registered_courses sc INNER JOIN courses c ON c.class_id = sc.class_id WHERE sc.Student_ID = '" +Student_ID+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()) {
                String Courses = rs.getString("Course_Description");
                SP_Course_Selection_ComboBox.addItem(Courses);
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // Student - Setting Exam Grades Table from Selected Course in Student Records & Enrollment Panel
    public ArrayList<ExamGrades> ExamGrades(){
        ArrayList<ExamGrades> ExamGrades = new ArrayList<>();
        try{
            String Selected_Course = SP_Course_Selection_ComboBox.getSelectedItem().toString();
            conn = MySqlConnect.ConnectDB();
            String Query5 = "SELECT c.Class_ID, Course_No, Course_Description, Exam_Name, Exam_Weight, Score_Received FROM exam_grades sc INNER JOIN courses c ON c.class_id = sc.class_id WHERE sc.Student_ID = '"+Student_ID+"' AND c.Course_Description = '"+Selected_Course+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query5);
            ExamGrades eg;
            while(rs.next()){
                eg = new ExamGrades(rs.getString("Exam_Name"), rs.getString("Exam_Weight"), rs.getString("Score_Received"));
                ExamGrades.add(eg);
            }
            st.close();
            rs.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        return ExamGrades;
    }
    // Student - Show GPA Table in Student Records & Enrollment Panel
    public void Show_Exam_Grades_Table() {
        ArrayList<ExamGrades> list = ExamGrades();
        DefaultTableModel model = (DefaultTableModel)SP_Exam_Grades_Table.getModel();
        Object[] row = new Object[3];
        for(int i=0;i<list.size();i++){
            row[0]=list.get(i).getExamName();
            row[1]=list.get(i).getExamWeight();
            row[2]=list.get(i).getScoreReceived();
            model.addRow(row);
        }
    }
    // Clear All Fields in Manage Courses Panel
    public void Admin_MC_Clear_Fields() {
        MC_Class_ID_Field.setText("");
        MC_Course_No_Field.setText("");
        MC_Course_Description_Field.setText("");
        MC_Room_No_Field.setText("");
        MC_Instructor_Name_Field.setText("");
        MC_Days_Time_Field.setText("");
        MC_Start_Date_Field.setText("");
        MC_End_Date_Field.setText("");
        MC_Units_Field.setText("");
        MC_Current_Courses_Table.getSelectionModel().clearSelection();
    }
    // Clear All Fields in Manage Student Panel
    public void Admin_MS_Clear_Fields() {
        MS_Student_ID_Field.setText("");
        MS_First_Name_Field.setText("");
        MS_Last_Name_Field.setText("");
        MS_Student_Status_Field.setText("");
        MS_Residency_Status_Field.setText("");
        MS_Class_Standing_Field.setText("");
        MS_Location_Field.setText("");
        MS_Major_Field.setText("");
        MS_Phone_Number_Field.setText("");
        MS_Email_Address_Field.setText("");
        MS_Current_Students_Table.getSelectionModel().clearSelection();
    }
    // Admin - Clear Student Registered Courses Table in Student Courses Panel
    public void Admin_SC_ClearRegisteredCoursesTable() {
        DefaultTableModel model = (DefaultTableModel)SC_Student_Courses_Table.getModel();
            while(model.getRowCount()>0){
                for(int i = 0; i < model.getRowCount(); i++)
                {
                    model.removeRow(i);
                }
            }
    }
    // Search Course Table in Manage Courses Panel
    public void MC_FilterCourse(String SearchCourse){
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(MC_CC_Table);
        MC_Current_Courses_Table.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(SearchCourse));
    }
    // Search Student Table in Manage Students Panel
    public void MS_FilterStudent(String SearchStudent){
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(MS_CS_Table);
        MS_Current_Students_Table.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(SearchStudent));
    }
    // Search Student Table in Assign Courses Panel
    public void AC_FilterStudent(String SearchStudent){
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(AC_CS_Table);
        SC_Current_Students_Table.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(SearchStudent));
    }
    // Search Courses Table in Assign Courses Panel
    public void AC_FilterCourse(String SearchCourse){
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(AC_CC_Table);
        SC_Current_Courses_Table.setRowSorter(tr);
        
        tr.setRowFilter(RowFilter.regexFilter(SearchCourse));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Main_Frame_Panel = new javax.swing.JPanel();
        Move_Panel = new javax.swing.JPanel();
        Close_Icon_Panel = new javax.swing.JPanel();
        Close_Icon = new javax.swing.JLabel();
        Minimize_Icon_Panel = new javax.swing.JPanel();
        Minimize_Icon = new javax.swing.JLabel();
        LMS_Header_Label = new javax.swing.JLabel();
        Admin_SC_Panel = new javax.swing.JPanel();
        Admin_SC_Left_Panel = new javax.swing.JPanel();
        Admin_SC_Img = new javax.swing.JLabel();
        SC_Home_Button_Panel = new javax.swing.JPanel();
        SC_Home_Button_Label = new javax.swing.JLabel();
        SC_Home_Button_Icon = new javax.swing.JLabel();
        SC_MC_Button_Panel = new javax.swing.JPanel();
        SC_MC_Button_Label = new javax.swing.JLabel();
        SC_MC_Button_Icon = new javax.swing.JLabel();
        SC_MS_Button_Panel = new javax.swing.JPanel();
        SC_MS_Button_Label = new javax.swing.JLabel();
        SC_MS_Button_Icon = new javax.swing.JLabel();
        SC_SC_Button_Panel = new javax.swing.JPanel();
        SC_SC_Button_Label = new javax.swing.JLabel();
        SC_SC_Button_Icon = new javax.swing.JLabel();
        SC_AECG_Button_Panel = new javax.swing.JPanel();
        SC_AECG_Button_Label = new javax.swing.JLabel();
        SC_AECG_Button_Icon = new javax.swing.JLabel();
        SC_SFA_Button_Panel = new javax.swing.JPanel();
        SC_SFA_Button_Label = new javax.swing.JLabel();
        SC_SFA_Button_Icon = new javax.swing.JLabel();
        SC_SF_Button_Panel = new javax.swing.JPanel();
        SC_SF_Button_Label = new javax.swing.JLabel();
        SC_SF_Button_Icon = new javax.swing.JLabel();
        SC_Logout_Button_Icon = new javax.swing.JLabel();
        SC_Logout_Button_Label = new javax.swing.JLabel();
        Admin_SC_Right_Panel = new javax.swing.JPanel();
        SC_Student_Information_Panel = new javax.swing.JPanel();
        SC_Student_Information_Heading_Label = new javax.swing.JLabel();
        SC_Student_ID_Heading_Label = new javax.swing.JLabel();
        SC_Student_Name_Heading_Label = new javax.swing.JLabel();
        SC_Student_Status_Heading_Label = new javax.swing.JLabel();
        SC_Residency_Status_Heading_Label = new javax.swing.JLabel();
        SC_Class_Standing_Heading_Label = new javax.swing.JLabel();
        SC_Location_Heading_Label = new javax.swing.JLabel();
        SC_Major_Heading_Label = new javax.swing.JLabel();
        SC_Contact_Label = new javax.swing.JLabel();
        SC_Phone_Number_Heading_Label = new javax.swing.JLabel();
        SC_Email_Heading_Label = new javax.swing.JLabel();
        SC_Assign_Withdraw_Courses_Panel = new javax.swing.JPanel();
        SC_Assign_Courses_Heading_Label = new javax.swing.JLabel();
        SC_Search_Course_Button_Panel = new javax.swing.JPanel();
        SC_Search_Courses_Button_Icon = new javax.swing.JLabel();
        SC_Search_Courses_Placeholder_Field = new javax.swing.JLabel();
        SC_Search_Course_Field = new javax.swing.JTextField();
        SC_Registered_Courses_Panel = new javax.swing.JPanel();
        SC_Courses_Table_ScrollPane1 = new javax.swing.JScrollPane();
        SC_Student_Courses_Table = new javax.swing.JTable();
        SC_Withdraw_Button = new javax.swing.JButton();
        SC_Hide_Student_Current_Courses_Label = new javax.swing.JLabel();
        SC_Assgin_Courses_Panel = new javax.swing.JPanel();
        SC_Courses_Table_ScrollPane = new javax.swing.JScrollPane();
        SC_Current_Courses_Table = new javax.swing.JTable();
        SC_Add_Course_Button = new javax.swing.JButton();
        SC_Show_Student_Current_Courses_Label = new javax.swing.JLabel();
        SC_Current_Students_Panel = new javax.swing.JPanel();
        SC_Current_Students_Table_Heading_Label = new javax.swing.JLabel();
        SC_Search_Button_Panel = new javax.swing.JPanel();
        SC_Search_Button_Icon = new javax.swing.JLabel();
        SC_Search_Placeholder_Field = new javax.swing.JLabel();
        SC_Search_Student_Field = new javax.swing.JTextField();
        SC_Current_Students_Table_ScrollPane = new javax.swing.JScrollPane();
        SC_Current_Students_Table = new javax.swing.JTable();
        Admin_AECG_Panel = new javax.swing.JPanel();
        Admin_AECG_Left_Panel = new javax.swing.JPanel();
        Admin_AECG_Img = new javax.swing.JLabel();
        AECG_Home_Button_Panel = new javax.swing.JPanel();
        AECG_Home_Button_Label = new javax.swing.JLabel();
        AECG_Home_Button_Icon = new javax.swing.JLabel();
        AECG_MC_Button_Panel = new javax.swing.JPanel();
        AECG_MC_Button_Label = new javax.swing.JLabel();
        AECG_MC_Button_Icon = new javax.swing.JLabel();
        AECG_MS_Button_Panel = new javax.swing.JPanel();
        AECG_MS_Button_Label = new javax.swing.JLabel();
        AECG_MS_Button_Icon = new javax.swing.JLabel();
        AECG_SC_Button_Panel = new javax.swing.JPanel();
        AECG_SC_Button_Label = new javax.swing.JLabel();
        AECG_SC_Button_Icon = new javax.swing.JLabel();
        AECG_AECG_Button_Panel = new javax.swing.JPanel();
        AECG_AECG_Button_Label = new javax.swing.JLabel();
        AECG_AECG_Button_Icon = new javax.swing.JLabel();
        AECG_SFA_Button_Panel = new javax.swing.JPanel();
        AECG_SFA_Button_Label = new javax.swing.JLabel();
        AECG_SFA_Button_Icon = new javax.swing.JLabel();
        AECG_SF_Button_Panel = new javax.swing.JPanel();
        AECG_SF_Button_Label = new javax.swing.JLabel();
        AECG_SF_Button_Icon = new javax.swing.JLabel();
        AECG_Logout_Button_Icon = new javax.swing.JLabel();
        AECG_Logout_Button_Label = new javax.swing.JLabel();
        Admin_AECG_Right_Panel = new javax.swing.JPanel();
        SC_Student_Information_Panel1 = new javax.swing.JPanel();
        SC_Student_Information_Heading_Label1 = new javax.swing.JLabel();
        SC_Student_ID_Heading_Label1 = new javax.swing.JLabel();
        SC_Student_Name_Heading_Label1 = new javax.swing.JLabel();
        SC_Student_Status_Heading_Label1 = new javax.swing.JLabel();
        SC_Residency_Status_Heading_Label1 = new javax.swing.JLabel();
        SC_Class_Standing_Heading_Label1 = new javax.swing.JLabel();
        SC_Location_Heading_Label1 = new javax.swing.JLabel();
        SC_Major_Heading_Label1 = new javax.swing.JLabel();
        SC_Contact_Label1 = new javax.swing.JLabel();
        SC_Phone_Number_Heading_Label1 = new javax.swing.JLabel();
        SC_Email_Heading_Label1 = new javax.swing.JLabel();
        SC_Assign_Courses_Panel1 = new javax.swing.JPanel();
        AECG_Assign_Exam_Grades_Header_Label = new javax.swing.JLabel();
        SC_Current_Students_Panel1 = new javax.swing.JPanel();
        SC_Current_Students_Table_Heading_Label1 = new javax.swing.JLabel();
        SC_Search_Button_Panel1 = new javax.swing.JPanel();
        SC_Search_Button_Icon1 = new javax.swing.JLabel();
        SC_Search_Placeholder_Field1 = new javax.swing.JLabel();
        SC_Search_Student_Field1 = new javax.swing.JTextField();
        SC_Current_Students_Table_ScrollPane1 = new javax.swing.JScrollPane();
        SC_Current_Students_Table1 = new javax.swing.JTable();
        SC_Assign_Courses_Panel2 = new javax.swing.JPanel();
        AECG_Assign_Exam_Grades_Header_Label1 = new javax.swing.JLabel();
        Admin_MS_Panel = new javax.swing.JPanel();
        Admin_MS_Left_Panel = new javax.swing.JPanel();
        Admin_MS_Img = new javax.swing.JLabel();
        MS_Home_Button_Panel = new javax.swing.JPanel();
        MS_Home_Button_Label = new javax.swing.JLabel();
        MS_Home_Button_Icon = new javax.swing.JLabel();
        MS_MC_Button_Panel = new javax.swing.JPanel();
        MS_MC_Button_Label = new javax.swing.JLabel();
        MS_MC_Button_Icon = new javax.swing.JLabel();
        MS_SR_Button_Panel = new javax.swing.JPanel();
        MS_Button_Label = new javax.swing.JLabel();
        MS_Button_Icon = new javax.swing.JLabel();
        MS_MS_Button_Panel = new javax.swing.JPanel();
        MS_MS_Button_Label = new javax.swing.JLabel();
        MS_MS_Button_Icon = new javax.swing.JLabel();
        MS_AA_Button_Panel = new javax.swing.JPanel();
        MS_AA_Button_Label = new javax.swing.JLabel();
        MS_AA_Button_Icon = new javax.swing.JLabel();
        MS_SFA_Button_Panel = new javax.swing.JPanel();
        MS_SFA_Button_Label = new javax.swing.JLabel();
        MS_SFA_Button_Icon = new javax.swing.JLabel();
        MS_SF_Button_Panel = new javax.swing.JPanel();
        MS_SF_Button_Label = new javax.swing.JLabel();
        MS_SF_Button_Icon = new javax.swing.JLabel();
        MS_Logout_Button_Icon = new javax.swing.JLabel();
        MS_Logout_Button_Label = new javax.swing.JLabel();
        Admin_MS_Right_Panel = new javax.swing.JPanel();
        Admin_Add_Courses__Form_Panel1 = new javax.swing.JPanel();
        Admin_MS_Heading_Label = new javax.swing.JLabel();
        MS_First_Name_Field = new javax.swing.JTextField();
        MS_Last_Name_Field = new javax.swing.JTextField();
        MS_Student_Status_Field = new javax.swing.JTextField();
        MS_Residency_Status_Field = new javax.swing.JTextField();
        MS_Class_Standing_Field = new javax.swing.JTextField();
        MS_Location_Field = new javax.swing.JTextField();
        MS_Email_Address_Field = new javax.swing.JTextField();
        Admin_MS_Delete_Button = new javax.swing.JButton();
        Admin_MS_Insert_Button = new javax.swing.JButton();
        Admin_MS_Reset_Button = new javax.swing.JButton();
        Admin_MS_Update_Button = new javax.swing.JButton();
        MS_Student_ID_Field = new javax.swing.JTextField();
        MS_Major_Field = new javax.swing.JTextField();
        MS_Phone_Number_Field = new javax.swing.JTextField();
        MC_Current_Courses_Panel1 = new javax.swing.JPanel();
        MS_Search_Button_Panel = new javax.swing.JPanel();
        MS_Search_Button_Icon = new javax.swing.JLabel();
        MC_Current_Courses_Table_ScrollPane1 = new javax.swing.JScrollPane();
        MS_Current_Students_Table = new javax.swing.JTable();
        SP_Student_Information_Heading_Label4 = new javax.swing.JLabel();
        MS_Search_Placeholder_Field = new javax.swing.JLabel();
        MS_Search_Student_Field = new javax.swing.JTextField();
        Admin_MC_Panel = new javax.swing.JPanel();
        Admin_MC_Left_Panel = new javax.swing.JPanel();
        Admin_MC_Img = new javax.swing.JLabel();
        MC_Home_Button_Panel = new javax.swing.JPanel();
        MC_Home_Button_Label = new javax.swing.JLabel();
        MC_Home_Button_Icon = new javax.swing.JLabel();
        MC_MC_Button_Panel = new javax.swing.JPanel();
        MC_MC_Button_Label = new javax.swing.JLabel();
        MC_MC_Button_Icon = new javax.swing.JLabel();
        MC_SR_Button_Panel = new javax.swing.JPanel();
        SR_Button_Label = new javax.swing.JLabel();
        SR_Button_Icon = new javax.swing.JLabel();
        MC_MS_Button_Panel = new javax.swing.JPanel();
        MC_MS_Button_Label = new javax.swing.JLabel();
        MC_MS_Button_Icon = new javax.swing.JLabel();
        MC_AA_Button_Panel = new javax.swing.JPanel();
        MC_AA_Button_Label = new javax.swing.JLabel();
        MC_AA_Button_Icon = new javax.swing.JLabel();
        MC_SFA_Button_Panel = new javax.swing.JPanel();
        MC_SFA_Button_Label = new javax.swing.JLabel();
        MC_SFA_Button_Icon = new javax.swing.JLabel();
        MC_SF_Button_Panel = new javax.swing.JPanel();
        MC_SF_Button_Label = new javax.swing.JLabel();
        MC_SF_Button_Icon = new javax.swing.JLabel();
        MC_Logout_Button_Icon = new javax.swing.JLabel();
        MC_Logout_Button_Label = new javax.swing.JLabel();
        Admin_MC_Right_Panel = new javax.swing.JPanel();
        Admin_Add_Courses_Form_Panel = new javax.swing.JPanel();
        SP_Student_Information_Heading_Label1 = new javax.swing.JLabel();
        MC_Course_No_Field = new javax.swing.JTextField();
        MC_Course_Description_Field = new javax.swing.JTextField();
        MC_Room_No_Field = new javax.swing.JTextField();
        MC_Instructor_Name_Field = new javax.swing.JTextField();
        MC_Days_Time_Field = new javax.swing.JTextField();
        MC_Start_Date_Field = new javax.swing.JTextField();
        MC_Units_Field = new javax.swing.JTextField();
        Admin_MC_Delete_Button = new javax.swing.JButton();
        Admin_MC_Insert_Button = new javax.swing.JButton();
        Admin_MC_Reset_Button = new javax.swing.JButton();
        Admin_MC_Update_Button = new javax.swing.JButton();
        MC_Class_ID_Field = new javax.swing.JTextField();
        MC_End_Date_Field = new javax.swing.JTextField();
        MC_Current_Courses_Panel = new javax.swing.JPanel();
        MC_Search_Button_Panel = new javax.swing.JPanel();
        MC_Search_Button_Icon = new javax.swing.JLabel();
        MC_Current_Courses_Table_ScrollPane = new javax.swing.JScrollPane();
        MC_Current_Courses_Table = new javax.swing.JTable();
        SP_Student_Information_Heading_Label2 = new javax.swing.JLabel();
        MC_Search_Placeholder_Field = new javax.swing.JLabel();
        MC_Search_Courses_Field = new javax.swing.JTextField();
        Admin_Main_Page_Panel = new javax.swing.JPanel();
        Admin_Main_Page_Left_Panel = new javax.swing.JPanel();
        Admin_Img = new javax.swing.JLabel();
        Admin_Main_Page_Logout_Button_Icon = new javax.swing.JLabel();
        Admin_Main_Page_Logout_Button_Label = new javax.swing.JLabel();
        Admin_Main_Page_Right_Panel = new javax.swing.JPanel();
        Admin_Manage_Courses_Panel = new RoundedPanel(20,Color.WHITE);
        Admin_MC_Tile_Label = new javax.swing.JLabel();
        Admin_MC_img_Label = new javax.swing.JLabel();
        Admin_Manage_Student_Panel = new RoundedPanel(20,Color.WHITE);
        Admin_MS_Tile_Label = new javax.swing.JLabel();
        Admin_MS_img_Label = new javax.swing.JLabel();
        Assign_Courses_Panel = new RoundedPanel(20,Color.WHITE);
        Admin_SR_Tile_Label = new javax.swing.JLabel();
        Admin_SR_img_Label = new javax.swing.JLabel();
        Admin_Assign_Exam_Grades_Panel = new RoundedPanel(20,Color.WHITE);
        Admin_AA_Tile_Label = new javax.swing.JLabel();
        Admin_SRE_img_Label = new javax.swing.JLabel();
        Admin_Scholarships_Financial_Aid_Panel = new RoundedPanel(20,Color.WHITE);
        Admin_SFA_Tile_Label = new javax.swing.JLabel();
        Admin_SFA_Tile_Label1 = new javax.swing.JLabel();
        Admin_SFA_img_Label = new javax.swing.JLabel();
        Admin_Student_Financials_Panel = new RoundedPanel(20,Color.WHITE);
        Admin_SF_Tile_Label = new javax.swing.JLabel();
        Admin_SF_img_Label = new javax.swing.JLabel();
        Student_SRE_Panel = new javax.swing.JPanel();
        Student_SRE_Right_Panel = new javax.swing.JPanel();
        SP_Student_Information_Panel = new javax.swing.JPanel();
        SP_Student_Information_Heading_Label = new javax.swing.JLabel();
        SP_Student_ID_Heading_Label = new javax.swing.JLabel();
        SP_Student_Status_Heading_Label = new javax.swing.JLabel();
        SP_Residency_Status_Heading_Label = new javax.swing.JLabel();
        SP_Class_Standing_Heading_Label = new javax.swing.JLabel();
        SP_Location_Heading_Label = new javax.swing.JLabel();
        SP_Major_Heading_Label = new javax.swing.JLabel();
        SP_Contact_Label = new javax.swing.JLabel();
        SP_Phone_Number_Heading_Label = new javax.swing.JLabel();
        SP_Email_Heading_Label = new javax.swing.JLabel();
        SP_Registered_Courses_Panel = new javax.swing.JPanel();
        SP_Registered_Courses_Heading_Label = new javax.swing.JLabel();
        SP_Registered_Courses_Table_ScrollPane = new javax.swing.JScrollPane();
        SP_Registered_Courses_Table = new javax.swing.JTable();
        SP_Exam_Grades_Panel = new javax.swing.JPanel();
        SP_Exam_Grades_Heading_Label = new javax.swing.JLabel();
        SP_Course_Selection_ComboBox = new javax.swing.JComboBox<>();
        SP_Exam_Grades_Table_ScrollPane = new javax.swing.JScrollPane();
        SP_Exam_Grades_Table = new javax.swing.JTable();
        SP_GPA_Panel = new javax.swing.JPanel();
        SP_GPA_Heading_Label = new javax.swing.JLabel();
        SP_GPA_Table_ScrollPane = new javax.swing.JScrollPane();
        SP_GPA_Table = new javax.swing.JTable();
        SP_GPA_Label = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        SP_GPA_Formula_Label = new javax.swing.JLabel();
        Student_SRE_Left_Panel = new javax.swing.JPanel();
        User_Img = new javax.swing.JLabel();
        Home_Button_Panel = new javax.swing.JPanel();
        SP_Home_Button_Label = new javax.swing.JLabel();
        SP_Home_Button_Icon = new javax.swing.JLabel();
        SC_Button_Panel = new javax.swing.JPanel();
        SP_SC_Button_Label = new javax.swing.JLabel();
        SP_SC_Button_Icon = new javax.swing.JLabel();
        SCC_Button_Panel = new javax.swing.JPanel();
        SP_SCC_Button_Label = new javax.swing.JLabel();
        SP_SCC_Button_Icon = new javax.swing.JLabel();
        AA_Button_Panel = new javax.swing.JPanel();
        SP_AA_Button_Label = new javax.swing.JLabel();
        SP_AA_Button_Icon = new javax.swing.JLabel();
        SRE_Button_Panel = new javax.swing.JPanel();
        SP_SRE_Button_Label = new javax.swing.JLabel();
        SP_SRE_Button_Icon = new javax.swing.JLabel();
        SFA_Button_Panel = new javax.swing.JPanel();
        SP_SFA_Button_Label = new javax.swing.JLabel();
        SP_SFA_Button_Icon = new javax.swing.JLabel();
        SF_Button_Panel = new javax.swing.JPanel();
        SP_SF_Button_Label = new javax.swing.JLabel();
        SP_SF_Button_Icon = new javax.swing.JLabel();
        SP_Logout_Button_Icon = new javax.swing.JLabel();
        SP_Logout_Button_Label = new javax.swing.JLabel();
        Student_Main_Page_Panel = new javax.swing.JPanel();
        Student_Main_Page_Left_Panel = new javax.swing.JPanel();
        MP_User_Img = new javax.swing.JLabel();
        MP_Logout_Button_Icon = new javax.swing.JLabel();
        MP_Logout_Button_Label = new javax.swing.JLabel();
        MP_Class_Schedule_Panel = new javax.swing.JPanel();
        MP_Class_Schedule_Title_Panel = new javax.swing.JPanel();
        MP_Class_Schedule_Title_Label = new javax.swing.JLabel();
        MP_Class_Schedule_Header_Panel = new javax.swing.JPanel();
        MP_Class_Header_Label = new javax.swing.JLabel();
        MP_Room_Header_Label = new javax.swing.JLabel();
        MP_Days_Time_Header_Label = new javax.swing.JLabel();
        Class_Schedule_ScrollPane = new javax.swing.JScrollPane();
        MP_Class_Schedule_Table = new javax.swing.JTable();
        Student_Main_Page_Right_Panel = new javax.swing.JPanel();
        Student_Center_Panel = new RoundedPanel(20,Color.WHITE);
        SC_Tile_Label = new javax.swing.JLabel();
        SC_img_Label = new javax.swing.JLabel();
        Student_Comminication_Center_Panel = new RoundedPanel(20,Color.WHITE);
        SCC_Tile_Label1 = new javax.swing.JLabel();
        SCC_Tile_Label2 = new javax.swing.JLabel();
        SCC_img_Label = new javax.swing.JLabel();
        Academic_Advising_Panel = new RoundedPanel(20,Color.WHITE);
        AA_Tile_Label = new javax.swing.JLabel();
        AA_img_Label = new javax.swing.JLabel();
        Student_Records_Enrollment_Panel = new RoundedPanel(20,Color.WHITE);
        SRE_Tile_Label1 = new javax.swing.JLabel();
        SRE_Tile_Label2 = new javax.swing.JLabel();
        SRE_img_Label = new javax.swing.JLabel();
        Scholarships_Financial_Aid_Panel = new RoundedPanel(20,Color.WHITE);
        SFA_Tile_Label1 = new javax.swing.JLabel();
        SFA_Tile_Label2 = new javax.swing.JLabel();
        SFA_img_Label = new javax.swing.JLabel();
        Student_Financials_Panel = new RoundedPanel(20,Color.WHITE);
        SF_Tile_Label = new javax.swing.JLabel();
        SF_img_Label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        Main_Frame_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Move_Panel.setBackground(new java.awt.Color(0, 41, 85));
        Move_Panel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(153, 153, 0)));
        Move_Panel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Move_PanelMouseDragged(evt);
            }
        });
        Move_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Move_PanelMousePressed(evt);
            }
        });
        Move_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Close_Icon_Panel.setBackground(new java.awt.Color(0, 41, 85));
        Close_Icon_Panel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(153, 153, 0)));
        Close_Icon_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Close_Icon_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Close_Icon_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Close_Icon_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Close_Icon_PanelMouseExited(evt);
            }
        });

        Close_Icon.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        Close_Icon.setForeground(new java.awt.Color(255, 255, 255));
        Close_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Close_Icon.setText("X");

        javax.swing.GroupLayout Close_Icon_PanelLayout = new javax.swing.GroupLayout(Close_Icon_Panel);
        Close_Icon_Panel.setLayout(Close_Icon_PanelLayout);
        Close_Icon_PanelLayout.setHorizontalGroup(
            Close_Icon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Close_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        Close_Icon_PanelLayout.setVerticalGroup(
            Close_Icon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Close_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Move_Panel.add(Close_Icon_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1235, 0, 40, 40));

        Minimize_Icon_Panel.setBackground(new java.awt.Color(0, 41, 85));
        Minimize_Icon_Panel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(153, 153, 0)));
        Minimize_Icon_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Minimize_Icon_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Minimize_Icon_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Minimize_Icon_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Minimize_Icon_PanelMouseExited(evt);
            }
        });

        Minimize_Icon.setFont(new java.awt.Font("SansSerif", 0, 30)); // NOI18N
        Minimize_Icon.setForeground(new java.awt.Color(255, 255, 255));
        Minimize_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Minimize_Icon.setText("-");

        javax.swing.GroupLayout Minimize_Icon_PanelLayout = new javax.swing.GroupLayout(Minimize_Icon_Panel);
        Minimize_Icon_Panel.setLayout(Minimize_Icon_PanelLayout);
        Minimize_Icon_PanelLayout.setHorizontalGroup(
            Minimize_Icon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Minimize_Icon_PanelLayout.createSequentialGroup()
                .addComponent(Minimize_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Minimize_Icon_PanelLayout.setVerticalGroup(
            Minimize_Icon_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Minimize_Icon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, Short.MAX_VALUE)
        );

        Move_Panel.add(Minimize_Icon_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1195, 0, 40, 40));

        LMS_Header_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        LMS_Header_Label.setForeground(new java.awt.Color(255, 255, 255));
        LMS_Header_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LMS_Header_Label.setText("LEARNING MANAGEMENT SYSTEM");
        Move_Panel.add(LMS_Header_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1275, 35));

        Main_Frame_Panel.add(Move_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1275, 40));

        Admin_SC_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_SC_Left_Panel.setBackground(new java.awt.Color(85, 142, 203));
        Admin_SC_Left_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_SC_Img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_SC_Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/custom user.png"))); // NOI18N
        Admin_SC_Left_Panel.add(Admin_SC_Img, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 30, 130, 130));

        Admin_SC_Name_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        Admin_SC_Name_Label.setForeground(new java.awt.Color(255, 255, 255));
        Admin_SC_Name_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_SC_Name_Label.setText("Admin Name");
        Admin_SC_Left_Panel.add(Admin_SC_Name_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 320, 40));

        Admin_SC_Discription_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Admin_SC_Discription_Label.setForeground(new java.awt.Color(255, 255, 255));
        Admin_SC_Discription_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_SC_Discription_Label.setText("Admin | University of Houston");
        Admin_SC_Left_Panel.add(Admin_SC_Discription_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 320, 20));

        SC_Home_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        SC_Home_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_Home_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SC_Home_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SC_Home_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SC_Home_Button_PanelMouseExited(evt);
            }
        });
        SC_Home_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Home_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SC_Home_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SC_Home_Button_Label.setText("Home");
        SC_Home_Button_Panel.add(SC_Home_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SC_Home_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SC_Home_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Home Icon 16x16.png"))); // NOI18N
        SC_Home_Button_Panel.add(SC_Home_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_SC_Left_Panel.add(SC_Home_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 320, 40));

        SC_MC_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        SC_MC_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_MC_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SC_MC_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SC_MC_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SC_MC_Button_PanelMouseExited(evt);
            }
        });
        SC_MC_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_MC_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        SC_MC_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SC_MC_Button_Label.setText("Manage Courses");
        SC_MC_Button_Panel.add(SC_MC_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SC_MC_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SC_MC_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Center Icon 16x16.png"))); // NOI18N
        SC_MC_Button_Panel.add(SC_MC_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_SC_Left_Panel.add(SC_MC_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 320, 40));

        SC_MS_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        SC_MS_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_MS_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SC_MS_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SC_MS_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SC_MS_Button_PanelMouseExited(evt);
            }
        });
        SC_MS_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_MS_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        SC_MS_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SC_MS_Button_Label.setText("Manage Students");
        SC_MS_Button_Panel.add(SC_MS_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SC_MS_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SC_MS_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Records Enrollment 16x16.png"))); // NOI18N
        SC_MS_Button_Panel.add(SC_MS_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_SC_Left_Panel.add(SC_MS_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 320, 40));

        SC_SC_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        SC_SC_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_SC_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SC_SC_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SC_SC_Button_PanelMouseExited(evt);
            }
        });
        SC_SC_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_SC_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        SC_SC_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SC_SC_Button_Label.setText("Student Courses");
        SC_SC_Button_Panel.add(SC_SC_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SC_SC_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SC_SC_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Communication Center 16x16.png"))); // NOI18N
        SC_SC_Button_Panel.add(SC_SC_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_SC_Left_Panel.add(SC_SC_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 320, 40));

        SC_AECG_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        SC_AECG_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_AECG_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SC_AECG_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SC_AECG_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SC_AECG_Button_PanelMouseExited(evt);
            }
        });
        SC_AECG_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_AECG_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        SC_AECG_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SC_AECG_Button_Label.setText("Assign Exam & Course Grades");
        SC_AECG_Button_Panel.add(SC_AECG_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SC_AECG_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SC_AECG_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Academic Advising 16x16.png"))); // NOI18N
        SC_AECG_Button_Panel.add(SC_AECG_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_SC_Left_Panel.add(SC_AECG_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 320, 40));

        SC_SFA_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        SC_SFA_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_SFA_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SC_SFA_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SC_SFA_Button_PanelMouseExited(evt);
            }
        });
        SC_SFA_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_SFA_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        SC_SFA_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SC_SFA_Button_Label.setText("Scholarship & Financial Aid");
        SC_SFA_Button_Panel.add(SC_SFA_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SC_SFA_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SC_SFA_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Scholarship Financial Aid 16x16.png"))); // NOI18N
        SC_SFA_Button_Panel.add(SC_SFA_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_SC_Left_Panel.add(SC_SFA_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 320, 40));

        SC_SF_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        SC_SF_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_SF_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SC_SF_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SC_SF_Button_PanelMouseExited(evt);
            }
        });
        SC_SF_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_SF_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        SC_SF_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SC_SF_Button_Label.setText("Student Financials");
        SC_SF_Button_Panel.add(SC_SF_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SC_SF_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SC_SF_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Financials 16x16.png"))); // NOI18N
        SC_SF_Button_Panel.add(SC_SF_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_SC_Left_Panel.add(SC_SF_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 320, 40));

        SC_Logout_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Logout Icon 32x32.png"))); // NOI18N
        SC_Logout_Button_Icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_Logout_Button_Icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SC_Logout_Button_IconMouseClicked(evt);
            }
        });
        Admin_SC_Left_Panel.add(SC_Logout_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 32, 32));

        SC_Logout_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        SC_Logout_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SC_Logout_Button_Label.setText("Logout");
        SC_Logout_Button_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_Logout_Button_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SC_Logout_Button_LabelMouseClicked(evt);
            }
        });
        Admin_SC_Left_Panel.add(SC_Logout_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 645, 75, 40));

        Admin_SC_Panel.add(Admin_SC_Left_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 700));

        Admin_SC_Right_Panel.setBackground(new java.awt.Color(242, 242, 242));
        Admin_SC_Right_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Student_Information_Panel.setBackground(new java.awt.Color(255, 255, 255));
        SC_Student_Information_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Student_Information_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SC_Student_Information_Heading_Label.setText("Student Information");
        SC_Student_Information_Panel.add(SC_Student_Information_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));

        SC_Student_ID_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Student_ID_Heading_Label.setText("Student ID");
        SC_Student_Information_Panel.add(SC_Student_ID_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, 80, 20));

        SC_Student_ID_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Student_ID_Label.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel.add(SC_Student_ID_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 45, 170, 20));

        SC_Student_Name_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Student_Name_Heading_Label.setText("Student Name");
        SC_Student_Information_Panel.add(SC_Student_Name_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, 100, 20));

        SC_Student_Name_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Student_Name_Label.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel.add(SC_Student_Name_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 75, 170, 20));

        SC_Student_Status_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Student_Status_Heading_Label.setText("Student Status");
        SC_Student_Information_Panel.add(SC_Student_Status_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 100, 20));

        SC_Student_Status_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Student_Status_Label.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel.add(SC_Student_Status_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 105, 170, 20));

        SC_Residency_Status_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Residency_Status_Heading_Label.setText("Residency Status");
        SC_Student_Information_Panel.add(SC_Residency_Status_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 135, 110, 20));

        SC_Residency_Status_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Residency_Status_Label.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel.add(SC_Residency_Status_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 135, 170, 20));

        SC_Class_Standing_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Class_Standing_Heading_Label.setText("Class Standing");
        SC_Student_Information_Panel.add(SC_Class_Standing_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 165, 110, 20));

        SC_Class_Standing_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Class_Standing_Label.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel.add(SC_Class_Standing_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 165, 170, 20));

        SC_Location_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Location_Heading_Label.setText("Location");
        SC_Student_Information_Panel.add(SC_Location_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 195, 110, 20));

        SC_Location_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Location_Label.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel.add(SC_Location_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 195, 170, 20));

        SC_Major_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Major_Heading_Label.setText("Major");
        SC_Student_Information_Panel.add(SC_Major_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 225, 110, 20));

        SC_Major_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Major_Label.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel.add(SC_Major_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 225, 170, 20));

        SC_Contact_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SC_Contact_Label.setText("Contact");
        SC_Student_Information_Panel.add(SC_Contact_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 255, 310, 30));

        SC_Phone_Number_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Phone_Number_Heading_Label.setText("Phone Number");
        SC_Student_Information_Panel.add(SC_Phone_Number_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 100, 20));

        SC_Phone_Number_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Phone_Number_Label.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel.add(SC_Phone_Number_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 170, 20));

        SC_Email_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Email_Heading_Label.setText("E-Mail");
        SC_Student_Information_Panel.add(SC_Email_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 100, 20));

        SC_Email_Address_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Email_Address_Label.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel.add(SC_Email_Address_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 170, 20));

        Admin_SC_Right_Panel.add(SC_Student_Information_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 350));

        SC_Assign_Withdraw_Courses_Panel.setBackground(new java.awt.Color(255, 255, 255));
        SC_Assign_Withdraw_Courses_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Assign_Courses_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SC_Assign_Courses_Heading_Label.setText("Assign or Withdraw Courses");
        SC_Assign_Withdraw_Courses_Panel.add(SC_Assign_Courses_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 260, 30));

        SC_Search_Course_Button_Panel.setBackground(new java.awt.Color(255, 255, 255));
        SC_Search_Course_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_Search_Course_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Search_Courses_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Search_Icon_20x20.png"))); // NOI18N
        SC_Search_Course_Button_Panel.add(SC_Search_Courses_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, 25, 33));

        SC_Assign_Withdraw_Courses_Panel.add(SC_Search_Course_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 8, 30, 33));

        SC_Search_Courses_Placeholder_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        SC_Search_Courses_Placeholder_Field.setForeground(new java.awt.Color(153, 153, 153));
        SC_Search_Courses_Placeholder_Field.setText("Search Course");
        SC_Search_Courses_Placeholder_Field.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        SC_Assign_Withdraw_Courses_Panel.add(SC_Search_Courses_Placeholder_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 9, 190, 30));

        SC_Search_Course_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SC_Search_Course_Field.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 1, 1)));
        SC_Search_Course_Field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                SC_Search_Course_FieldFocusLost(evt);
            }
        });
        SC_Search_Course_Field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SC_Search_Course_FieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SC_Search_Course_FieldKeyTyped(evt);
            }
        });
        SC_Assign_Withdraw_Courses_Panel.add(SC_Search_Course_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(351, 7, 230, 35));

        SC_Registered_Courses_Panel.setBackground(new java.awt.Color(255, 255, 255));
        SC_Registered_Courses_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Student_Courses_Table.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Student_Courses_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLASS ID", "COURSE #", "DESCRIPTION", "ROOM", "INSTRUCTOR", "DAYS & TIME"
            }
        ));
        SC_Student_Courses_Table.setRowHeight(20);
        SC_Courses_Table_ScrollPane1.setViewportView(SC_Student_Courses_Table);

        SC_Registered_Courses_Panel.add(SC_Courses_Table_ScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, 585, 230));

        SC_Withdraw_Button.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Withdraw_Button.setText("Withdaw");
        SC_Withdraw_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_Withdraw_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SC_Withdraw_ButtonActionPerformed(evt);
            }
        });
        SC_Registered_Courses_Panel.add(SC_Withdraw_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 5, 105, 35));

        SC_Hide_Student_Current_Courses_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Hide_Student_Current_Courses_Label.setForeground(new java.awt.Color(85, 142, 203));
        SC_Hide_Student_Current_Courses_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SC_Hide_Student_Current_Courses_Label.setText("Hide Student Current Courses");
        SC_Hide_Student_Current_Courses_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_Hide_Student_Current_Courses_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SC_Hide_Student_Current_Courses_LabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SC_Hide_Student_Current_Courses_LabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SC_Hide_Student_Current_Courses_LabelMouseExited(evt);
            }
        });
        SC_Registered_Courses_Panel.add(SC_Hide_Student_Current_Courses_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 227, 20));

        SC_Assign_Withdraw_Courses_Panel.add(SC_Registered_Courses_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 70, 585, 275));

        SC_Assgin_Courses_Panel.setBackground(new java.awt.Color(255, 255, 255));
        SC_Assgin_Courses_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Current_Courses_Table.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Current_Courses_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLASS ID", "COURSE #", "DESCRIPTION", "ROOM", "INSTRUCTOR", "DAYS & TIME"
            }
        ));
        SC_Current_Courses_Table.setRowHeight(20);
        SC_Courses_Table_ScrollPane.setViewportView(SC_Current_Courses_Table);

        SC_Assgin_Courses_Panel.add(SC_Courses_Table_ScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 45, 585, 230));

        SC_Add_Course_Button.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Add_Course_Button.setText("Add Course");
        SC_Add_Course_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_Add_Course_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SC_Add_Course_ButtonActionPerformed(evt);
            }
        });
        SC_Assgin_Courses_Panel.add(SC_Add_Course_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 5, -1, 35));

        SC_Show_Student_Current_Courses_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Show_Student_Current_Courses_Label.setForeground(new java.awt.Color(85, 142, 203));
        SC_Show_Student_Current_Courses_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SC_Show_Student_Current_Courses_Label.setText("Show Student Current Courses");
        SC_Show_Student_Current_Courses_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_Show_Student_Current_Courses_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SC_Show_Student_Current_Courses_LabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SC_Show_Student_Current_Courses_LabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SC_Show_Student_Current_Courses_LabelMouseExited(evt);
            }
        });
        SC_Assgin_Courses_Panel.add(SC_Show_Student_Current_Courses_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 227, 20));

        SC_Assign_Withdraw_Courses_Panel.add(SC_Assgin_Courses_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 70, 585, 275));

        Admin_SC_Right_Panel.add(SC_Assign_Withdraw_Courses_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 595, 350));

        SC_Current_Students_Panel.setBackground(new java.awt.Color(255, 255, 255));
        SC_Current_Students_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Current_Students_Table_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SC_Current_Students_Table_Heading_Label.setText("Current Students");
        SC_Current_Students_Panel.add(SC_Current_Students_Table_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));

        SC_Search_Button_Panel.setBackground(new java.awt.Color(255, 255, 255));
        SC_Search_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_Search_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Search_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Search_Icon_20x20.png"))); // NOI18N
        SC_Search_Button_Panel.add(SC_Search_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, 25, 33));

        SC_Current_Students_Panel.add(SC_Search_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(889, 8, 30, 33));

        SC_Search_Placeholder_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        SC_Search_Placeholder_Field.setForeground(new java.awt.Color(153, 153, 153));
        SC_Search_Placeholder_Field.setText("Search Student");
        SC_Search_Placeholder_Field.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        SC_Current_Students_Panel.add(SC_Search_Placeholder_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(702, 9, 190, 30));

        SC_Search_Student_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SC_Search_Student_Field.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 1, 1)));
        SC_Search_Student_Field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                SC_Search_Student_FieldFocusLost(evt);
            }
        });
        SC_Search_Student_Field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SC_Search_Student_FieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SC_Search_Student_FieldKeyTyped(evt);
            }
        });
        SC_Current_Students_Panel.add(SC_Search_Student_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(691, 7, 230, 35));

        SC_Current_Students_Table.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Current_Students_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STUDENT ID", "FIRST NAME", "LAST NAME", "STUDENT STATUS", "RESIDENCY STATUS", "CLASS STANDING", "LOCATION", "MAJOR", "PHONE NUMBER", "EMAIL ADDRESS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        SC_Current_Students_Table.setRowHeight(20);
        SC_Current_Students_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SC_Current_Students_TableMouseClicked(evt);
            }
        });
        SC_Current_Students_Table_ScrollPane.setViewportView(SC_Current_Students_Table);

        SC_Current_Students_Panel.add(SC_Current_Students_Table_ScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 915, 260));

        Admin_SC_Right_Panel.add(SC_Current_Students_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 935, 320));

        Admin_SC_Panel.add(Admin_SC_Right_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 955, 700));

        Main_Frame_Panel.add(Admin_SC_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1275, 700));

        Admin_AECG_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_AECG_Left_Panel.setBackground(new java.awt.Color(85, 142, 203));
        Admin_AECG_Left_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_AECG_Img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_AECG_Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/custom user.png"))); // NOI18N
        Admin_AECG_Left_Panel.add(Admin_AECG_Img, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 30, 130, 130));

        Admin_AECG_Name_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        Admin_AECG_Name_Label.setForeground(new java.awt.Color(255, 255, 255));
        Admin_AECG_Name_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_AECG_Name_Label.setText("Admin Name");
        Admin_AECG_Left_Panel.add(Admin_AECG_Name_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 320, 40));

        Admin_AECG_Discription_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Admin_AECG_Discription_Label.setForeground(new java.awt.Color(255, 255, 255));
        Admin_AECG_Discription_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_AECG_Discription_Label.setText("Admin | University of Houston");
        Admin_AECG_Left_Panel.add(Admin_AECG_Discription_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 320, 20));

        AECG_Home_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        AECG_Home_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AECG_Home_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AECG_Home_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AECG_Home_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AECG_Home_Button_PanelMouseExited(evt);
            }
        });
        AECG_Home_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AECG_Home_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        AECG_Home_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        AECG_Home_Button_Label.setText("Home");
        AECG_Home_Button_Panel.add(AECG_Home_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        AECG_Home_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AECG_Home_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Home Icon 16x16.png"))); // NOI18N
        AECG_Home_Button_Panel.add(AECG_Home_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_AECG_Left_Panel.add(AECG_Home_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 320, 40));

        AECG_MC_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        AECG_MC_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AECG_MC_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AECG_MC_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AECG_MC_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AECG_MC_Button_PanelMouseExited(evt);
            }
        });
        AECG_MC_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AECG_MC_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        AECG_MC_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        AECG_MC_Button_Label.setText("Manage Courses");
        AECG_MC_Button_Panel.add(AECG_MC_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        AECG_MC_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AECG_MC_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Center Icon 16x16.png"))); // NOI18N
        AECG_MC_Button_Panel.add(AECG_MC_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_AECG_Left_Panel.add(AECG_MC_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 320, 40));

        AECG_MS_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        AECG_MS_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AECG_MS_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AECG_MS_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AECG_MS_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AECG_MS_Button_PanelMouseExited(evt);
            }
        });
        AECG_MS_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AECG_MS_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        AECG_MS_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        AECG_MS_Button_Label.setText("Manage Students");
        AECG_MS_Button_Panel.add(AECG_MS_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        AECG_MS_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AECG_MS_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Records Enrollment 16x16.png"))); // NOI18N
        AECG_MS_Button_Panel.add(AECG_MS_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_AECG_Left_Panel.add(AECG_MS_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 320, 40));

        AECG_SC_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        AECG_SC_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AECG_SC_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AECG_SC_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AECG_SC_Button_PanelMouseExited(evt);
            }
        });
        AECG_SC_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AECG_SC_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        AECG_SC_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        AECG_SC_Button_Label.setText("Student Courses");
        AECG_SC_Button_Panel.add(AECG_SC_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        AECG_SC_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AECG_SC_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Communication Center 16x16.png"))); // NOI18N
        AECG_SC_Button_Panel.add(AECG_SC_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_AECG_Left_Panel.add(AECG_SC_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 320, 40));

        AECG_AECG_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        AECG_AECG_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AECG_AECG_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AECG_AECG_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AECG_AECG_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AECG_AECG_Button_PanelMouseExited(evt);
            }
        });
        AECG_AECG_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AECG_AECG_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        AECG_AECG_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        AECG_AECG_Button_Label.setText("Assign Exam & Course Grades");
        AECG_AECG_Button_Panel.add(AECG_AECG_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        AECG_AECG_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AECG_AECG_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Academic Advising 16x16.png"))); // NOI18N
        AECG_AECG_Button_Panel.add(AECG_AECG_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_AECG_Left_Panel.add(AECG_AECG_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 320, 40));

        AECG_SFA_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        AECG_SFA_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AECG_SFA_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AECG_SFA_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AECG_SFA_Button_PanelMouseExited(evt);
            }
        });
        AECG_SFA_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AECG_SFA_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        AECG_SFA_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        AECG_SFA_Button_Label.setText("Scholarship & Financial Aid");
        AECG_SFA_Button_Panel.add(AECG_SFA_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        AECG_SFA_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AECG_SFA_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Scholarship Financial Aid 16x16.png"))); // NOI18N
        AECG_SFA_Button_Panel.add(AECG_SFA_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_AECG_Left_Panel.add(AECG_SFA_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 320, 40));

        AECG_SF_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        AECG_SF_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AECG_SF_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AECG_SF_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AECG_SF_Button_PanelMouseExited(evt);
            }
        });
        AECG_SF_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AECG_SF_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        AECG_SF_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        AECG_SF_Button_Label.setText("Student Financials");
        AECG_SF_Button_Panel.add(AECG_SF_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        AECG_SF_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AECG_SF_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Financials 16x16.png"))); // NOI18N
        AECG_SF_Button_Panel.add(AECG_SF_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_AECG_Left_Panel.add(AECG_SF_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 320, 40));

        AECG_Logout_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Logout Icon 32x32.png"))); // NOI18N
        AECG_Logout_Button_Icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AECG_Logout_Button_Icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AECG_Logout_Button_IconMouseClicked(evt);
            }
        });
        Admin_AECG_Left_Panel.add(AECG_Logout_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 32, 32));

        AECG_Logout_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        AECG_Logout_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        AECG_Logout_Button_Label.setText("Logout");
        AECG_Logout_Button_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AECG_Logout_Button_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AECG_Logout_Button_LabelMouseClicked(evt);
            }
        });
        Admin_AECG_Left_Panel.add(AECG_Logout_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 645, 75, 40));

        Admin_AECG_Panel.add(Admin_AECG_Left_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 700));

        Admin_AECG_Right_Panel.setBackground(new java.awt.Color(242, 242, 242));
        Admin_AECG_Right_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Student_Information_Panel1.setBackground(new java.awt.Color(255, 255, 255));
        SC_Student_Information_Panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Student_Information_Heading_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SC_Student_Information_Heading_Label1.setText("Student Information");
        SC_Student_Information_Panel1.add(SC_Student_Information_Heading_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));

        SC_Student_ID_Heading_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Student_ID_Heading_Label1.setText("Student ID");
        SC_Student_Information_Panel1.add(SC_Student_ID_Heading_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, 80, 20));

        SC_Student_ID_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Student_ID_Label1.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel1.add(SC_Student_ID_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 45, 140, 20));

        SC_Student_Name_Heading_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Student_Name_Heading_Label1.setText("Student Name");
        SC_Student_Information_Panel1.add(SC_Student_Name_Heading_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 75, 100, 20));

        SC_Student_Name_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Student_Name_Label1.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel1.add(SC_Student_Name_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 75, 140, 20));

        SC_Student_Status_Heading_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Student_Status_Heading_Label1.setText("Student Status");
        SC_Student_Information_Panel1.add(SC_Student_Status_Heading_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 100, 20));

        SC_Student_Status_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Student_Status_Label1.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel1.add(SC_Student_Status_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 105, 140, 20));

        SC_Residency_Status_Heading_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Residency_Status_Heading_Label1.setText("Residency Status");
        SC_Student_Information_Panel1.add(SC_Residency_Status_Heading_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 135, 110, 20));

        SC_Residency_Status_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Residency_Status_Label1.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel1.add(SC_Residency_Status_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 135, 140, 20));

        SC_Class_Standing_Heading_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Class_Standing_Heading_Label1.setText("Class Standing");
        SC_Student_Information_Panel1.add(SC_Class_Standing_Heading_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 165, 110, 20));

        SC_Class_Standing_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Class_Standing_Label1.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel1.add(SC_Class_Standing_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 165, 140, 20));

        SC_Location_Heading_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Location_Heading_Label1.setText("Location");
        SC_Student_Information_Panel1.add(SC_Location_Heading_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 195, 110, 20));

        SC_Location_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Location_Label1.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel1.add(SC_Location_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 195, 140, 20));

        SC_Major_Heading_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Major_Heading_Label1.setText("Major");
        SC_Student_Information_Panel1.add(SC_Major_Heading_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 225, 110, 20));

        SC_Major_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Major_Label1.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel1.add(SC_Major_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 225, 140, 20));

        SC_Contact_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SC_Contact_Label1.setText("Contact");
        SC_Student_Information_Panel1.add(SC_Contact_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 255, 280, 30));

        SC_Phone_Number_Heading_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Phone_Number_Heading_Label1.setText("Phone Number");
        SC_Student_Information_Panel1.add(SC_Phone_Number_Heading_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 100, 20));

        SC_Phone_Number_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Phone_Number_Label1.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel1.add(SC_Phone_Number_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 140, 20));

        SC_Email_Heading_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Email_Heading_Label1.setText("E-Mail");
        SC_Student_Information_Panel1.add(SC_Email_Heading_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 100, 20));

        SC_Email_Address_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Email_Address_Label1.setForeground(new java.awt.Color(102, 102, 102));
        SC_Student_Information_Panel1.add(SC_Email_Address_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 140, 20));

        Admin_AECG_Right_Panel.add(SC_Student_Information_Panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 340, 350));

        SC_Assign_Courses_Panel1.setBackground(new java.awt.Color(255, 255, 255));
        SC_Assign_Courses_Panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AECG_Assign_Exam_Grades_Header_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        AECG_Assign_Exam_Grades_Header_Label.setText("Assign Course Grade");
        SC_Assign_Courses_Panel1.add(AECG_Assign_Exam_Grades_Header_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 210, 30));

        Admin_AECG_Right_Panel.add(SC_Assign_Courses_Panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 370, 485, 320));

        SC_Current_Students_Panel1.setBackground(new java.awt.Color(255, 255, 255));
        SC_Current_Students_Panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Current_Students_Table_Heading_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SC_Current_Students_Table_Heading_Label1.setText("Current Students");
        SC_Current_Students_Panel1.add(SC_Current_Students_Table_Heading_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));

        SC_Search_Button_Panel1.setBackground(new java.awt.Color(255, 255, 255));
        SC_Search_Button_Panel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_Search_Button_Panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Search_Button_Icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Search_Icon_20x20.png"))); // NOI18N
        SC_Search_Button_Panel1.add(SC_Search_Button_Icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, 25, 33));

        SC_Current_Students_Panel1.add(SC_Search_Button_Panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 11, 30, 33));

        SC_Search_Placeholder_Field1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        SC_Search_Placeholder_Field1.setForeground(new java.awt.Color(153, 153, 153));
        SC_Search_Placeholder_Field1.setText("Search Student");
        SC_Search_Placeholder_Field1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        SC_Current_Students_Panel1.add(SC_Search_Placeholder_Field1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 190, 30));

        SC_Search_Student_Field1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SC_Search_Student_Field1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 1, 1)));
        SC_Search_Student_Field1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                SC_Search_Student_Field1FocusLost(evt);
            }
        });
        SC_Search_Student_Field1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SC_Search_Student_Field1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                SC_Search_Student_Field1KeyTyped(evt);
            }
        });
        SC_Current_Students_Panel1.add(SC_Search_Student_Field1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 230, 35));

        SC_Current_Students_Table1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SC_Current_Students_Table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STUDENT ID", "FIRST NAME", "LAST NAME"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        SC_Current_Students_Table1.setRowHeight(20);
        SC_Current_Students_Table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SC_Current_Students_Table1MouseClicked(evt);
            }
        });
        SC_Current_Students_Table_ScrollPane1.setViewportView(SC_Current_Students_Table1);

        SC_Current_Students_Panel1.add(SC_Current_Students_Table_ScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 420, 260));

        Admin_AECG_Right_Panel.add(SC_Current_Students_Panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 440, 320));

        SC_Assign_Courses_Panel2.setBackground(new java.awt.Color(255, 255, 255));
        SC_Assign_Courses_Panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AECG_Assign_Exam_Grades_Header_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        AECG_Assign_Exam_Grades_Header_Label1.setText("Assign Exam Grade");
        SC_Assign_Courses_Panel2.add(AECG_Assign_Exam_Grades_Header_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 210, 30));

        Admin_AECG_Right_Panel.add(SC_Assign_Courses_Panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 585, 350));

        Admin_AECG_Panel.add(Admin_AECG_Right_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 955, 700));

        Main_Frame_Panel.add(Admin_AECG_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1275, 700));

        Admin_MS_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_MS_Left_Panel.setBackground(new java.awt.Color(85, 142, 203));
        Admin_MS_Left_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_MS_Img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_MS_Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/custom user.png"))); // NOI18N
        Admin_MS_Left_Panel.add(Admin_MS_Img, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 30, 130, 130));

        Admin_MS_Name_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        Admin_MS_Name_Label.setForeground(new java.awt.Color(255, 255, 255));
        Admin_MS_Name_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_MS_Name_Label.setText("Admin Name");
        Admin_MS_Left_Panel.add(Admin_MS_Name_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 320, 40));

        Admin_MS_Discription_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Admin_MS_Discription_Label.setForeground(new java.awt.Color(255, 255, 255));
        Admin_MS_Discription_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_MS_Discription_Label.setText("Admin | University of Houston");
        Admin_MS_Left_Panel.add(Admin_MS_Discription_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 320, 20));

        MS_Home_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MS_Home_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MS_Home_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MS_Home_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MS_Home_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MS_Home_Button_PanelMouseExited(evt);
            }
        });
        MS_Home_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MS_Home_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MS_Home_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MS_Home_Button_Label.setText("Home");
        MS_Home_Button_Panel.add(MS_Home_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        MS_Home_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MS_Home_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Home Icon 16x16.png"))); // NOI18N
        MS_Home_Button_Panel.add(MS_Home_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MS_Left_Panel.add(MS_Home_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 320, 40));

        MS_MC_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MS_MC_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MS_MC_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MS_MC_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MS_MC_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MS_MC_Button_PanelMouseExited(evt);
            }
        });
        MS_MC_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MS_MC_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        MS_MC_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MS_MC_Button_Label.setText("Manage Courses");
        MS_MC_Button_Panel.add(MS_MC_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        MS_MC_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MS_MC_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Center Icon 16x16.png"))); // NOI18N
        MS_MC_Button_Panel.add(MS_MC_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MS_Left_Panel.add(MS_MC_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 320, 40));

        MS_SR_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MS_SR_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MS_SR_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MS_SR_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MS_SR_Button_PanelMouseExited(evt);
            }
        });
        MS_SR_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MS_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        MS_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MS_Button_Label.setText("Assign Courses");
        MS_SR_Button_Panel.add(MS_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        MS_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MS_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Communication Center 16x16.png"))); // NOI18N
        MS_SR_Button_Panel.add(MS_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MS_Left_Panel.add(MS_SR_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 320, 40));

        MS_MS_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MS_MS_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MS_MS_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MS_MS_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MS_MS_Button_PanelMouseExited(evt);
            }
        });
        MS_MS_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MS_MS_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        MS_MS_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MS_MS_Button_Label.setText("Manage Students");
        MS_MS_Button_Panel.add(MS_MS_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        MS_MS_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MS_MS_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Records Enrollment 16x16.png"))); // NOI18N
        MS_MS_Button_Panel.add(MS_MS_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MS_Left_Panel.add(MS_MS_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 320, 40));

        MS_AA_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MS_AA_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MS_AA_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MS_AA_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MS_AA_Button_PanelMouseExited(evt);
            }
        });
        MS_AA_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MS_AA_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        MS_AA_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MS_AA_Button_Label.setText("Assign Exam Grades");
        MS_AA_Button_Panel.add(MS_AA_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        MS_AA_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MS_AA_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Academic Advising 16x16.png"))); // NOI18N
        MS_AA_Button_Panel.add(MS_AA_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MS_Left_Panel.add(MS_AA_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 320, 40));

        MS_SFA_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MS_SFA_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MS_SFA_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MS_SFA_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MS_SFA_Button_PanelMouseExited(evt);
            }
        });
        MS_SFA_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MS_SFA_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        MS_SFA_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MS_SFA_Button_Label.setText("Scholarship & Financial Aid");
        MS_SFA_Button_Panel.add(MS_SFA_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        MS_SFA_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MS_SFA_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Scholarship Financial Aid 16x16.png"))); // NOI18N
        MS_SFA_Button_Panel.add(MS_SFA_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MS_Left_Panel.add(MS_SFA_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 320, 40));

        MS_SF_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MS_SF_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MS_SF_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MS_SF_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MS_SF_Button_PanelMouseExited(evt);
            }
        });
        MS_SF_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MS_SF_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        MS_SF_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MS_SF_Button_Label.setText("Student Financials");
        MS_SF_Button_Panel.add(MS_SF_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        MS_SF_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MS_SF_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Financials 16x16.png"))); // NOI18N
        MS_SF_Button_Panel.add(MS_SF_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MS_Left_Panel.add(MS_SF_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 320, 40));

        MS_Logout_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Logout Icon 32x32.png"))); // NOI18N
        MS_Logout_Button_Icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MS_Logout_Button_Icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MS_Logout_Button_IconMouseClicked(evt);
            }
        });
        Admin_MS_Left_Panel.add(MS_Logout_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 32, 32));

        MS_Logout_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        MS_Logout_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MS_Logout_Button_Label.setText("Logout");
        MS_Logout_Button_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MS_Logout_Button_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MS_Logout_Button_LabelMouseClicked(evt);
            }
        });
        Admin_MS_Left_Panel.add(MS_Logout_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 645, 75, 40));

        Admin_MS_Panel.add(Admin_MS_Left_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 700));

        Admin_MS_Right_Panel.setBackground(new java.awt.Color(242, 242, 242));
        Admin_MS_Right_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_Add_Courses__Form_Panel1.setBackground(new java.awt.Color(255, 255, 255));
        Admin_Add_Courses__Form_Panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_MS_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        Admin_MS_Heading_Label.setText("Manage Student");
        Admin_Add_Courses__Form_Panel1.add(Admin_MS_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));

        MS_First_Name_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MS_First_Name_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "First Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses__Form_Panel1.add(MS_First_Name_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 210, 50));

        MS_Last_Name_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MS_Last_Name_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Last Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses__Form_Panel1.add(MS_Last_Name_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 210, 50));

        MS_Student_Status_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MS_Student_Status_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Student Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses__Form_Panel1.add(MS_Student_Status_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 60, 210, 50));

        MS_Residency_Status_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MS_Residency_Status_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Residency Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses__Form_Panel1.add(MS_Residency_Status_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 210, 50));

        MS_Class_Standing_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MS_Class_Standing_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Class Standing", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses__Form_Panel1.add(MS_Class_Standing_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 210, 50));

        MS_Location_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MS_Location_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Location", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses__Form_Panel1.add(MS_Location_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 120, 210, 50));

        MS_Email_Address_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MS_Email_Address_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Email Address", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses__Form_Panel1.add(MS_Email_Address_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 180, 210, 50));

        Admin_MS_Delete_Button.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Admin_MS_Delete_Button.setText("DELETE");
        Admin_MS_Delete_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_MS_Delete_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Admin_MS_Delete_ButtonActionPerformed(evt);
            }
        });
        Admin_Add_Courses__Form_Panel1.add(Admin_MS_Delete_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(652, 190, 85, 35));

        Admin_MS_Insert_Button.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Admin_MS_Insert_Button.setText("INSERT");
        Admin_MS_Insert_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_MS_Insert_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Admin_MS_Insert_ButtonActionPerformed(evt);
            }
        });
        Admin_Add_Courses__Form_Panel1.add(Admin_MS_Insert_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(834, 190, 85, 35));

        Admin_MS_Reset_Button.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Admin_MS_Reset_Button.setText("RESET");
        Admin_MS_Reset_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_MS_Reset_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Admin_MS_Reset_ButtonActionPerformed(evt);
            }
        });
        Admin_Add_Courses__Form_Panel1.add(Admin_MS_Reset_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(561, 190, 85, 35));

        Admin_MS_Update_Button.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Admin_MS_Update_Button.setText("UPDATE");
        Admin_MS_Update_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_MS_Update_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Admin_MS_Update_ButtonActionPerformed(evt);
            }
        });
        Admin_Add_Courses__Form_Panel1.add(Admin_MS_Update_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(743, 190, 85, 35));

        MS_Student_ID_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MS_Student_ID_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Student ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses__Form_Panel1.add(MS_Student_ID_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 210, 50));

        MS_Major_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MS_Major_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Major", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses__Form_Panel1.add(MS_Major_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 120, 210, 50));

        MS_Phone_Number_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MS_Phone_Number_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Phone Number", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses__Form_Panel1.add(MS_Phone_Number_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 210, 50));

        Admin_MS_Right_Panel.add(Admin_Add_Courses__Form_Panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 935, 260));

        MC_Current_Courses_Panel1.setBackground(new java.awt.Color(255, 255, 255));
        MC_Current_Courses_Panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MS_Search_Button_Panel.setBackground(new java.awt.Color(255, 255, 255));
        MS_Search_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MS_Search_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MS_Search_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Search_Icon_20x20.png"))); // NOI18N
        MS_Search_Button_Panel.add(MS_Search_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, 25, 33));

        MC_Current_Courses_Panel1.add(MS_Search_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(889, 8, 30, 33));

        MS_Current_Students_Table.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        MS_Current_Students_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STUDENT ID", "FRIST NAME", "LAST NAME", "STUDENT STATUS", "RESIDENCY STATUS", "CLASS STANDING", "LOCATION", "MAJOR", "PHONE NUMBER", "EMAIL ADDRESS"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        MS_Current_Students_Table.setRowHeight(20);
        MS_Current_Students_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MS_Current_Students_TableMouseClicked(evt);
            }
        });
        MC_Current_Courses_Table_ScrollPane1.setViewportView(MS_Current_Students_Table);

        MC_Current_Courses_Panel1.add(MC_Current_Courses_Table_ScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 915, 350));

        SP_Student_Information_Heading_Label4.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SP_Student_Information_Heading_Label4.setText("Current Students");
        MC_Current_Courses_Panel1.add(SP_Student_Information_Heading_Label4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));

        MS_Search_Placeholder_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        MS_Search_Placeholder_Field.setForeground(new java.awt.Color(153, 153, 153));
        MS_Search_Placeholder_Field.setText("Search Student");
        MS_Search_Placeholder_Field.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        MC_Current_Courses_Panel1.add(MS_Search_Placeholder_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(702, 9, 190, 30));

        MS_Search_Student_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MS_Search_Student_Field.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 1, 1)));
        MS_Search_Student_Field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                MS_Search_Student_FieldFocusLost(evt);
            }
        });
        MS_Search_Student_Field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MS_Search_Student_FieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MS_Search_Student_FieldKeyTyped(evt);
            }
        });
        MC_Current_Courses_Panel1.add(MS_Search_Student_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(691, 7, 230, 35));

        Admin_MS_Right_Panel.add(MC_Current_Courses_Panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 935, 410));

        Admin_MS_Panel.add(Admin_MS_Right_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 955, 700));

        Main_Frame_Panel.add(Admin_MS_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1275, 700));

        Admin_MC_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_MC_Left_Panel.setBackground(new java.awt.Color(85, 142, 203));
        Admin_MC_Left_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_MC_Img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_MC_Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/custom user.png"))); // NOI18N
        Admin_MC_Left_Panel.add(Admin_MC_Img, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 30, 130, 130));

        Admin_MC_Name_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        Admin_MC_Name_Label.setForeground(new java.awt.Color(255, 255, 255));
        Admin_MC_Name_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_MC_Name_Label.setText("Admin Name");
        Admin_MC_Left_Panel.add(Admin_MC_Name_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 320, 40));

        Admin_MC_Discription_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Admin_MC_Discription_Label.setForeground(new java.awt.Color(255, 255, 255));
        Admin_MC_Discription_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_MC_Discription_Label.setText("Admin | University of Houston");
        Admin_MC_Left_Panel.add(Admin_MC_Discription_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 320, 20));

        MC_Home_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MC_Home_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MC_Home_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MC_Home_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MC_Home_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MC_Home_Button_PanelMouseExited(evt);
            }
        });
        MC_Home_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MC_Home_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MC_Home_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MC_Home_Button_Label.setText("Home");
        MC_Home_Button_Panel.add(MC_Home_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        MC_Home_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MC_Home_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Home Icon 16x16.png"))); // NOI18N
        MC_Home_Button_Panel.add(MC_Home_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MC_Left_Panel.add(MC_Home_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 320, 40));

        MC_MC_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MC_MC_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MC_MC_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MC_MC_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MC_MC_Button_PanelMouseExited(evt);
            }
        });
        MC_MC_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MC_MC_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        MC_MC_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MC_MC_Button_Label.setText("Manage Courses");
        MC_MC_Button_Panel.add(MC_MC_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        MC_MC_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MC_MC_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Center Icon 16x16.png"))); // NOI18N
        MC_MC_Button_Panel.add(MC_MC_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MC_Left_Panel.add(MC_MC_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 320, 40));

        MC_SR_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MC_SR_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MC_SR_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MC_SR_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MC_SR_Button_PanelMouseExited(evt);
            }
        });
        MC_SR_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SR_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        SR_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SR_Button_Label.setText("Assign Courses");
        MC_SR_Button_Panel.add(SR_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SR_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SR_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Communication Center 16x16.png"))); // NOI18N
        MC_SR_Button_Panel.add(SR_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MC_Left_Panel.add(MC_SR_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 320, 40));

        MC_MS_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MC_MS_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MC_MS_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MC_MS_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MC_MS_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MC_MS_Button_PanelMouseExited(evt);
            }
        });
        MC_MS_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MC_MS_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        MC_MS_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MC_MS_Button_Label.setText("Manage Students");
        MC_MS_Button_Panel.add(MC_MS_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        MC_MS_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MC_MS_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Records Enrollment 16x16.png"))); // NOI18N
        MC_MS_Button_Panel.add(MC_MS_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MC_Left_Panel.add(MC_MS_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 320, 40));

        MC_AA_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MC_AA_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MC_AA_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MC_AA_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MC_AA_Button_PanelMouseExited(evt);
            }
        });
        MC_AA_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MC_AA_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        MC_AA_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MC_AA_Button_Label.setText("Assign Exam Grades");
        MC_AA_Button_Panel.add(MC_AA_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        MC_AA_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MC_AA_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Academic Advising 16x16.png"))); // NOI18N
        MC_AA_Button_Panel.add(MC_AA_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MC_Left_Panel.add(MC_AA_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 320, 40));

        MC_SFA_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MC_SFA_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MC_SFA_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MC_SFA_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MC_SFA_Button_PanelMouseExited(evt);
            }
        });
        MC_SFA_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MC_SFA_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        MC_SFA_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MC_SFA_Button_Label.setText("Scholarship & Financial Aid");
        MC_SFA_Button_Panel.add(MC_SFA_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        MC_SFA_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MC_SFA_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Scholarship Financial Aid 16x16.png"))); // NOI18N
        MC_SFA_Button_Panel.add(MC_SFA_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MC_Left_Panel.add(MC_SFA_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 320, 40));

        MC_SF_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MC_SF_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MC_SF_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MC_SF_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                MC_SF_Button_PanelMouseExited(evt);
            }
        });
        MC_SF_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MC_SF_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        MC_SF_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MC_SF_Button_Label.setText("Student Financials");
        MC_SF_Button_Panel.add(MC_SF_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        MC_SF_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MC_SF_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Financials 16x16.png"))); // NOI18N
        MC_SF_Button_Panel.add(MC_SF_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Admin_MC_Left_Panel.add(MC_SF_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 320, 40));

        MC_Logout_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Logout Icon 32x32.png"))); // NOI18N
        MC_Logout_Button_Icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MC_Logout_Button_Icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MC_Logout_Button_IconMouseClicked(evt);
            }
        });
        Admin_MC_Left_Panel.add(MC_Logout_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 32, 32));

        MC_Logout_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        MC_Logout_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MC_Logout_Button_Label.setText("Logout");
        MC_Logout_Button_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MC_Logout_Button_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MC_Logout_Button_LabelMouseClicked(evt);
            }
        });
        Admin_MC_Left_Panel.add(MC_Logout_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 645, 75, 40));

        Admin_MC_Panel.add(Admin_MC_Left_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 700));

        Admin_MC_Right_Panel.setBackground(new java.awt.Color(242, 242, 242));
        Admin_MC_Right_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_Add_Courses_Form_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Admin_Add_Courses_Form_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SP_Student_Information_Heading_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SP_Student_Information_Heading_Label1.setText("Manage Courses");
        Admin_Add_Courses_Form_Panel.add(SP_Student_Information_Heading_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));

        MC_Course_No_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MC_Course_No_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Course No.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses_Form_Panel.add(MC_Course_No_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 230, 50));

        MC_Course_Description_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MC_Course_Description_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Course Description", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses_Form_Panel.add(MC_Course_Description_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 360, 50));

        MC_Room_No_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MC_Room_No_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Room No.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses_Form_Panel.add(MC_Room_No_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 230, 50));

        MC_Instructor_Name_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MC_Instructor_Name_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Instructor Name", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses_Form_Panel.add(MC_Instructor_Name_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 230, 50));

        MC_Days_Time_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MC_Days_Time_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Days & Time", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses_Form_Panel.add(MC_Days_Time_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 120, 270, 50));

        MC_Start_Date_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MC_Start_Date_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Start Date", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses_Form_Panel.add(MC_Start_Date_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 230, 50));

        MC_Units_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MC_Units_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Units", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses_Form_Panel.add(MC_Units_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(793, 120, 87, 50));

        Admin_MC_Delete_Button.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Admin_MC_Delete_Button.setText("DELETE");
        Admin_MC_Delete_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_MC_Delete_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Admin_MC_Delete_ButtonActionPerformed(evt);
            }
        });
        Admin_Add_Courses_Form_Panel.add(Admin_MC_Delete_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(612, 190, 85, 35));

        Admin_MC_Insert_Button.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Admin_MC_Insert_Button.setText("INSERT");
        Admin_MC_Insert_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_MC_Insert_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Admin_MC_Insert_ButtonActionPerformed(evt);
            }
        });
        Admin_Add_Courses_Form_Panel.add(Admin_MC_Insert_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(794, 190, 85, 35));

        Admin_MC_Reset_Button.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Admin_MC_Reset_Button.setText("RESET");
        Admin_MC_Reset_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_MC_Reset_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Admin_MC_Reset_ButtonActionPerformed(evt);
            }
        });
        Admin_Add_Courses_Form_Panel.add(Admin_MC_Reset_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(521, 190, 85, 35));

        Admin_MC_Update_Button.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Admin_MC_Update_Button.setText("UPDATE");
        Admin_MC_Update_Button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_MC_Update_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Admin_MC_Update_ButtonActionPerformed(evt);
            }
        });
        Admin_Add_Courses_Form_Panel.add(Admin_MC_Update_Button, new org.netbeans.lib.awtextra.AbsoluteConstraints(703, 190, 85, 35));

        MC_Class_ID_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MC_Class_ID_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Class ID", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses_Form_Panel.add(MC_Class_ID_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 230, 50));

        MC_End_Date_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MC_End_Date_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "End Date", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 0, 12))); // NOI18N
        Admin_Add_Courses_Form_Panel.add(MC_End_Date_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 230, 50));

        Admin_MC_Right_Panel.add(Admin_Add_Courses_Form_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 935, 260));

        MC_Current_Courses_Panel.setBackground(new java.awt.Color(255, 255, 255));
        MC_Current_Courses_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MC_Search_Button_Panel.setBackground(new java.awt.Color(255, 255, 255));
        MC_Search_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MC_Search_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MC_Search_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Search_Icon_20x20.png"))); // NOI18N
        MC_Search_Button_Panel.add(MC_Search_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, 25, 33));

        MC_Current_Courses_Panel.add(MC_Search_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(889, 8, 30, 33));

        MC_Current_Courses_Table.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        MC_Current_Courses_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CLASS ID", "COURSE NO.", "COURSE DESCRIPTION", "ROOM NO.", "INSTRUCTOR", "DAYS & TIME", "START DATE", "END DATE"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        MC_Current_Courses_Table.setRowHeight(20);
        MC_Current_Courses_Table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MC_Current_Courses_TableMouseClicked(evt);
            }
        });
        MC_Current_Courses_Table_ScrollPane.setViewportView(MC_Current_Courses_Table);

        MC_Current_Courses_Panel.add(MC_Current_Courses_Table_ScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 915, 350));

        SP_Student_Information_Heading_Label2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SP_Student_Information_Heading_Label2.setText("Current Courses");
        MC_Current_Courses_Panel.add(SP_Student_Information_Heading_Label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));

        MC_Search_Placeholder_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        MC_Search_Placeholder_Field.setForeground(new java.awt.Color(153, 153, 153));
        MC_Search_Placeholder_Field.setText("Search Courses");
        MC_Search_Placeholder_Field.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        MC_Current_Courses_Panel.add(MC_Search_Placeholder_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(702, 9, 190, 30));

        MC_Search_Courses_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        MC_Search_Courses_Field.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), javax.swing.BorderFactory.createEmptyBorder(0, 10, 1, 1)));
        MC_Search_Courses_Field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                MC_Search_Courses_FieldFocusLost(evt);
            }
        });
        MC_Search_Courses_Field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MC_Search_Courses_FieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MC_Search_Courses_FieldKeyTyped(evt);
            }
        });
        MC_Current_Courses_Panel.add(MC_Search_Courses_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(691, 7, 230, 35));

        Admin_MC_Right_Panel.add(MC_Current_Courses_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 935, 410));

        Admin_MC_Panel.add(Admin_MC_Right_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 955, 700));

        Main_Frame_Panel.add(Admin_MC_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1275, 700));

        Admin_Main_Page_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_Main_Page_Left_Panel.setBackground(new java.awt.Color(85, 142, 203));
        Admin_Main_Page_Left_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_Img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/custom user.png"))); // NOI18N
        Admin_Main_Page_Left_Panel.add(Admin_Img, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 30, 130, 130));

        Admin_Name_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        Admin_Name_Label.setForeground(new java.awt.Color(255, 255, 255));
        Admin_Name_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_Name_Label.setText("Admin Name");
        Admin_Main_Page_Left_Panel.add(Admin_Name_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 320, 40));

        Admin_Discription_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Admin_Discription_Label.setForeground(new java.awt.Color(255, 255, 255));
        Admin_Discription_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_Discription_Label.setText("Admin | University of Houston");
        Admin_Main_Page_Left_Panel.add(Admin_Discription_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 320, 20));

        Admin_Main_Page_Logout_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Logout Icon 32x32.png"))); // NOI18N
        Admin_Main_Page_Logout_Button_Icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_Main_Page_Logout_Button_Icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Admin_Main_Page_Logout_Button_IconMouseClicked(evt);
            }
        });
        Admin_Main_Page_Left_Panel.add(Admin_Main_Page_Logout_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 32, 32));

        Admin_Main_Page_Logout_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        Admin_Main_Page_Logout_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        Admin_Main_Page_Logout_Button_Label.setText("Logout");
        Admin_Main_Page_Logout_Button_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_Main_Page_Logout_Button_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Admin_Main_Page_Logout_Button_LabelMouseClicked(evt);
            }
        });
        Admin_Main_Page_Left_Panel.add(Admin_Main_Page_Logout_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 645, 75, 40));

        Admin_Main_Page_Panel.add(Admin_Main_Page_Left_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 700));

        Admin_Main_Page_Right_Panel.setBackground(new java.awt.Color(242, 242, 242));
        Admin_Main_Page_Right_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_Manage_Courses_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Admin_Manage_Courses_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_Manage_Courses_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Admin_Manage_Courses_PanelMouseClicked(evt);
            }
        });
        Admin_Manage_Courses_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_MC_Tile_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        Admin_MC_Tile_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_MC_Tile_Label.setText("Manage Courses");
        Admin_MC_Tile_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Admin_Manage_Courses_Panel.add(Admin_MC_Tile_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 20));

        Admin_MC_img_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_MC_img_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/home_tiles/Student Center.png"))); // NOI18N
        Admin_Manage_Courses_Panel.add(Admin_MC_img_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        Admin_Main_Page_Right_Panel.add(Admin_Manage_Courses_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 200, 200));

        Admin_Manage_Student_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Admin_Manage_Student_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_Manage_Student_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Admin_Manage_Student_PanelMouseClicked(evt);
            }
        });
        Admin_Manage_Student_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_MS_Tile_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        Admin_MS_Tile_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_MS_Tile_Label.setText("Manage Students");
        Admin_MS_Tile_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Admin_Manage_Student_Panel.add(Admin_MS_Tile_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 20));

        Admin_MS_img_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_MS_img_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/home_tiles/Student Records & Enrollment.png"))); // NOI18N
        Admin_Manage_Student_Panel.add(Admin_MS_img_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        Admin_Main_Page_Right_Panel.add(Admin_Manage_Student_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 200, 200));

        Assign_Courses_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Assign_Courses_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Assign_Courses_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_SR_Tile_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        Admin_SR_Tile_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_SR_Tile_Label.setText("Assign Courses");
        Admin_SR_Tile_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Assign_Courses_Panel.add(Admin_SR_Tile_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 20));

        Admin_SR_img_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_SR_img_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/home_tiles/Student Communication Center.png"))); // NOI18N
        Admin_SR_img_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Admin_SR_img_LabelMouseClicked(evt);
            }
        });
        Assign_Courses_Panel.add(Admin_SR_img_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        Admin_Main_Page_Right_Panel.add(Assign_Courses_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 200, 200));

        Admin_Assign_Exam_Grades_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Admin_Assign_Exam_Grades_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_Assign_Exam_Grades_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_AA_Tile_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        Admin_AA_Tile_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_AA_Tile_Label.setText("Assign Exam Grades");
        Admin_AA_Tile_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Admin_Assign_Exam_Grades_Panel.add(Admin_AA_Tile_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 20));

        Admin_SRE_img_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_SRE_img_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/home_tiles/Academic Advising.png"))); // NOI18N
        Admin_Assign_Exam_Grades_Panel.add(Admin_SRE_img_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        Admin_Main_Page_Right_Panel.add(Admin_Assign_Exam_Grades_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 200, 200));

        Admin_Scholarships_Financial_Aid_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Admin_Scholarships_Financial_Aid_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_Scholarships_Financial_Aid_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_SFA_Tile_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        Admin_SFA_Tile_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_SFA_Tile_Label.setText("Scholarships");
        Admin_SFA_Tile_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Admin_Scholarships_Financial_Aid_Panel.add(Admin_SFA_Tile_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 20));

        Admin_SFA_Tile_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        Admin_SFA_Tile_Label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_SFA_Tile_Label1.setText("& Financial Aid");
        Admin_SFA_Tile_Label1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Admin_Scholarships_Financial_Aid_Panel.add(Admin_SFA_Tile_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 200, 20));

        Admin_SFA_img_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_SFA_img_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/home_tiles/Scholarship & Financial Aid.png"))); // NOI18N
        Admin_Scholarships_Financial_Aid_Panel.add(Admin_SFA_img_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        Admin_Main_Page_Right_Panel.add(Admin_Scholarships_Financial_Aid_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 400, 200, 200));

        Admin_Student_Financials_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Admin_Student_Financials_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Admin_Student_Financials_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Admin_SF_Tile_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        Admin_SF_Tile_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_SF_Tile_Label.setText("Student Financials");
        Admin_SF_Tile_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Admin_Student_Financials_Panel.add(Admin_SF_Tile_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 20));

        Admin_SF_img_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Admin_SF_img_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/home_tiles/Student Financials.png"))); // NOI18N
        Admin_Student_Financials_Panel.add(Admin_SF_img_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        Admin_Main_Page_Right_Panel.add(Admin_Student_Financials_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 400, 200, 200));

        Admin_Main_Page_Panel.add(Admin_Main_Page_Right_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 955, 700));

        Main_Frame_Panel.add(Admin_Main_Page_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1275, 700));

        Student_SRE_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Student_SRE_Right_Panel.setBackground(new java.awt.Color(242, 242, 242));
        Student_SRE_Right_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SP_Student_Information_Panel.setBackground(new java.awt.Color(255, 255, 255));
        SP_Student_Information_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SP_Student_Information_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SP_Student_Information_Heading_Label.setText("Student Information");
        SP_Student_Information_Panel.add(SP_Student_Information_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 30));

        SP_Student_ID_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Student_ID_Heading_Label.setText("Student ID");
        SP_Student_Information_Panel.add(SP_Student_ID_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 45, 80, 20));

        SP_Student_ID_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Student_ID_Label.setForeground(new java.awt.Color(102, 102, 102));
        SP_Student_ID_Label.setText("ID-0001");
        SP_Student_Information_Panel.add(SP_Student_ID_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 45, 160, 20));

        SP_Student_Status_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Student_Status_Heading_Label.setText("Student Status");
        SP_Student_Information_Panel.add(SP_Student_Status_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 100, 20));

        SP_Student_Status_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Student_Status_Label.setForeground(new java.awt.Color(102, 102, 102));
        SP_Student_Status_Label.setText("Active");
        SP_Student_Information_Panel.add(SP_Student_Status_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 190, 20));

        SP_Residency_Status_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Residency_Status_Heading_Label.setText("Residency Status");
        SP_Student_Information_Panel.add(SP_Residency_Status_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 110, 20));

        SP_Residency_Status_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Residency_Status_Label.setForeground(new java.awt.Color(102, 102, 102));
        SP_Residency_Status_Label.setText("Non-Resident");
        SP_Student_Information_Panel.add(SP_Residency_Status_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 190, 20));

        SP_Class_Standing_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Class_Standing_Heading_Label.setText("Class Standing");
        SP_Student_Information_Panel.add(SP_Class_Standing_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 110, 20));

        SP_Class_Standing_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Class_Standing_Label.setForeground(new java.awt.Color(102, 102, 102));
        SP_Class_Standing_Label.setText("Senior");
        SP_Student_Information_Panel.add(SP_Class_Standing_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 190, 20));

        SP_Location_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Location_Heading_Label.setText("Location");
        SP_Student_Information_Panel.add(SP_Location_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 110, 20));

        SP_Location_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Location_Label.setForeground(new java.awt.Color(102, 102, 102));
        SP_Location_Label.setText("Main Campus");
        SP_Student_Information_Panel.add(SP_Location_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 190, 20));

        SP_Major_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Major_Heading_Label.setText("Major");
        SP_Student_Information_Panel.add(SP_Major_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 110, 20));

        SP_Major_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Major_Label.setForeground(new java.awt.Color(102, 102, 102));
        SP_Major_Label.setText("Computer Science");
        SP_Student_Information_Panel.add(SP_Major_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 190, 20));

        SP_Contact_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SP_Contact_Label.setText("Contact");
        SP_Student_Information_Panel.add(SP_Contact_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 460, 30));

        SP_Phone_Number_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Phone_Number_Heading_Label.setText("Phone Number");
        SP_Student_Information_Panel.add(SP_Phone_Number_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 100, 20));

        SP_Phone_Number_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Phone_Number_Label.setForeground(new java.awt.Color(102, 102, 102));
        SP_Phone_Number_Label.setText("832-435-8524");
        SP_Student_Information_Panel.add(SP_Phone_Number_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 190, 20));

        SP_Email_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Email_Heading_Label.setText("E-Mail");
        SP_Student_Information_Panel.add(SP_Email_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 100, 20));

        SP_Email_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Email_Label.setForeground(new java.awt.Color(102, 102, 102));
        SP_Email_Label.setText("student@uh.edu");
        SP_Student_Information_Panel.add(SP_Email_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 190, 20));

        Student_SRE_Right_Panel.add(SP_Student_Information_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 350, 350));

        SP_Registered_Courses_Panel.setBackground(new java.awt.Color(255, 255, 255));
        SP_Registered_Courses_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SP_Registered_Courses_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SP_Registered_Courses_Heading_Label.setText("Registered Courses (Current Semester)");
        SP_Registered_Courses_Panel.add(SP_Registered_Courses_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 560, 30));

        SP_Registered_Courses_Table.setAutoCreateRowSorter(true);
        SP_Registered_Courses_Table.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        SP_Registered_Courses_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COURSE #", "COURSE DESCRIPTION", "ROOM", "INSTRUCTOR", "START DATE", "END DATE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SP_Registered_Courses_Table.setGridColor(new java.awt.Color(0, 41, 85));
        SP_Registered_Courses_Table.setIntercellSpacing(new java.awt.Dimension(0, 0));
        SP_Registered_Courses_Table.setRowHeight(20);
        SP_Registered_Courses_Table.setShowGrid(true);
        SP_Registered_Courses_Table_ScrollPane.setViewportView(SP_Registered_Courses_Table);

        SP_Registered_Courses_Panel.add(SP_Registered_Courses_Table_ScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 560, 280));

        Student_SRE_Right_Panel.add(SP_Registered_Courses_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 580, 350));

        SP_Exam_Grades_Panel.setBackground(new java.awt.Color(255, 255, 255));
        SP_Exam_Grades_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SP_Exam_Grades_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SP_Exam_Grades_Heading_Label.setText("Exam Grades");
        SP_Exam_Grades_Panel.add(SP_Exam_Grades_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 260, 30));

        SP_Course_Selection_ComboBox.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        SP_Course_Selection_ComboBox.setMaximumRowCount(10);
        SP_Course_Selection_ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<Select Course>" }));
        SP_Course_Selection_ComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SP_Course_Selection_ComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SP_Course_Selection_ComboBoxActionPerformed(evt);
            }
        });
        SP_Exam_Grades_Panel.add(SP_Course_Selection_ComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 190, 30));

        SP_Exam_Grades_Table.setAutoCreateRowSorter(true);
        SP_Exam_Grades_Table.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        SP_Exam_Grades_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EXAM NAME", "WEIGHT (%)", "SCORE RECEIVED (%)", "GRADE EARNED (%)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SP_Exam_Grades_Table.setGridColor(new java.awt.Color(0, 41, 85));
        SP_Exam_Grades_Table.setIntercellSpacing(new java.awt.Dimension(0, 0));
        SP_Exam_Grades_Table.setRowHeight(20);
        SP_Exam_Grades_Table.setShowGrid(true);
        SP_Exam_Grades_Table_ScrollPane.setViewportView(SP_Exam_Grades_Table);

        SP_Exam_Grades_Panel.add(SP_Exam_Grades_Table_ScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 460, 250));

        Student_SRE_Right_Panel.add(SP_Exam_Grades_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 480, 320));

        SP_GPA_Panel.setBackground(new java.awt.Color(255, 255, 255));
        SP_GPA_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SP_GPA_Heading_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SP_GPA_Heading_Label.setText("GPA (Current Semester)");
        SP_GPA_Panel.add(SP_GPA_Heading_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 270, 30));

        SP_GPA_Table.setAutoCreateRowSorter(true);
        SP_GPA_Table.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        SP_GPA_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "COURSE #", "COURSE DESCRIPTION", "UNITS", "GRADE", "POINTS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        SP_GPA_Table.setGridColor(new java.awt.Color(0, 41, 85));
        SP_GPA_Table.setIntercellSpacing(new java.awt.Dimension(0, 0));
        SP_GPA_Table.setRowHeight(20);
        SP_GPA_Table.setShowGrid(true);
        SP_GPA_Table_ScrollPane.setViewportView(SP_GPA_Table);

        SP_GPA_Panel.add(SP_GPA_Table_ScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 430, 180));

        SP_GPA_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 40)); // NOI18N
        SP_GPA_Label.setForeground(new java.awt.Color(0, 41, 85));
        SP_GPA_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SP_GPA_Panel.add(SP_GPA_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 275, 430, 40));

        jButton1.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        jButton1.setText("Calculate GPA");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        SP_GPA_Panel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 13, -1, 30));

        SP_GPA_Formula_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        SP_GPA_Formula_Label.setForeground(new java.awt.Color(0, 41, 85));
        SP_GPA_Formula_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SP_GPA_Panel.add(SP_GPA_Formula_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 237, 430, 40));

        Student_SRE_Right_Panel.add(SP_GPA_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 370, 450, 320));

        Student_SRE_Panel.add(Student_SRE_Right_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 955, 700));

        Student_SRE_Left_Panel.setBackground(new java.awt.Color(85, 142, 203));
        Student_SRE_Left_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        User_Img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        User_Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/custom user.png"))); // NOI18N
        Student_SRE_Left_Panel.add(User_Img, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 30, 130, 130));

        Student_Name_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        Student_Name_Label.setForeground(new java.awt.Color(255, 255, 255));
        Student_Name_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Student_Name_Label.setText("Student Name");
        Student_SRE_Left_Panel.add(Student_Name_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 320, 40));

        Student_Discription_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        Student_Discription_Label.setForeground(new java.awt.Color(255, 255, 255));
        Student_Discription_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Student_Discription_Label.setText("Student | University of Houston");
        Student_SRE_Left_Panel.add(Student_Discription_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 320, 20));

        Home_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        Home_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Home_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Home_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Home_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Home_Button_PanelMouseExited(evt);
            }
        });
        Home_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SP_Home_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        SP_Home_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SP_Home_Button_Label.setText("Home");
        Home_Button_Panel.add(SP_Home_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SP_Home_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SP_Home_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Home Icon 16x16.png"))); // NOI18N
        Home_Button_Panel.add(SP_Home_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Student_SRE_Left_Panel.add(Home_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 320, 40));

        SC_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        SC_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SC_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SC_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SC_Button_PanelMouseExited(evt);
            }
        });
        SC_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SP_SC_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        SP_SC_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SP_SC_Button_Label.setText("Student Center");
        SC_Button_Panel.add(SP_SC_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SP_SC_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SP_SC_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Center Icon 16x16.png"))); // NOI18N
        SC_Button_Panel.add(SP_SC_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Student_SRE_Left_Panel.add(SC_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 320, 40));

        SCC_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        SCC_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SCC_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SCC_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SCC_Button_PanelMouseExited(evt);
            }
        });
        SCC_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SP_SCC_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        SP_SCC_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SP_SCC_Button_Label.setText("Student Communication Center");
        SCC_Button_Panel.add(SP_SCC_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SP_SCC_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SP_SCC_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Communication Center 16x16.png"))); // NOI18N
        SCC_Button_Panel.add(SP_SCC_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Student_SRE_Left_Panel.add(SCC_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 320, 40));

        AA_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        AA_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        AA_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                AA_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                AA_Button_PanelMouseExited(evt);
            }
        });
        AA_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SP_AA_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        SP_AA_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SP_AA_Button_Label.setText("Academic Advising");
        AA_Button_Panel.add(SP_AA_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SP_AA_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SP_AA_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Academic Advising 16x16.png"))); // NOI18N
        AA_Button_Panel.add(SP_AA_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Student_SRE_Left_Panel.add(AA_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 320, 40));

        SRE_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        SRE_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SRE_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SRE_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SRE_Button_PanelMouseExited(evt);
            }
        });
        SRE_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SP_SRE_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        SP_SRE_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SP_SRE_Button_Label.setText("Student Records & Enrollment");
        SRE_Button_Panel.add(SP_SRE_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SP_SRE_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SP_SRE_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Records Enrollment 16x16.png"))); // NOI18N
        SRE_Button_Panel.add(SP_SRE_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Student_SRE_Left_Panel.add(SRE_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 320, 40));

        SFA_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        SFA_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SFA_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SFA_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SFA_Button_PanelMouseExited(evt);
            }
        });
        SFA_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SP_SFA_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        SP_SFA_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SP_SFA_Button_Label.setText("Scholarship & Financial Aid");
        SFA_Button_Panel.add(SP_SFA_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SP_SFA_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SP_SFA_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Scholarship Financial Aid 16x16.png"))); // NOI18N
        SFA_Button_Panel.add(SP_SFA_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Student_SRE_Left_Panel.add(SFA_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 320, 40));

        SF_Button_Panel.setBackground(new java.awt.Color(85, 142, 203));
        SF_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SF_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                SF_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                SF_Button_PanelMouseExited(evt);
            }
        });
        SF_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SP_SF_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 17)); // NOI18N
        SP_SF_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SP_SF_Button_Label.setText("Student Financials");
        SF_Button_Panel.add(SP_SF_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 0, 250, 40));

        SP_SF_Button_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SP_SF_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Student Financials 16x16.png"))); // NOI18N
        SF_Button_Panel.add(SP_SF_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 5, 35, 35));

        Student_SRE_Left_Panel.add(SF_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 320, 40));

        SP_Logout_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Logout Icon 32x32.png"))); // NOI18N
        SP_Logout_Button_Icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SP_Logout_Button_Icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SP_Logout_Button_IconMouseClicked(evt);
            }
        });
        Student_SRE_Left_Panel.add(SP_Logout_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 32, 32));

        SP_Logout_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        SP_Logout_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        SP_Logout_Button_Label.setText("Logout");
        SP_Logout_Button_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SP_Logout_Button_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SP_Logout_Button_LabelMouseClicked(evt);
            }
        });
        Student_SRE_Left_Panel.add(SP_Logout_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 645, 75, 40));

        Student_SRE_Panel.add(Student_SRE_Left_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 700));

        Main_Frame_Panel.add(Student_SRE_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1275, 700));

        Student_Main_Page_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Student_Main_Page_Left_Panel.setBackground(new java.awt.Color(85, 142, 203));
        Student_Main_Page_Left_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MP_User_Img.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MP_User_Img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/custom user.png"))); // NOI18N
        Student_Main_Page_Left_Panel.add(MP_User_Img, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 30, 130, 130));

        MP_User_Name_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        MP_User_Name_Label.setForeground(new java.awt.Color(255, 255, 255));
        MP_User_Name_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MP_User_Name_Label.setText("Student Name");
        Student_Main_Page_Left_Panel.add(MP_User_Name_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 320, 40));

        MP_Student_Discription_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        MP_Student_Discription_Label.setForeground(new java.awt.Color(255, 255, 255));
        MP_Student_Discription_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MP_Student_Discription_Label.setText("Student | University of Houston");
        Student_Main_Page_Left_Panel.add(MP_Student_Discription_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 320, 20));

        MP_Logout_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Logout Icon 32x32.png"))); // NOI18N
        MP_Logout_Button_Icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MP_Logout_Button_Icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MP_Logout_Button_IconMouseClicked(evt);
            }
        });
        Student_Main_Page_Left_Panel.add(MP_Logout_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, 32, 32));

        MP_Logout_Button_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        MP_Logout_Button_Label.setForeground(new java.awt.Color(255, 255, 255));
        MP_Logout_Button_Label.setText("Logout");
        MP_Logout_Button_Label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MP_Logout_Button_Label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MP_Logout_Button_LabelMouseClicked(evt);
            }
        });
        Student_Main_Page_Left_Panel.add(MP_Logout_Button_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 645, 75, 40));

        MP_Class_Schedule_Panel.setBackground(new java.awt.Color(0, 0, 0));
        MP_Class_Schedule_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MP_Class_Schedule_Title_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MP_Class_Schedule_Title_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MP_Class_Schedule_Title_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 20)); // NOI18N
        MP_Class_Schedule_Title_Label.setForeground(new java.awt.Color(255, 255, 255));
        MP_Class_Schedule_Title_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MP_Class_Schedule_Title_Label.setText("Class Schedule (Current Semester)");
        MP_Class_Schedule_Title_Panel.add(MP_Class_Schedule_Title_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 40));

        MP_Class_Schedule_Panel.add(MP_Class_Schedule_Title_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 50));

        MP_Class_Schedule_Header_Panel.setBackground(new java.awt.Color(85, 142, 203));
        MP_Class_Schedule_Header_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MP_Class_Header_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        MP_Class_Header_Label.setForeground(new java.awt.Color(255, 255, 255));
        MP_Class_Header_Label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MP_Class_Header_Label.setText("CLASS");
        MP_Class_Schedule_Header_Panel.add(MP_Class_Header_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, 70, 30));

        MP_Room_Header_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        MP_Room_Header_Label.setForeground(new java.awt.Color(255, 255, 255));
        MP_Room_Header_Label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MP_Room_Header_Label.setText("ROOM");
        MP_Class_Schedule_Header_Panel.add(MP_Room_Header_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(92, 0, 60, 30));

        MP_Days_Time_Header_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        MP_Days_Time_Header_Label.setForeground(new java.awt.Color(255, 255, 255));
        MP_Days_Time_Header_Label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        MP_Days_Time_Header_Label.setText("DAYS & TIME");
        MP_Class_Schedule_Header_Panel.add(MP_Days_Time_Header_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 150, 30));

        MP_Class_Schedule_Panel.add(MP_Class_Schedule_Header_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 320, 30));

        Class_Schedule_ScrollPane.setBackground(new java.awt.Color(7, 114, 220));
        Class_Schedule_ScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        Class_Schedule_ScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        Class_Schedule_ScrollPane.setOpaque(false);
        Class_Schedule_ScrollPane.setPreferredSize(new java.awt.Dimension(0, 0));
        Class_Schedule_ScrollPane.setWheelScrollingEnabled(false);

        MP_Class_Schedule_Table.setBackground(new java.awt.Color(85, 142, 203));
        MP_Class_Schedule_Table.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        MP_Class_Schedule_Table.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 15)); // NOI18N
        MP_Class_Schedule_Table.setForeground(new java.awt.Color(255, 255, 255));
        MP_Class_Schedule_Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"CS 3321", "Online", "We 4:00PM - 5:15PM"},
                {"CS 4303", "Online", "Tu 11:30AM - 12:45PM"},
                {"CS 4318", "Online", "MoWe 1:00PM - 2:15PM"},
                {"CS 4332", "Online", "We 2:30PM - 3:45PM"},
                {"MATH 3302", "Online", "TuTh 10:00AM - 11:15AM"},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        MP_Class_Schedule_Table.setAutoscrolls(false);
        MP_Class_Schedule_Table.setDragEnabled(true);
        MP_Class_Schedule_Table.setFocusable(false);
        MP_Class_Schedule_Table.setGridColor(new java.awt.Color(255, 255, 255));
        MP_Class_Schedule_Table.setIntercellSpacing(new java.awt.Dimension(0, 0));
        MP_Class_Schedule_Table.setMaximumSize(new java.awt.Dimension(320, 292));
        MP_Class_Schedule_Table.setMinimumSize(new java.awt.Dimension(320, 292));
        MP_Class_Schedule_Table.setPreferredSize(new java.awt.Dimension(320, 292));
        MP_Class_Schedule_Table.setRowHeight(40);
        MP_Class_Schedule_Table.setRowSelectionAllowed(false);
        MP_Class_Schedule_Table.setSelectionBackground(new java.awt.Color(7, 114, 220));
        MP_Class_Schedule_Table.setShowGrid(true);
        MP_Class_Schedule_Table.setShowHorizontalLines(false);
        MP_Class_Schedule_Table.setShowVerticalLines(false);
        Class_Schedule_ScrollPane.setViewportView(MP_Class_Schedule_Table);
        if (MP_Class_Schedule_Table.getColumnModel().getColumnCount() > 0) {
            MP_Class_Schedule_Table.getColumnModel().getColumn(0).setResizable(false);
            MP_Class_Schedule_Table.getColumnModel().getColumn(1).setResizable(false);
            MP_Class_Schedule_Table.getColumnModel().getColumn(2).setResizable(false);
        }

        MP_Class_Schedule_Panel.add(Class_Schedule_ScrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 320, 292));

        Student_Main_Page_Left_Panel.add(MP_Class_Schedule_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 250, 320, 362));

        Student_Main_Page_Panel.add(Student_Main_Page_Left_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 700));

        Student_Main_Page_Right_Panel.setBackground(new java.awt.Color(242, 242, 242));
        Student_Main_Page_Right_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Student_Center_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Student_Center_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Student_Center_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SC_Tile_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        SC_Tile_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SC_Tile_Label.setText("Student Center");
        SC_Tile_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Student_Center_Panel.add(SC_Tile_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 20));

        SC_img_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SC_img_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/home_tiles/Student Center.png"))); // NOI18N
        Student_Center_Panel.add(SC_img_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        Student_Main_Page_Right_Panel.add(Student_Center_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 200, 200));

        Student_Comminication_Center_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Student_Comminication_Center_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Student_Comminication_Center_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SCC_Tile_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        SCC_Tile_Label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SCC_Tile_Label1.setText("Student");
        SCC_Tile_Label1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Student_Comminication_Center_Panel.add(SCC_Tile_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 20));

        SCC_Tile_Label2.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        SCC_Tile_Label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SCC_Tile_Label2.setText("Communication Center");
        SCC_Tile_Label2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Student_Comminication_Center_Panel.add(SCC_Tile_Label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 200, 20));

        SCC_img_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SCC_img_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/home_tiles/Student Communication Center.png"))); // NOI18N
        Student_Comminication_Center_Panel.add(SCC_img_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        Student_Main_Page_Right_Panel.add(Student_Comminication_Center_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, 200, 200));

        Academic_Advising_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Academic_Advising_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Academic_Advising_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AA_Tile_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        AA_Tile_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AA_Tile_Label.setText("Academic Advising");
        AA_Tile_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Academic_Advising_Panel.add(AA_Tile_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 20));

        AA_img_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        AA_img_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/home_tiles/Academic Advising.png"))); // NOI18N
        Academic_Advising_Panel.add(AA_img_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        Student_Main_Page_Right_Panel.add(Academic_Advising_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 200, 200));

        Student_Records_Enrollment_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Student_Records_Enrollment_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Student_Records_Enrollment_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Student_Records_Enrollment_PanelMouseClicked(evt);
            }
        });
        Student_Records_Enrollment_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SRE_Tile_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        SRE_Tile_Label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SRE_Tile_Label1.setText("Student Records");
        SRE_Tile_Label1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Student_Records_Enrollment_Panel.add(SRE_Tile_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 20));

        SRE_Tile_Label2.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        SRE_Tile_Label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SRE_Tile_Label2.setText("& Enrollment");
        SRE_Tile_Label2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Student_Records_Enrollment_Panel.add(SRE_Tile_Label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 200, 20));

        SRE_img_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SRE_img_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/home_tiles/Student Records & Enrollment.png"))); // NOI18N
        Student_Records_Enrollment_Panel.add(SRE_img_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        Student_Main_Page_Right_Panel.add(Student_Records_Enrollment_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 400, 200, 200));

        Scholarships_Financial_Aid_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Scholarships_Financial_Aid_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Scholarships_Financial_Aid_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SFA_Tile_Label1.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        SFA_Tile_Label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SFA_Tile_Label1.setText("Scholarships");
        SFA_Tile_Label1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Scholarships_Financial_Aid_Panel.add(SFA_Tile_Label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 20));

        SFA_Tile_Label2.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        SFA_Tile_Label2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SFA_Tile_Label2.setText("& Financial Aid");
        SFA_Tile_Label2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Scholarships_Financial_Aid_Panel.add(SFA_Tile_Label2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 200, 20));

        SFA_img_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SFA_img_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/home_tiles/Scholarship & Financial Aid.png"))); // NOI18N
        Scholarships_Financial_Aid_Panel.add(SFA_img_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        Student_Main_Page_Right_Panel.add(Scholarships_Financial_Aid_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 400, 200, 200));

        Student_Financials_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Student_Financials_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Student_Financials_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SF_Tile_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        SF_Tile_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SF_Tile_Label.setText("Student Financials");
        SF_Tile_Label.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Student_Financials_Panel.add(SF_Tile_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 200, 20));

        SF_img_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SF_img_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/home_tiles/Student Financials.png"))); // NOI18N
        Student_Financials_Panel.add(SF_img_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 200));

        Student_Main_Page_Right_Panel.add(Student_Financials_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 400, 200, 200));

        Student_Main_Page_Panel.add(Student_Main_Page_Right_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 955, 700));

        Main_Frame_Panel.add(Student_Main_Page_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1275, 700));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main_Frame_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Main_Frame_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1275, 738));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Close_Icon_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Close_Icon_PanelMouseEntered
        // TODO add your handling code here:
        Close_Icon_Panel.setBackground(Color.RED);
    }//GEN-LAST:event_Close_Icon_PanelMouseEntered

    private void Close_Icon_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Close_Icon_PanelMouseExited
        // TODO add your handling code here:
        Close_Icon_Panel.setBackground(new Color(0,41,85));
    }//GEN-LAST:event_Close_Icon_PanelMouseExited

    private void Minimize_Icon_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Minimize_Icon_PanelMouseEntered
        // TODO add your handling code here:
        Minimize_Icon_Panel.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_Minimize_Icon_PanelMouseEntered

    private void Minimize_Icon_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Minimize_Icon_PanelMouseExited
        // TODO add your handling code here:
        Minimize_Icon_Panel.setBackground(new Color(0,41,85));
    }//GEN-LAST:event_Minimize_Icon_PanelMouseExited

    private void Close_Icon_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Close_Icon_PanelMouseClicked
        // TODO add your handling code here:
        // Create a Logout Form
        ViewLogoutFrame.setVisible(true);
    }//GEN-LAST:event_Close_Icon_PanelMouseClicked

    private void Minimize_Icon_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Minimize_Icon_PanelMouseClicked
        // TODO add your handling code here:
        this.setState(MainFrame.ICONIFIED);
    }//GEN-LAST:event_Minimize_Icon_PanelMouseClicked

    private void Move_PanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Move_PanelMouseDragged
        // TODO add your handling code here:
        int X = evt.getXOnScreen();
        int Y = evt.getYOnScreen();
        this.setLocation(X - mouseposX, Y - mouseposY);
    }//GEN-LAST:event_Move_PanelMouseDragged

    private void Move_PanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Move_PanelMousePressed
        // TODO add your handling code here:
        mouseposX = evt.getX();
        mouseposY = evt.getY();
    }//GEN-LAST:event_Move_PanelMousePressed

    private void Home_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_Button_PanelMouseEntered
        // TODO add your handling code here:
        Home_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_Home_Button_PanelMouseEntered

    private void Home_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_Button_PanelMouseExited
        // TODO add your handling code here:
        Home_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_Home_Button_PanelMouseExited

    private void SC_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Button_PanelMouseEntered
        // TODO add your handling code here:
        SC_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_SC_Button_PanelMouseEntered

    private void SC_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Button_PanelMouseExited
        // TODO add your handling code here:
        SC_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_SC_Button_PanelMouseExited

    private void Student_Records_Enrollment_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Student_Records_Enrollment_PanelMouseClicked
        // TODO add your handling code here:
        Student_Main_Page_Panel.hide();
        Student_SRE_Panel.show();
    }//GEN-LAST:event_Student_Records_Enrollment_PanelMouseClicked

    private void SCC_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SCC_Button_PanelMouseEntered
        // TODO add your handling code here:
        SCC_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_SCC_Button_PanelMouseEntered

    private void SCC_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SCC_Button_PanelMouseExited
        // TODO add your handling code here:
        SCC_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_SCC_Button_PanelMouseExited

    private void AA_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AA_Button_PanelMouseEntered
        // TODO add your handling code here:
        AA_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_AA_Button_PanelMouseEntered

    private void AA_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AA_Button_PanelMouseExited
        // TODO add your handling code here:
        AA_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_AA_Button_PanelMouseExited

    private void SRE_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SRE_Button_PanelMouseEntered
        // TODO add your handling code here:
        SRE_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_SRE_Button_PanelMouseEntered

    private void SRE_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SRE_Button_PanelMouseExited
        // TODO add your handling code here:
        SRE_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_SRE_Button_PanelMouseExited

    private void SFA_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SFA_Button_PanelMouseEntered
        // TODO add your handling code here:
        SFA_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_SFA_Button_PanelMouseEntered

    private void SFA_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SFA_Button_PanelMouseExited
        // TODO add your handling code here:
        SFA_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_SFA_Button_PanelMouseExited

    private void SF_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SF_Button_PanelMouseEntered
        // TODO add your handling code here:
        SF_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_SF_Button_PanelMouseEntered

    private void SF_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SF_Button_PanelMouseExited
        // TODO add your handling code here:
        SF_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_SF_Button_PanelMouseExited

    private void MP_Logout_Button_IconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MP_Logout_Button_IconMouseClicked
        // TODO add your handling code here:
        ViewLogoutFrame.setVisible(true);
    }//GEN-LAST:event_MP_Logout_Button_IconMouseClicked

    private void Home_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home_Button_PanelMouseClicked
        // TODO add your handling code here:
        Student_SRE_Panel.hide();
        Student_Main_Page_Panel.show();
    }//GEN-LAST:event_Home_Button_PanelMouseClicked

    private void SP_Logout_Button_IconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SP_Logout_Button_IconMouseClicked
        // TODO add your handling code here:
        ViewLogoutFrame.setVisible(true);
    }//GEN-LAST:event_SP_Logout_Button_IconMouseClicked

    private void SP_Logout_Button_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SP_Logout_Button_LabelMouseClicked
        // TODO add your handling code here:
        ViewLogoutFrame.setVisible(true);
    }//GEN-LAST:event_SP_Logout_Button_LabelMouseClicked

    private void MP_Logout_Button_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MP_Logout_Button_LabelMouseClicked
        // TODO add your handling code here:
        ViewLogoutFrame.setVisible(true);
    }//GEN-LAST:event_MP_Logout_Button_LabelMouseClicked

    private void Admin_Main_Page_Logout_Button_IconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Admin_Main_Page_Logout_Button_IconMouseClicked
        // TODO add your handling code here:
        ViewLogoutFrame.setVisible(true);
    }//GEN-LAST:event_Admin_Main_Page_Logout_Button_IconMouseClicked

    private void Admin_Main_Page_Logout_Button_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Admin_Main_Page_Logout_Button_LabelMouseClicked
        // TODO add your handling code here:
        ViewLogoutFrame.setVisible(true);
    }//GEN-LAST:event_Admin_Main_Page_Logout_Button_LabelMouseClicked

    private void MC_Home_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_Home_Button_PanelMouseClicked
        // TODO add your handling code here:
        Admin_MC_Panel.hide();
        Admin_Main_Page_Panel.show();
    }//GEN-LAST:event_MC_Home_Button_PanelMouseClicked

    private void MC_Home_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_Home_Button_PanelMouseEntered
        // TODO add your handling code here:
        MC_Home_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MC_Home_Button_PanelMouseEntered

    private void MC_Home_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_Home_Button_PanelMouseExited
        // TODO add your handling code here:
        MC_Home_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MC_Home_Button_PanelMouseExited

    private void MC_MC_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_MC_Button_PanelMouseEntered
        // TODO add your handling code here:
        MC_MC_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MC_MC_Button_PanelMouseEntered

    private void MC_MC_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_MC_Button_PanelMouseExited
        // TODO add your handling code here:
        MC_MC_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MC_MC_Button_PanelMouseExited

    private void MC_SR_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_SR_Button_PanelMouseEntered
        // TODO add your handling code here:
        MC_SR_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MC_SR_Button_PanelMouseEntered

    private void MC_SR_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_SR_Button_PanelMouseExited
        // TODO add your handling code here:
        MC_SR_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MC_SR_Button_PanelMouseExited

    private void MC_MS_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_MS_Button_PanelMouseEntered
        // TODO add your handling code here:
        MC_MS_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MC_MS_Button_PanelMouseEntered

    private void MC_MS_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_MS_Button_PanelMouseExited
        // TODO add your handling code here:
        MC_MS_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MC_MS_Button_PanelMouseExited

    private void MC_AA_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_AA_Button_PanelMouseEntered
        // TODO add your handling code here:
        MC_AA_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MC_AA_Button_PanelMouseEntered

    private void MC_AA_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_AA_Button_PanelMouseExited
        // TODO add your handling code here:
        MC_AA_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MC_AA_Button_PanelMouseExited

    private void MC_SFA_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_SFA_Button_PanelMouseEntered
        // TODO add your handling code here:
        MC_SFA_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MC_SFA_Button_PanelMouseEntered

    private void MC_SFA_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_SFA_Button_PanelMouseExited
        // TODO add your handling code here:
        MC_SFA_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MC_SFA_Button_PanelMouseExited

    private void MC_SF_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_SF_Button_PanelMouseEntered
        // TODO add your handling code here:
        MC_SF_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MC_SF_Button_PanelMouseEntered

    private void MC_SF_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_SF_Button_PanelMouseExited
        // TODO add your handling code here:
        MC_SF_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MC_SF_Button_PanelMouseExited

    private void MC_Logout_Button_IconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_Logout_Button_IconMouseClicked
        // TODO add your handling code here:
        ViewLogoutFrame.setVisible(true);
    }//GEN-LAST:event_MC_Logout_Button_IconMouseClicked

    private void MC_Logout_Button_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_Logout_Button_LabelMouseClicked
        // TODO add your handling code here:
        ViewLogoutFrame.setVisible(true);
    }//GEN-LAST:event_MC_Logout_Button_LabelMouseClicked

    private void Admin_MC_Insert_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Admin_MC_Insert_ButtonActionPerformed
        // TODO add your handling code here:
        conn = MySqlConnect.ConnectDB();
        String ClassID = MC_Class_ID_Field.getText();
        String CourseNo = MC_Course_No_Field.getText();
        String CourseDescription = MC_Course_Description_Field.getText();
        String RoomNo = MC_Room_No_Field.getText();
        String InstructorName = MC_Instructor_Name_Field.getText();
        String DaysTime = MC_Days_Time_Field.getText();
        String StartDate = MC_Start_Date_Field.getText();
        String EndDate = MC_End_Date_Field.getText();
        String Units = MC_Units_Field.getText();
        try{
        PreparedStatement insert = conn.prepareStatement("INSERT INTO courses(Class_ID, Course_No, Course_Description, Room_No, Instructor_Name, Days_Time, Start_Date, End_Date, Units) VALUES ('"+ClassID+"', '"+CourseNo+"', '"+CourseDescription+"', '"+RoomNo+"', '"+InstructorName+"', '"+DaysTime+"', '"+StartDate+"', '"+EndDate+"', '"+Units+"')");
            insert.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)MC_Current_Courses_Table.getModel();
            while(model.getRowCount()>0){
                for(int i = 0; i < model.getRowCount(); i++)
                {
                    model.removeRow(i);
                }
            }
            JOptionPane.showMessageDialog(null, "New Course Added Successfully");
            MC_Show_Courses();
            Admin_MC_Clear_Fields();
            conn.close();
            }catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
            }
    }//GEN-LAST:event_Admin_MC_Insert_ButtonActionPerformed

    private void Admin_MC_Reset_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Admin_MC_Reset_ButtonActionPerformed
        // TODO add your handling code here:
        Admin_MC_Clear_Fields();
    }//GEN-LAST:event_Admin_MC_Reset_ButtonActionPerformed

    private void MC_Current_Courses_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_Current_Courses_TableMouseClicked
        // TODO add your handling code here:
        int i = MC_Current_Courses_Table.getSelectedRow();
        TableModel model = MC_Current_Courses_Table.getModel();
        MC_Class_ID_Field.setText(model.getValueAt(MC_Current_Courses_Table.convertRowIndexToModel(i), 0).toString());
        MC_Course_No_Field.setText(model.getValueAt(MC_Current_Courses_Table.convertRowIndexToModel(i), 1).toString());
        MC_Course_Description_Field.setText(model.getValueAt(MC_Current_Courses_Table.convertRowIndexToModel(i), 2).toString());
        MC_Room_No_Field.setText(model.getValueAt(MC_Current_Courses_Table.convertRowIndexToModel(i), 3).toString());
        MC_Instructor_Name_Field.setText(model.getValueAt(MC_Current_Courses_Table.convertRowIndexToModel(i), 4).toString());
        MC_Days_Time_Field.setText(model.getValueAt(MC_Current_Courses_Table.convertRowIndexToModel(i), 5).toString());
        MC_Start_Date_Field.setText(model.getValueAt(MC_Current_Courses_Table.convertRowIndexToModel(i), 6).toString());
        MC_End_Date_Field.setText(model.getValueAt(MC_Current_Courses_Table.convertRowIndexToModel(i), 7).toString()); 
    }//GEN-LAST:event_MC_Current_Courses_TableMouseClicked

    private void MC_Search_Courses_FieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MC_Search_Courses_FieldFocusLost
        // TODO add your handling code here:
        if(MC_Search_Courses_Field.getText().equals("")){
            MC_Search_Placeholder_Field.show();
        }
        else{
        MC_Search_Placeholder_Field.hide();
        }
    }//GEN-LAST:event_MC_Search_Courses_FieldFocusLost

    private void MC_Search_Courses_FieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MC_Search_Courses_FieldKeyTyped
        // TODO add your handling code here:
        MC_Search_Placeholder_Field.hide();
    }//GEN-LAST:event_MC_Search_Courses_FieldKeyTyped

    private void MC_Search_Courses_FieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MC_Search_Courses_FieldKeyReleased
        // TODO add your handling code here:
        String SearchCourse = MC_Search_Courses_Field.getText();
        MC_FilterCourse(SearchCourse);
    }//GEN-LAST:event_MC_Search_Courses_FieldKeyReleased

    private void Admin_Manage_Courses_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Admin_Manage_Courses_PanelMouseClicked
        // TODO add your handling code here:
        Admin_Main_Page_Panel.hide();
        Admin_MC_Panel.show();
    }//GEN-LAST:event_Admin_Manage_Courses_PanelMouseClicked

    private void MS_Home_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_Home_Button_PanelMouseClicked
        // TODO add your handling code here:
        Admin_MS_Panel.hide();
        Admin_Main_Page_Panel.show();
    }//GEN-LAST:event_MS_Home_Button_PanelMouseClicked

    private void MS_Home_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_Home_Button_PanelMouseEntered
        // TODO add your handling code here:
        MS_Home_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MS_Home_Button_PanelMouseEntered

    private void MS_Home_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_Home_Button_PanelMouseExited
        // TODO add your handling code here:
        MS_Home_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MS_Home_Button_PanelMouseExited

    private void MS_MC_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_MC_Button_PanelMouseEntered
        // TODO add your handling code here:
        MS_MC_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MS_MC_Button_PanelMouseEntered

    private void MS_MC_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_MC_Button_PanelMouseExited
        // TODO add your handling code here:
        MS_MC_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MS_MC_Button_PanelMouseExited

    private void MS_SR_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_SR_Button_PanelMouseEntered
        // TODO add your handling code here:
        MS_SR_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MS_SR_Button_PanelMouseEntered

    private void MS_SR_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_SR_Button_PanelMouseExited
        // TODO add your handling code here:
        MS_SR_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MS_SR_Button_PanelMouseExited

    private void MS_MS_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_MS_Button_PanelMouseEntered
        // TODO add your handling code here:
        MS_MS_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MS_MS_Button_PanelMouseEntered

    private void MS_MS_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_MS_Button_PanelMouseExited
        // TODO add your handling code here:
        MS_MS_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MS_MS_Button_PanelMouseExited

    private void MS_AA_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_AA_Button_PanelMouseEntered
        // TODO add your handling code here:
        MS_AA_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MS_AA_Button_PanelMouseEntered

    private void MS_AA_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_AA_Button_PanelMouseExited
        // TODO add your handling code here:
        MS_AA_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MS_AA_Button_PanelMouseExited

    private void MS_SFA_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_SFA_Button_PanelMouseEntered
        // TODO add your handling code here:
        MS_SFA_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MS_SFA_Button_PanelMouseEntered

    private void MS_SFA_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_SFA_Button_PanelMouseExited
        // TODO add your handling code here:
        MS_SFA_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MS_SFA_Button_PanelMouseExited

    private void MS_SF_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_SF_Button_PanelMouseEntered
        // TODO add your handling code here:
        MS_SF_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_MS_SF_Button_PanelMouseEntered

    private void MS_SF_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_SF_Button_PanelMouseExited
        // TODO add your handling code here:
        MS_SF_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_MS_SF_Button_PanelMouseExited

    private void MS_Logout_Button_IconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_Logout_Button_IconMouseClicked
        // TODO add your handling code here:
        ViewLogoutFrame.setVisible(true);
    }//GEN-LAST:event_MS_Logout_Button_IconMouseClicked

    private void MS_Logout_Button_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_Logout_Button_LabelMouseClicked
        // TODO add your handling code here:
        ViewLogoutFrame.setVisible(true);
    }//GEN-LAST:event_MS_Logout_Button_LabelMouseClicked

    private void Admin_MS_Insert_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Admin_MS_Insert_ButtonActionPerformed
        // TODO add your handling code here:
        conn = MySqlConnect.ConnectDB();
        String StudentID = MS_Student_ID_Field.getText();
        String FirstName = MS_First_Name_Field.getText();
        String LastName = MS_Last_Name_Field.getText();
        String StudentStatus = MS_Student_Status_Field.getText();
        String ResidencyStatus = MS_Residency_Status_Field.getText();
        String ClassStanding = MS_Class_Standing_Field.getText();
        String Location = MS_Location_Field.getText();
        String Major = MS_Major_Field.getText();
        String PhoneNumber = MS_Phone_Number_Field.getText();
        String EmailAddress = MS_Email_Address_Field.getText();
        try{
            PreparedStatement insert = conn.prepareStatement("INSERT INTO student(Student_ID, First_Name, Last_Name, Student_Status, Residency_Status, Class_Standing, Location, Major, Phone_Number, Email_Address) VALUES ('"+StudentID+"', '"+FirstName+"', '"+LastName+"', '"+StudentStatus+"', '"+ResidencyStatus+"', '"+ClassStanding+"', '"+Location+"', '"+Major+"', '"+PhoneNumber+"', '"+EmailAddress+"')");
            insert.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)MS_Current_Students_Table.getModel();
            while(model.getRowCount()>0){
                for(int i = 0; i < model.getRowCount(); i++)
                {
                    model.removeRow(i);
                }
            }
        JOptionPane.showMessageDialog(null, "New Student Added Successfully");
        MS_Show_Students();
        Admin_MS_Clear_Fields();
        conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
        }
    }//GEN-LAST:event_Admin_MS_Insert_ButtonActionPerformed

    private void Admin_MS_Reset_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Admin_MS_Reset_ButtonActionPerformed
        // TODO add your handling code here:
        Admin_MS_Clear_Fields();
    }//GEN-LAST:event_Admin_MS_Reset_ButtonActionPerformed

    private void MS_Current_Students_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_Current_Students_TableMouseClicked
        // TODO add your handling code here:
        int i = MS_Current_Students_Table.getSelectedRow();
        TableModel model = MS_Current_Students_Table.getModel();
        MS_Student_ID_Field.setText(model.getValueAt(MS_Current_Students_Table.convertRowIndexToModel(i), 0).toString());
        MS_First_Name_Field.setText(model.getValueAt(MS_Current_Students_Table.convertRowIndexToModel(i), 1).toString());
        MS_Last_Name_Field.setText(model.getValueAt(MS_Current_Students_Table.convertRowIndexToModel(i), 2).toString());
        MS_Student_Status_Field.setText(model.getValueAt(MS_Current_Students_Table.convertRowIndexToModel(i), 3).toString());
        MS_Residency_Status_Field.setText(model.getValueAt(MS_Current_Students_Table.convertRowIndexToModel(i), 4).toString());
        MS_Class_Standing_Field.setText(model.getValueAt(MS_Current_Students_Table.convertRowIndexToModel(i), 5).toString());
        MS_Location_Field.setText(model.getValueAt(MS_Current_Students_Table.convertRowIndexToModel(i), 6).toString());
        MS_Major_Field.setText(model.getValueAt(MS_Current_Students_Table.convertRowIndexToModel(i), 7).toString());
        MS_Phone_Number_Field.setText(model.getValueAt(MS_Current_Students_Table.convertRowIndexToModel(i), 8).toString());
        MS_Email_Address_Field.setText(model.getValueAt(MS_Current_Students_Table.convertRowIndexToModel(i), 9).toString());
    }//GEN-LAST:event_MS_Current_Students_TableMouseClicked

    private void MS_Search_Student_FieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_MS_Search_Student_FieldFocusLost
        // TODO add your handling code here:
        if(MS_Search_Student_Field.getText().equals("")){
            MS_Search_Placeholder_Field.show();
        }
        else{
        MS_Search_Placeholder_Field.hide();
        }
    }//GEN-LAST:event_MS_Search_Student_FieldFocusLost

    private void MS_Search_Student_FieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MS_Search_Student_FieldKeyReleased
        // TODO add your handling code here:
        String SearchStudent = MS_Search_Student_Field.getText();
        MS_FilterStudent(SearchStudent);
    }//GEN-LAST:event_MS_Search_Student_FieldKeyReleased

    private void MS_Search_Student_FieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MS_Search_Student_FieldKeyTyped
        // TODO add your handling code here:
        MS_Search_Placeholder_Field.hide();
    }//GEN-LAST:event_MS_Search_Student_FieldKeyTyped

    private void Admin_Manage_Student_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Admin_Manage_Student_PanelMouseClicked
        // TODO add your handling code here:
        Admin_Main_Page_Panel.hide();
        Admin_MS_Panel.show();
    }//GEN-LAST:event_Admin_Manage_Student_PanelMouseClicked

    private void MS_MC_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MS_MC_Button_PanelMouseClicked
        // TODO add your handling code here:
        Admin_MS_Panel.hide();
        Admin_MC_Panel.show();
    }//GEN-LAST:event_MS_MC_Button_PanelMouseClicked

    private void MC_MS_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MC_MS_Button_PanelMouseClicked
        // TODO add your handling code here:
        Admin_MC_Panel.hide();
        Admin_MS_Panel.show();
    }//GEN-LAST:event_MC_MS_Button_PanelMouseClicked

    private void Admin_MC_Delete_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Admin_MC_Delete_ButtonActionPerformed
        // TODO add your handling code here:
        conn = MySqlConnect.ConnectDB();
        int row = MC_Current_Courses_Table.getSelectedRow();
        String cell = MC_Current_Courses_Table.getModel().getValueAt(row, 0).toString();
        String sql = "DELETE FROM courses WHERE Class_ID = '" +cell+"'";
        try{
            PreparedStatement delete = conn.prepareStatement(sql);
            JOptionPane.showMessageDialog(null, "Course Deleted Successfully");
            delete.execute();
            DefaultTableModel model = (DefaultTableModel)MC_Current_Courses_Table.getModel();
            while(model.getRowCount()>0){
                for(int i = 0; i < model.getRowCount(); i++)
                {
                    model.removeRow(i);
                }
            }
            MC_Show_Courses();
            Admin_MC_Clear_Fields();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_Admin_MC_Delete_ButtonActionPerformed

    private void Admin_MS_Delete_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Admin_MS_Delete_ButtonActionPerformed
        // TODO add your handling code here:
        conn = MySqlConnect.ConnectDB();
        int row = MS_Current_Students_Table.getSelectedRow();
        String cell = MS_Current_Students_Table.getModel().getValueAt(row, 0).toString();
        String sql = "DELETE FROM student WHERE Student_ID = '" +cell+"'";
        try{
            PreparedStatement delete = conn.prepareStatement(sql);
            JOptionPane.showMessageDialog(null, "Student Deleted Successfully");
            delete.execute();
            DefaultTableModel model = (DefaultTableModel)MS_Current_Students_Table.getModel();
            while(model.getRowCount()>0){
                for(int i = 0; i < model.getRowCount(); i++)
                {
                    model.removeRow(i);
                }
            }
            MS_Show_Students();
            Admin_MS_Clear_Fields();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_Admin_MS_Delete_ButtonActionPerformed

    private void Admin_MC_Update_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Admin_MC_Update_ButtonActionPerformed
        // TODO add your handling code here:
        conn = MySqlConnect.ConnectDB();
        String ClassID = MC_Class_ID_Field.getText();
        String CourseNo = MC_Course_No_Field.getText();
        String CourseDescription = MC_Course_Description_Field.getText();
        String RoomNo = MC_Room_No_Field.getText();
        String InstructorName = MC_Instructor_Name_Field.getText();
        String DaysTime = MC_Days_Time_Field.getText();
        String StartDate = MC_Start_Date_Field.getText();
        String EndDate = MC_End_Date_Field.getText();
        try{
            PreparedStatement update = conn.prepareStatement("UPDATE courses SET Course_No= '"+CourseNo+"', Course_Description= '"+CourseDescription+"', Room_No= '"+RoomNo+"', Instructor_Name= '"+InstructorName+"', Days_Time= '"+DaysTime+"', Start_Date= '"+StartDate+"', End_Date= '"+EndDate+"' WHERE Class_ID = '"+ClassID+"'");
            update.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)MC_Current_Courses_Table.getModel();
            while(model.getRowCount()>0){
                for(int i = 0; i < model.getRowCount(); i++)
                {
                    model.removeRow(i);
                }
            }
            JOptionPane.showMessageDialog(null, "Selected Course Updated Successfully");
            MC_Show_Courses();
            Admin_MC_Clear_Fields();
            conn.close();
            }catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
            }
    }//GEN-LAST:event_Admin_MC_Update_ButtonActionPerformed

    private void Admin_MS_Update_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Admin_MS_Update_ButtonActionPerformed
        // TODO add your handling code here:
        conn = MySqlConnect.ConnectDB();
        String StudentID = MS_Student_ID_Field.getText();
        String FirstName = MS_First_Name_Field.getText();
        String LastName = MS_Last_Name_Field.getText();
        String StudentStatus = MS_Student_Status_Field.getText();
        String ResidencyStatus = MS_Residency_Status_Field.getText();
        String ClassStanding = MS_Class_Standing_Field.getText();
        String Location = MS_Location_Field.getText();
        String Major = MS_Major_Field.getText();
        String PhoneNumber = MS_Phone_Number_Field.getText();
        String EmailAddress = MS_Email_Address_Field.getText();
        try{
            PreparedStatement update = conn.prepareStatement("UPDATE student SET First_Name= '"+FirstName+"', Last_Name= '"+LastName+"', Student_Status= '"+StudentStatus+"', Residency_Status= '"+ResidencyStatus+"', Class_Standing= '"+ClassStanding+"', Location= '"+Location+"', Major= '"+Major+"', Phone_Number= '"+PhoneNumber+"', Email_Address= '"+EmailAddress+"' WHERE Student_ID = '"+StudentID+"'");
            update.executeUpdate();
            DefaultTableModel model = (DefaultTableModel)MS_Current_Students_Table.getModel();
            while(model.getRowCount()>0){
                for(int i = 0; i < model.getRowCount(); i++)
                {
                    model.removeRow(i);
                }
            }
            JOptionPane.showMessageDialog(null, "Selected Student Updated Successfully");
            MS_Show_Students();
            Admin_MS_Clear_Fields();
            conn.close();
            }catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e);
            }
    }//GEN-LAST:event_Admin_MS_Update_ButtonActionPerformed

    private void SC_Home_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Home_Button_PanelMouseClicked
        // TODO add your handling code here:
        Admin_SC_Panel.hide();
        Admin_Main_Page_Panel.show();
    }//GEN-LAST:event_SC_Home_Button_PanelMouseClicked

    private void SC_Home_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Home_Button_PanelMouseEntered
        // TODO add your handling code here:
        SC_Home_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_SC_Home_Button_PanelMouseEntered

    private void SC_Home_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Home_Button_PanelMouseExited
        // TODO add your handling code here:
        SC_Home_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_SC_Home_Button_PanelMouseExited

    private void SC_MC_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_MC_Button_PanelMouseClicked
        // TODO add your handling code here:
        Admin_SC_Panel.hide();
        Admin_MC_Panel.show();
    }//GEN-LAST:event_SC_MC_Button_PanelMouseClicked

    private void SC_MC_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_MC_Button_PanelMouseEntered
        // TODO add your handling code here:
        SC_MC_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_SC_MC_Button_PanelMouseEntered

    private void SC_MC_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_MC_Button_PanelMouseExited
        // TODO add your handling code here:
        SC_MC_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_SC_MC_Button_PanelMouseExited

    private void SC_SC_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_SC_Button_PanelMouseEntered
        // TODO add your handling code here:
        SC_SC_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_SC_SC_Button_PanelMouseEntered

    private void SC_SC_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_SC_Button_PanelMouseExited
        // TODO add your handling code here:
        SC_SC_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_SC_SC_Button_PanelMouseExited

    private void SC_MS_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_MS_Button_PanelMouseEntered
        // TODO add your handling code here:
        SC_MS_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_SC_MS_Button_PanelMouseEntered

    private void SC_MS_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_MS_Button_PanelMouseExited
        // TODO add your handling code here:
        SC_MS_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_SC_MS_Button_PanelMouseExited

    private void SC_AECG_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_AECG_Button_PanelMouseEntered
        // TODO add your handling code here:
        SC_AECG_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_SC_AECG_Button_PanelMouseEntered

    private void SC_AECG_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_AECG_Button_PanelMouseExited
        // TODO add your handling code here:
        SC_AECG_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_SC_AECG_Button_PanelMouseExited

    private void SC_SFA_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_SFA_Button_PanelMouseEntered
        // TODO add your handling code here:
        SC_SFA_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_SC_SFA_Button_PanelMouseEntered

    private void SC_SFA_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_SFA_Button_PanelMouseExited
        // TODO add your handling code here:
        SC_SFA_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_SC_SFA_Button_PanelMouseExited

    private void SC_SF_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_SF_Button_PanelMouseEntered
        // TODO add your handling code here:
        SC_SF_Button_Panel.setBackground(new Color(0,93,186));
    }//GEN-LAST:event_SC_SF_Button_PanelMouseEntered

    private void SC_SF_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_SF_Button_PanelMouseExited
        // TODO add your handling code here:
        SC_SF_Button_Panel.setBackground(new Color(85,142,203));
    }//GEN-LAST:event_SC_SF_Button_PanelMouseExited

    private void SC_Logout_Button_IconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Logout_Button_IconMouseClicked
        // TODO add your handling code here:
        ViewLogoutFrame.setVisible(true);
    }//GEN-LAST:event_SC_Logout_Button_IconMouseClicked

    private void SC_Logout_Button_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Logout_Button_LabelMouseClicked
        // TODO add your handling code here:
        ViewLogoutFrame.setVisible(true);
    }//GEN-LAST:event_SC_Logout_Button_LabelMouseClicked

    private void SC_Current_Students_TableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Current_Students_TableMouseClicked
        // TODO add your handling code here:
        int i = SC_Current_Students_Table.getSelectedRow();
        TableModel model = SC_Current_Students_Table.getModel();
        SC_Student_ID_Label.setText(model.getValueAt(SC_Current_Students_Table.convertRowIndexToModel(i), 0).toString());
        SC_Student_Name_Label.setText(model.getValueAt(SC_Current_Students_Table.convertRowIndexToModel(i), 1).toString() + " " + model.getValueAt(SC_Current_Students_Table.convertRowIndexToModel(i), 2).toString());
        SC_Student_Status_Label.setText(model.getValueAt(SC_Current_Students_Table.convertRowIndexToModel(i), 3).toString());
        SC_Residency_Status_Label.setText(model.getValueAt(SC_Current_Students_Table.convertRowIndexToModel(i), 4).toString());
        SC_Class_Standing_Label.setText(model.getValueAt(SC_Current_Students_Table.convertRowIndexToModel(i), 5).toString());
        SC_Location_Label.setText(model.getValueAt(SC_Current_Students_Table.convertRowIndexToModel(i), 6).toString());
        SC_Major_Label.setText(model.getValueAt(SC_Current_Students_Table.convertRowIndexToModel(i), 7).toString());
        SC_Phone_Number_Label.setText(model.getValueAt(SC_Current_Students_Table.convertRowIndexToModel(i), 8).toString());
        SC_Email_Address_Label.setText(model.getValueAt(SC_Current_Students_Table.convertRowIndexToModel(i), 9).toString());
        Admin_SC_ClearRegisteredCoursesTable();
        SC_Show_RegisteredCourses();
    }//GEN-LAST:event_SC_Current_Students_TableMouseClicked

    private void SC_Search_Student_FieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SC_Search_Student_FieldFocusLost
        // TODO add your handling code here:
        if(SC_Search_Student_Field.getText().equals("")){
            SC_Search_Placeholder_Field.show();
        }
        else{
        SC_Search_Placeholder_Field.hide();
        }
    }//GEN-LAST:event_SC_Search_Student_FieldFocusLost

    private void SC_Search_Student_FieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SC_Search_Student_FieldKeyReleased
        // TODO add your handling code here:
        String SearchStudent = SC_Search_Student_Field.getText();
        AC_FilterStudent(SearchStudent);
    }//GEN-LAST:event_SC_Search_Student_FieldKeyReleased

    private void SC_Search_Student_FieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SC_Search_Student_FieldKeyTyped
        // TODO add your handling code here:
        SC_Search_Placeholder_Field.hide();
    }//GEN-LAST:event_SC_Search_Student_FieldKeyTyped

    private void Admin_SR_img_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Admin_SR_img_LabelMouseClicked
        // TODO add your handling code here:
        Admin_Main_Page_Panel.hide();
        Admin_SC_Panel.show();
    }//GEN-LAST:event_Admin_SR_img_LabelMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int i = SP_GPA_Table.getSelectedRow();
        TableModel model = SP_GPA_Table.getModel();
        int nRow = SP_GPA_Table.getRowCount();
        for(int x = 0; x < nRow; x++) {
            int Units = Integer.parseInt(SP_GPA_Table.getValueAt(x, 2).toString());
            int GradePoint = 0;
            if(SP_GPA_Table.getValueAt(x, 3) == null)
            {
                GradePoint = 0;
            }
            else if(SP_GPA_Table.getValueAt(x, 3).equals("A"))
            {
                GradePoint = 4 * Units;
            }
            else if(SP_GPA_Table.getValueAt(x, 3).equals("B"))
            {
                GradePoint = 3 * Units;
            }
            else if(SP_GPA_Table.getValueAt(x, 3).equals("C"))
            {
                GradePoint = 2 * Units;
            }
            else if(SP_GPA_Table.getValueAt(x, 3).equals("D"))
            {
                GradePoint = 1 * Units;
            }
            else if(SP_GPA_Table.getValueAt(x, 3).equals("F"))
            {
                GradePoint = 0 * Units;
            }
            model.setValueAt(GradePoint, x, 4);
        }
        float GPA = (getTotalPoints()) / (getTotalCredits());
        String GPA_Text = String.valueOf(GPA);
        SP_GPA_Formula_Label.setText("Total Points (" + getTotalPoints() + ") / " + "Total Credits (" + getTotalCredits() + " )");
        SP_GPA_Label.setText("GPA = " + GPA_Text);
        SP_GPA_Table.getSelectionModel().clearSelection();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SP_Course_Selection_ComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SP_Course_Selection_ComboBoxActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel)SP_Exam_Grades_Table.getModel();
            while(model.getRowCount()>0){
                for(int i = 0; i < model.getRowCount(); i++)
                {
                    model.removeRow(i);
                }
            }
        Show_Exam_Grades_Table();
        int nRow = SP_Exam_Grades_Table.getRowCount();
        for (int x = 0; x < nRow; x++) {
            double Exam_Weight = Float.parseFloat(SP_Exam_Grades_Table.getValueAt(x, 1).toString());
            double Score_Received = Float.parseFloat(SP_Exam_Grades_Table.getValueAt(x, 2).toString());
            BigDecimal bd = new BigDecimal((Exam_Weight / 100) * (Score_Received)).setScale(2, RoundingMode.HALF_UP);
            double Grade_Earned = bd.doubleValue();
            model.setValueAt(Grade_Earned, x, 3);
        }
    }//GEN-LAST:event_SP_Course_Selection_ComboBoxActionPerformed

    private void SC_Search_Course_FieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SC_Search_Course_FieldFocusLost
        // TODO add your handling code here:
        if(SC_Search_Course_Field.getText().equals("")){
            SC_Search_Courses_Placeholder_Field.show();
        }
        else{
        SC_Search_Courses_Placeholder_Field.hide();
        }
    }//GEN-LAST:event_SC_Search_Course_FieldFocusLost

    private void SC_Search_Course_FieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SC_Search_Course_FieldKeyReleased
        // TODO add your handling code here:
        String SearchCourse = SC_Search_Course_Field.getText();
        AC_FilterCourse(SearchCourse);
    }//GEN-LAST:event_SC_Search_Course_FieldKeyReleased

    private void SC_Search_Course_FieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SC_Search_Course_FieldKeyTyped
        // TODO add your handling code here:
        SC_Search_Courses_Placeholder_Field.hide();
    }//GEN-LAST:event_SC_Search_Course_FieldKeyTyped

    private void SC_Add_Course_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SC_Add_Course_ButtonActionPerformed
        // TODO add your handling code here:
        int row = SC_Current_Courses_Table.getSelectedRow();
        String Student_ID = SC_Student_ID_Label.getText();
        String Student_Name = SC_Student_Name_Label.getText();
        String Class_ID = SC_Current_Courses_Table.getModel().getValueAt(SC_Current_Courses_Table.convertRowIndexToModel(row), 0).toString();
        int result = JOptionPane.showConfirmDialog(null,"Sure you want to assign (" + Class_ID + ") to " + Student_Name + " (" + Student_ID + ")", "Confirmation",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
               conn = MySqlConnect.ConnectDB();
                    try{
                        PreparedStatement addcourse = conn.prepareStatement("INSERT INTO registered_courses(Student_ID, Class_ID) VALUES ('"+Student_ID+"', '"+Class_ID+"')");
                        addcourse.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Course Assigned Successfully");
                        conn.close();
                    }catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, e);
                    }
            }
            else if (result == JOptionPane.NO_OPTION){
               
            }
            else {
               
            }
    }//GEN-LAST:event_SC_Add_Course_ButtonActionPerformed

    private void SC_MS_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_MS_Button_PanelMouseClicked
        // TODO add your handling code here:
        Admin_SC_Panel.hide();
        Admin_MS_Panel.show();
    }//GEN-LAST:event_SC_MS_Button_PanelMouseClicked

    private void SC_AECG_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_AECG_Button_PanelMouseClicked
        // TODO add your handling code here:
        Admin_SC_Panel.hide();
        Admin_AECG_Panel.show();
    }//GEN-LAST:event_SC_AECG_Button_PanelMouseClicked

    private void AECG_Home_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_Home_Button_PanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_Home_Button_PanelMouseClicked

    private void AECG_Home_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_Home_Button_PanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_Home_Button_PanelMouseEntered

    private void AECG_Home_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_Home_Button_PanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_Home_Button_PanelMouseExited

    private void AECG_MC_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_MC_Button_PanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_MC_Button_PanelMouseClicked

    private void AECG_MC_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_MC_Button_PanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_MC_Button_PanelMouseEntered

    private void AECG_MC_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_MC_Button_PanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_MC_Button_PanelMouseExited

    private void AECG_MS_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_MS_Button_PanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_MS_Button_PanelMouseClicked

    private void AECG_MS_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_MS_Button_PanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_MS_Button_PanelMouseEntered

    private void AECG_MS_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_MS_Button_PanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_MS_Button_PanelMouseExited

    private void AECG_SC_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_SC_Button_PanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_SC_Button_PanelMouseEntered

    private void AECG_SC_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_SC_Button_PanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_SC_Button_PanelMouseExited

    private void AECG_AECG_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_AECG_Button_PanelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_AECG_Button_PanelMouseClicked

    private void AECG_AECG_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_AECG_Button_PanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_AECG_Button_PanelMouseEntered

    private void AECG_AECG_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_AECG_Button_PanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_AECG_Button_PanelMouseExited

    private void AECG_SFA_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_SFA_Button_PanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_SFA_Button_PanelMouseEntered

    private void AECG_SFA_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_SFA_Button_PanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_SFA_Button_PanelMouseExited

    private void AECG_SF_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_SF_Button_PanelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_SF_Button_PanelMouseEntered

    private void AECG_SF_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_SF_Button_PanelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_SF_Button_PanelMouseExited

    private void AECG_Logout_Button_IconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_Logout_Button_IconMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_Logout_Button_IconMouseClicked

    private void AECG_Logout_Button_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AECG_Logout_Button_LabelMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_AECG_Logout_Button_LabelMouseClicked

    private void SC_Search_Student_Field1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_SC_Search_Student_Field1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_SC_Search_Student_Field1FocusLost

    private void SC_Search_Student_Field1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SC_Search_Student_Field1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_SC_Search_Student_Field1KeyReleased

    private void SC_Search_Student_Field1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SC_Search_Student_Field1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_SC_Search_Student_Field1KeyTyped

    private void SC_Current_Students_Table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Current_Students_Table1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_SC_Current_Students_Table1MouseClicked

    private void SC_Withdraw_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SC_Withdraw_ButtonActionPerformed
        // TODO add your handling code here:
        int row = SC_Student_Courses_Table.getSelectedRow();
        String Student_ID = SC_Student_ID_Label.getText();
        String Student_Name = SC_Student_Name_Label.getText();
        String Class_ID = SC_Student_Courses_Table.getModel().getValueAt(SC_Student_Courses_Table.convertRowIndexToModel(row), 0).toString();
        int result = JOptionPane.showConfirmDialog(null,"Sure you want to withdraw (" + Class_ID + ") from " + Student_Name + " (" + Student_ID + ")", "Confirmation",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION){
               conn = MySqlConnect.ConnectDB();
                    try{
                        PreparedStatement withdrawcourse = conn.prepareStatement("DELETE FROM registered_courses WHERE Student_ID = '"+Student_ID+"' AND Class_ID = '"+Class_ID+"'");
                        withdrawcourse.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Course Withdrawn Successfully");
                        conn.close();
                    }catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, e);
                    }
            }
        DefaultTableModel model = (DefaultTableModel)SC_Student_Courses_Table.getModel();
            while(model.getRowCount()>0){
                for(int i = 0; i < model.getRowCount(); i++)
                {
                    model.removeRow(i);
                }
            }
        SC_Show_RegisteredCourses();
    }//GEN-LAST:event_SC_Withdraw_ButtonActionPerformed

    private void SC_Hide_Student_Current_Courses_LabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Hide_Student_Current_Courses_LabelMouseEntered
        // TODO add your handling code here:
        SC_Hide_Student_Current_Courses_Label.setForeground(new Color(0,41,85));
    }//GEN-LAST:event_SC_Hide_Student_Current_Courses_LabelMouseEntered

    private void SC_Hide_Student_Current_Courses_LabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Hide_Student_Current_Courses_LabelMouseExited
        // TODO add your handling code here:
        SC_Hide_Student_Current_Courses_Label.setForeground(new Color(85,142,203));
    }//GEN-LAST:event_SC_Hide_Student_Current_Courses_LabelMouseExited

    private void SC_Hide_Student_Current_Courses_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Hide_Student_Current_Courses_LabelMouseClicked
        // TODO add your handling code here:
        SC_Registered_Courses_Panel.hide();
        SC_Assgin_Courses_Panel.show();
    }//GEN-LAST:event_SC_Hide_Student_Current_Courses_LabelMouseClicked

    private void SC_Show_Student_Current_Courses_LabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Show_Student_Current_Courses_LabelMouseClicked
        // TODO add your handling code here:
        SC_Assgin_Courses_Panel.hide();
        SC_Registered_Courses_Panel.show();
        DefaultTableModel model = (DefaultTableModel)SC_Student_Courses_Table.getModel();
            while(model.getRowCount()>0){
                for(int i = 0; i < model.getRowCount(); i++)
                {
                    model.removeRow(i);
                }
            }
        SC_Show_RegisteredCourses();
    }//GEN-LAST:event_SC_Show_Student_Current_Courses_LabelMouseClicked

    private void SC_Show_Student_Current_Courses_LabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Show_Student_Current_Courses_LabelMouseEntered
        // TODO add your handling code here:
        SC_Show_Student_Current_Courses_Label.setForeground(new Color(0,41,85));
    }//GEN-LAST:event_SC_Show_Student_Current_Courses_LabelMouseEntered

    private void SC_Show_Student_Current_Courses_LabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SC_Show_Student_Current_Courses_LabelMouseExited
        // TODO add your handling code here:
        SC_Show_Student_Current_Courses_Label.setForeground(new Color(85,142,203));
    }//GEN-LAST:event_SC_Show_Student_Current_Courses_LabelMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AA_Button_Panel;
    private javax.swing.JLabel AA_Tile_Label;
    private javax.swing.JLabel AA_img_Label;
    private javax.swing.JLabel AECG_AECG_Button_Icon;
    private javax.swing.JLabel AECG_AECG_Button_Label;
    private javax.swing.JPanel AECG_AECG_Button_Panel;
    private javax.swing.JLabel AECG_Assign_Exam_Grades_Header_Label;
    private javax.swing.JLabel AECG_Assign_Exam_Grades_Header_Label1;
    private javax.swing.JLabel AECG_Home_Button_Icon;
    private javax.swing.JLabel AECG_Home_Button_Label;
    private javax.swing.JPanel AECG_Home_Button_Panel;
    private javax.swing.JLabel AECG_Logout_Button_Icon;
    private javax.swing.JLabel AECG_Logout_Button_Label;
    private javax.swing.JLabel AECG_MC_Button_Icon;
    private javax.swing.JLabel AECG_MC_Button_Label;
    private javax.swing.JPanel AECG_MC_Button_Panel;
    private javax.swing.JLabel AECG_MS_Button_Icon;
    private javax.swing.JLabel AECG_MS_Button_Label;
    private javax.swing.JPanel AECG_MS_Button_Panel;
    private javax.swing.JLabel AECG_SC_Button_Icon;
    private javax.swing.JLabel AECG_SC_Button_Label;
    private javax.swing.JPanel AECG_SC_Button_Panel;
    private javax.swing.JLabel AECG_SFA_Button_Icon;
    private javax.swing.JLabel AECG_SFA_Button_Label;
    private javax.swing.JPanel AECG_SFA_Button_Panel;
    private javax.swing.JLabel AECG_SF_Button_Icon;
    private javax.swing.JLabel AECG_SF_Button_Label;
    private javax.swing.JPanel AECG_SF_Button_Panel;
    private javax.swing.JPanel Academic_Advising_Panel;
    private javax.swing.JLabel Admin_AA_Tile_Label;
    public static final javax.swing.JLabel Admin_AECG_Discription_Label = new javax.swing.JLabel();
    private javax.swing.JLabel Admin_AECG_Img;
    private javax.swing.JPanel Admin_AECG_Left_Panel;
    public static final javax.swing.JLabel Admin_AECG_Name_Label = new javax.swing.JLabel();
    private javax.swing.JPanel Admin_AECG_Panel;
    private javax.swing.JPanel Admin_AECG_Right_Panel;
    private javax.swing.JPanel Admin_Add_Courses_Form_Panel;
    private javax.swing.JPanel Admin_Add_Courses__Form_Panel1;
    private javax.swing.JPanel Admin_Assign_Exam_Grades_Panel;
    public static final javax.swing.JLabel Admin_Discription_Label = new javax.swing.JLabel();
    private javax.swing.JLabel Admin_Img;
    private javax.swing.JButton Admin_MC_Delete_Button;
    public static final javax.swing.JLabel Admin_MC_Discription_Label = new javax.swing.JLabel();
    private javax.swing.JLabel Admin_MC_Img;
    private javax.swing.JButton Admin_MC_Insert_Button;
    private javax.swing.JPanel Admin_MC_Left_Panel;
    public static final javax.swing.JLabel Admin_MC_Name_Label = new javax.swing.JLabel();
    private javax.swing.JPanel Admin_MC_Panel;
    private javax.swing.JButton Admin_MC_Reset_Button;
    private javax.swing.JPanel Admin_MC_Right_Panel;
    private javax.swing.JLabel Admin_MC_Tile_Label;
    private javax.swing.JButton Admin_MC_Update_Button;
    private javax.swing.JLabel Admin_MC_img_Label;
    private javax.swing.JButton Admin_MS_Delete_Button;
    public static final javax.swing.JLabel Admin_MS_Discription_Label = new javax.swing.JLabel();
    private javax.swing.JLabel Admin_MS_Heading_Label;
    private javax.swing.JLabel Admin_MS_Img;
    private javax.swing.JButton Admin_MS_Insert_Button;
    private javax.swing.JPanel Admin_MS_Left_Panel;
    public static final javax.swing.JLabel Admin_MS_Name_Label = new javax.swing.JLabel();
    private javax.swing.JPanel Admin_MS_Panel;
    private javax.swing.JButton Admin_MS_Reset_Button;
    private javax.swing.JPanel Admin_MS_Right_Panel;
    private javax.swing.JLabel Admin_MS_Tile_Label;
    private javax.swing.JButton Admin_MS_Update_Button;
    private javax.swing.JLabel Admin_MS_img_Label;
    private javax.swing.JPanel Admin_Main_Page_Left_Panel;
    private javax.swing.JLabel Admin_Main_Page_Logout_Button_Icon;
    private javax.swing.JLabel Admin_Main_Page_Logout_Button_Label;
    private javax.swing.JPanel Admin_Main_Page_Panel;
    private javax.swing.JPanel Admin_Main_Page_Right_Panel;
    private javax.swing.JPanel Admin_Manage_Courses_Panel;
    private javax.swing.JPanel Admin_Manage_Student_Panel;
    public static final javax.swing.JLabel Admin_Name_Label = new javax.swing.JLabel();
    public static final javax.swing.JLabel Admin_SC_Discription_Label = new javax.swing.JLabel();
    private javax.swing.JLabel Admin_SC_Img;
    private javax.swing.JPanel Admin_SC_Left_Panel;
    public static final javax.swing.JLabel Admin_SC_Name_Label = new javax.swing.JLabel();
    private javax.swing.JPanel Admin_SC_Panel;
    private javax.swing.JPanel Admin_SC_Right_Panel;
    private javax.swing.JLabel Admin_SFA_Tile_Label;
    private javax.swing.JLabel Admin_SFA_Tile_Label1;
    private javax.swing.JLabel Admin_SFA_img_Label;
    private javax.swing.JLabel Admin_SF_Tile_Label;
    private javax.swing.JLabel Admin_SF_img_Label;
    private javax.swing.JLabel Admin_SRE_img_Label;
    private javax.swing.JLabel Admin_SR_Tile_Label;
    private javax.swing.JLabel Admin_SR_img_Label;
    private javax.swing.JPanel Admin_Scholarships_Financial_Aid_Panel;
    private javax.swing.JPanel Admin_Student_Financials_Panel;
    private javax.swing.JPanel Assign_Courses_Panel;
    private javax.swing.JScrollPane Class_Schedule_ScrollPane;
    private javax.swing.JLabel Close_Icon;
    private javax.swing.JPanel Close_Icon_Panel;
    private javax.swing.JPanel Home_Button_Panel;
    private javax.swing.JLabel LMS_Header_Label;
    private javax.swing.JLabel MC_AA_Button_Icon;
    private javax.swing.JLabel MC_AA_Button_Label;
    private javax.swing.JPanel MC_AA_Button_Panel;
    private javax.swing.JTextField MC_Class_ID_Field;
    private javax.swing.JTextField MC_Course_Description_Field;
    private javax.swing.JTextField MC_Course_No_Field;
    private javax.swing.JPanel MC_Current_Courses_Panel;
    private javax.swing.JPanel MC_Current_Courses_Panel1;
    private javax.swing.JTable MC_Current_Courses_Table;
    private javax.swing.JScrollPane MC_Current_Courses_Table_ScrollPane;
    private javax.swing.JScrollPane MC_Current_Courses_Table_ScrollPane1;
    private javax.swing.JTextField MC_Days_Time_Field;
    private javax.swing.JTextField MC_End_Date_Field;
    private javax.swing.JLabel MC_Home_Button_Icon;
    private javax.swing.JLabel MC_Home_Button_Label;
    private javax.swing.JPanel MC_Home_Button_Panel;
    private javax.swing.JTextField MC_Instructor_Name_Field;
    private javax.swing.JLabel MC_Logout_Button_Icon;
    private javax.swing.JLabel MC_Logout_Button_Label;
    private javax.swing.JLabel MC_MC_Button_Icon;
    private javax.swing.JLabel MC_MC_Button_Label;
    private javax.swing.JPanel MC_MC_Button_Panel;
    private javax.swing.JLabel MC_MS_Button_Icon;
    private javax.swing.JLabel MC_MS_Button_Label;
    private javax.swing.JPanel MC_MS_Button_Panel;
    private javax.swing.JTextField MC_Room_No_Field;
    private javax.swing.JLabel MC_SFA_Button_Icon;
    private javax.swing.JLabel MC_SFA_Button_Label;
    private javax.swing.JPanel MC_SFA_Button_Panel;
    private javax.swing.JLabel MC_SF_Button_Icon;
    private javax.swing.JLabel MC_SF_Button_Label;
    private javax.swing.JPanel MC_SF_Button_Panel;
    private javax.swing.JPanel MC_SR_Button_Panel;
    private javax.swing.JLabel MC_Search_Button_Icon;
    private javax.swing.JPanel MC_Search_Button_Panel;
    private javax.swing.JTextField MC_Search_Courses_Field;
    private javax.swing.JLabel MC_Search_Placeholder_Field;
    private javax.swing.JTextField MC_Start_Date_Field;
    private javax.swing.JTextField MC_Units_Field;
    private javax.swing.JLabel MP_Class_Header_Label;
    private javax.swing.JPanel MP_Class_Schedule_Header_Panel;
    private javax.swing.JPanel MP_Class_Schedule_Panel;
    private javax.swing.JTable MP_Class_Schedule_Table;
    private javax.swing.JLabel MP_Class_Schedule_Title_Label;
    private javax.swing.JPanel MP_Class_Schedule_Title_Panel;
    private javax.swing.JLabel MP_Days_Time_Header_Label;
    private javax.swing.JLabel MP_Logout_Button_Icon;
    private javax.swing.JLabel MP_Logout_Button_Label;
    private javax.swing.JLabel MP_Room_Header_Label;
    public static final javax.swing.JLabel MP_Student_Discription_Label = new javax.swing.JLabel();
    private javax.swing.JLabel MP_User_Img;
    public static final javax.swing.JLabel MP_User_Name_Label = new javax.swing.JLabel();
    private javax.swing.JLabel MS_AA_Button_Icon;
    private javax.swing.JLabel MS_AA_Button_Label;
    private javax.swing.JPanel MS_AA_Button_Panel;
    private javax.swing.JLabel MS_Button_Icon;
    private javax.swing.JLabel MS_Button_Label;
    private javax.swing.JTextField MS_Class_Standing_Field;
    private javax.swing.JTable MS_Current_Students_Table;
    private javax.swing.JTextField MS_Email_Address_Field;
    private javax.swing.JTextField MS_First_Name_Field;
    private javax.swing.JLabel MS_Home_Button_Icon;
    private javax.swing.JLabel MS_Home_Button_Label;
    private javax.swing.JPanel MS_Home_Button_Panel;
    private javax.swing.JTextField MS_Last_Name_Field;
    private javax.swing.JTextField MS_Location_Field;
    private javax.swing.JLabel MS_Logout_Button_Icon;
    private javax.swing.JLabel MS_Logout_Button_Label;
    private javax.swing.JLabel MS_MC_Button_Icon;
    private javax.swing.JLabel MS_MC_Button_Label;
    private javax.swing.JPanel MS_MC_Button_Panel;
    private javax.swing.JLabel MS_MS_Button_Icon;
    private javax.swing.JLabel MS_MS_Button_Label;
    private javax.swing.JPanel MS_MS_Button_Panel;
    private javax.swing.JTextField MS_Major_Field;
    private javax.swing.JTextField MS_Phone_Number_Field;
    private javax.swing.JTextField MS_Residency_Status_Field;
    private javax.swing.JLabel MS_SFA_Button_Icon;
    private javax.swing.JLabel MS_SFA_Button_Label;
    private javax.swing.JPanel MS_SFA_Button_Panel;
    private javax.swing.JLabel MS_SF_Button_Icon;
    private javax.swing.JLabel MS_SF_Button_Label;
    private javax.swing.JPanel MS_SF_Button_Panel;
    private javax.swing.JPanel MS_SR_Button_Panel;
    private javax.swing.JLabel MS_Search_Button_Icon;
    private javax.swing.JPanel MS_Search_Button_Panel;
    private javax.swing.JLabel MS_Search_Placeholder_Field;
    private javax.swing.JTextField MS_Search_Student_Field;
    private javax.swing.JTextField MS_Student_ID_Field;
    private javax.swing.JTextField MS_Student_Status_Field;
    private javax.swing.JPanel Main_Frame_Panel;
    private javax.swing.JLabel Minimize_Icon;
    private javax.swing.JPanel Minimize_Icon_Panel;
    private javax.swing.JPanel Move_Panel;
    private javax.swing.JPanel SCC_Button_Panel;
    private javax.swing.JLabel SCC_Tile_Label1;
    private javax.swing.JLabel SCC_Tile_Label2;
    private javax.swing.JLabel SCC_img_Label;
    private javax.swing.JLabel SC_AECG_Button_Icon;
    private javax.swing.JLabel SC_AECG_Button_Label;
    private javax.swing.JPanel SC_AECG_Button_Panel;
    private javax.swing.JButton SC_Add_Course_Button;
    private javax.swing.JPanel SC_Assgin_Courses_Panel;
    private javax.swing.JLabel SC_Assign_Courses_Heading_Label;
    private javax.swing.JPanel SC_Assign_Courses_Panel1;
    private javax.swing.JPanel SC_Assign_Courses_Panel2;
    private javax.swing.JPanel SC_Assign_Withdraw_Courses_Panel;
    private javax.swing.JPanel SC_Button_Panel;
    private javax.swing.JLabel SC_Class_Standing_Heading_Label;
    private javax.swing.JLabel SC_Class_Standing_Heading_Label1;
    public static final javax.swing.JLabel SC_Class_Standing_Label = new javax.swing.JLabel();
    public static final javax.swing.JLabel SC_Class_Standing_Label1 = new javax.swing.JLabel();
    private javax.swing.JLabel SC_Contact_Label;
    private javax.swing.JLabel SC_Contact_Label1;
    private javax.swing.JScrollPane SC_Courses_Table_ScrollPane;
    private javax.swing.JScrollPane SC_Courses_Table_ScrollPane1;
    private javax.swing.JTable SC_Current_Courses_Table;
    private javax.swing.JPanel SC_Current_Students_Panel;
    private javax.swing.JPanel SC_Current_Students_Panel1;
    private javax.swing.JTable SC_Current_Students_Table;
    private javax.swing.JTable SC_Current_Students_Table1;
    private javax.swing.JLabel SC_Current_Students_Table_Heading_Label;
    private javax.swing.JLabel SC_Current_Students_Table_Heading_Label1;
    private javax.swing.JScrollPane SC_Current_Students_Table_ScrollPane;
    private javax.swing.JScrollPane SC_Current_Students_Table_ScrollPane1;
    public static final javax.swing.JLabel SC_Email_Address_Label = new javax.swing.JLabel();
    public static final javax.swing.JLabel SC_Email_Address_Label1 = new javax.swing.JLabel();
    private javax.swing.JLabel SC_Email_Heading_Label;
    private javax.swing.JLabel SC_Email_Heading_Label1;
    private javax.swing.JLabel SC_Hide_Student_Current_Courses_Label;
    private javax.swing.JLabel SC_Home_Button_Icon;
    private javax.swing.JLabel SC_Home_Button_Label;
    private javax.swing.JPanel SC_Home_Button_Panel;
    private javax.swing.JLabel SC_Location_Heading_Label;
    private javax.swing.JLabel SC_Location_Heading_Label1;
    public static final javax.swing.JLabel SC_Location_Label = new javax.swing.JLabel();
    public static final javax.swing.JLabel SC_Location_Label1 = new javax.swing.JLabel();
    private javax.swing.JLabel SC_Logout_Button_Icon;
    private javax.swing.JLabel SC_Logout_Button_Label;
    private javax.swing.JLabel SC_MC_Button_Icon;
    private javax.swing.JLabel SC_MC_Button_Label;
    private javax.swing.JPanel SC_MC_Button_Panel;
    private javax.swing.JLabel SC_MS_Button_Icon;
    private javax.swing.JLabel SC_MS_Button_Label;
    private javax.swing.JPanel SC_MS_Button_Panel;
    private javax.swing.JLabel SC_Major_Heading_Label;
    private javax.swing.JLabel SC_Major_Heading_Label1;
    public static final javax.swing.JLabel SC_Major_Label = new javax.swing.JLabel();
    public static final javax.swing.JLabel SC_Major_Label1 = new javax.swing.JLabel();
    private javax.swing.JLabel SC_Phone_Number_Heading_Label;
    private javax.swing.JLabel SC_Phone_Number_Heading_Label1;
    public static final javax.swing.JLabel SC_Phone_Number_Label = new javax.swing.JLabel();
    public static final javax.swing.JLabel SC_Phone_Number_Label1 = new javax.swing.JLabel();
    private javax.swing.JPanel SC_Registered_Courses_Panel;
    private javax.swing.JLabel SC_Residency_Status_Heading_Label;
    private javax.swing.JLabel SC_Residency_Status_Heading_Label1;
    public static final javax.swing.JLabel SC_Residency_Status_Label = new javax.swing.JLabel();
    public static final javax.swing.JLabel SC_Residency_Status_Label1 = new javax.swing.JLabel();
    private javax.swing.JLabel SC_SC_Button_Icon;
    private javax.swing.JLabel SC_SC_Button_Label;
    private javax.swing.JPanel SC_SC_Button_Panel;
    private javax.swing.JLabel SC_SFA_Button_Icon;
    private javax.swing.JLabel SC_SFA_Button_Label;
    private javax.swing.JPanel SC_SFA_Button_Panel;
    private javax.swing.JLabel SC_SF_Button_Icon;
    private javax.swing.JLabel SC_SF_Button_Label;
    private javax.swing.JPanel SC_SF_Button_Panel;
    private javax.swing.JLabel SC_Search_Button_Icon;
    private javax.swing.JLabel SC_Search_Button_Icon1;
    private javax.swing.JPanel SC_Search_Button_Panel;
    private javax.swing.JPanel SC_Search_Button_Panel1;
    private javax.swing.JPanel SC_Search_Course_Button_Panel;
    private javax.swing.JTextField SC_Search_Course_Field;
    private javax.swing.JLabel SC_Search_Courses_Button_Icon;
    private javax.swing.JLabel SC_Search_Courses_Placeholder_Field;
    private javax.swing.JLabel SC_Search_Placeholder_Field;
    private javax.swing.JLabel SC_Search_Placeholder_Field1;
    private javax.swing.JTextField SC_Search_Student_Field;
    private javax.swing.JTextField SC_Search_Student_Field1;
    private javax.swing.JLabel SC_Show_Student_Current_Courses_Label;
    private javax.swing.JTable SC_Student_Courses_Table;
    private javax.swing.JLabel SC_Student_ID_Heading_Label;
    private javax.swing.JLabel SC_Student_ID_Heading_Label1;
    public static final javax.swing.JLabel SC_Student_ID_Label = new javax.swing.JLabel();
    public static final javax.swing.JLabel SC_Student_ID_Label1 = new javax.swing.JLabel();
    private javax.swing.JLabel SC_Student_Information_Heading_Label;
    private javax.swing.JLabel SC_Student_Information_Heading_Label1;
    private javax.swing.JPanel SC_Student_Information_Panel;
    private javax.swing.JPanel SC_Student_Information_Panel1;
    private javax.swing.JLabel SC_Student_Name_Heading_Label;
    private javax.swing.JLabel SC_Student_Name_Heading_Label1;
    public static final javax.swing.JLabel SC_Student_Name_Label = new javax.swing.JLabel();
    public static final javax.swing.JLabel SC_Student_Name_Label1 = new javax.swing.JLabel();
    private javax.swing.JLabel SC_Student_Status_Heading_Label;
    private javax.swing.JLabel SC_Student_Status_Heading_Label1;
    public static final javax.swing.JLabel SC_Student_Status_Label = new javax.swing.JLabel();
    public static final javax.swing.JLabel SC_Student_Status_Label1 = new javax.swing.JLabel();
    private javax.swing.JLabel SC_Tile_Label;
    private javax.swing.JButton SC_Withdraw_Button;
    private javax.swing.JLabel SC_img_Label;
    private javax.swing.JPanel SFA_Button_Panel;
    private javax.swing.JLabel SFA_Tile_Label1;
    private javax.swing.JLabel SFA_Tile_Label2;
    private javax.swing.JLabel SFA_img_Label;
    private javax.swing.JPanel SF_Button_Panel;
    private javax.swing.JLabel SF_Tile_Label;
    private javax.swing.JLabel SF_img_Label;
    private javax.swing.JLabel SP_AA_Button_Icon;
    private javax.swing.JLabel SP_AA_Button_Label;
    private javax.swing.JLabel SP_Class_Standing_Heading_Label;
    public static final javax.swing.JLabel SP_Class_Standing_Label = new javax.swing.JLabel();
    private javax.swing.JLabel SP_Contact_Label;
    private javax.swing.JComboBox<String> SP_Course_Selection_ComboBox;
    private javax.swing.JLabel SP_Email_Heading_Label;
    public static final javax.swing.JLabel SP_Email_Label = new javax.swing.JLabel();
    private javax.swing.JLabel SP_Exam_Grades_Heading_Label;
    private javax.swing.JPanel SP_Exam_Grades_Panel;
    private javax.swing.JTable SP_Exam_Grades_Table;
    private javax.swing.JScrollPane SP_Exam_Grades_Table_ScrollPane;
    private javax.swing.JLabel SP_GPA_Formula_Label;
    private javax.swing.JLabel SP_GPA_Heading_Label;
    private javax.swing.JLabel SP_GPA_Label;
    private javax.swing.JPanel SP_GPA_Panel;
    private javax.swing.JTable SP_GPA_Table;
    private javax.swing.JScrollPane SP_GPA_Table_ScrollPane;
    private javax.swing.JLabel SP_Home_Button_Icon;
    private javax.swing.JLabel SP_Home_Button_Label;
    private javax.swing.JLabel SP_Location_Heading_Label;
    public static final javax.swing.JLabel SP_Location_Label = new javax.swing.JLabel();
    private javax.swing.JLabel SP_Logout_Button_Icon;
    private javax.swing.JLabel SP_Logout_Button_Label;
    private javax.swing.JLabel SP_Major_Heading_Label;
    public static final javax.swing.JLabel SP_Major_Label = new javax.swing.JLabel();
    private javax.swing.JLabel SP_Phone_Number_Heading_Label;
    public static final javax.swing.JLabel SP_Phone_Number_Label = new javax.swing.JLabel();
    private javax.swing.JLabel SP_Registered_Courses_Heading_Label;
    private javax.swing.JPanel SP_Registered_Courses_Panel;
    private javax.swing.JTable SP_Registered_Courses_Table;
    private javax.swing.JScrollPane SP_Registered_Courses_Table_ScrollPane;
    private javax.swing.JLabel SP_Residency_Status_Heading_Label;
    public static final javax.swing.JLabel SP_Residency_Status_Label = new javax.swing.JLabel();
    private javax.swing.JLabel SP_SCC_Button_Icon;
    private javax.swing.JLabel SP_SCC_Button_Label;
    private javax.swing.JLabel SP_SC_Button_Icon;
    private javax.swing.JLabel SP_SC_Button_Label;
    private javax.swing.JLabel SP_SFA_Button_Icon;
    private javax.swing.JLabel SP_SFA_Button_Label;
    private javax.swing.JLabel SP_SF_Button_Icon;
    private javax.swing.JLabel SP_SF_Button_Label;
    private javax.swing.JLabel SP_SRE_Button_Icon;
    private javax.swing.JLabel SP_SRE_Button_Label;
    private javax.swing.JLabel SP_Student_ID_Heading_Label;
    public static final javax.swing.JLabel SP_Student_ID_Label = new javax.swing.JLabel();
    private javax.swing.JLabel SP_Student_Information_Heading_Label;
    private javax.swing.JLabel SP_Student_Information_Heading_Label1;
    private javax.swing.JLabel SP_Student_Information_Heading_Label2;
    private javax.swing.JLabel SP_Student_Information_Heading_Label4;
    private javax.swing.JPanel SP_Student_Information_Panel;
    private javax.swing.JLabel SP_Student_Status_Heading_Label;
    public static final javax.swing.JLabel SP_Student_Status_Label = new javax.swing.JLabel();
    private javax.swing.JPanel SRE_Button_Panel;
    private javax.swing.JLabel SRE_Tile_Label1;
    private javax.swing.JLabel SRE_Tile_Label2;
    private javax.swing.JLabel SRE_img_Label;
    private javax.swing.JLabel SR_Button_Icon;
    private javax.swing.JLabel SR_Button_Label;
    private javax.swing.JPanel Scholarships_Financial_Aid_Panel;
    private javax.swing.JPanel Student_Center_Panel;
    private javax.swing.JPanel Student_Comminication_Center_Panel;
    public static final javax.swing.JLabel Student_Discription_Label = new javax.swing.JLabel();
    private javax.swing.JPanel Student_Financials_Panel;
    private javax.swing.JPanel Student_Main_Page_Left_Panel;
    private javax.swing.JPanel Student_Main_Page_Panel;
    private javax.swing.JPanel Student_Main_Page_Right_Panel;
    public static final javax.swing.JLabel Student_Name_Label = new javax.swing.JLabel();
    private javax.swing.JPanel Student_Records_Enrollment_Panel;
    private javax.swing.JPanel Student_SRE_Left_Panel;
    private javax.swing.JPanel Student_SRE_Panel;
    private javax.swing.JPanel Student_SRE_Right_Panel;
    private javax.swing.JLabel User_Img;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
// Round Panel
class RoundedPanel extends JPanel
    {
        private Color backgroundColor;
        private int cornerRadius = 15;
        public RoundedPanel(LayoutManager layout, int radius) {
            super(layout);
            cornerRadius = radius;
        }
        public RoundedPanel(LayoutManager layout, int radius, Color bgColor) {
            super(layout);
            cornerRadius = radius;
            backgroundColor = bgColor;
        }
        public RoundedPanel(int radius) {
            super();
            cornerRadius = radius;
            
        }
        public RoundedPanel(int radius, Color bgColor) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            //Draws the rounded panel with borders.
            if (backgroundColor != null) {
                graphics.setColor(backgroundColor);
            } else {
                graphics.setColor(getBackground());
            }
            graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint background
            graphics.setColor(getForeground());
//          graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
//             
        }
}
