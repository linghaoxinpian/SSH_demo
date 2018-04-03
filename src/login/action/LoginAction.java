package login.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import core.action.BaseAction;
import core.constant.Constant;
import taxservice.user.entity.User;
import taxservice.user.service.UserService;

public class LoginAction extends BaseAction {
	
	private User user;
	private String loginResult;
	@Resource
	private UserService userService;
	
	public String toLoginUI(){
		return "loginUI";
	}
	
	public String login(){
		if(user!=null){
			if(StringUtils.isNotBlank(user.getAccount()) && StringUtils.isNotBlank(user.getPassword())){
				List<User> list= userService.findUserByAccountAndPassword(user.getAccount(),user.getPassword());
				if(list!=null && list.size()>0){
					User user=list.get(0);
					ServletActionContext.getRequest().getSession().setAttribute(Constant.SYS_USER, user);	//将登陆用户的信息存储到Session中
					
					//跳转到首页
					return "home";
				}
			}else{
				loginResult = "账号和密码不能为空";
			}
		}else{
			loginResult="请输入帐号和密码";
		}
		return toLoginUI();
	}

	public String loginOut(){
		//清除session
		ServletActionContext.getRequest().getSession().removeAttribute(Constant.SYS_USER);
		return toLoginUI();
	}
//------------------------属性---------------------------	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
}
