
public class JsonParser implements IParser {
	
	public String substringWithin(String source, String leftStr, int leftIdx, String rightStr, int rightIdx){
		String out = null;
		try {
			out = source.substring(source.indexOf(leftStr, leftIdx), source.indexOf(rightStr, rightIdx));
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return out;
	}

	@Override
	public String substringDelimited(String source, String strDelimitator, int idx) {
		String out = null;
		try {
			int idx1 = source.indexOf(strDelimitator, idx) + 1;
			int idx2 = source.indexOf(strDelimitator, idx1);
			out = source.substring(idx1, idx2);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return out;
	}

	@Override
	public int skipOverDelimited(String source, String strDelimitator, int idx) {
		int idx1 = idx, idx2 = idx;
		try {
			idx1 = source.indexOf(strDelimitator, idx) + 1;
			idx2 = source.indexOf(strDelimitator, idx1);
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
		return idx2 + 1;
	}

	@Override
	public String idFormat(String id) {
		return new String("\"" + id + "\": ");
	}
	
}
