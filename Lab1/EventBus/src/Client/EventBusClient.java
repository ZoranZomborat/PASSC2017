package Client;
import com.google.common.eventbus.EventBus;
import AbstractEntities.IEvent;
import AbstractEntities.IProduct;
import AbstractEntities.IWorker;
import Events.ChairEventDone0;
import Events.EventEndWork;
import Products.Product;
import Workers.BackrestWorker;
import Workers.FeetWorker;
import Workers.PackageWorker;
import Workers.SeatWorker;
import Workers.Shipper;
import Workers.StabilizerWorker;

public class EventBusClient {

	
	public static void main(String[] args) {
		int count =3;
		EventBus eb =new EventBus();
		IWorker w8=new SeatWorker(1000, eb);
		IWorker w9=new SeatWorker(1000, eb);
		IWorker w2=new FeetWorker(1500, eb);
		IWorker w10=new FeetWorker(1500, eb);
		IWorker w5=new StabilizerWorker(4000, eb);
		IWorker w6=new StabilizerWorker(4000, eb);
		IWorker w7=new StabilizerWorker(4000, eb);
		IWorker w121=new StabilizerWorker(4000, eb);
		IWorker w1=new BackrestWorker(2000, eb);
		IWorker w12=new BackrestWorker(2000, eb);
		IWorker w3=new PackageWorker(1000, eb);
		IWorker w11=new PackageWorker(1000, eb);
		IWorker shipper=new Shipper(eb);
		
		for (int i = 0; i < count; i++) {
			IProduct p = new Product();
			IEvent e = new ChairEventDone0();
			e.attachProduct(p);
			do {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				eb.post(e);
			} while (!e.isTaken());
		}
		
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		eb.post(new EventEndWork());
		System.exit(0);
	}

}
