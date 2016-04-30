package cn.water.test;

import java.util.Date;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import cn.water.cf.domain.Text;

public class TestHibernate {
	
	@Test
	public void testAddObject(){
		Configuration conf = new Configuration();
		conf.configure();
		
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		Text text = new Text();
		text.setTestName("测试hibernate层");
		text.setTestDate(new Date());
		text.setTestRemark("测试hibernate层是否搭建成功");
		
		session.save(text);
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void test2(){
		Configuration conf = new Configuration();
		conf.configure();
		
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		
		SQLQuery query = session.createSQLQuery("update t_text set testName ='haohao' where id = '402888e44dc420c4014dc420c7100001'");
		int result = query.executeUpdate();
		System.out.println("一共有 + "+result+"条数据被影响");
		System.out.println("执行成功");
		session.getTransaction().commit();
		session.close();
	}
}
