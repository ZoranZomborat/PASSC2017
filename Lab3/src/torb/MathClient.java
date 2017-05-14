package torb;

import torb.proxy.MathClientProxy;

public class MathClient implements Runnable{
	
	Thread _thread;
	MathClientProxy clientProxy;
	
	public MathClient() {
		clientProxy=new MathClientProxy();
		_thread=new Thread(this);
		_thread.start();
	}
		
	@Override
	public void run() {
		float result = clientProxy.do_add(1.0f, 2.0f);
		System.out.println("do_add(1.0f, 2.0f) : " + result);
		result = clientProxy.do_add(8.0f, 9.0f);
		System.out.println("do_add(8.0f, 9.0f) : " + result);
		double sqrt = clientProxy.do_sqr(4.0f);
		System.out.println("do_sqr(4.0f) : " + sqrt);
		sqrt = clientProxy.do_sqr(2.0f);
		System.out.println("do_sqr(2.0f) : " + sqrt);
	}
	
}
