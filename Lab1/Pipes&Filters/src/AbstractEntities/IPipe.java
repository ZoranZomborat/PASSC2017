package AbstractEntities;

public interface IPipe extends Runnable {
	void attachWorker(IFilter worker);
	void sendOrder(IProduct chair);
	void stop(boolean recursive);
}
