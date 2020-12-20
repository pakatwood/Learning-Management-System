/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.management.system;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import static learning.management.system.MainFrame.Admin_AECG_Discription_Label;
import static learning.management.system.MainFrame.Admin_AECG_Name_Label;
import static learning.management.system.MainFrame.Admin_Discription_Label;
import static learning.management.system.MainFrame.Admin_MC_Discription_Label;
import static learning.management.system.MainFrame.Admin_MC_Name_Label;
import static learning.management.system.MainFrame.Admin_MS_Discription_Label;
import static learning.management.system.MainFrame.Admin_MS_Name_Label;
import static learning.management.system.MainFrame.Admin_Name_Label;
import static learning.management.system.MainFrame.MP_Student_Discription_Label;
import static learning.management.system.MainFrame.MP_User_Name_Label;
import static learning.management.system.MainFrame.SP_Class_Standing_Label;
import static learning.management.system.MainFrame.SP_Email_Label;
import static learning.management.system.MainFrame.SP_Location_Label;
import static learning.management.system.MainFrame.SP_Major_Label;
import static learning.management.system.MainFrame.SP_Phone_Number_Label;
import static learning.management.system.MainFrame.SP_Residency_Status_Label;
import static learning.management.system.MainFrame.SP_Student_ID_Label;
import static learning.management.system.MainFrame.Student_Discription_Label;
import static learning.management.system.MainFrame.Student_Name_Label;
import static learning.management.system.MainFrame.SP_Student_Status_Label;
import static learning.management.system.MainFrame.Admin_SC_Name_Label;
import static learning.management.system.MainFrame.Admin_SC_Discription_Label;
/**
 *
 * @author Haider Qazi (Chief Programmer), Daniel Gasperini (Backup Programmer), Javier Blanco (Programmer), David Dinh (Programmer), Christian Francois (Programming Secretary)
 */
public class LoginFrame extends javax.swing.JFrame {
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    public static Boolean User_Admin = false;
    public static String Current_Student_ID;
    public static String LoggedInTime;
    public static String Date;
    
    /**
     * Creates new form LoginFrame
     */
    public LoginFrame() {
        initComponents();
        Show_Password_Panel.setVisible(false);
        Hide_Password_Panel.setVisible(false);
    }
    
    // Admin Login
    public void AdminLogin(){
        conn = MySqlConnect.ConnectDB();
        String Sql = "SELECT * FROM administrator where Username = ? and Password = ?";
        try{
            pst = conn.prepareStatement(Sql);
            pst.setString(1,Username_Field.getText());
            pst.setString(2, Password_Field.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                this.hide();
                LoadingScreen splash = new LoadingScreen();
                splash.setVisible(true);
                try {
                    for(int i = 0; i <= 100; i++) {
                        if(i == 1){
                            MainFrame w = new MainFrame();
                            splash.setVisible(false);
                            w.setVisible(true);
                        }
                    }
                } catch (Exception e) {
                    
                }
                Admin_Name_Label.setText(rs.getString(2) + " " + rs.getString(3));
                Admin_Discription_Label.setText("Administrator" + " | " + "University of Houston");
                Admin_MC_Name_Label.setText(rs.getString(2) + " " + rs.getString(3));
                Admin_MC_Discription_Label.setText("Administrator" + " | " + "University of Houston");
                Admin_MS_Name_Label.setText(rs.getString(2) + " " + rs.getString(3));
                Admin_MS_Discription_Label.setText("Administrator" + " | " + "University of Houston");
                Admin_SC_Name_Label.setText(rs.getString(2) + " " + rs.getString(3));
                Admin_SC_Discription_Label.setText("Administrator" + " | " + "University of Houston");
                Admin_AECG_Name_Label.setText(rs.getString(2) + " " + rs.getString(3));
                Admin_AECG_Discription_Label.setText("Administrator" + " | " + "University of Houston");
                rs.close();
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Access Denied", JOptionPane.ERROR_MESSAGE);
            } 
        } catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    // Student Login
    public void StudentLogin(){
        conn = MySqlConnect.ConnectDB();
        String Sql = "SELECT * FROM student where Username = ? and Password = ?";
        try{
            pst = conn.prepareStatement(Sql);
            pst.setString(1,Username_Field.getText());
            pst.setString(2, Password_Field.getText());
            rs = pst.executeQuery();
            if(rs.next()){
                Current_Student_ID = rs.getString(1);
                this.hide();
                LoadingScreen splash = new LoadingScreen();
                splash.setVisible(true);
                try {
                    for(int i = 0; i <= 100; i++) {
                        if(i == 1){
                            MainFrame w = new MainFrame();
                            splash.setVisible(false);
                            w.setVisible(true);
                        }
                    }
                } catch (Exception e) {
                    
                }
                String Student_Name = rs.getString(2) + " " + rs.getString(3);

                conn = MySqlConnect.ConnectDB();
                    try{
                        Date currentDate = new Date();
                        SimpleDateFormat LoggedIn = new SimpleDateFormat("h:mm a");
                        LoggedInTime = LoggedIn.format(currentDate);
                        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
                        Date = date.format(currentDate);
                        InetAddress IP = InetAddress.getLocalHost();
                        String IP_Address = IP.getHostAddress();
                        PreparedStatement insert = conn.prepareStatement("INSERT INTO login_history (Student_ID, Student_Name, Logged_In, Date, IP_Address) VALUES ('"+Current_Student_ID+"', '"+Student_Name+"', '"+LoggedInTime+"', '"+Date+"', '"+IP_Address+"')");
                        insert.executeUpdate();
                        conn.close();
                    }catch (Exception e) {
                        JOptionPane.showMessageDialog(rootPane, e);
                    }
                MP_User_Name_Label.setText(Student_Name);
                MP_Student_Discription_Label.setText("Student" + " | " + "University of Houston");
                Student_Name_Label.setText(Student_Name);
                Student_Discription_Label.setText("Student" + " | " + "University of Houston");
                SP_Student_ID_Label.setText(rs.getString(1));
                SP_Student_Status_Label.setText(rs.getString(4));
                SP_Residency_Status_Label.setText(rs.getString(5));
                SP_Class_Standing_Label.setText(rs.getString(6));
                SP_Location_Label.setText(rs.getString(7));
                SP_Major_Label.setText(rs.getString(8));
                SP_Phone_Number_Label.setText(rs.getString(9));
                SP_Email_Label.setText(rs.getString(10));
                rs.close();
            }
            else{
                //JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Access Denied", JOptionPane.ERROR_MESSAGE);
                Login_Message_Label.setText("Invalid User or Password!");
            } 
        } catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void close(){
        WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login_Panel = new javax.swing.JPanel();
        logo_Panel = new javax.swing.JPanel();
        logo_Label = new javax.swing.JLabel();
        title_Label = new javax.swing.JLabel();
        Login_Form_Panel = new javax.swing.JPanel();
        Username_Field = new javax.swing.JTextField();
        Show_Password_Panel = new javax.swing.JPanel();
        Show_Password_Icon = new javax.swing.JLabel();
        Hide_Password_Panel = new javax.swing.JPanel();
        Hide_Password_Icon = new javax.swing.JLabel();
        Password_Field = new javax.swing.JPasswordField();
        Administrator_CheckBox = new javax.swing.JCheckBox();
        Login_Button_Panel = new javax.swing.JPanel();
        Login_Button_Icon = new javax.swing.JLabel();
        Cancel_Button_Panel = new javax.swing.JPanel();
        Cancel_Button_Icon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        login_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo_Panel.setBackground(new java.awt.Color(0, 41, 85));
        logo_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(85, 142, 203), 3));
        logo_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logo_Label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/login_page/Login_Logo_200x200.png"))); // NOI18N
        logo_Panel.add(logo_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 210));

        title_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 15)); // NOI18N
        title_Label.setForeground(new java.awt.Color(85, 142, 203));
        title_Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_Label.setText("Learning Management System");
        logo_Panel.add(title_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 220, 40));

        login_Panel.add(logo_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 250));

        Login_Form_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Login_Form_Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(85, 142, 203), 3));
        Login_Form_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Username_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        Username_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 41, 85)), "USERNAME", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 1, 12), new java.awt.Color(0, 41, 85))); // NOI18N
        Login_Form_Panel.add(Username_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 250, 55));

        Show_Password_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Show_Password_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Show_Password_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Show_Password_PanelMouseClicked(evt);
            }
        });
        Show_Password_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Show_Password_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Show_Password_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Hide_Password_Icon_20x16.png"))); // NOI18N
        Show_Password_Panel.add(Show_Password_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 20, 40));

        Login_Form_Panel.add(Show_Password_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 20, 40));

        Hide_Password_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Hide_Password_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Hide_Password_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Hide_Password_PanelMouseClicked(evt);
            }
        });
        Hide_Password_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Hide_Password_Icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Hide_Password_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/icons/Show_Password_Icon_20x16.png"))); // NOI18N
        Hide_Password_Panel.add(Hide_Password_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 20, 40));

        Login_Form_Panel.add(Hide_Password_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 110, 20, 40));

        Password_Field.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 20)); // NOI18N
        Password_Field.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 41, 85)), "PASSWORD", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Yu Gothic UI Light", 1, 12), new java.awt.Color(0, 41, 85))); // NOI18N
        Password_Field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                Password_FieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                Password_FieldFocusLost(evt);
            }
        });
        Password_Field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Password_FieldKeyPressed(evt);
            }
        });
        Login_Form_Panel.add(Password_Field, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 250, 55));

        Administrator_CheckBox.setBackground(new java.awt.Color(255, 255, 255));
        Administrator_CheckBox.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        Administrator_CheckBox.setText("Administrator");
        Administrator_CheckBox.setBorder(null);
        Administrator_CheckBox.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Administrator_CheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Administrator_CheckBoxActionPerformed(evt);
            }
        });
        Login_Form_Panel.add(Administrator_CheckBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 165, 100, -1));

        Login_Button_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Login_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Login_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Login_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Login_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Login_Button_PanelMouseExited(evt);
            }
        });
        Login_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Login_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/login_page/Login_Button.png"))); // NOI18N
        Login_Button_Panel.add(Login_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 100, 40));

        Login_Form_Panel.add(Login_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 195, 100, 40));

        Cancel_Button_Panel.setBackground(new java.awt.Color(255, 255, 255));
        Cancel_Button_Panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Cancel_Button_Panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Cancel_Button_PanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Cancel_Button_PanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Cancel_Button_PanelMouseExited(evt);
            }
        });
        Cancel_Button_Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Cancel_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/login_page/Cancel_Button.png"))); // NOI18N
        Cancel_Button_Panel.add(Cancel_Button_Icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 100, 40));

        Login_Form_Panel.add(Cancel_Button_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 195, 100, 40));

        Login_Message_Label.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 10)); // NOI18N
        Login_Message_Label.setForeground(new java.awt.Color(204, 0, 0));
        Login_Message_Label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Login_Form_Panel.add(Login_Message_Label, new org.netbeans.lib.awtextra.AbsoluteConstraints(107, 0, 170, 30));

        login_Panel.add(Login_Form_Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 308, 250));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login_Panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(login_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(528, 250));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void Password_FieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Password_FieldKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if (LoginFrame.User_Admin == true){
                AdminLogin();
            }
            else if ((LoginFrame.User_Admin == false))
            {
                StudentLogin();
            }
        }
    }//GEN-LAST:event_Password_FieldKeyPressed

    private void Login_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Login_Button_PanelMouseEntered
        // TODO add your handling code here:
        Login_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/login_page/Login_Button_Hover.png")));
    }//GEN-LAST:event_Login_Button_PanelMouseEntered

    private void Login_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Login_Button_PanelMouseExited
        // TODO add your handling code here:
        Login_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/login_page/Login_Button.png")));
    }//GEN-LAST:event_Login_Button_PanelMouseExited

    private void Cancel_Button_PanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cancel_Button_PanelMouseEntered
        // TODO add your handling code here:
        Cancel_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/login_page/Cancel_Button_Hover.png")));
    }//GEN-LAST:event_Cancel_Button_PanelMouseEntered

    private void Cancel_Button_PanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cancel_Button_PanelMouseExited
        // TODO add your handling code here:
        Cancel_Button_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/learning/management/system/resources/images/login_page/Cancel_Button.png")));
    }//GEN-LAST:event_Cancel_Button_PanelMouseExited

    private void Cancel_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Cancel_Button_PanelMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_Cancel_Button_PanelMouseClicked

    private void Login_Button_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Login_Button_PanelMouseClicked
        // TODO add your handling code here:
        if (LoginFrame.User_Admin == true){
            AdminLogin();
        }
        else if ((LoginFrame.User_Admin == false))
        {
            StudentLogin();
        }
    }//GEN-LAST:event_Login_Button_PanelMouseClicked

    private void Show_Password_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Show_Password_PanelMouseClicked
        // TODO add your handling code here:
        Show_Password_Panel.setVisible(false);
        Hide_Password_Panel.setVisible(true);
        Password_Field.setEchoChar((char)0);
    }//GEN-LAST:event_Show_Password_PanelMouseClicked

    private void Hide_Password_PanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Hide_Password_PanelMouseClicked
        // TODO add your handling code here:
        Hide_Password_Panel.setVisible(false);
        Show_Password_Panel.setVisible(true);
        Password_Field.setEchoChar('*');
    }//GEN-LAST:event_Hide_Password_PanelMouseClicked

    private void Password_FieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Password_FieldFocusGained
        // TODO add your handling code here:
        Show_Password_Panel.setVisible(true);
    }//GEN-LAST:event_Password_FieldFocusGained

    private void Password_FieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_Password_FieldFocusLost
        // TODO add your handling code here:
        Show_Password_Panel.setVisible(false);
        Hide_Password_Panel.setVisible(false);
    }//GEN-LAST:event_Password_FieldFocusLost

    private void Administrator_CheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Administrator_CheckBoxActionPerformed
        // TODO add your handling code here:
        if(Administrator_CheckBox.isSelected()){
            LoginFrame.User_Admin = true;
        }
        else
        {
            LoginFrame.User_Admin = false;
        }
    }//GEN-LAST:event_Administrator_CheckBoxActionPerformed

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
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox Administrator_CheckBox;
    private javax.swing.JLabel Cancel_Button_Icon;
    private javax.swing.JPanel Cancel_Button_Panel;
    private javax.swing.JLabel Hide_Password_Icon;
    private javax.swing.JPanel Hide_Password_Panel;
    private javax.swing.JLabel Login_Button_Icon;
    private javax.swing.JPanel Login_Button_Panel;
    private javax.swing.JPanel Login_Form_Panel;
    public static final javax.swing.JLabel Login_Message_Label = new javax.swing.JLabel();
    private javax.swing.JPasswordField Password_Field;
    private javax.swing.JLabel Show_Password_Icon;
    private javax.swing.JPanel Show_Password_Panel;
    private javax.swing.JTextField Username_Field;
    private javax.swing.JPanel login_Panel;
    private javax.swing.JLabel logo_Label;
    private javax.swing.JPanel logo_Panel;
    private javax.swing.JLabel title_Label;
    // End of variables declaration//GEN-END:variables
}
