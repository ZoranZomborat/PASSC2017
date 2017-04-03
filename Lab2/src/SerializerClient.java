import java.util.ArrayList;

public class SerializerClient {

	public static void main(String []args){
		Serializer serializer=new Serializer();
		TestA ta=new TestA(null);
		TestB tb=new TestB(0);
		ArrayList<Object> objects= new ArrayList<Object>();
		objects.add(ta);
		objects.add(tb);
		serializer.serialize(objects, "output.json");
	}
}
