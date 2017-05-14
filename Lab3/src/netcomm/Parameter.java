package netcomm;

public class Parameter {
	public Class<?> type;
	public String pName;
	public Object value;

	public Parameter(){
		
	}
	
	public Parameter(Class<?> t, String n, Object v) {
		type = t;
		pName = n;
		value = v;
	}
}
