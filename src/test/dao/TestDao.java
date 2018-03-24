package test.dao;

import java.io.Serializable;

import test.entity.Person;

public interface TestDao {

	// 保存
	public void save(Person person);

	// 查询
	public Person findById(Serializable id);
}
