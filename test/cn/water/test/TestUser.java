package cn.water.test;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.domain.User;
import cn.water.cf.service.IUserService;

public class TestUser {
	
	@Test
	public void testAddUser(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		IUserService service = (IUserService)context.getBean(IUserService.SERVICE_NAME);
		
		User user = new User();
		user.setUserName("zhangmiaojie");
		user.setPassword("dfd");
		
		service.saveUser(user);
		
	}

}
