package taxservice.role.entity;

import java.util.Set;

public class Role {
	private String roleId;
	private String name;
	private String state;
	private Set<RolePrivilege> rolePrivileges;
	
	//角色的启用状态
	public static String ROLE_STATE_VALID="1";
	public static String ROLE_STATE_INVALID="0";
	
	public Role(){
		
	}
	
	public Role(String roleId) {
		this.roleId = roleId;
	}
	
	
//----------------属性------------------	
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Set<RolePrivilege> getRolePrivileges() {
		return rolePrivileges;
	}
	public void setRolePrivileges(Set<RolePrivilege> rolePrivileges) {
		this.rolePrivileges = rolePrivileges;
	}	
	
}
