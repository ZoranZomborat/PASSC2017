
public interface IParser {
	String substringWithin(String source, String leftStr, int leftIdx, String rightStr, int rightIdx);
	String substringDelimited(String source, String strDelimitator, int idx);
	int skipOverDelimited(String source, String strDelimitator, int idx);
	String idFormat(String id);
}
