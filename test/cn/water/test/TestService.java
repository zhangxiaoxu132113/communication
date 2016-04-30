package cn.water.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.domain.Text;
import cn.water.cf.service.ITextService;

public class TestService {
	
	@Test
	public void testAddObject(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		ITextService service = (ITextService)context.getBean(ITextService.SERVICE_NAME);
		
		Text text = new Text();
		text.setTestName("测试service层");
		text.setTestDate(new Date());
		text.setTestRemark("测试serivce层是否搭建成功");
		
		service.saveText(text);
	}

}
