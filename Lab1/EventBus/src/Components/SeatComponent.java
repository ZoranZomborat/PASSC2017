package Components;

import AbstractEntities.IComponent;

public class SeatComponent implements IComponent {
	static private SeatComponent _instance = null;
	
	private SeatComponent()
	{
		
	}
	
	static public IComponent instance() {
		if(null==_instance)
		{
			_instance=new SeatComponent();
		}
		return _instance;
	}
}
