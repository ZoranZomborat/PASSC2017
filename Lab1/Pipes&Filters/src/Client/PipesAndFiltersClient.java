package Client;

import Workers.*;
import AbstractEntities.*;
import Pipes.*;
import Products.*;

public class PipesAndFiltersClient {

	void setupPipesAndFiltersSystem()
	{

	}
	
	public static void main(String[] args) {
		
		ISource dataSource = new DataSource();
		ISink sink = new Sink();
		
		IWorker w1=new Worker(1000);
		IWorker w2=new Worker(1500);
		IWorker w3=new Worker(4000);
		IWorker w4=new Worker(2000);
		IWorker w5=new Worker(1000);
		
		IPipe p1=new ChairPipe(w1);
		IPipe p2=new ChairPipe(w2);
		IPipe p3=new ChairPipe(w3);
		IPipe p4=new ChairPipe(w4);
		IPipe p5=new ChairPipe(w5);
		IPipe p6=new ChairPipe(sink);
		
		dataSource.attachPipe(p1);
		
		w1.attachPipe(p2);
		w2.attachPipe(p3);
		w3.attachPipe(p4);
		w4.attachPipe(p5);
		w5.attachPipe(p6);
		
		ChairInProgress chair = new ChairInProgress();
		dataSource.doWork(chair);
		
	}

}
