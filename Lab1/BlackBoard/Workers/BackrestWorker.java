import java.util.ArrayList;
import java.util.Arrays;

public class BackrestWorker extends ChairWorker {

	private IComponent cc=SeatComponent.instance();
	private IComponent pc=PackageComponent.instance();
	private IComponent bc=BackrestComponent.instance();
	
	public BackrestWorker(long time) {
		super(BackrestComponent.instance(), time);
		forbidden = new ArrayList<IComponent>(Arrays.asList(bc, pc));
		required = new ArrayList<IComponent>(Arrays.asList(cc));
	}

}
