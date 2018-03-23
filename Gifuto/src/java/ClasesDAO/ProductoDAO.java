/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesDAO;

import DAO.DBConnection;
import JavaBeans.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Noe
 */
public class ProductoDAO {

    public static boolean registrarProducto(Producto obj) {
         boolean resp=true;
        try {
            String sql = "call registrarProducto(?,?,?,true,?,?,?,?,?,?)";
            DBConnection con = DBConnection.getInstance("root", "");
            PreparedStatement ps = con.getStatement(sql);
            ps.setInt(1, obj.getCategoria());
            ps.setString(2, obj.getNombre());
            ps.setString(3, obj.getDescripcion());
            ps.setBoolean(4, obj.isVisible());
            ps.setBoolean(5, obj.isRecomendado());
            ps.setInt(6, obj.getExistencia());
            ps.setDouble(7, obj.getCosto());
            ps.setDouble(8, obj.getCostoNuevo());
            ps.setString(9, obj.getFoto());
            ps.executeUpdate();
            ps.close();
                return resp;
        } catch (ClassNotFoundException ex) {
            return false;
        } catch (SQLException ex) {
            return false;
        }
    }

    
       public static ArrayList<Producto> listarProductosRecomendados() {
         boolean resp=true;
       
        try {
            String sql = "call listar_recomendados()";
            DBConnection con = DBConnection.getInstance("root", "");
            PreparedStatement ps = con.getStatement(sql);
            ResultSet rs = ps.executeQuery();
              ArrayList<Producto> proc = new ArrayList<>();
            while(rs.next()){
            Producto p = new Producto();
            p.setId(rs.getInt(1));
            p.setNombre(rs.getString(2));
            String desc = rs.getString(3);
            desc=Character.toUpperCase(desc.charAt(0)) + desc.substring(1,desc.length());
            p.setDescripcion(desc);
            p.setNuevo(rs.getBoolean(4));
            p.setVisible(rs.getBoolean(5));
            p.setRecomendado(rs.getBoolean(6));
            p.setExistencia(rs.getInt(7));
            p.setCosto(rs.getDouble(8));
            p.setCostoNuevo(rs.getDouble(9));
            p.setFoto(rs.getString(10));
            proc.add(p);
            }
            ps.close();
                return proc;
        } catch (ClassNotFoundException ex) {
            return null;
        } catch (SQLException ex) {
            return null;
        }
    }

        public static ArrayList<Producto> listarProductosPorCategoria(int cat) {
         boolean resp=true;
       
        try {
            String sql = "call listarPorCategoria(?)";
            DBConnection con = DBConnection.getInstance("root", "");
            PreparedStatement ps = con.getStatement(sql);
            ps.setInt(1, cat);
            ResultSet rs = ps.executeQuery();
              ArrayList<Producto> proc = new ArrayList<>();
            while(rs.next()){
            Producto p = new Producto();
            p.setId(rs.getInt(1));
            p.setNombre(rs.getString(2));
            String desc = rs.getString(3);
            desc=Character.toUpperCase(desc.charAt(0)) + desc.substring(1,desc.length());
            p.setDescripcion(desc);
            p.setNuevo(rs.getBoolean(4));
            p.setVisible(rs.getBoolean(5));
            p.setRecomendado(rs.getBoolean(6));
            p.setExistencia(rs.getInt(7));
            p.setCosto(rs.getDouble(8));
            p.setCostoNuevo(rs.getDouble(9));
            p.setFoto(rs.getString(10));
            proc.add(p);
            }
            ps.close();
                return proc;
        } catch (ClassNotFoundException ex) {
            return null;
        } catch (SQLException ex) {
            return null;
        }
    }

    
}
