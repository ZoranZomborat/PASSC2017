package torb.proxy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import netcomm.SocketRPC;
import socketutil.ServerSocketWrapper;
import socketutil.SocketWrapper;
import torb.InfoServer;
import torb.naming.NamingEntry;

public class InfoServerProxy implements Runnable {
	
	InfoServer infoServer;
	private Thread _thread;
	private SocketWrapper namingServer;
	private ServerSocketWrapper srvSocket;
	private SocketWrapper clientSocket;
	
	private String ServerID = "InfoServer1";
	private String ServerIP = "127.0.0.1";
	private int ServerPort = 5101;

	public InfoServerProxy() {
		infoServer = new InfoServer();
		_thread=new Thread(this);
		_thread.start();
	}
	
	private void register() {
		namingServer = new SocketWrapper("127.0.0.1", 5100);
		namingServer.openSocket();
		
		NamingEntry namingEntry= new NamingEntry();
		namingEntry.name = ServerID;
		namingEntry.type = InfoServer.class;
		namingEntry.IPaddress = ServerIP;
		namingEntry.port = ServerPort;
		
		try {
			namingServer.out.writeUTF("register");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		namingEntry.sendNamingEntry(namingServer.out);
		
		namingServer.closeSocket();
	}
	
	void treatClient(SocketWrapper clientSocket) {
		SocketRPC srpc = new SocketRPC();
		srpc.readRPC(clientSocket.in);
		Class<?> c = infoServer.getClass();
		Method method = null;
		Object result = null;
		
		try {
			method = c.getMethod(srpc.name, srpc.buildClassArray());
			result = method.invoke(infoServer, srpc.buildObjectArray());
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			e.printStackTrace();
		}
		
		try {
			clientSocket.out.writeUTF(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		register();
		srvSocket = new ServerSocketWrapper(ServerPort);
		srvSocket.openServerSocket();
		
		while (true) {
			clientSocket = srvSocket.acceptClientSocket();
			treatClient(clientSocket);
			clientSocket.closeSocket();
		}
	}
	
}
