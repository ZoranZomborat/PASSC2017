import java.util.ArrayList;

public class BlackBoardClient {

	public static ArrayList<IWorker> _workers=new ArrayList<IWorker>();
	
	public static void main(String []args)
	{
		IBlackBoard bb=new ChairStorage();
		IArbiter wa=new WorkerArbiter(bb);
		
		_workers.add(new SeatWorker(1000));
		_workers.add(new SeatWorker(1000));
		_workers.add(new SeatWorker(1000));
		_workers.add(new SeatWorker(1000));
		_workers.add(new FeetWorker(1500));
		_workers.add(new FeetWorker(1500));
		_workers.add(new FeetWorker(1500));
		_workers.add(new BackrestWorker(3000));
		_workers.add(new BackrestWorker(3000));
		_workers.add(new StabilizerWorker(1000));
		_workers.add(new StabilizerWorker(1000));
		_workers.add(new PackageWorker(2000));
		_workers.add(new PackageWorker(2000));
		_workers.add(new PackageWorker(2000));
		_workers.add(new PackageWorker(2000));
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
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		wa.stop();
		
		for (IWorker w : _workers) {
			w.stop();
		}
		
		System.exit(0);
			
	}
	
}
