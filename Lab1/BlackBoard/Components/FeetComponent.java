public class FeetComponent implements IComponent {

	static private FeetComponent _instance = null;
	
	private FeetComponent()
	{
		
	}
	
	static public IComponent instance() {
		if(null==_instance)
		{
			_instance=new FeetComponent();
		}
		return _instance;
	}
	
	public String toString()
	{
		return "Feet";
	}
}
