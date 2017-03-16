package Workers;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import AbstractEntities.IComponent;
import AbstractEntities.IEvent;
import AbstractEntities.IProduct;
import AbstractEntities.EventFilters.IEventFilterP;
import Components.BackrestComponent;
import Components.PackageComponent;
import Components.StabilizerComponent;
import Events.ChairEventDoneP;

public class PackageWorker extends Worker implements Runnable{
	
	private int _time;
	private IProduct _product;
	private IComponent _component; 		//component to be added
	private IEvent _event;				//event to publish
	private EventBus _eventBus;

	private Thread _thread;
	private boolean _busy=false;
	
	public PackageWorker(int time,EventBus eb)
	{
		_time=time;
		_eventBus=eb;
		_component=PackageComponent.instance();
		_eventBus.register(this);
		_thread=new Thread(this);
		workerActive=true;
		_thread.start();
	}
	
	public boolean validateProduct(IProduct p)
	{
		if(p.containsComponent(BackrestComponent.instance()) && p.containsComponent(StabilizerComponent.instance()))
			return true;
		return false;
	}
	
	@Subscribe
	public synchronized void chairEventHandler(IEventFilterP e) {
		
		IProduct p = e.getProduct();
		
		if (!_busy && !e.isTaken() && validateProduct(p)) {
			_product=p;
			e.takeEvent();
			_busy=true;
		}
		
	}

	@Override
	public void run() {
		while(workerActive)
		{
			if(_busy)
			{
				try {
					Thread.sleep(_time);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				_product.addComponent(_component);
				_event=new ChairEventDoneP();
				_event.attachProduct(_product);
				_eventBus.post(_event);
				while(!_event.isTaken()){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					_eventBus.post(_event);
				}
				System.out.println("Chair with P posted!");
				_busy=false;
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}
		
	}	
	
}
