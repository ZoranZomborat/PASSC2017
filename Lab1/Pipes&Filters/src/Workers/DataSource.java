package Workers;

import AbstractEntities.*;
import Products.ChairInProgress;

public class DataSource implements ISource,Runnable {

	private IPipe _pipe;
	private ChairInProgress _prototype;
	private boolean sourceActive;
	private Thread thread;
	private int count=0;
	private int max=3;
	
	public DataSource()
	{
		thread = new Thread(this);
		sourceActive=true;
		thread.start();
	}
	
	public void doWork(IProduct product) {
		_prototype=(ChairInProgress)(product);
	}

	public void attachPipe(IPipe pipe) {
		_pipe=pipe;
	}
	
	public void stop(boolean recursive)
	{
		if(recursive)
			_pipe.stop(recursive);
		sourceActive=false;
	}

	public void run() {
		
		while(sourceActive)
		{
			if(_prototype!=null)
			{
				_pipe.sendOrder(_prototype.clone());
				count++;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			if(count==max)
				sourceActive=false;
							
		}
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
