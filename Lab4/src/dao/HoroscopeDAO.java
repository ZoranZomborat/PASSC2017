package dao;

public interface HoroscopeDAO {
	
	boolean predictGrades(int id);
	
	int getCountUnderValue();
	
	boolean goodDay(int id);
	
}
