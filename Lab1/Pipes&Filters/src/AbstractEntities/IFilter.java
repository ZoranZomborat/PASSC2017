package AbstractEntities;

public interface IFilter extends Runnable {
	void doWork(IProduct product);
	void stop(boolean recursive);
}
