
public interface IProduct {
	public int getCompletionState();
	public int increaseCompletionState(int inc);
	public int getId();
	public void addComponent(IComponent component);
	public boolean containsComponent(IComponent component);
}
