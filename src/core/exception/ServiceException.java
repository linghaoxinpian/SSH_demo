package core.exception;

public class ServiceException extends SysException {

	public ServiceException() {
		super("Service层抛出错误/n");
		
	}

	public ServiceException(String message) {
		super(message);
		
	}

}
