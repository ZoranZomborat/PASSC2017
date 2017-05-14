package torb.naming;

import socketutil.ServerSocketWrapper;
import socketutil.SocketWrapper;

public class NamingServer implements Runnable {
	
	private int srvPort = 5100;
	
	private Thread _thread;
	private static NamingServer _instace = null;
	private ServerSocketWrapper _srvSocket = null;
	private SocketWrapper _clientSocket = null;
	
	protected NamingServer() {
		_thread = new Thread(this);
		_thread.start();
	}
	
	public static NamingServer instance() {
		if (_instace == null)
			_instace = new NamingServer();
		return _instace;
	}
	
	@Override
	public void run() {
		_srvSocket = new ServerSocketWrapper(srvPort);
		_srvSocket.openServerSocket();
		
		while (true) {
			_clientSocket = _srvSocket.acceptClientSocket();
			new NamingService(_clientSocket).start();
		}
		
	}

}
