package dao.factory;

import dao.HoroscopeDAO;

public abstract class DAOFactory {
	
	public abstract HoroscopeDAO getHoroscopeDAO(); 
	
	public static DAOFactory getDAOFactory(String factory) {
		if(factory.equals("MySql")){
			return new MySqlDAOFactory();
		} else {
			return new DummyDAOFactory();
		}
	}
	
}
