package taxservice.user.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import core.exception.ServiceException;
import taxservice.role.entity.Role;
import taxservice.user.dao.UserDao;
import taxservice.user.entity.User;
import taxservice.user.entity.UserRole;
import taxservice.user.entity.UserRoleId;
import taxservice.user.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource 
	private UserDao userDao;

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(Serializable id) {
		//1.先删除用户对应的各个角色（对应关系存在另一张表中）
		userDao.deleteUserRoleByUserId(id);
		userDao.delete(id);
	}

	@Override
	public User findObjectById(Serializable id) throws ServiceException {
		return userDao.findObjectById(id);
	}

	@Override
	public List<User> findObjects() {
		return userDao.findObjects();
	}

	@Override
	public List<User> findUserByIdAndAccount(String id, String account) {
		
		return userDao.findUserByIdAndAccount(id,account);
	}

	@Override
	public void saveUserAndRole(User user, String... userRoleIds) {
		//1.保存用户
		save(user);
		//2.保存该用户随对应的多个角色
		if(userRoleIds!=null){
			for(String roleId:userRoleIds){
				userDao.saveUserAndRole(new UserRole(new UserRoleId(new Role(roleId),user.getId())));
			}
		}
	}

	@Override
	public List<UserRole> findUserRolesByUserId(String userId) {
		return userDao.findUserRolesByUserId(userId);
	}

	@Override
	public void updateUserAndRole(User user, String... userRoleIds) {
		//1.先删除当前用户原有的所有角色
		userDao.deleteUserRoleByUserId(user.getId());
		//2.更新用户信息
		update(user);
		//3.更新用户所属角色
		for (int i = 0, length = userRoleIds.length; i < length; i++) {
			userDao.saveUserAndRole(new UserRole(new UserRoleId(new Role(userRoleIds[i]),user.getId())));
		}
	}

}








