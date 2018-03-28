package taxservice.role.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;

import taxservice.role.entity.Role;
import taxservice.role.entity.RolePrivilege;
import taxservice.role.entity.RolePrivilegeId;
import taxservice.role.service.RoleService;
import core.action.BaseAction;
import core.constant.Constant;

public class RoleAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private RoleService roleService;
	private List<Role> roleList;
	private Role role;
	private String[] privilegeIds;

	//列表页面
	public String listUI(){
		ActionContext.getContext().put("privilegeMap", Constant.PRIVILEGE_MAP);
		roleList = roleService.findObjects();
		return "listUI";
	}
	//跳转到新增页面
	public String addUI(){
		ActionContext.getContext().put("privilegeMap", Constant.PRIVILEGE_MAP);
		return "addUI";
	}
	//保存新增
	public String add(){
		try {
			if(role != null){
				//角色的权限
				if(privilegeIds!=null){
					HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
					for (int i = 0, length = privilegeIds.length; i < length; i++) {				
						set.add(new RolePrivilege(new RolePrivilegeId(role,privilegeIds[i])));
					}
					role.setRolePrivileges(set);
				}
				roleService.save(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "list";
	}
	//跳转到编辑页面
	public String editUI() {
		ActionContext.getContext().put("privilegeMap", Constant.PRIVILEGE_MAP);
		if (role != null && role.getRoleId() != null) {
			role = roleService.findObjectById(role.getRoleId());
			//权限回显
			if(role.getRolePrivileges()!=null){
				privilegeIds=new String[role.getRolePrivileges().size()];
				int i=0;
				for(RolePrivilege privilege:role.getRolePrivileges()){
					privilegeIds[i++]=privilege.getId().getCode();
				}
			}
		}
		return "editUI";	
	}
	//保存编辑
	public String edit() throws IOException{
		if(role != null){
			//角色的权限
			if(privilegeIds!=null){
				HashSet<RolePrivilege> set = new HashSet<RolePrivilege>();
				for (int i = 0, length = privilegeIds.length; i < length; i++) {				
					set.add(new RolePrivilege(new RolePrivilegeId(role,privilegeIds[i])));
				}
				role.setRolePrivileges(set);
			}
			roleService.update(role);
		}
		return "list";
	}
	//删除
	public String delete(){
		if(role != null && role.getRoleId() != null){
			roleService.delete(role.getRoleId());
		}
		return "list";
	}
	//批量删除
	public String deleteSelected(){
		if(selectedRow != null){
			for(String id: selectedRow){
				roleService.delete(id);
			}
		}
		return "list";
	}
	
	
		
//----------------属性----------------------	
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String[] getPrivilegeIds() {
		return privilegeIds;
	}
	public void setPrivilegeIds(String[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}	
	
}
