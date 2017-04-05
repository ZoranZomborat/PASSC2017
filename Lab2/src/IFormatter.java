
public interface IFormatter {
	String beginTag();
	String endTag();
	String beginArray();
	String endArray();
	String id(String val);
	String value(String val);
	String arrayValue(String val);
	String delimiter();
	void increaseIdent();
	void decreaseIdent();
}
