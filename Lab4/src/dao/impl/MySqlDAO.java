package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import dao.HoroscopeDAO;
import dao.factory.MySqlDAOFactory;

public class MySqlDAO implements HoroscopeDAO{
	
	@Override
	public boolean predictGrades(int id) {
		Connection conn = MySqlDAOFactory.createConnection();
		int val = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS WHERE ID = " + id);
			if (rs.next()) {
				String name = rs.getString("NAME");

				char[] nameChars = new char[name.length()];
				name.getChars(0, name.length(), nameChars, 0);

				while (id > 0) {
					val = (val + id) % 2;
					id /= 10;
				}
				for (char c : nameChars) {
					val = (val + c) % 2;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		MySqlDAOFactory.closeConnection(conn);
		
		return (val==0)?false:true;
	}

	@Override
	public int getCountUnderValue() {
		Connection conn = MySqlDAOFactory.createConnection();
		int val = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS WHERE AVGRADE > " + 8);
			while (rs.next()) {
				int id = rs.getInt("ID");
				
				if(predictGrades(id)){
					val++;
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		MySqlDAOFactory.closeConnection(conn);
		
		return val;
	}

	@Override
	public boolean goodDay(int id) {
		Connection conn = MySqlDAOFactory.createConnection();
		int val = 0;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENTS WHERE ID = " + id);
			if (rs.next()) {
				String name = rs.getString("NAME");
				val += (name.toCharArray()[0] + Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) % 2;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		MySqlDAOFactory.closeConnection(conn);
		
		return (val==0)?false:true;
	}

}
