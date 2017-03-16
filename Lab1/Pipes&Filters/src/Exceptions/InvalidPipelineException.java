package Exceptions;

public class InvalidPipelineException extends Throwable {

	private static final long serialVersionUID = 1L;

	private String _errMessage;
	
	public InvalidPipelineException(String str)
	{
		_errMessage=new String(str);
	}
	
	public void errMessage()
	{
		System.out.println(_errMessage);
	}
	
}
