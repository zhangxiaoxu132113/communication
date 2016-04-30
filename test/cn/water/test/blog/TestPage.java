package cn.water.test.blog;


import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.dao.IArticleDao;
import cn.water.cf.domain.Article;
import cn.water.cf.service.IArticleService;
import cn.water.cf.utils.PageUtil;
import cn.water.cf.web.form.Page;
import cn.water.cf.web.form.Result;

public class TestPage {
	
	@Test
	public void testPage(){
		
		Page page = PageUtil.createPage(5, 46, 1);
		System.out.println(page.getBeginIndex());
		System.out.println(page.getCurrentPage());
		System.out.println(page.getEveryPage());
		System.out.println(page.getTotalCount());
		System.out.println(page.getTotalPage());
		System.out.println(page.isHasPrePage());
	}
	
	@Test
	public void getCountarticle(){
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IArticleDao articleDao = (IArticleDao)context.getBean(IArticleDao.SERVICE_NAME);
		int count = articleDao.queryUserAllArticleCount("8a04a3e94e472fe6014e473063950001");
		System.out.println("count = "+count);
		
	}
	@Test
	public void getAllArticle(){
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IArticleDao articleDao = (IArticleDao)context.getBean(IArticleDao.SERVICE_NAME);
		String userId = "8a04a3e94e472fe6014e473063950001";
		Page page = new Page();
		page.setEveryPage(5);
		page.setBeginIndex(5);
		List<Article> articleList = articleDao.queryByPage(userId, page);
		Iterator<Article> iterator = articleList.iterator();
		while(iterator.hasNext()){
			Article article = (Article) iterator.next();
			System.out.println(article.getTitle());
		}
	}

	@Test
	public void testService(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IArticleService service = (IArticleService)context.getBean(IArticleService.SERVICE_NAME);
		Page page = new Page();
		page.setCurrentPage(1);
		page.setEveryPage(5);
		Result result = service.showUserArticleByPage("8a04a3e94e472fe6014e473063950001", page);
		List<Article> articleList = result.getList();
		Iterator<Article> iterator = articleList.iterator();
		while(iterator.hasNext()){
			Article article = (Article) iterator.next();
			System.out.println(article.getTitle());
		}
	}
	
	@Test
	public void testFenyeNum(){
		int totalPage = 24;
		int currentPage = 8;
		System.out.println("yushu "+currentPage%7);
		int i = 1 + currentPage/7*7;
		int end = i+7;
		for(;i<totalPage;i++){
			if(i == end){
				return;
			}
			System.out.println(i);
		}
	}
}
