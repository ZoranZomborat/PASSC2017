import java.util.ArrayList;

public class TestA implements Runnable{
	public String aPublicString;
	private String aPrivateString;
	public String []args;
	public ArrayList<String> argv;

	public TestA(String aString) {
		Op1(aString);
	}

	private TestA(TestA a){
		this.aPublicString=a.aPublicString;
	} 

	private void Op1(String s) {
		// …
		aPrivateString = s;
	}

	protected String Op2(int x) {
		// …
		return aPrivateString;
	}

	public void Op3() {
		// …
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
