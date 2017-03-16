import java.util.ArrayList;

public class Product implements IProduct {
	
	private int _state=0;
	private int _id=0;
	private ArrayList<IComponent> _components=null;
	
	public Product(int id, int state)
	{
		_id = id;
		_state = state;
		_components=new ArrayList<IComponent>();
	}

	public int getCompletionState() {
		return _state;
	}

	public int increaseCompletionState(int inc) {
		_state += inc;
		return _state;
	}

	public int getId() {
		return _id;
	}
	
	public void addComponent(IComponent component) {
		_components.add(component);
	}

	public boolean containsComponent(IComponent component) {
		if(_components!=null&&!_components.isEmpty()){
			return _components.contains(component);
		}
		return false;
	}
	
	
}
