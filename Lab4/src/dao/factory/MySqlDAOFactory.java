package dao.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import dao.HoroscopeDAO;
import dao.impl.MySqlDAO;

public class MySqlDAOFactory extends DAOFactory {
	
	private static Object userName = new String ("farseer");
	private static Object password = new String ("magic");
	private static Object dbms = new String("mysql");
	private static String serverName = "127.0.0.1";
	private static String portNumber = "3306";
	private static String dbName = "hscope";
	
	public static Connection createConnection() {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if (dbms.equals("mysql")) {
			try {
				conn = DriverManager.getConnection(
						"jdbc:" + dbms + "://" + serverName + ":" + portNumber + "/" + dbName + 
						"?useSSL=false", connectionProps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				
		return conn;

	}
	
	public static void closeConnection(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public HoroscopeDAO getHoroscopeDAO() {
		return new MySqlDAO();
	}
	
}
