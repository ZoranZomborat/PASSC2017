package Products;
import java.util.ArrayList;

import AbstractEntities.IComponent;
import AbstractEntities.IProduct;

public class Product implements IProduct{

	private ArrayList<IComponent> _components=null;

	public Product() {
		_components=new ArrayList<IComponent>();
	}
	
	public void addComponent(IComponent component) {
		_components.add(component);
	}

	public boolean containsComponent(IComponent component) {
		if(_components!=null){
			return _components.contains(component);
		}
		return false;
	}
	
}
