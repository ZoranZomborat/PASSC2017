package dao.factory;

import dao.HoroscopeDAO;

public class DummyDAOFactory extends DAOFactory {

	@Override
	public HoroscopeDAO getHoroscopeDAO() {
		return null;
	}

}
