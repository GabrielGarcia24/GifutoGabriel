/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClasesDAO;

import DAO.DBConnection;
import JavaBeans.Categoria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Noe
 */
public class CategoriaDAO {

    public static ArrayList<Categoria> listar(){
        try {
            String sql = "call listarCategoriasSupeior()";
            DBConnection con = DBConnection.getInstance("root", "");
            PreparedStatement sentencia = con.getStatement(sql);
            ResultSet rs = sentencia.executeQuery();

            ArrayList<Categoria> lista = new ArrayList<>();

            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getInt(1));
                cat.setNombre(rs.getString(2));
                lista.add(cat);
            }
            return lista;

        } catch (ClassNotFoundException ex) {
            return null;
        } catch (SQLException ex) {
            return null;
        }

    }
    
    public static ArrayList<Categoria> listarSubCategorias(int idCatSup) {
        try {
            String sql = "call listarSubCategoriasSupeior(?)";
            DBConnection con = DBConnection.getInstance("root", "");
            PreparedStatement sentencia = con.getStatement(sql);
            sentencia.setInt(1, idCatSup);
            ResultSet rs = sentencia.executeQuery();

            ArrayList<Categoria> lista = new ArrayList<>();

            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setId(rs.getInt(1));
                cat.setNombre(rs.getString(2));
                lista.add(cat);
            }
            return lista;

        } catch (ClassNotFoundException ex) {
            return null;
        } catch (SQLException ex) {
            return null;
        }

    }

     public static boolean esSuperior(int idCatSup){
        try {
            String sql = "call contarSubCategoriasSupeior(?)";
            DBConnection con = DBConnection.getInstance("root", "");
            PreparedStatement sentencia = con.getStatement(sql);
            sentencia.setInt(1, idCatSup);
            ResultSet rs = sentencia.executeQuery();

            rs.next();
               
            return rs.getInt(1)>0;

        }catch (ClassNotFoundException ex) {
           return false;
        } catch (SQLException ex) {
            return false;
        }

    }
    
    public static void main(String[] as) {
        listar();
    }
}
