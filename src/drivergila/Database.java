/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drivergila;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author aditj
 */
public class Database {
    private static Connection conn;
    
    public static Connection koneksiDB(){
        if(conn==null){
            try {
                String DB="jdbc:mysql://localhost:3306/drivergila"; // 
                String user="root"; // user database
                String pass=""; // password database
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                conn = (Connection) DriverManager.getConnection(DB,user,pass);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"gagal koneksi "+e);
            }
        }
        return conn;
    }
}
