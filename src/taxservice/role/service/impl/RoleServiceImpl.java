package taxservice.role.service.impl;

import java.io.Serializable;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import taxservice.role.service.RoleService;
import taxservice.role.dao.RoleDao;
import taxservice.role.entity.Role;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	
	@Resource 
	private RoleDao roleDao;

	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public void update(Role role) {
		//更新角色权限前先删除该角色的所有权限（此方法最简单，解决了新权限与以前的权限比较问题）
		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		roleDao.update(role);
	}

	@Override
	public void delete(Serializable id) {
		roleDao.delete(id);
	}

	@Override
	public Role findObjectById(Serializable id) {
		return roleDao.findObjectById(id);
	}

	@Override
	public List<Role> findObjects() {
		return roleDao.findObjects();
	}
}
