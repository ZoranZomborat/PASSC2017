package Workers;

import java.util.ArrayList;

import AbstractEntities.*;


public class Sink implements ISink , Runnable {
	
	private int productCount = 0;
	private Thread thread;
	private boolean sinkActive;
	private boolean _busy;
	
	private ArrayList<IProduct> _products=new ArrayList<IProduct>() ;

	
	public Sink()
	{
		sinkActive=true;
		thread=new Thread(this);
		thread.start();
	}

	public synchronized void doWork(IProduct product) {
		_products.add(product);
		_busy=true;
	}

	public synchronized IProduct getProduct()
	{
		if(_products!=null&&!_products.isEmpty()){
			productCount--;
			return _products.remove(0);
		}
		return null;
	}
	
	public boolean isBusy() {
		return _busy;
	}
	
	public void stop(boolean recursive)
	{
		sinkActive=false;
	}

	public void run() {
		
		while(sinkActive)
		{
			if (_busy) {
				if (_products.size() > productCount) {
					productCount++;
					System.out.println("Product done!");
				}
				_busy = false;
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			};
		}
		
	}

}
