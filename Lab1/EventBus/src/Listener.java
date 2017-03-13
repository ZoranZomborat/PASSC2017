import com.google.common.eventbus.Subscribe;

public class Listener {
	private int _i;
	private boolean _busy=false;
	
	public Listener(int i) {
		_i=i;
	}

	@Subscribe
	public void recordCustomerChange(ChangeEvent e) {
		
		if (!_busy && !e.isTaken()) {
			_busy=true;
			e.takeEvent();
			int change = e.getChange();
			System.out.println(_i + " - " + change);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			_busy=false;
		}
	}

}
