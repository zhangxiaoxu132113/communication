package cn.water.test.blog;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.domain.KeyWord;
import cn.water.cf.service.IKeyWordService;




public class TestKeyword {

	@Test
	public void testAddKeyword(){
		Configuration conf = new Configuration();
		conf.configure();
		
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		KeyWord keyword = new KeyWord();
		keyword.setKeyWordName("Ajax");
		
		session.save(keyword);
		
		session.getTransaction().commit();
		session.close();
	
	}
	@Test
	public void testFindAllKeyword(){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IKeyWordService service = (IKeyWordService)context.getBean(IKeyWordService.SERVICE_NAME);
	
		List<KeyWord> allKeyWord = service.getAllKeyWord();
		Iterator<KeyWord> iterator = allKeyWord.iterator();
		
		while(iterator.hasNext()){
			KeyWord next = iterator.next();
			System.out.println(next.getKeyWordName());
		}
		
	
	}
}
