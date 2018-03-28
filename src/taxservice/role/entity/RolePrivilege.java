package taxservice.role.entity;

import java.io.Serializable;

public class RolePrivilege implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private RolePrivilegeId id;
	
	public RolePrivilege(){}
	
	public RolePrivilege(RolePrivilegeId id) {
		this.id = id;
	}
	
	//---------------属性--------------

	public RolePrivilegeId getId() {
		return id;
	}

	public void setId(RolePrivilegeId id) {
		this.id = id;
	}


	
}
