package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

	private static DBConnection instance;
	private static Connection con;
	
	private static final String DRIVER="com.mysql.jdbc.Driver";
	private static final String URL="jdbc:mysql://localhost/";
	//private static final String URL="jdbc:mysql://127.0.0.1:3306";
	private static final String DB_NAME="tienda_regalos";
	
	
	public DBConnection(){
		con=null;
		
	}
	
	public static DBConnection getInstance(String user,String pass) throws ClassNotFoundException, SQLException{
		String url="";
		if(instance==null){
			instance=new DBConnection();
		}
		if(con==null){
			Class.forName(DRIVER);
			url=URL + DB_NAME;
			con= DriverManager.getConnection(url,user,pass);
		}
		return instance;
	}
	
	public PreparedStatement getStatement(String sql) throws SQLException{
		return con.prepareStatement(sql);
	}
	
	public void close() throws SQLException {
		con.close();
	}
	
	
}
