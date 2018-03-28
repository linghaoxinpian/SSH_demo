package taxservice.role.dao;

import core.dao.BaseDao;
import taxservice.role.entity.Role;

public interface RoleDao extends BaseDao<Role>{

	public void deleteRolePrivilegeByRoleId(String roleId);

}
