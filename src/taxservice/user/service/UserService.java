package taxservice.user.service;

import java.io.Serializable;
import java.util.List;

import core.exception.ServiceException;
import taxservice.user.entity.User;

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

}
