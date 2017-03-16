package AbstractEntities;

public interface IPipe extends Runnable {
	void attachWorker(ISink worker);
	void sendOrder(IProduct chair);
	void stop(boolean recursive);
}
