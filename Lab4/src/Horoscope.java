import dao.HoroscopeDAO;
import dao.factory.DAOFactory;

public class Horoscope {
	
	private DAOFactory daoFactory = DAOFactory.getDAOFactory("MySql");
	private HoroscopeDAO horoscopeDAO;
	
	void testGoodDay(int id){
		if(horoscopeDAO.goodDay(id))
			System.out.println("[" + id + "] " + " has a good day");
		else
			System.out.println("[" + id + "] " + " has a bad day");
	}
	
	void testUnderValue(){
		System.out.println( horoscopeDAO.getCountUnderValue() + " student get lower grades ");
	}
	
	void testPredictGrades(int id){
		if(horoscopeDAO.predictGrades(id))
			System.out.println("[" + id + "] " + " gets higher grades");
		else
			System.out.println("[" + id + "] " + " gets lower grades");
	}
	
	public void doJob(){
		horoscopeDAO = daoFactory.getHoroscopeDAO();
		
		testPredictGrades(1);
		testPredictGrades(11);
		
		testUnderValue();
		
		testGoodDay(1);
		testGoodDay(3);
	}
	
}
