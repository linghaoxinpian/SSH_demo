package taxservice.role.entity;

import java.io.Serializable;

public class RolePrivilegeId implements Serializable{

	private static final long serialVersionUID = 1L;

	private Role role;
	
	private String code;	//这是权限的英文名缩写，eg:纳税服务=nsfw

	public RolePrivilegeId(){}
	
	public RolePrivilegeId(Role role, String code) {
		this.role = role;
		this.code = code;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
