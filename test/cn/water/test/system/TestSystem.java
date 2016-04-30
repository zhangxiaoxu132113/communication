package cn.water.test.system;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.domain.SystemDDL;
import cn.water.cf.service.ISystemDDLService;

public class TestSystem {
	
	@Test
	public void testAddSystemDDL(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ISystemDDLService service = (ISystemDDLService)context.getBean(ISystemDDLService.SERVICE_NAME);
		SystemDDL systemDDL = new SystemDDL();
		systemDDL.setKeyword("status");
		systemDDL.setDdlCode(1);
		systemDDL.setDdlName("已婚");
		
		service.addSystemDDL(systemDDL);
	
	}

	@Test
	public void testFindSystemDDL(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ISystemDDLService service = (ISystemDDLService)context.getBean(ISystemDDLService.SERVICE_NAME);
		
		String keyword = "gender";
		int ddlCode = 1;
		SystemDDL object = service.findSystemDDLbyCondition(keyword, ddlCode);
		System.out.println(object.getDdlName());
		
	}
}
