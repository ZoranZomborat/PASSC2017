package Workers;
import com.google.common.eventbus.Subscribe;

import AbstractEntities.IProduct;
import AbstractEntities.IWorker;
import Events.EventEndWork;

public abstract class Worker implements IWorker{

	protected boolean workerActive;
	
	public abstract boolean validateProduct(IProduct p);
	
	@Subscribe
	public synchronized void chairEventHandler(EventEndWork e) {
		workerActive=false;
	}
		
}
