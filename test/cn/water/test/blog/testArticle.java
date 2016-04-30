package cn.water.test.blog;


import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.domain.Article;
import cn.water.cf.domain.Attach;
import cn.water.cf.domain.Type;
import cn.water.cf.domain.User;
import cn.water.cf.service.IArticleService;

public class testArticle {

	@Test
	public void testAddArticle(){
		
		Configuration conf = new Configuration();
		conf.configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		
		Article article = new Article();
		/*article.setArticle_id(1);*/
		article.setTitle("title");
		article.setAuthor("author");
		article.setContent("content");
		article.setCreateDate(new Date());
		article.setUpdateDate(new Date());
		article.setKeywords("112");
		
		User user = new User();
		user.setId("8a04a3e94e29b722014e29b728c10001");
		article.setOwner(user);
		
		
		article.setType(1);
		
		Attach attach1 = new Attach();
		Attach attach2 = new Attach();
		/*attach1.setAttach_id(1);
		attach2.setAttach_id(2);*/
		attach1.setAttacheName("attach1's name");
		attach2.setAttacheName("attach2's name");
		attach1.setLocation("attach1's location");
		attach2.setLocation("attach2's location");
		
		//Attache对象关联Article对象
		attach1.setArticle_id(article);
		attach2.setArticle_id(article);
		
		//Article对象关联Attach对象
		article.getAttaches().add(attach1);
		article.getAttaches().add(attach2);
		
		session.save(article);
		
		session.getTransaction().commit();
		session.close();
		
		
	}
	
	
	@Test
	public void testFillAllArticle(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IArticleService service = (IArticleService)context.getBean(IArticleService.SERVICE_NAME);
		List<Article> list = service.findAllArticleByUserId("8a04a3e94e472fe6014e473063950001");
		if(list != null && list.size()>0){
			for(Article article:list){
				System.out.print(article.getArticle_id());
				System.out.print(article.getOwner().getId());
				System.out.print(article.getTitle());
				System.out.print(article.getSummary());
				System.out.print(article.getContent());
				System.out.println("===========================================");
			}
		}else{
			System.out.println("没有获取到数据");
		}
	}
	
	
	
	
	@Test
	public void testAddArticle2(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IArticleService service = (IArticleService)context.getBean(IArticleService.SERVICE_NAME);
		
		Article article = new Article();
		article.setTitle("title");
		article.setAuthor("author");
		article.setContent("content");
		article.setCreateDate(new Date());
		article.setUpdateDate(new Date());
		article.setKeywords("112");
		
		User user = new User();
		user.setId("8abc8af14e2ff7c1014e2ff812290001");
		article.setOwner(user);
		
		
		article.setType(1);
		
		service.addArticle(article);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
