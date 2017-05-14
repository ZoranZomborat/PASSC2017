package socketutil;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SocketWrapper {

	public Socket socket;
	public DataInputStream in;
	public DataOutputStream out;

	private String _ip;
	private int _port;

	public SocketWrapper(String ip, int port){
		_ip = ip;
		_port = port;
		try {
			socket = new Socket(_ip, _port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public SocketWrapper(Socket s){
		socket=s;
	}

	public void openSocket(){
		try {
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void closeSocket(){
		try {
            try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        finally {
            try {
                in.close();
            } catch (IOException e) {
				e.printStackTrace();
			}
            finally {
                try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
        }
	}

}
