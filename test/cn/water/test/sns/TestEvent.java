package cn.water.test.sns;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.dao.IEventUserDao;
import cn.water.cf.domain.User;
import cn.water.cf.domain.sns.Event;
import cn.water.cf.domain.sns.EventType;
import cn.water.cf.domain.sns.EventUser;
import cn.water.cf.service.IEventService;

public class TestEvent {
	
	@Test
	public void testAddEvent(){
		Configuration conf = new Configuration();
		conf.configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		Event event = new Event();
		event.setMessage_type(EventType.CREATE_UBLUM);
		User owner = new User();
		owner.setId("8abc8af14e492acd014e492b07370001");
		event.setOwner(owner);
		event.setCreateTime(new Date());
		event.setEventMsg("msg conent");
		
		session.save(event);
		session.getTransaction().commit();
		session.close();
	}

	@Test
	public void testAddEventByService(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IEventService service = (IEventService)context.getBean(IEventService.SERVICE_NAME);
		Event event = new Event();
		event.setMessage_type(EventType.CREATE_UBLUM);
		User owner = new User();
		owner.setId("8abc8af14e492acd014e492b07370001");
		event.setOwner(owner);
		event.setCreateTime(new Date());
		event.setEventMsg("msg conent1");
		service.addEvent(event);
	}
	@Test
	public void testAddEventUser(){
		Configuration conf = new Configuration();
		conf.configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		Event event = new Event();
		event.setId(6L);
		
		EventUser eventUser = new EventUser();
		eventUser.setCreateDate(new Date());
		eventUser.setEvent_id(event);
		eventUser.setFriendsUserId("8abc8af14e492acd014e492b07370001");
		
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void testEventUser(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IEventService service = (IEventService)context.getBean(IEventService.SERVICE_NAME);
		
		Event event = new Event();
		event.setMessage_type(EventType.CREATE_UBLUM);
		User owner = new User();
		owner.setId("8abc8af14e524a44014e524b1e6a0001");
		event.setOwner(owner);
		event.setCreateTime(new Date());
		event.setEventMsg("这是在测试获取好友动态功能的实现");
		
		EventUser eventUser = new EventUser();
		eventUser.setCreateDate(new Date());
		eventUser.setEvent_id(event);
		eventUser.setFriendsUserId("8abc8af14e492acd014e492b07370001");
		
		service.addEvent(event);
		service.addEventUser(eventUser);
		
	}
	//懒加载问题
	@Test
	public void testEventUser2(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IEventUserDao dao = (IEventUserDao)context.getBean(IEventUserDao.SERVICE_NAME);
		/*List<EventUser> friendsDynamics = dao.findMyFriendsDynamicByUserId("8abc8af14e492acd014e492b07370001");
		Iterator<EventUser> iterator = friendsDynamics.iterator();
		
		while(iterator.hasNext()){
			EventUser eventUser = iterator.next();
			System.out.println(eventUser.getEvent_id().getId());
		}*/
		Long[] ids = dao.findMyFriendsDynamicIdByUserId("8abc8af14e492acd014e492b07370001");
		for(Long id:ids){
			System.out.println(id);
		}
	
	}
	
	@Test
	public void testEventUser3(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IEventService service = (IEventService)context.getBean(IEventService.SERVICE_NAME);
		
		Set<Event> friendEvents = service.findFriendDynamicByUserId("8abc8af14e492acd014e492b07370001");
		Iterator<Event> iterator = friendEvents.iterator();
		while(iterator.hasNext()){
			Event event = iterator.next();
			System.out.println(event.toString());
		}
	}
	
	
}
