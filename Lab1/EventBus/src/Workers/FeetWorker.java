package Workers;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import AbstractEntities.*;
import AbstractEntities.EventFilters.*;
import Components.*;
import Events.*;

public class FeetWorker implements IWorker {
	private int _time;
	private IProduct _product;
	private IComponent _component; 		//component to be added
	private IEvent _event;				//event to publish
	private EventBus _eventBus;

	private boolean _busy=false;
	
	public FeetWorker(int time,EventBus eb)
	{
		_time=time;
		_eventBus=eb;
		_component=FeetComponent.instance();
		_event=new ChairEventDoneF();
		_eventBus.register(this);
	}
	
	public boolean validateProduct(IProduct p)
	{
		if(!p.containsComponent(FeetComponent.instance()))
			return true;
		return false;
	}
	
	@Subscribe
	public void chairEventHandler(IEventFilterF e) {
		
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
			System.out.println("Chair with F posted!");
			_busy=false;
			
		}
		
	}	
}
