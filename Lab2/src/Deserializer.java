import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class Deserializer {

	private ArrayList<Object> objects = new ArrayList<Object>();
	private String stream = null;
	private IParser jsonPs = new JsonParser();
	private int idx = 0;
	
	private boolean isPrimitiveWrapper(Class<?> type){
		if (type == Double.class || type == Float.class || type == Long.class || type == Integer.class
				|| type == Short.class || type == Character.class || type == Byte.class || type == Boolean.class)
			return true;
		if (type == String.class)
			return true;
		return false;
	}
	
	private void setProperty(Object object, String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        Class<?> fieldType = field.getType();
        field.setAccessible(true);
        if (fieldType == Character.class || fieldType == char.class) {field.set(object, value.charAt(0)); return;}
        if (fieldType == Short.class || fieldType == short.class) {field.set(object, Short.parseShort(value)); return;}
        if (fieldType == Integer.class || fieldType == int.class) {field.set(object, Integer.parseInt(value)); return;}
        if (fieldType == Long.class || fieldType == long.class) {field.set(object, Long.parseLong(value)); return;}
        if (fieldType == Float.class || fieldType == float.class) {field.set(object, Float.parseFloat(value)); return;}
        if (fieldType == Double.class || fieldType == double.class) {field.set(object, Double.parseDouble(value)); return;}
        if (fieldType == Byte.class || fieldType == byte.class) {field.set(object, Byte.parseByte(value)); return;}
        if (fieldType == Boolean.class || fieldType == boolean.class) {field.set(object, Boolean.parseBoolean(value)); return;}
		if (fieldType == String.class) {field.set(object, value); return;}
        field.set(object, value);
    }
	
	private void setArray(Object object, Class<?> typeClass, Field field) throws IllegalArgumentException, IllegalAccessException{
		Object arrObject = null;
		field.setAccessible(true);
		arrObject=field.get(object);
		int length=Array.getLength(arrObject);
		Class<?> arrType=typeClass.getComponentType();
		String value = null;
		for (int i = 0; i < length; i++) {
			value=jsonPs.substringDelimited(stream, "\"", idx);
			idx=jsonPs.skipOverDelimited(stream, "\"", idx);
			field.setAccessible(true);
	        if (arrType == Character.class || arrType == char.class) {Array.set(arrObject, i, value.charAt(0)); continue;}
	        if (arrType == Short.class || arrType == short.class) {Array.set(arrObject, i, Short.parseShort(value)); continue;}
	        if (arrType == Integer.class || arrType == int.class) {Array.set(arrObject, i, Integer.parseInt(value));  continue;}
	        if (arrType == Long.class || arrType == long.class) {Array.set(arrObject, i, Long.parseLong(value)); continue;}
	        if (arrType == Float.class || arrType == float.class) {Array.set(arrObject, i, Float.parseFloat(value)); continue;}
	        if (arrType == Double.class || arrType == double.class) {Array.set(arrObject, i, Double.parseDouble(value)); continue;}
	        if (arrType == Byte.class || arrType == byte.class) {Array.set(arrObject, i, Byte.parseByte(value)); continue;}
	        if (arrType == Boolean.class || arrType == boolean.class) {Array.set(arrObject, i, Boolean.parseBoolean(value)); continue;}
			if (arrType == String.class) {Array.set(arrObject, i, value); continue;}
		}
	}
	
	private String readFromFile(String filename){
		FileReader fr = null;
		String content = null;
		File file = new File(filename);
		try {
			fr = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			fr.read(chars);
			content = new String(chars);
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	private void setFields(Object object,Class<?> c){
		
		String fieldsTag=new String("\"fields\":");
		int fieldsIdx = (stream.indexOf(fieldsTag, idx)) + fieldsTag.length();
		String fieldName=null;
		String fieldValue=null;
		
		Field[] fields = c.getDeclaredFields();
		for (int i=0; i < fields.length; i++) {
			fieldName = fields[i].getName();
			idx=stream.indexOf(jsonPs.idFormat(fieldName), fieldsIdx);
			idx+=jsonPs.idFormat(fieldName).length();
			
			Class<?> typeClass=fields[i].getType();
			if (typeClass.isArray()) {
				try {
					setArray(object,typeClass,fields[i]);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				continue;
			}
			
			if (!(typeClass.isPrimitive()) && !(isPrimitiveWrapper(typeClass))) {
				Object fieldObj=null;
				try {
					fieldObj=fields[i].get(object);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				setFields(fieldObj, typeClass);
				continue;
			}
			


			fieldValue=jsonPs.substringDelimited(stream, "\"", idx);
			try {
				setProperty(object, fieldName, fieldValue);
			} catch (NoSuchFieldException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}	
		}		
	}
	
	private Object getNextObject(){
		String className=jsonPs.substringDelimited(stream, "\"", idx);
		Class<?> c = null;
		Object object= null;
		int i, depth = 1;
		int classIdx = stream.indexOf('{',idx) + 1;
		try {
			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			object=c.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		setFields(object, c);
		
		//get to end of current object
		for (i = classIdx; i < stream.length() && depth > 0; i++) {
			if(stream.charAt(i)=='{')
				depth++;
			else if (stream.charAt(i)=='}')
				depth--;
		}
		idx=i;
		
		return object;
	}
	
	public ArrayList<Object> deserialize(String filename){
		stream=readFromFile(filename);
		String objtectsTag=new String("\"objects\":");
		if((idx=stream.indexOf(objtectsTag, 0))<0){
			idx+=objtectsTag.length();
			System.out.println("no objects in file or missing objects tag");
			return null;
		}
		idx=stream.indexOf('[',idx);
		

		objects.add(getNextObject());
		while(stream.charAt(idx)==','){
			objects.add(getNextObject());
		}
		
		return objects;
	} 
	
}
