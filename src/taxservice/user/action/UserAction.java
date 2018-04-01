package taxservice.user.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import taxservice.role.service.RoleService;
import taxservice.user.entity.User;
import taxservice.user.entity.UserRole;
import taxservice.user.service.UserService;

import core.action.BaseAction;
import core.exception.SysException;


public class UserAction extends BaseAction {
	
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	private List<User> userList;
	private User user;
	//头像属性
	private File headImg;
	private String headImgFileName; 
	private String headImgContentType;
	private String[] userRoleIds;

	//列表页面
	public String listUI(){;
		userList = userService.findObjects();
		return "listUI";
	}
	//跳转到新增页面
	public String addUI(){
		ActionContext.getContext().getContextMap().put("roleList", roleService.findObjects());
		return "addUI";
	}
	//保存新增
	public String add() throws IOException{
		if(user != null){
			if(headImg!=null){
				String realPath = ServletActionContext.getServletContext().getRealPath("upload/user");
				String fileName= UUID.randomUUID().toString().replaceAll("-", "")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
				//复制图片
				FileUtils.copyFile(headImg, new File(realPath,fileName));
				
				//设置头像
				user.setHeadImg("user/"+fileName);
			}
			userService.saveUserAndRole(user,userRoleIds);	//user->role 多对多关系
		}

		return "list";
	}
	//跳转到编辑页面
	public String editUI() throws SysException {
		ActionContext.getContext().getContextMap().put("roleList",roleService.findObjects());
			if (user != null && user.getId() != null) {
				user = userService.findObjectById(user.getId());
				//角色权限的回显
				List<UserRole> list= userService.findUserRolesByUserId(user.getId());
				int i=0;
				userRoleIds=new String[list.size()];
				for(UserRole userRole:list){
					userRoleIds[i++]=userRole.getId().getRole().getRoleId();
				}
			}
			return "editUI";
	}
	//保存编辑
	public String edit() throws IOException{
		if(user != null){
			if(headImg!=null){
				String realPath = ServletActionContext.getServletContext().getRealPath("upload/user");
				String fileName= UUID.randomUUID().toString().replaceAll("-", "")+headImgFileName.substring(headImgFileName.lastIndexOf("."));
				//复制图片
				FileUtils.copyFile(headImg, new File(realPath,fileName));
				
				//设置头像
				user.setHeadImg("user/"+fileName);
			}
			userService.updateUserAndRole(user,userRoleIds);
		}
		return "list";
	}
	//删除
	public String delete(){
		if(user != null && user.getId() != null){
			userService.delete(user.getId());
		}
		return "list";
	}
	//批量删除
	public String deleteSelected(){
		if(selectedRow != null){
			for(String id: selectedRow){
				userService.delete(id);
			}
		}
		return "list";
	}
	
	public void verifyAccount(){
		try {
			List<User> list=userService.findUserByIdAndAccount(user.getId(),user.getAccount());
			String str_reult="true";	//验证结果
			if(list!=null && list.size()>0){
				//说明已有帐号存在
				str_reult="false";
			}
			
			//返回给浏览器结果
			//1.设置报文头信息
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html");
			//2.设置输出流
			ServletOutputStream outputStream=response.getOutputStream();
			outputStream.write(str_reult.getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
		
//----------------属性----------------------	
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}	
	public File getHeadImg() {
		return headImg;
	}
	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}
	public String getHeadImgFileName() {
		return headImgFileName;
	}
	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}
	public String getHeadImgContentType() {
		return headImgContentType;
	}
	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}
	public String[] getUserRoleIds() {
		return userRoleIds;
	}
	public void setUserRoleIds(String[] userRoleIds) {
		this.userRoleIds = userRoleIds;
	}	

}
