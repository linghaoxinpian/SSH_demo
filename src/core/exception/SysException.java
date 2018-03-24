package core.exception;

public class SysException extends Exception{

	private String errMessage;	

	public SysException() {
		super();		
	}
	
	public SysException(String message, Throwable cause) {
		super(message, cause);
		errMessage=message;
		
	}

	public SysException(String message) {
		super(message);
		errMessage=message	;
		
	}

	public SysException(Throwable cause) {
		super(cause);
		
	}
//--------------------属性------------------------	
	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

}
