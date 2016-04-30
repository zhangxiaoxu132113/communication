package cn.water.test.blog;

import java.sql.Types;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.domain.Type;
import cn.water.cf.service.ITypeService;

public class TestType {
	
	@Test
	public void testAddType(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ITypeService typeService = (ITypeService)context.getBean(ITypeService.SERVICE_NAME);
	
		Type type = new Type();
		type.setTypeName("只有好友可见");
		
		typeService.saveType(type);
	
	}

	@Test
	public void testFindAllType(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ITypeService typeService = (ITypeService)context.getBean(ITypeService.SERVICE_NAME);
	
		List<Type> typeList = typeService.findAllType();
		Iterator<Type> types = typeList.iterator();
		while(types.hasNext()){
			Type type = types.next();
			System.out.println("typeId="+type.getType_id()+" typeName="+type.getTypeName());
		}
	
	}
}
