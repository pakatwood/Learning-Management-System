/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learning.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Haider Qazi (Chief Programmer), Daniel Gasperini (Backup Programmer), Javier Blanco (Programmer), David Dinh (Programmer), Christian Francois (Programming Secretary)
 */
public class MySqlConnect {
    Connection conn = null;
    public static Connection ConnectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://82.163.176.111:3306/learni12_learning_management_system", "learni12_learni12", "tenapples9");
            return conn; 
        }
    catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
    return null;
    }
    }
}
