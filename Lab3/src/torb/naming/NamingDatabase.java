package torb.naming;

import java.util.ArrayList;

public class NamingDatabase {
	
	private ArrayList<NamingEntry> entries = new ArrayList<>();
	private static NamingDatabase _instace = null;
	
	protected NamingDatabase(){
		
	}
	
	public static NamingDatabase instance(){
		if(_instace == null)
			_instace=new NamingDatabase();
		return _instace;
	}
	
	public synchronized void addEntry(NamingEntry e){
		entries.add(e);
	}
	
	public synchronized NamingEntry getEntry(String name){
		for (NamingEntry namingEntry : entries) {
			if(namingEntry.name.equals(name))
				return namingEntry;
		}
		return null;
	}
	
}
