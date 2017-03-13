package AbstractEntities;

public interface IEvent {
	public IProduct getProduct();
	public boolean isTaken();
	public void takeEvent();
	public void attachProduct(IProduct p);
}
