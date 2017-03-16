package Workers;
import AbstractEntities.*;
import Products.ChairInProgress;

public class Worker implements IWorker, Runnable {

	private boolean _busy;
	protected long _time;
	private IPipe _pipe;
	private IComponent _component;
	private IProduct _product;
	
	private boolean workerActive;
	private Thread thread;

	public Worker(long time,IComponent c) 
	{
		_time=time;
		_component=c;
		workerActive=true;
		thread =new Thread(this);
		thread.start();
	}
	
	public void setTime(long time)
	{
		_time=time;
	}

	public void attachPipe(IPipe pipe) 
	{
		_pipe=pipe;
	}

	public void doWork(IProduct product)
	{
		_product=product;
		_busy=true;
	}
	
	public boolean isBusy()
	{
		return _busy;
	}
	
	public void stop(boolean recursive)
	{
		if(recursive)
			_pipe.stop(recursive);
		workerActive=false;
	}

	public void run() {
		while(workerActive)
		{
			if(_busy)
			{
				try {
					Thread.sleep(_time);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				_product.addComponent(_component);;
				System.out.println("Worker "+ _time +" done!");
				_pipe.sendOrder(_product);
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
