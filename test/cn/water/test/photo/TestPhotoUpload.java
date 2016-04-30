package cn.water.test.photo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.dao.IUserDao;
import cn.water.cf.domain.User;

public class TestPhotoUpload {

	@Test
	public void testAddHeadImage2User(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		/*IUserService service = (IUserService)context.getBean(IUserService.SERVICE_NAME);*/
		IUserDao userDao = (IUserDao)context.getBean(IUserDao.SERVICE_NAME);
		User user = new User();
		user.setId("8abc8af14e2e9d85014e2e9e1c5e0001");
		String headImage = "gagagagaga/user/zhangmioajie/haha.jpg";
		List<Object> keys = new ArrayList<Object>();
		List<Object> params = new ArrayList<Object>();
		keys.add("headImage");
		params.add(headImage);
		LinkedHashMap<String,Object> condition = new LinkedHashMap<String, Object>();
		condition.put("id", user.getId());
		userDao.partialRenewal(keys, params, condition);
		
		/*service.addHeadImageUrl(headImage, user.getId());*/
		
		
		
	
	}
}
