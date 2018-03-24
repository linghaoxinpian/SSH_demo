package test.service;

import java.io.Serializable;

import test.entity.Person;

public interface TestService {

	//输出
	public void say();
	
	//保存
	public void save(Person person);
	
	//查询
	public Person findById(Serializable id);
}
