public class StabilizerComponent implements IComponent {
	static private StabilizerComponent _instance = null;
	
	private StabilizerComponent()
	{
		
	}
	
	static public IComponent instance() {
		if(null==_instance)
		{
			_instance=new StabilizerComponent();
		}
		return _instance;
	}
	
	public String toString()
	{
		return "Stabilizer";
	}
}
