package cn.water.test.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.dao.IUserDao;

public class TestUser {
	
	@Test
	public void testperfectionProfile(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		IUserDao userDao = (IUserDao)context.getBean(IUserDao.SERVICE_NAME);
		
		List<Object>keys = new ArrayList<Object>();
		List<Object>params = new ArrayList<Object>();
		LinkedHashMap<String,Object> condition = new LinkedHashMap<String, Object>();
		//组织好
			keys.add("realname");
			params.add("dfsdf");
			keys.add("address");
			params.add("dizhi");
			keys.add("birthday");
			params.add("1992-12-22");
			keys.add("profile");
			params.add("person profile");
			keys.add("email");
			params.add("2323");
			keys.add("qq");
			params.add(343434);
			keys.add("hightschool");
			params.add("ADDRES");
			keys.add("university");
			params.add("University");
		
		//组织更新的条件
		condition.put("id", "8abc8af14e2a4d53014e2a4d56d50001");
		userDao.partialRenewal(keys, params, condition);
	}
	
	public void testDate2String(){
		Date date = new Date();
		String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
		System.out.println("date = "+dateStr);
	}

}
