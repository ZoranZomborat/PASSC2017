public interface IArbiter {
	public void subscribe(IWorker worker);
	public void loop();
}
