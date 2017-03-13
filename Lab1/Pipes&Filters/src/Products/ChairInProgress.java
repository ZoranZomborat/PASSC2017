package Products;

import AbstractEntities.IProduct;

public class ChairInProgress implements IProduct {

	public int completionState=0;

	public ChairInProgress() {
	}
	
	public int GetState() {
		return 0;
	}
	
	public ChairInProgress clone()
	{
		ChairInProgress copy = new ChairInProgress();
		copy.completionState=this.completionState;
		return copy;
	}

}


