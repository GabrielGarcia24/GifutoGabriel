/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesDAO;



import DAO.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * @author Noe
 */ 
public class ClienteDAO { 
    public static int iniciarSesion(String cuenta,String pass){
        try {
           
            String sql = "call sesionCliente(?,?)";
            DBConnection con = DBConnection.getInstance("root", "");
            PreparedStatement ps = con.getStatement(sql);
            ps.setString(1,cuenta);
            ps.setString(2,pass);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            
            return rs.getInt(1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
        
    }
    
    
    public static String miSesion(String cuenta){
        try {
            
            String sql = "call miSesion(?)";
            DBConnection con = DBConnection.getInstance("root", "");
            PreparedStatement ps = con.getStatement(sql);
            ps.setString(1,cuenta);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            
            return rs.getString(1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static boolean enSesion(String cuenta){
        try {
           
            String sql = "call enSesion(?)";
            DBConnection con = DBConnection.getInstance("root", "");
            PreparedStatement ps = con.getStatement(sql);
            ps.setString(1,cuenta);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            
            return rs.getBoolean(1);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void cerrarSesion(String cuenta){
    try {
           
            String sql = "call cerrarSesion(?)";
            DBConnection con = DBConnection.getInstance("root", "");
            PreparedStatement ps = con.getStatement(sql);
            ps.setString(1,cuenta);
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void iniciarSesion(String cuenta){
    try {
           
            String sql = "call iniciarSesion(?)";
            DBConnection con = DBConnection.getInstance("root", "");
            PreparedStatement ps = con.getStatement(sql);
            ps.setString(1,cuenta);
            ps.executeUpdate();
            ps.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
