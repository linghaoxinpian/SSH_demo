package taxservice.user.dao;

import java.util.List;

import core.dao.BaseDao;
import taxservice.user.entity.User;

public interface UserDao extends BaseDao<User> {

	public List<User> findUserByIdAndAccount(String id, String account);

}
