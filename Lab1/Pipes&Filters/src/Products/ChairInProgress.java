package Products;

import java.util.ArrayList;

import AbstractEntities.IComponent;
import AbstractEntities.IProduct;

public class ChairInProgress implements IProduct {

	protected ArrayList<IComponent> _components=null;
	
	public ChairInProgress() {
		_components=new ArrayList<IComponent>();
	}
	
	public void addComponent(IComponent c)
	{
		_components.add(c);
	}
	
	public ChairInProgress clone()
	{
		ChairInProgress copy = new ChairInProgress();
		return copy;
	}

}


