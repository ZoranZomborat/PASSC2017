package torb.proxy;
import java.io.IOException;

import netcomm.Parameter;
import netcomm.SocketRPC;
import socketutil.SocketWrapper;
import torb.api.InfoApi;
import torb.naming.NamingEntry;

public class InfoClientProxy implements InfoApi{

	private NamingEntry nme = new NamingEntry();
	private SocketWrapper namingServer;
	private SocketWrapper infoServer;
	
	public boolean getObjectRef(String name){
		namingServer = new SocketWrapper("127.0.0.1", 5100);
		namingServer.openSocket();
		
		try {
			namingServer.out.writeUTF("getObjectRef");
			namingServer.out.writeUTF(name);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		nme.readNamingEntry(namingServer.in);
		if(nme.name.equals("")){
			namingServer.closeSocket();
			return false;
		}
		
		//System.out.println("Got obj ref with port " + nme.port);
		namingServer.closeSocket();
		return true;
	}
	
	public void tryGetObjectRef(){
		boolean sc = getObjectRef("InfoServer1");
		while(sc==false)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sc = getObjectRef("InfoServer1");
		}
	}
		
	@Override
	public String get_road_info(int road_ID) {

		tryGetObjectRef();
		infoServer = new SocketWrapper(nme.IPaddress,nme.port);
		infoServer.openSocket();
		SocketRPC srpc= new SocketRPC();
		srpc.name="get_road_info";
		srpc.numberParams=1;
		srpc.params.add(new Parameter(int.class, "road_ID", road_ID));
		srpc.sendRPC(infoServer.out);
		
		String string = null;
		try {
			string = infoServer.in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		infoServer.closeSocket();
		return string;
	}

	@Override
	public float get_temp(String city) {
		tryGetObjectRef();
		infoServer = new SocketWrapper(nme.IPaddress,nme.port);
		infoServer.openSocket();
		SocketRPC srpc= new SocketRPC();
		srpc.name="get_temp";
		srpc.numberParams=1;
		srpc.params.add(new Parameter(String.class, "city", city));
		srpc.sendRPC(infoServer.out);
		
		String string = null;
		try {
			string = infoServer.in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Float result = Float.parseFloat(string);
		infoServer.closeSocket();
		return result;
	}

}
