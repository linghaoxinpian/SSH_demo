package taxservice.user.dao;

import java.io.Serializable;
import java.util.List;

import core.dao.BaseDao;
import taxservice.user.entity.User;
import taxservice.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {

	public List<User> findUserByIdAndAccount(String id, String account);

	public void saveUserAndRole(UserRole userRole);

	public List<UserRole> findUserRolesByUserId(String userId);

	public void deleteUserRoleByUserId(Serializable userId);

	public List<User> findUserByAccountAndPassword(String account, String password);

}
