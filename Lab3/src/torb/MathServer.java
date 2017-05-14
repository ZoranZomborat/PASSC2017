package torb;

import torb.api.MathApi;

public class MathServer implements MathApi {

	@Override
	public float do_add(float a, float b) {
		return a + b;
	}

	@Override
	public double do_sqr(float a) {
		return Math.sqrt(a);
	}

}
