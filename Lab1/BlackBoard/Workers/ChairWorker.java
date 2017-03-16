import java.util.ArrayList;

public abstract class ChairWorker implements IWorker, Runnable {

	private IComponent _component;
	private long _time;
	private IBlackBoard _chaiStorage;
	
	private IProduct _chairInUse;
	
	private Thread _thread;
	private boolean workerActive=false;
	private boolean _busy=false;
	
	protected ArrayList<IComponent> forbidden;
	protected ArrayList<IComponent> required;
	
	public ChairWorker(IComponent component,long time)
	{
		_component = component;
		_time = time;
		_thread=new Thread(this);
		workerActive=true;
		_thread.start();
	}
	
	public boolean execCondition(IProduct product) {
		for(IComponent c : forbidden)
		{
			if(product.containsComponent(c))
				return false;
		}
		for(IComponent c : required)
		{
			if(!product.containsComponent(c))
				return false;
		}
		return true;
	}

	public void execAction(IProduct product) {
		_chairInUse=product;
		_busy=true;
	}
	
	public synchronized boolean isBusy()
	{
		return _busy;
	}

	public void supervise(IBlackBoard bb) {
		_chaiStorage=bb;	
	}
	
	public void stop()
	{
		workerActive=false;
	}

	public void run() {
		while(workerActive)
		{
			if(_busy)
			{				
				_chairInUse.increaseCompletionState(1);
				_chairInUse.addComponent(_component);
				System.out.printf("Product id %d state %s!\n", _chairInUse.getId(),_component);
				try {
					Thread.sleep(_time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				_chaiStorage.addProduct(_chairInUse);
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
