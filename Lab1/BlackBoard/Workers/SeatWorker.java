import java.util.ArrayList;
import java.util.Arrays;

public class SeatWorker extends ChairWorker {

	private IComponent cc=SeatComponent.instance();
	private IComponent fc=FeetComponent.instance();
	private IComponent sc=StabilizerComponent.instance();
	private IComponent bc=BackrestComponent.instance();
	private IComponent pc=PackageComponent.instance();
		
	public SeatWorker(long time) {
		super(SeatComponent.instance(), time);
		forbidden = new ArrayList<IComponent>(Arrays.asList(cc, fc, sc, bc, pc));
		required = new ArrayList<IComponent>();
	}

}
