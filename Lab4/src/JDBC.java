
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC {
	
	private Object userName = new String ("root");
	private Object password = new String ("Pa$$w0rd");
	private Object dbms = new String("mysql");
	private String serverName = "127.0.0.1";
	private String portNumber = "3306";
	private String dbName = "hscope";

	void connect() {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		if (this.dbms.equals("mysql")) {
			try {
				conn = DriverManager.getConnection(
						"jdbc:" + this.dbms + "://" + this.serverName + ":" + this.portNumber + "/" + dbName, connectionProps);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS");
			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				Float avgrade = rs.getFloat("AVGRADE");
				System.out.println("[" + id + "] " + name + " = " + avgrade );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connected to database");}
}
