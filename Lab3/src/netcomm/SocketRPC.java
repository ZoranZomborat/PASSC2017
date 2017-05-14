package netcomm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.util.ClassUtils;


public class SocketRPC {
	
	public String name;
	public int numberParams;
	public ArrayList<Parameter> params=new ArrayList<Parameter>();
	
	public void readRPC(DataInputStream input) {
		try {
			name = input.readUTF();
			numberParams = input.readInt();
			for (int i = 0; i < numberParams; i++) {
				String className = input.readUTF();
				Parameter parameter = new Parameter();
				parameter.type = ClassUtils.forName(className);
				parameter.pName = input.readUTF();
				
				String value = input.readUTF();
				try {
					if (parameter.type == Integer.class || parameter.type == int.class) {
						parameter.value = new Integer(Integer.parseInt(value));
					} else if (parameter.type == Float.class || parameter.type == float.class) {
						parameter.value = new Float(Float.parseFloat(value));
					} else if (parameter.type == String.class) {
						parameter.value = new String(value);
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
				params.add(parameter);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void sendRPC(DataOutputStream output){
		try {
			output.writeUTF(name);
			output.writeInt(numberParams);
			
			for (Parameter parameter : params) {
				output.writeUTF(parameter.type.getName());
				output.writeUTF(parameter.pName);
				output.writeUTF(parameter.value.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Class<?>[] buildClassArray(){
		Class<?>[]classes =new Class[numberParams];
		for (int i =0;i<numberParams;i++){
			classes[i]=params.get(i).type;
		}
		return classes;
	}
	
	public Object[] buildObjectArray(){
		Object[]objects =new Object[numberParams];
		for (int i =0;i<numberParams;i++){
			objects[i]=params.get(i).value;
		}
		return objects;
	}
}
