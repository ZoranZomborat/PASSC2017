package torb;
import torb.api.InfoApi;

public class InfoServer implements InfoApi{

	public InfoServer() {

	}
	
	@Override
	public String get_road_info(int road_ID) {
		if (road_ID ==0)
			return new String("Shinny");
		return new String("Not so shinny");
	}

	@Override
	public float get_temp(String city) {
		if(city.equals("Timi"))
			return 28.5f;
		return 34.69f;
	}
}
