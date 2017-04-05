import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SerializerClient {

	static String filename = new String("output.json");
	static String filename2 = new String("output2.json");
	
	private static void compareOutputs(){
		FileReader fr = null;
		String serialized = null;
		String deserialized = null;
		File fileser = new File(filename);
		File filedes = new File(filename2);
		try {
			char[] chars;
			fr = new FileReader(fileser);
			chars = new char[(int) fileser.length()];
			fr.read(chars);
			serialized = new String(chars);
			fr.close();
			
			fr = new FileReader(filedes);
			chars = new char[(int) filedes.length()];
			fr.read(chars);
			deserialized = new String(chars);
			fr.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(serialized.equals(deserialized)){
			System.out.println("Test passed!");
		}
		else{
			System.out.println("Test failed!");
		}
	}
	
	public static void main(String []args){
		Serializer serializer = new Serializer();
		Deserializer deserializer = new Deserializer();
		TestA ta=new TestA("ble ble");
		ta.Op3();
		TestB tb=new TestB("blo blo");
		ArrayList<Object> objects = new ArrayList<Object>();
		ArrayList<Object> deserializedObjs = null;

		objects.add(ta);
		objects.add(tb);
		serializer.serialize(objects, filename);
		deserializedObjs = deserializer.deserialize(filename);
		serializer.serialize(deserializedObjs, filename2);
		
		compareOutputs();
	}
}
