package Workers;

import AbstractEntities.*;


public class Sink implements ISink , Runnable {
	
	private int newProductDone;
	private Thread thread;
	private boolean sinkActive;
	
	public Sink()
	{
		sinkActive=true;
		thread=new Thread(this);
		thread.start();
	}

	public void doWork(IProduct product) {
		newProductDone++;
	}

	public boolean isBusy() {
		return false;
	}
	
	public void stop(boolean recursive)
	{
		sinkActive=false;
	}

	public void run() {
		
		while(sinkActive)
		{
			if(newProductDone>0)
			{
				System.out.println("Product done!");
				newProductDone--;
			}
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			};
		}
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
