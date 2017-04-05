

public class TestA implements Runnable{
	public String aPublicString = new String("public bla bla");
	private String aPrivateString = new String("private bla bla");
//	private String aPrivateString2 = new String("private2 bla bla");
//	protected String aProtectedString2 = new String("protected bla bla");
	public TestC tc = new TestC();
	public int [] array= new int[3];
	
	public TestA(){
		
	}
	
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
		for (int i=0; i<3; i++)
		{
			array[i]=i;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
