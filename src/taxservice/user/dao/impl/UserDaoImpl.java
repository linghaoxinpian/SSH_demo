package taxservice.user.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import core.dao.impl.BaseDaoImpl;

import taxservice.user.dao.UserDao;
import taxservice.user.entity.User;
import taxservice.user.entity.UserRole;
import taxservice.user.entity.UserRoleId;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findUserByIdAndAccount(String id, String account) {
		String hql="from User where account=?";	//hql语句敏感大小写，表名必须与类名相同
		if(StringUtils.isNotBlank(id)){
			hql+=" And id !=?";
		}
		Query query = getSession().createQuery(hql);
		//设置查询参数
		if(StringUtils.isNotBlank(id)){		
			query.setParameter(1, id);
		}
		query.setParameter(0, account);
		//发送数据库
		return query.list();
	}

	@Override
	public void saveUserAndRole(UserRole userRole) {
		getHibernateTemplate().save(userRole);
	}

	@Override
	public List<UserRole> findUserRolesByUserId(String userId) {
//		UserRoleId userRoleId= getHibernateTemplate().get(UserRoleId.class, userId);
//		return (List<UserRole>) getHibernateTemplate().get(UserRole.class, userRoleId);	//这样不行，因为UserRoleId没有映射进hibernate
		
		String hql="FROM UserRole WHERE id.userId=?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, userId);
		return query.list();
	}

	@Override
	public void deleteUserRoleByUserId(Serializable userId) {
		Query query = getSession().createQuery("DELETE FROM UserRole WHERE id.userId=?");
		query.setParameter(0, userId);
		query.executeUpdate();
	}

}
