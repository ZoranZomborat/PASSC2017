import java.util.ArrayList;
import java.util.Arrays;

public class FeetWorker extends ChairWorker {

	private IComponent cc=SeatComponent.instance();
	private IComponent fc=FeetComponent.instance();
	private IComponent sc=StabilizerComponent.instance();
	private IComponent pc=PackageComponent.instance();
	
	public FeetWorker(long time) {
		super(FeetComponent.instance(), time);
		forbidden = new ArrayList<IComponent>(Arrays.asList(fc, sc, pc));
		required = new ArrayList<IComponent>(Arrays.asList(cc));
	}

}
