public class PackageComponent implements IComponent {

	static private PackageComponent _instance = null;
	
	private PackageComponent()
	{
		
	}
	
	static public IComponent instance() {
		if(null==_instance)
		{
			_instance=new PackageComponent();
		}
		return _instance;
	}
	
	public String toString()
	{
		return "Package";
	}
	
}
