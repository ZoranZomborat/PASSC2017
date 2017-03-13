
public class ChairWorker implements IWorker, Runnable {

	private int _threshold;
	private long _time;
	private IBlackBoard _chaiStorage;
	
	private IProduct _chairInUse;
	
	private Thread _thread;
	private boolean workerActive=false;
	private boolean _busy=false;
	
	public ChairWorker(int threshold,long time)
	{
		_threshold = threshold;
		_time = time;
		_thread=new Thread(this);
		workerActive=true;
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
		_chaiStorage=bb;	
	}

	public void run() {
		while(workerActive)
		{
			if(_busy)
			{				
				_chairInUse.increaseCompletionState(1);
				System.out.printf("Product id %d state %d!\n", _chairInUse.getId(), _chairInUse.getCompletionState());
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
