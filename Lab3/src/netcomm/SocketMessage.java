package netcomm;

import java.io.IOException;
import java.io.InputStream;

public class SocketMessage {
	
	private byte[] buffer = new byte[1024];
	
	public String readMessage(InputStream input) {
		int len = 0;
		String message = null;
		try {
			len = input.read();
			input.read(buffer, 0, len);
			message = new String(buffer, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
	
}
