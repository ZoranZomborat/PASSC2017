import java.util.ArrayList;

public class BlackBoardClient {

	public static ArrayList<IWorker> _workers=new ArrayList<IWorker>();
	
	public static void main(String []args)
	{
		IBlackBoard bb=new ChairStorage();
		IArbiter wa=new WorkerArbiter(bb);
		
		_workers.add(new ChairWorker(0,1000));
		_workers.add(new ChairWorker(0,1000));
		_workers.add(new ChairWorker(0,1000));
		_workers.add(new ChairWorker(0,1000));
		_workers.add(new ChairWorker(1,1500));
		_workers.add(new ChairWorker(1,1500));
		_workers.add(new ChairWorker(1,1500));
		_workers.add(new ChairWorker(2,3000));
		_workers.add(new ChairWorker(2,3000));
		_workers.add(new ChairWorker(3,1000));
		//_workers.add(new ChairWorker(3,2000));
		_workers.add(new ChairWorker(4,2000));
		_workers.add(new ChairWorker(4,2000));
		_workers.add(new ChairWorker(4,2000));
		_workers.add(new ChairWorker(4,2000));
		_workers.add(new ChairShipper(5));
		
		for (IWorker w : _workers) {
			wa.subscribe(w);
			w.supervise(bb);
		}
		
		for(int i=0;i<4;i++)
		{
			bb.addProduct(new Product(i, 0));
		}
		
		wa.loop();
		
	}
	
}
