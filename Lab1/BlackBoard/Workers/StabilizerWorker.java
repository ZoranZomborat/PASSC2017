import java.util.ArrayList;
import java.util.Arrays;

public class StabilizerWorker extends ChairWorker {

	private IComponent fc=FeetComponent.instance();
	private IComponent sc=StabilizerComponent.instance();
	private IComponent pc=PackageComponent.instance();
		
	public StabilizerWorker(long time) {
		super(StabilizerComponent.instance(), time);
		forbidden = new ArrayList<IComponent>(Arrays.asList(sc, pc));
		required = new ArrayList<IComponent>(Arrays.asList(fc));
	}

}
