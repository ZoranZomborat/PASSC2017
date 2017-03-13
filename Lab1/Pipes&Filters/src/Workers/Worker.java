package Workers;
import AbstractEntities.*;
import Products.ChairInProgress;

public class Worker implements IWorker, Runnable {

	private boolean _busy;
	protected long _time;
	private IPipe _pipe;
	
	private ChairInProgress _chair;
	
	private boolean workerActive;
	private Thread thread;

	public Worker(long time) 
	{
		_time=time;
		workerActive=true;
		thread =new Thread(this);
		thread.start();
	}
	
	public Worker(IPipe pipe) 
	{
		_pipe=pipe;
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
		_chair=(ChairInProgress)(product);
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
				_chair.completionState++;
				System.out.println("Worker "+ _time +" done!");
				_pipe.sendOrder(_chair);
				_busy=false;
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
