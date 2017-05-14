import torb.InfoClient;
import torb.MathClient;
import torb.naming.NamingServer;
import torb.proxy.InfoServerProxy;
import torb.proxy.MathServerProxy;

public class Main {

	public static void main(String[] args) {
		NamingServer nmServer=NamingServer.instance();
		new InfoServerProxy();
		new MathServerProxy();
		new InfoClient();
		new MathClient();
		new MathClient();
		new InfoClient();
		new InfoClient();
	}

}
