import java.util.ArrayList;


public class ChairStorage implements IBlackBoard {

	private ArrayList<IProduct> _products = new ArrayList<IProduct>();
	private int _currentIndex;
	
	public ChairStorage()
	{
		
	}
	
	public void addProduct(IProduct product)
	{
		_products.add(product);
	}

	public synchronized boolean removeProduct(IProduct product) {
		return _products.remove(product);
	}

	public synchronized IProduct nextProduct() {
		
		IProduct lastProduct=null;
		
		if(_products!=null && !_products.isEmpty())
		try{
			lastProduct=_products.get(0);
		} catch(IndexOutOfBoundsException e)
		{
			System.out.printf("Invalid index %d !\n",_currentIndex);
		}
		
		return lastProduct;
	}
	
}
