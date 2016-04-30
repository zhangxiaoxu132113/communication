package cn.water.test.blog.album;

import java.util.Date;
import java.util.List;
import java.util.Set;

import net.sf.cglib.asm.Type;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.dao.IAlbumDao;
import cn.water.cf.domain.User;
import cn.water.cf.domain.blog.album.Album;
import cn.water.cf.domain.blog.album.Photo;
import cn.water.cf.service.IAlbumService;


public class TestAlbum {
	
	@Test
	public void testAddAlbum(){
		Configuration conf = new Configuration();
		conf.configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		Album album = new Album();
		album.setAlbumName("海贼王");
		album.setCreateTime(new Date());
		album.setDescription("这是一部很有名的动漫作品");
		album.setLinkAddr("http://localhost:8080/onepiece/album");
		
		User user = new User();
		user.setId("8abc8af14e2ff7c1014e2ff812290001");
		
		album.setUser(user);
		session.save(album);
		session.getTransaction().commit();
		session.close();
	}

	
	@Test
	public void testAddAlbum2(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IAlbumDao dao = (IAlbumDao)context.getBean(IAlbumDao.SERVICE_NAME);
	
		Album album = new Album();
		album.setAlbumName("海贼王");
		album.setCreateTime(new Date());
		album.setDescription("这是一部很有名的动漫作品");
		album.setLinkAddr("http://localhost:8080/onepiece/album");
		
		User user = new User();
		user.setId("8abc8af14e2ff7c1014e2ff812290001");
		
		album.setUser(user);
		
		dao.save(album);
	}
	@Test
	public void testAddAlbum3(){
		Configuration conf = new Configuration();
		conf.configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		Photo photo = new Photo();
		photo.setAlbum_id(null);
		photo.setUser_id("8abc8af14e2ff7c1014e2ff812290001");
		Album album = new Album();
		album.setAlbum_id(1L);
		photo.setAlbum_id(album);
		session.save(photo);
		
		session.getTransaction().commit();
		session.close();
	}
	
	@Test
	public void findAlbum() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IAlbumService service = (IAlbumService)context.getBean(IAlbumService.SERVICE_NAME);
		List<Album> albums = service.findAllAlbumsByUserId("8abc8af14e56870d014e5687c0ad0001");
		for(Album album : albums){
			System.out.println(album.getAlbumName());
		}
	}
	@Test
	public void findAlbum2() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IAlbumDao dao = (IAlbumDao)context.getBean(IAlbumDao.SERVICE_NAME);
		List<Integer> types = dao.getAllTypeOfAlbum("8abc8af14e56870d014e5687c0ad0001");
		System.out.println(types.toString());
		for(int i=0;i<types.size();i++){
			Integer type = types.get(i);
			System.out.println(type);
		}
	}
	@Test
	public void findAlbumType3() {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IAlbumService service = (IAlbumService)context.getBean(IAlbumService.SERVICE_NAME);
		/*Set<String> set = service.findAlbumTypeByUserId("8abc8af14e56870d014e5687c0ad0001");
		for(String str : set){
			System.out.println(str);
		}
*/	}
}
