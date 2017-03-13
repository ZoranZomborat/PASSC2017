package Pipes;

import java.util.ArrayList;

import AbstractEntities.IPipe;
import AbstractEntities.ISink;
import AbstractEntities.IProduct;
import AbstractEntities.IWorker;
import AbstractEntities.IFilter;
import Products.ChairInProgress;

public class ChairPipe implements IPipe ,Runnable{

	private ArrayList<ChairInProgress> _chairsBuffer = new ArrayList<ChairInProgress>();
	private ISink _nextWorker;
	private Thread thread;
	private boolean pipeActive;
	
	public ChairPipe() {
		pipeActive = true;
		thread =new Thread(this);
		thread.start();
	}
	
	public ChairPipe(IFilter worker) {
		_nextWorker=(ISink)worker;
		pipeActive = true;
		thread =new Thread(this);
		thread.start();
	}
	
	public void attachWorker(IFilter worker)
	{
		_nextWorker=(IWorker)worker;
	}

	public void sendOrder(IProduct product) {
		
		ChairInProgress chair= (ChairInProgress)(product);
		_chairsBuffer.add(chair);	
	}

	public void stop(boolean recursive)
	{
		if(recursive)
			_nextWorker.stop(recursive);
		pipeActive=false;
	}
	
	@Override
	public void run() {
		
		if(_nextWorker==null)
		{
			System.out.println("Error pipe broken!");
			return;
		}
		
		while(pipeActive)
		{
			if ((!_nextWorker.isBusy()) && (_chairsBuffer.size() != 0)) {
				_nextWorker.doWork(_chairsBuffer.get(0));
				_chairsBuffer.remove(0);
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
