import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.ArrayList;



public class Serializer {
	
	private IFormatter jsonf = new JsonFormatter();
	
	public Serializer(){
		
	}
	
	private String stream=new String("");
	
	public String serConstructors(String stream, Class<?> objClass){
		
		String className=objClass.getName();
		stream+=jsonf.id("constructors");
		stream+=jsonf.beginTag() + "\n";
		jsonf.increaseIdent();
		Constructor<?>[] constructors = objClass.getDeclaredConstructors();
		for (int i=0; i<constructors.length; i++) {
			stream +=jsonf.id(className);
			stream+=jsonf.beginTag() + "\n";
			jsonf.increaseIdent();
			Class<?>[] params = constructors[i].getParameterTypes();
			for (int k = 0; k < params.length; ++k) {
				String paramType = params[k].getName();
				stream +=jsonf.arrayValue(paramType) + ((k==params.length-1)?"":jsonf.delimiter()) + "\n";
			}
			jsonf.decreaseIdent();
			stream+=jsonf.endTag() + ((i==constructors.length-1)?"":jsonf.delimiter()) + "\n";
		} 
		jsonf.decreaseIdent();
		stream+=jsonf.endTag() + "\n";
		return stream;
	}
	
	private String serFields(String stream, Class<?> c) {
		
		Field[] fields = c.getDeclaredFields();
		stream+=jsonf.id("fields");
		stream+=jsonf.beginTag() + "\n";
		jsonf.increaseIdent();
		for (int i=0; i<fields.length; i++) {
			String fieldName = fields[i].getName();
			Class<?> typeClass = fields[i].getType();
			stream +=jsonf.id(fieldName);
			stream +=jsonf.value(typeClass.getName());
			stream +=((i==fields.length-1)?"":jsonf.delimiter())+"\n";
		}
		jsonf.decreaseIdent();
		stream+=jsonf.endTag() + "\n";

		return stream;
	}
	
	private String serMethods(String stream, Class<?> c){
		Method[] methods = c.getDeclaredMethods();
		stream+=jsonf.id("methods");
		stream+=jsonf.beginTag() + "\n";
		jsonf.increaseIdent();
		for (int i=0; i<methods.length; i++) {
			String mname = methods[i].getName();
			stream += jsonf.id(mname) + jsonf.beginTag() + "\n";
			jsonf.increaseIdent();
			
			Class<?> retType = methods[i].getReturnType();
			stream += jsonf.id("returns") + jsonf.value(retType.getName()) + ",\n";
			stream += jsonf.id("parameters") + jsonf.beginTag() + "\n";
			jsonf.increaseIdent();
			Class<?>[] params = methods[i].getParameterTypes();
			for (int k = 0; k < params.length; ++k) {
				String paramType = params[k].getName();
				stream += jsonf.arrayValue(paramType) + ((k == (params.length - 1))?"":jsonf.delimiter()) + "\n";
			}
			jsonf.decreaseIdent();
			stream+=jsonf.endTag() + "\n";
			jsonf.decreaseIdent();
			stream += jsonf.endTag() + ((i == (methods.length - 1))?"\n":",\n");
		}
		jsonf.decreaseIdent();
		stream+=jsonf.endTag() + "\n";
		
		return stream;
	}
	
	public void serialize(ArrayList<Object> objects ,String file){
		
		stream+=jsonf.beginTag() + "\n";
		jsonf.increaseIdent();
		for (Object object : objects) {
			
			Class<?> c = object.getClass();
			String className=c.getName();
			
			stream+=jsonf.id(className);
			stream+=jsonf.beginTag() + "\n";
			
			jsonf.increaseIdent();
			stream=serConstructors(stream, c);
			stream=serFields(stream, c);
			stream=serMethods(stream, c);
			jsonf.decreaseIdent();
			if(object.equals(objects.get(objects.size() - 1)))
				stream+=jsonf.endTag() + "\n";
			else
				stream+=jsonf.endTag() + ",\n";
		}
		jsonf.decreaseIdent();
		stream+=jsonf.endTag();
		writeToFile(stream,file);
	}


	private void writeToFile(String stream, String file) {
		FileWriter fw;
		try {
			fw = new FileWriter(new File(file));
			fw.write(stream);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public ArrayList<Object> deserialize(String file){
		return null;
	}
	
	
	
}
