package cn.water.test.lucene;

import java.io.File;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.water.cf.dao.IArticleDao;
import cn.water.cf.domain.Article;
import cn.water.cf.service.IArticleService;
import cn.water.cf.utils.lucene.ArticleLucene;
import cn.water.cf.utils.lucene.LuceneUtil1;

public class TestLuceneIndexWriter {

	@Test
	public void testCreateIndexWriter(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IArticleService service = (IArticleService)context.getBean(IArticleService.SERVICE_NAME);
		List<Article> articleList = service.findAllArticleByUserId("8a04a3e94e51e283014e51e37cef0001");
		LuceneUtil1.createIndex("D:"+File.separator+"lucene", true, articleList);
	}
	
	@Test
	public void testCreateIndexWriter1(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IArticleService service = (IArticleService)context.getBean(IArticleService.SERVICE_NAME);
		List<Article> articleList = service.findAllArticleByUserId("8abc8af14e492acd014e492b07370001");
		ArticleLucene articleLucene = ArticleLucene.newInstance();
		articleLucene.createArticleIndex(articleList,true);
	}
	
	@Test
	public void testSearcherByTerm(){
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		IArticleDao dao = (IArticleDao)context.getBean(IArticleDao.SERVICE_NAME);
		ArticleLucene articleLucene = ArticleLucene.newInstance();
//		List<Integer> articleIdList = articleLucene.searchArticleByTerm("title", "boys", 5);
		List<Integer> articleIdList = articleLucene.searchArticleByTerm("content", "数据", 4);
		/*int[]articleIdArray = new int[articleIdList.size()];
		for(int i=0;i<articleIdArray.length;i++){
			articleIdArray[i] = articleIdList.get(i);
		}*/

		/*Set<Article> articleList = dao.findEntitiesByIds(articleIdArray);
		
		
		Iterator<Article> iterator = articleList.iterator();
		while(iterator.hasNext()){
			Article article = iterator.next();
			System.out.print(article.getArticle_id());
			System.out.print(article.getContent());
			System.out.println("===================================================");
		}*/
		Iterator<Integer> iterator = articleIdList.iterator();
		while(iterator.hasNext()){
			Article article = dao.findEntityById(iterator.next());
			System.out.print(article.getArticle_id());
			System.out.print(article.getContent());
			System.out.println("===================================================");
		}
	}
	
	public void testChineseAnalyzer(){
		
	}
	
}
