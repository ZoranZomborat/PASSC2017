package Workers;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import AbstractEntities.IComponent;
import AbstractEntities.IEvent;
import AbstractEntities.IProduct;
import AbstractEntities.IWorker;
import Events.ChairEvent;

public abstract class Worker implements IWorker{

	private int _time;
	private IProduct _product;
	private IComponent _component; 		//component to be added
	private IEvent _event;				//event to publish
	private EventBus _eventBus;

	
	private boolean _busy;
	
	protected Worker(int time,EventBus eb)
	{
		_time = time;
		_eventBus = eb;
	}
	
	protected abstract boolean validateProduct(IProduct p);
	
	protected abstract void subcribeToEvents();
	
	@Subscribe
	public void chairEventHandler(ChairEvent e) {
		
		_product = e.getProduct();
		
		if (!_busy && !e.isTaken() && validateProduct(_product)) {
			_busy=true;
			e.takeEvent();
			
			
			try {
				Thread.sleep(_time);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			_product.addComponent(_component);
			_event.attachProduct(_product);
			_eventBus.post(_event);
			_busy=false;
			
		}
		
	}	
	
}
