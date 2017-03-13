import com.google.common.eventbus.EventBus;


public class Producer {

	EventBus eventBus=new EventBus();
	
	public void changeCustomer() {
		  ChangeEvent event = new ChangeEvent(10);
		  Listener l2=new Listener(2);
		  Listener l1=new Listener(1);
		  Listener l3=new Listener(3);
		  eventBus.register(l1);
		  eventBus.register(l2);
		  eventBus.register(l3);
		  eventBus.post(event);
		  eventBus.post(new ChangeEvent(9));
		  eventBus.post(new ChangeEvent(11));
		  System.out.println("done posting");
	}
}
