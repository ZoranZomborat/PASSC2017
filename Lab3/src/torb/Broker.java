package torb;



public class Broker implements Runnable {
	
	Thread _thread;
	
	public Broker(){
		_thread = new Thread(this);
		_thread.start();
	}

	@Override
	public void run() {
		
	}
	
}
