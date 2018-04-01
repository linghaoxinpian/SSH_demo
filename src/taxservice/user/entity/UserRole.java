package taxservice.user.entity;

public class UserRole {

	private UserRoleId id;

	public UserRoleId getId() {
		return id;
	}

	public void setId(UserRoleId id) {
		this.id = id;
	}

	public UserRole(UserRoleId id) {
		this.id = id;
	}

	public UserRole() {		
	}
	
	
}
