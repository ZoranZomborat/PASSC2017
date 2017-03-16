package Products;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;

import org.javatuples.Pair;

import AbstractEntities.IComponent;
import Components.*;

public class PipelineValidator extends ChairInProgress {

	private boolean valid = true;

	private ArrayList<IComponent> expectedList;
	private ArrayList<Pair<IComponent, ArrayList<IComponent>>> rules;
	private IComponent cc = SeatComponent.instance();
	private IComponent fc = FeetComponent.instance();
	private IComponent bc = BackrestComponent.instance();
	private IComponent sc = StabilizerComponent.instance();
	private IComponent pc = PackageComponent.instance();

	public PipelineValidator() {
		super();
		rules = new ArrayList<Pair<IComponent, ArrayList<IComponent>>>();
		rules.add(new Pair<IComponent, ArrayList<IComponent>>(cc, new ArrayList<IComponent>()));
		rules.add(new Pair<IComponent, ArrayList<IComponent>>(fc, new ArrayList<IComponent>(Arrays.asList(cc))));
		rules.add(new Pair<IComponent, ArrayList<IComponent>>(bc, new ArrayList<IComponent>(Arrays.asList(cc))));
		rules.add(new Pair<IComponent, ArrayList<IComponent>>(sc, new ArrayList<IComponent>(Arrays.asList(fc))));
		rules.add(new Pair<IComponent, ArrayList<IComponent>>(pc, new ArrayList<IComponent>(Arrays.asList(sc, bc))));
		expectedList=new ArrayList<>(Arrays.asList(cc,fc,bc,sc,pc));
	}

	public boolean isValid()
	{
		return valid;
	}
	
	public boolean finalResult()
	{
		if(!valid)
			return false;
		if (!(_components.size()==expectedList.size()))
			return false;
		for (IComponent c : expectedList) {
			if (!_components.contains(c))
				return false;
		}
		return true;
	}
	
	public void addComponent(IComponent component) {
		
		if(valid){
		
			for (Pair<IComponent, ArrayList<IComponent>> pair : rules) {
				if(component.equals(pair.getValue(0)))
				{
					if(_components.contains(component)){
						valid = false;
						return;
					}
					ArrayList<IComponent>list=pair.getValue1();
					for (IComponent c : list) {
						if(!_components.contains(c))
						{
							valid=false;
							return;
						}
					}
				}
			}
		}
		_components.add(component);
		return;
	}

}
