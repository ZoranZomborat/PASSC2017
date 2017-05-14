package socketutil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerSocketWrapper {

	public ServerSocket srvSocket;
	public DataInputStream in;
	public DataOutputStream out;
	
	private int _port;
	
	public ServerSocketWrapper(int port) {
		_port = port;
	}
	
	public void openServerSocket(){
		try {
			srvSocket = new ServerSocket(_port); 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public SocketWrapper acceptClientSocket(){
		
		Socket socket = null;
		try {
			socket = srvSocket.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SocketWrapper socketWrapper= new SocketWrapper(socket);
		socketWrapper.openSocket();
		
		return socketWrapper;
	}

}
