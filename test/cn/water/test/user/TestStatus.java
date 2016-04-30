package cn.water.test.user;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.dao.IStatusDao;
import cn.water.cf.domain.User;
import cn.water.cf.domain.status.Status;
import cn.water.cf.service.IStatusService;
import cn.water.cf.service.IUserService;

public class TestStatus {
	
	@Test
	public void testAddStatus()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		IStatusService statusService = (IStatusService)context.getBean(IStatusService.SERVICE_NAME);
		Status status = new Status();
		User user = new User();
		user.setId("ff80808152ab563a0152ab59c2e30001");
		status.setUser(user);
		status.setText("jdlkfjsljfd");
		status.setAttitudes_count(0);
		status.setContents_count(0);
		status.setReposts_count(0);
		status.setThumbnail_pic("kjhkjd.jpg");
		status.setCreated_at(new Date());
		statusService.saveStatus(status);
	}
	
	@Test
	public void addUser()
	{

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		IUserService statusService = (IUserService)context.getBean(IUserService.SERVICE_NAME);
		
		User user = new User();
		user.setUserName("zhangsan");
		
		statusService.saveUser(user);
	}

}
