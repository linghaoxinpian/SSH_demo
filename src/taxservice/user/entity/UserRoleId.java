package taxservice.user.entity;

import java.io.Serializable;

import taxservice.role.entity.Role;

public class UserRoleId implements Serializable {
	
	private  Role role;
	
	private String userId;	//之所以用的是userId而不是user对象，是因为考虑到实际操作中不太会用到本类的user属性对象
	
	public UserRoleId() {	
	}

	public UserRoleId(Role role, String userId) {
		this.role = role;
		this.userId = userId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
