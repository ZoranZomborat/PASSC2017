
public class JsonFormatter implements IFormatter {

	private int identValue=0;
	
	@Override
	public String beginTag() {
		return new String("{");
	}

	@Override
	public String endTag() {
		return new String(identation() + "}");
	}
	
	@Override
	public String beginArray() {
		return new String("[");
	}

	@Override
	public String endArray() {
		return new String(identation() + "]");
	}

	@Override
	public String id(String val) {
		return new String(identation() + "\"" + val + "\": ");
	}

	@Override
	public String value(String val) {
		return new String("\"" + val + "\"");
	}

	@Override
	public String delimiter() {
		return new String(",");
	}

	private String identation() {
		String identation = new String("");
		for (int i = 0; i < identValue; i++) {
			identation += "\t";
		}
		return identation;
	}
	
	@Override
	public void increaseIdent() {
		identValue++;
	}

	@Override
	public void decreaseIdent() {
		identValue--;
	}

	@Override
	public String arrayValue(String val) {
		return new String(identation() + "\"" + val + "\"");
	}

}
