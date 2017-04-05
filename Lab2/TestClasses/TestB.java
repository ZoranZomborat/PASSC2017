public class TestB extends TestA {
	public int aPublicInt = 21;
	private int aPrivateInt = 32;
	private double aPrivateDouble = 20.0f;
	public Integer intc= new Integer(88);
	TestA ta = new TestA("bli bli");
	private String[] strArr=new String[2];
	
	public TestB(){
		
	}
	
	public TestB(String str){
		super(str);
		for(int i =0; i<strArr.length; i++){
			strArr[i]=str;
		}
	}
	
	public TestB(int x) {
		super(null);
	}
	
	private void OpD1(String s) {
	}

	public String OpD2(int x) {
		// …
		return null;
	}

}
