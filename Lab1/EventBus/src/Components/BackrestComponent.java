package Components;

import AbstractEntities.IComponent;

public class BackrestComponent implements IComponent {

	static private BackrestComponent _instance = null;
	
	private BackrestComponent()
	{
		
	}
	
	static public IComponent instance() {
		if(null==_instance)
		{
			_instance=new BackrestComponent();
		}
		return _instance;
	}

}
