package cn.water.test.blog;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import cn.water.cf.domain.Attach;

public class TestAttach {
	
	@Test
	public void testAddAttach(){
		
		Configuration conf = new Configuration();
		conf.configure();
		
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		Attach attach = new Attach();
		attach.setAttach_id(1);
		attach.setAttacheName("common file");
		attach.setLocation("/user/photo/john/think.doc");
		
		session.save(attach);
		session.getTransaction().commit();
		session.close();
		
	}

}
