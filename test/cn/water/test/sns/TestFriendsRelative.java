package cn.water.test.sns;

import java.util.Iterator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.dao.IFriendshipsDao;
import cn.water.cf.domain.User;
import cn.water.cf.domain.sns.Friendships;
import cn.water.cf.service.IUserService;

public class TestFriendsRelative {
	
	@Test
	public void testAddFriendsRelative(){
		Configuration conf = new Configuration();
		conf.configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		Friendships ship = new Friendships();
		User userId = new User();
		User friendId = new User();
		userId.setId("8abc8af14e2ff7c1014e2ff812290001");
		friendId.setId("8abc8af14e524a44014e524b1e6a0001");
		
		ship.setUserId("8abc8af14e2ff7c1014e2ff812290001");
		ship.setFriendId(friendId);
		
		session.save(ship);
		
		session.getTransaction().commit();
		session.close();
	}
	@Test
	public void testfindFriendsByUserId(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IFriendshipsDao dao = (IFriendshipsDao)context.getBean(IFriendshipsDao.SERVICE_NAME);
	
		List<Object[]> friends = dao.findFriendsByUserId("8abc8af14e2ff7c1014e2ff812290001");
		Object[]friendsObj = friends.toArray();
		System.out.println(friendsObj.toString());
		for(int i=0;i<friendsObj.length;i++ ){
			
			System.out.println((String)friendsObj[i]);
		}
	
	}
	
	@Test
	public void test3(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IUserService dao = (IUserService)context.getBean(IUserService.SERVICE_NAME);
	
		String[] friends = dao.findFriendsById("8abc8af14e2ff7c1014e2ff812290001");
		for(String friend : friends){
			System.out.println(friend);
		}
	
	}

}
