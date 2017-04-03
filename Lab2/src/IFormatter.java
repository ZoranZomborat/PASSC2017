
public interface IFormatter {
	String beginTag();
	String endTag();
	String id(String val);
	String value(String val);
	String arrayValue(String val);
	String delimiter();
	void increaseIdent();
	void decreaseIdent();
}
