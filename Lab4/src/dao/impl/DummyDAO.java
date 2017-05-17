package dao.impl;

import dao.HoroscopeDAO;

public class DummyDAO implements HoroscopeDAO {

	@Override
	public int getCountUnderValue() {
		return 0;
	}

	@Override
	public boolean predictGrades(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean goodDay(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
