
public class ChairShipper implements IWorker,Runnable {

	private int _threshold;
	private IProduct _chairInUse;
	
	private Thread _thread;
	private boolean shipperActive=false;
	private boolean _busy=false;
	
	public ChairShipper(int threshold) 
	{
		_threshold = threshold;
		_thread=new Thread(this);
		shipperActive=true;
		_thread.start();		
	}
	
	public boolean execCondition(IProduct product) {
		if(product.getCompletionState()==_threshold)
			return true;
		return false;
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
		
	}
	
	public void stop()
	{
		shipperActive=false;
	}

	public void run() {
		while(shipperActive)
		{
			if(_busy)
			{
				System.out.printf("Product with id %d done!\n",_chairInUse.getId());
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
