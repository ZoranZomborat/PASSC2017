import com.google.common.eventbus.EventBus;

import AbstractEntities.IEvent;
import AbstractEntities.IWorker;
import Events.ChairEventDone0;
import Workers.BackrestWorker;
import Workers.FeetWorker;
import Workers.PackageWorker;
import Workers.SeatWorker;
import Workers.StabilizerWorker;

public class EventBusClient {

	public static void main(String[] args) {
		EventBus eb =new EventBus();
		IWorker w2=new FeetWorker(100, eb);
		IWorker w3=new PackageWorker(100, eb);
		IWorker w4=new SeatWorker(100, eb);
		IWorker w5=new StabilizerWorker(100, eb);
		IWorker w6=new StabilizerWorker(100, eb);
		IWorker w1=new BackrestWorker(100, eb);
		IEvent event=new ChairEventDone0();
		event.attachProduct(new Product());
		eb.post(event);
		System.out.println("hammer time");
		IEvent e=new ChairEventDone0();
		e.attachProduct(new Product());
		eb.post(e);
	}

}
