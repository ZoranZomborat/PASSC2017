package torb.naming;

import java.io.IOException;
import java.net.Socket;

import netcomm.SocketMessage;
import socketutil.SocketWrapper;

public class NamingService extends Thread {
	
	private SocketWrapper socket;
	private NamingDatabase namingDb;
	
	public NamingService(SocketWrapper s) {
		socket = s;
		namingDb = NamingDatabase.instance();
	}

	public void run() {
		String message = null;
		try {
			message = socket.in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (message.equals("register")) {
			NamingEntry nme = new NamingEntry();
			nme.readNamingEntry(socket.in);
			namingDb.addEntry(nme);
		} else if (message.equals("getObjectRef")) {
			String name = null;
			try {
				name = socket.in.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			NamingEntry nme = namingDb.getEntry(name);
			if (nme != null) {
				nme.sendNamingEntry(socket.out);
			} else {
				try {
					socket.out.writeUTF("");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			
		}
		
		socket.closeSocket();
	}
	
}
