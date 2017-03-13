import AbstractEntities.IEvent;
import AbstractEntities.IProduct;

public class ChangeEvent implements IEvent {
	
	private int _i;
	private boolean _taken=false;
	public ChangeEvent(int i) {
		_i=i;
	}

	public int getChange()
	{
		return _i;
		
	}

	@Override
	public IProduct getProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTaken() {
		return _taken;
	}

	@Override
	public void takeEvent() {
		_taken=true;
	}

	@Override
	public void attachProduct(IProduct p) {
		// TODO Auto-generated method stub
		
	}
	
}
