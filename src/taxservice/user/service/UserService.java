package taxservice.user.service;

import java.io.Serializable;
import java.util.List;

import core.exception.ServiceException;
import taxservice.user.entity.User;
import taxservice.user.entity.UserRole;

public interface UserService {

	//新增
	public void save(User user);
	//更新
	public void update(User user);
	//根据id删除O
	public void delete(Serializable id);
	//根据id查找
	public User findObjectById(Serializable id) throws ServiceException;
	//查找列表
	public List<User> findObjects();
	
	public List<User> findUserByIdAndAccount(String id, String account);
	
	public void saveUserAndRole(User user, String... userRoleIds);
	
	public List<UserRole> findUserRolesByUserId(String userId);
	
	public void updateUserAndRole(User user, String... userRoleIds);
	/**
	 * 为什么返回的是List？思考...
	 * @param account
	 * @param password
	 * @return
	 */
	public List<User> findUserByAccountAndPassword(String account, String password);

}
