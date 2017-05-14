package torb.naming;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class NamingEntry {
	
	public String name;
	public Class<?> type;
	public String IPaddress;
	public int port;
	
	public void readNamingEntry(DataInputStream input) {
		try {
			name = input.readUTF();
			if(name.equals(""))
				return;
			String className = input.readUTF();
			type = Class.forName(className);
			IPaddress = input.readUTF();
			port = input.readInt();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void sendNamingEntry(DataOutputStream output) {
		try {
			output.writeUTF(name);
			output.writeUTF(type.getName());
			output.writeUTF(IPaddress);
			output.writeInt(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
