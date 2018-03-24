package test.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import test.dao.TestDao;
import test.entity.Person;
import test.service.TestService;

@Service("testService")
public class TestServiceImpl implements TestService {

	@Resource
	TestDao testDao;
	
	@Override
	public void say() {
		System.out.print("say hi");
	}

	@Override
	public void save(Person person) {
		testDao.save(person);
		//int a=1/0;	//测试回滚事务
	}

	@Override
	public Person findById(Serializable id) {		
		//testDao.save(new Person("测试3"));	//测试只读事务
		return testDao.findById(id);
	}

}
