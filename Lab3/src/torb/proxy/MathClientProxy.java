package torb.proxy;

import java.io.IOException;

import netcomm.Parameter;
import netcomm.SocketRPC;
import socketutil.SocketWrapper;
import torb.api.MathApi;
import torb.naming.NamingEntry;

public class MathClientProxy implements MathApi{

	private NamingEntry nme = new NamingEntry();
	private SocketWrapper namingServer;
	private SocketWrapper mathServer;
	
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
		boolean sc = getObjectRef("MathServer1");
		while(sc==false)
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sc = getObjectRef("MathServer1");
		}
	}
	
	@Override
	public float do_add(float a, float b) {
		tryGetObjectRef();
		mathServer = new SocketWrapper(nme.IPaddress,nme.port);
		mathServer.openSocket();
		SocketRPC srpc= new SocketRPC();
		srpc.name="do_add";
		srpc.numberParams=2;
		srpc.params.add(new Parameter(float.class, "a", a));
		srpc.params.add(new Parameter(float.class, "b", b));
		srpc.sendRPC(mathServer.out);
		
		String string = null;
		try {
			string = mathServer.in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		float ft = Float.parseFloat(string);
		mathServer.closeSocket();
		return ft;
	}

	@Override
	public double do_sqr(float a) {
		tryGetObjectRef();
		mathServer = new SocketWrapper(nme.IPaddress,nme.port);
		mathServer.openSocket();
		SocketRPC srpc= new SocketRPC();
		srpc.name="do_sqr";
		srpc.numberParams=1;
		srpc.params.add(new Parameter(float.class, "a", a));
		srpc.sendRPC(mathServer.out);
		
		String string = null;
		try {
			string = mathServer.in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Double ft = Double.parseDouble(string);
		mathServer.closeSocket();
		return ft;
	}

}
