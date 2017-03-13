
public class Product implements IProduct {
	
	private int _state=0;
	private int _id=0;
	
	public Product(int id, int state)
	{
		_id = id;
		_state = state;
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
	
	
}
