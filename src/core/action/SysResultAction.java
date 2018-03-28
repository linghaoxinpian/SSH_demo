package core.action;

import org.apache.struts2.dispatcher.StrutsResultSupport;
import com.opensymphony.xwork2.ActionInvocation;

public class SysResultAction extends StrutsResultSupport {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doExecute(String arg0, ActionInvocation invocation) throws Exception {

/*		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		BaseAction action = (BaseAction) invocation.getAction();		*/

		//do something
		System.out.println("这里是SysResultAction的输出");
	}

}