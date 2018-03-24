package test;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.entity.Person;
import test.service.TestService;

public class TestMerge {

	private ClassPathXmlApplicationContext ctx;
	
	@Before
	public void loadCtx(){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	@Test
	public void testSpring(){
		ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		TestService testService=(TestService) ctx.getBean("testService");
		testService.say();
	}
	
	@Test
	public void testHibernate(){
		SessionFactory sf=(SessionFactory)ctx.getBean("sessionFactory");
		Session session = sf.openSession();
		Transaction transaction = session.beginTransaction();
		//保存用户
		session.save(new Person("测试用户1"));
		transaction.commit();
		session.close();		
	}
	
	@Test
	public void testServieAndDao(){		
		TestService testService=(TestService) ctx.getBean("testService");
	//	testService.save(new Person("测试用户2"));
		System.out.println(testService.findById("40289fd0623cc34901623cc34ac00000").getName());
	}
	
	/**
	 * 测试：只读事务
	 */
	@Test
	public void testTransactionReadOnly(){		
		TestService testService=(TestService) ctx.getBean("testService");
		System.out.println(testService.findById("40289fd0623cc34901623cc34ac00000").getName());
	}
	
	/**
	 * 测试：回滚事务
	 */
	@Test
	public void testTransactionRollBack(){		
		TestService testService=(TestService) ctx.getBean("testService");
		testService.save(new Person("测试用户4"));
	}
}
