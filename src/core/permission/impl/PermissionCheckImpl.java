package core.permission.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import core.permission.PermissionCheck;
import taxservice.role.entity.Role;
import taxservice.role.entity.RolePrivilege;
import taxservice.user.entity.User;
import taxservice.user.entity.UserRole;
import taxservice.user.service.UserService;

public class PermissionCheckImpl implements PermissionCheck {
	
	@Resource
	private UserService userService;

	@Override
	public boolean isAccessible(User user, String code) {		
		List<UserRole> list = user.getUserRoles();
		if(list == null || list.size()<=0){
			//从数据库再查一次
			list=userService.findUserRolesByUserId(user.getId());
			//判断是否依然为空,是则直接返回false
			if(list == null || list.size()<=0){
				return false;
			}
		}
		for (UserRole userRole:list) {
			Set<RolePrivilege> rolePrivileges = userRole.getId().getRole().getRolePrivileges();
			for (RolePrivilege privilege : rolePrivileges) {
				if(code.equals(privilege.getId().getCode())){
					return true;
				}
			}			
			
//			Role role = userRole.getId().getRole();
//			for(RolePrivilege rp: role.getRolePrivileges()){
//				//对比是否有code对应的权限
//				if(code.equals(rp.getId().getCode())){
//					//说明有权限，返回true
//					return true;
//				}
//			}
		}
		return false;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
