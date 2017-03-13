import java.util.ArrayList;

public class WorkerArbiter implements IArbiter,Runnable{
	
	private ArrayList<IWorker> _workers=new ArrayList<IWorker>();
	private IBlackBoard _chairStorage;
	
	private Thread _thread;
	private boolean arbiterActive=false;
	
	public WorkerArbiter(IBlackBoard storage)
	{
		_chairStorage=storage;
		_thread=new Thread(this);

	}
	
	public void subscribe(IWorker worker)
	{
		_workers.add(worker);
	}
	
	public void loop()
	{
		arbiterActive=true;
		_thread.start();
	}

	public void run() {
		while(arbiterActive)
		{
			IProduct product;
			product = _chairStorage.nextProduct();
			if (product != null) {
				for (IWorker w : _workers) {
					if (w.execCondition(product)) {
						if(!w.isBusy())
						{
							if (_chairStorage.removeProduct(product))
								w.execAction(product);
						}
					}
				}
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
