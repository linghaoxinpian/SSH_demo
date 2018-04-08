package core.permission;

import taxservice.user.entity.User;

public interface PermissionCheck {
	
	public boolean isAccessible(User user,String code);
}
