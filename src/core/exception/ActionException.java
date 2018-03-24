package core.exception;

public class ActionException extends SysException {

	public ActionException() {
		super("Action层抛出错误/n");
		
	}

	public ActionException(String message) {
		super(message);
		
	}
}
