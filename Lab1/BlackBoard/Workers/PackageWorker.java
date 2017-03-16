import java.util.ArrayList;
import java.util.Arrays;

public class PackageWorker extends ChairWorker {

	private IComponent sc=StabilizerComponent.instance();
	private IComponent bc=BackrestComponent.instance();
	private IComponent pc=PackageComponent.instance();
	
	public PackageWorker(long time) {
		super(PackageComponent.instance(), time);
		forbidden = new ArrayList<IComponent>(Arrays.asList(pc));
		required = new ArrayList<IComponent>(Arrays.asList(sc,bc));
	}

}
