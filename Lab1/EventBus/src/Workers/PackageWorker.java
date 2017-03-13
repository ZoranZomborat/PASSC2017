package Workers;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import AbstractEntities.IComponent;
import AbstractEntities.IEvent;
import AbstractEntities.IProduct;
import AbstractEntities.IWorker;
import AbstractEntities.EventFilters.IEventFilterP;
import Components.BackrestComponent;
import Components.PackageComponent;
import Components.StabilizerComponent;
import Events.ChairEventDoneP;

public class PackageWorker implements IWorker {
	
	private int _time;
	private IProduct _product;
	private IComponent _component; 		//component to be added
	private IEvent _event;				//event to publish
	private EventBus _eventBus;

	private boolean _busy=false;
	
	public PackageWorker(int time,EventBus eb)
	{
		_time=time;
		_eventBus=eb;
		_component=PackageComponent.instance();
		_event=new ChairEventDoneP();
		_eventBus.register(this);
	}
	
	public boolean validateProduct(IProduct p)
	{
		if(p.containsComponent(BackrestComponent.instance()) && p.containsComponent(StabilizerComponent.instance()))
			return true;
		return false;
	}
	
	@Subscribe
	public void chairEventHandler(IEventFilterP e) {
		
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
			System.out.println("Chair with P posted!");
			_busy=false;
			
		}
		
	}	
	
}
