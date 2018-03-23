package DAO;


import java.sql.*;

public class Conexion {

    
    public Connection conexion=null;
    public Statement sentenciaSQL = null;
    public ResultSet rt=null;

    public void Conectar(){
        try{
            String controlador ="com.mysql.jdbc.Driver";
            Class.forName(controlador).newInstance();
            
            conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/tienda_regalos","root", "");
            sentenciaSQL=getConexion().createStatement();
        }
        catch(ClassNotFoundException e){
            System.out.println("no se pudo cargar el controlador" + e.getMessage());
        }
        catch(SQLException e){
            System.out.println("Excepcion SQL:" + e.getMessage());
        }    
         catch(InstantiationException e){
             System.out.println("Objeto no creado:" +e.getMessage());
         }   
            catch(IllegalAccessException e){
                System.out.println("Acceso ilegal: " + e.getMessage());
            }
    }
     public void cerrar(){
         try{
             if(getSentenciaSQL() != null)
                 getSentenciaSQL().close();
             if(getConexion() != null)
                 getConexion().close();
         }
         catch(SQLException ignorada){
             
         }
     }     
     
     public Connection getConexion(){
         return conexion;
     }
     public Statement getSentenciaSQL(){
         return sentenciaSQL;
     }
}

   