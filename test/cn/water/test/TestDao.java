package cn.water.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.dao.ITextDao;
import cn.water.cf.dao.impl.HibernateUtils;
import cn.water.cf.domain.Text;

public class TestDao {
	
	@Test
	public void testAddObject(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		ITextDao textDao = (ITextDao)context.getBean(ITextDao.SERVICE_NAME);
		
		Text text = new Text();
		text.setTestName("测试dao层");
		text.setTestDate(new Date());
		text.setTestRemark("测试dao层是否搭建成功");
		
		textDao.save(text);
	}

	@Test
	public void testUpdateObject(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		ITextDao textDao = (ITextDao)context.getBean(ITextDao.SERVICE_NAME);
		
		Text text = new Text();
		text.setId("402888e44dc3bd81014dc3bd84f80001");
		text.setTestName("hahhahhhaha");
		
		textDao.update(text);
	}
	
	@Test
	public void testPartialRenewal(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		ITextDao textDao = (ITextDao)context.getBean(ITextDao.SERVICE_NAME);
		
		Text text = new Text();
		Object[] keys = {"testName","testRemark"};
		Object[] params = {"不使用引号","不使用引号"};
		Object[]condition = {"402888e44e000b18014e000be3d70002"};
		
		/*int result = HibernateUtils.partialRenewal(keys, params, condition);*/
		/*textDao.partialRenewal(keys, params, condition);*/
		/*System.out.println("一共有"+result+"条记录被影响");*/
		System.out.println("程序运行成功！");
	}
}
