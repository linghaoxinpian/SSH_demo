package test.action;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;

import test.service.TestService;

public class TestAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Resource
	TestService testService;
	
	@Override
	public String execute(){
		testService.say();
		return SUCCESS;
	}
	
}
