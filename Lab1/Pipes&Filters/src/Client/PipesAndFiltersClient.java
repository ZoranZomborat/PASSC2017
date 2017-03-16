package Client;

import Workers.*;

import java.util.ArrayList;

import AbstractEntities.*;
import Components.BackrestComponent;
import Components.FeetComponent;
import Components.PackageComponent;
import Components.SeatComponent;
import Components.StabilizerComponent;
import Exceptions.InvalidPipelineException;
import Pipes.*;
import Products.*;

public class PipesAndFiltersClient {

	private static ArrayList<IFilter>_filters=new ArrayList<IFilter>();
	
	public static void setupPipesAndFiltersSystem(PipelineValidator validator) throws InvalidPipelineException
	{
		int filtersSize=_filters.size();
				
		for (int i = 0; i < filtersSize - 1; i++) {
			IFilter filter=_filters.get(i);
			IFilter filternext=_filters.get(i+1);
			if(!(filternext instanceof ISink))
			{
				InvalidPipelineException exception = new InvalidPipelineException("Second till last filters must be of type ISink!");
				throw exception;
			}
			IPipe pipe =new ChairPipe((ISink) filternext); 
			if(!(filter instanceof ISource))
			{
				InvalidPipelineException exception = new InvalidPipelineException("First till second to last filters must be of type ISource!");
				throw exception;
			}
			((ISource)filter).attachPipe(pipe);
		}
		
		IWorker w1 = (IWorker) _filters.get(1);
		Sink sink = (Sink) _filters.get(filtersSize - 1);
		w1.doWork(validator);
		while (((Sink) sink).getProduct() == null) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (!validator.finalResult())
		{
			InvalidPipelineException exception = new InvalidPipelineException("Incorect sequencing of workers!");
			throw exception;
		}
		else
			System.out.println("Valid pipeline!");
		
	}
	
	public static void buildPipe()
	{
		
	}
	
	public static void main(String[] args) {
		
		ISource dataSource = new DataSource(3);
		
		IWorker w1=new Worker(1000,SeatComponent.instance());
		IWorker w2=new Worker(4000,FeetComponent.instance());
		IWorker w3=new Worker(1500,BackrestComponent.instance());
		IWorker w4=new Worker(2000,StabilizerComponent.instance());
		IWorker w5=new Worker(1000,PackageComponent.instance());
		
		ISink sink = new Sink();
		
		_filters.add(dataSource);
		_filters.add(w1);
		_filters.add(w2);
		_filters.add(w3);
		_filters.add(w4);
		_filters.add(w5);
		_filters.add(sink);
		PipelineValidator validator=new PipelineValidator();
		try {
			setupPipesAndFiltersSystem(validator);
		} catch (InvalidPipelineException e) {
			e.errMessage();
			System.out.println("Reconfigure pipeline!");
			e.printStackTrace();
		}

		ChairInProgress chair = new ChairInProgress();
		dataSource.doWork(chair);
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		dataSource.stop(true);
		System.out.print("Work stopped!");
		System.exit(0);
	}

}
