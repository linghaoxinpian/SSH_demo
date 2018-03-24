package taxservice.user.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import core.dao.impl.BaseDaoImpl;

import taxservice.user.dao.UserDao;
import taxservice.user.entity.User;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findUserByIdAndAccount(String id, String account) {
		String hql="from User where account=?";	//hql语句敏感大小写，表名必须与类名相同
		if(StringUtils.isNoneBlank(id)){
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

}
