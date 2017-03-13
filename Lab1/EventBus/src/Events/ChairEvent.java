package Events;
import AbstractEntities.IEvent;
import AbstractEntities.IProduct;

public abstract class ChairEvent implements IEvent {

	private IProduct _product=null;
	private boolean _taken=false;
	
	protected ChairEvent() {
		
	}
	
	public void attachProduct(IProduct p) {
		_product=p;
	}
	
	@Override
	public IProduct getProduct() {
		return _product;
	}

	@Override
	public boolean isTaken() {
		return _taken;
	}

	@Override
	public void takeEvent() {
		_taken=true;
	}

}
