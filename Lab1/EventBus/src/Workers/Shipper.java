package Workers;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import AbstractEntities.IProduct;
import AbstractEntities.EventFilters.IEventFilterShip;

public class Shipper extends Worker implements Runnable {
	
	private IProduct _product;
	private EventBus _eventBus;
	
	private Thread _thread;
	private boolean _busy=false;
	
	public Shipper(EventBus eb) {
		_eventBus=eb;
		_eventBus.register(this);
		_thread=new Thread(this);
		workerActive=true;
		_thread.start();
	}

	
	@Override
	public boolean validateProduct(IProduct p) {
		return true;
	}
	
	@Subscribe
	public synchronized void chairEventHandler(IEventFilterShip e) {
		
		IProduct p = e.getProduct();
		
		if (!_busy && !e.isTaken() && validateProduct(p)) {
			_product=p;
			e.takeEvent();
			_busy=true;
		}
		
	}
	
	@Override
	public void run() 
	{
		while (workerActive) 
		{
			if (_busy) 
			{
				System.out.println("Chair done and shipped!");
				_busy=false;
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
