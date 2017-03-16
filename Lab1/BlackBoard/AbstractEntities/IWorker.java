
public interface IWorker {
	
	public boolean execCondition(IProduct product);
	public void execAction(IProduct product);
	public void supervise(IBlackBoard bb);
	public boolean isBusy();
	public void stop();

}
