package torb;
import torb.proxy.InfoClientProxy;

public class InfoClient implements Runnable {

	Thread _thread;
	InfoClientProxy clientProxy;
	
	public InfoClient() {
		clientProxy=new InfoClientProxy();
		_thread=new Thread(this);
		_thread.start();
	}
		
	@Override
	public void run() {
		String info = clientProxy.get_road_info(0);
		System.out.println("get_road_info(0) : " + info);
		info = clientProxy.get_road_info(1);
		System.out.println("get_road_info(1) : " + info);
		Float ft = clientProxy.get_temp("Timi");
		System.out.println("get_temp(\"Timi\") : " + ft);
		ft = clientProxy.get_temp("Dub");
		System.out.println("get_temp(\"Dub\") : " + ft);
	}
	
}
