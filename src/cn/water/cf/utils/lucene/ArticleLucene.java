package cn.water.cf.utils.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import cn.water.cf.domain.Article;

public class ArticleLucene {
	
	private static ArticleLucene articleLucene = null;
	private String dir = "";
	private ArticleLucene(){
		dir = "D:"+File.separator+"ArticleLucene";
	}
	//获取ArticleLucene对象
	public static ArticleLucene newInstance(){
		if(articleLucene == null){
			articleLucene = new ArticleLucene();
		}
		return articleLucene;
	}
	/**  
	* @Name: createArticleIndex
	* @Description: 创建文章索引
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-02（创建日期）
	* @Parameters: List<Article>articleList 文章集合对象
	* @Return: null
	*/
	public void createArticleIndex(List<Article>articleList,boolean isDelIndex){
		/*String dir = ServletActionContext.getServletContext().getRealPath("/luceneDir");*/
		IndexWriter writer = LuceneUtil.getIndexWriter(dir);
		//是否需要删除原有的索引库
		if(isDelIndex){
			try {
				writer.deleteAll();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Iterator<Article> iterator = articleList.iterator();
		while(iterator.hasNext()){
			Article article = iterator.next();
			Document doc = Article2Documnet(article);
			try {
				writer.addDocument(doc);
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//关闭indexWriter对象
		LuceneUtil.closeIndexWriter(writer);
		
	}
	public void createArticleIndex(Article article){
		/*String dir = ServletActionContext.getServletContext().getRealPath("/luceneDir");*/
		String dir = "D:"+File.separator+"ArticleLucene";
		IndexWriter writer = LuceneUtil.getIndexWriter(dir);
		
		Document doc = Article2Documnet(article);
		try {
			writer.addDocument(doc);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//关闭indexWriter对象
		LuceneUtil.closeIndexWriter(writer);
		
	}
	/**  
	* @Name: Article2Documnet
	* @Description: 将文章对象转换为documnet对象
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-02（创建日期）
	* @Parameters: Article article 文章对象
	* @Return: Document 
	*/
	private Document Article2Documnet(Article article){
		Document document = new Document();
		document.add(new NumericField("article_id",Field.Store.YES,true).setIntValue(article.getArticle_id()));
		document.add(new Field("title",article.getTitle(),Field.Store.YES,Index.ANALYZED));
		document.add(new Field("author",article.getAuthor(),Field.Store.YES,Index.NOT_ANALYZED_NO_NORMS));
		document.add(new Field("content",article.getContent(),Field.Store.NO,Index.ANALYZED));
		document.add(new NumericField("createDate",Field.Store.YES,true).setLongValue(article.getCreateDate().getTime()));
		return document;
	}
	/**  
	* @Name: searchArticleByTerm
	* @Description: 根据某一个关键字查询文章
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-02（创建日期）
	* @Parameters: String field  关键字
	* 			   String name	  关键字值
	* 			   int num		  查询得到的数量
	* @Return: List<Integer>
	*/
	public List<Integer> searchArticleByTerm(String field,String name,int num){
		
		IndexSearcher searcher = null;
		
		try {
			List<Integer> list = new ArrayList<Integer>();
			Directory dire = FSDirectory.open(new File(dir));
			searcher = LuceneUtil.getIndexSearcher(dire, null);
			Query query = new TermQuery(new Term(field,name));
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:"+tds.totalHits);
			for(ScoreDoc sd:tds.scoreDocs){
				Document doc = searcher.doc(sd.doc);
				list.add(Integer.parseInt(doc.get("article_id")));
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(searcher != null){
				try {
					searcher.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	/**  
	* @Name: searchArticleByTermRange
	* @Description: 根据某一个范围来查询文章
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-02（创建日期）
	* @Return: List<Integer>
	*/
	public List<Integer> searchArticleByTermRange(String field,String start,String end,int num){
		
		IndexSearcher searcher = null;
		try {
			List<Integer> list = new ArrayList<Integer>();
			Directory dire = FSDirectory.open(new File(dir));
			searcher = LuceneUtil.getIndexSearcher(dire, null);
			
			Query query = new TermRangeQuery(field, start, end, true, true);
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:"+tds.totalHits);
			for(ScoreDoc sd:tds.scoreDocs){
				Document doc = searcher.doc(sd.doc);
				list.add(Integer.parseInt(doc.get("article_id")));
			}
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(searcher != null){
				try {
					searcher.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	/**  
	* @Name: searchArticleByNumricRange
	* @Description: 根据某一个范围来查询文章,对应整型类型
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-02（创建日期）
	* @Return: List<Integer>
	*/
	public List<Integer> searchArticleByNumricRange(String field,int start,int end,int num){
		IndexSearcher searcher = null;
		
		try {
			List<Integer> list = new ArrayList<Integer>();
			Directory dire = FSDirectory.open(new File(dir));
			searcher = LuceneUtil.getIndexSearcher(dire, null);
			
			Query query = NumericRangeQuery.newIntRange(field, start, end, true, true);
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:"+tds.totalHits);
			for(ScoreDoc sd:tds.scoreDocs){
				Document doc = searcher.doc(sd.doc);
				list.add(Integer.parseInt(doc.get("article_id")));
			}
			return list;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**  
	* @Name: searchArticleByPrefix
	* @Description: 根据前缀的查询对应文章
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-02（创建日期）
	* @Return: List<Integer>
	*/
	public List<Integer> searchArticleByPrefix(String field,String value,int num) {
		IndexSearcher searcher = null;
		
		try {
			List<Integer> list = new ArrayList<Integer>();
			Directory dire = FSDirectory.open(new File(dir));
			searcher = LuceneUtil.getIndexSearcher(dire, null);
			
			Query query = new PrefixQuery(new Term(field,value));
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:"+tds.totalHits);
			for(ScoreDoc sd:tds.scoreDocs){
				Document doc = searcher.doc(sd.doc);
				list.add(Integer.parseInt(doc.get("article_id")));
			}
			return list;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**  
	* @Name: searchArticleByWildcard
	* @Description: 根据通配符的查询对应文章
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-02（创建日期）
	* @Return: List<Integer>
	*/
	public List<Integer> searchArticleByWildcard(String field,String value,int num){
		IndexSearcher searcher = null;
		
		try {
			List<Integer> list = new ArrayList<Integer>();
			Directory dire = FSDirectory.open(new File(dir));
			searcher = LuceneUtil.getIndexSearcher(dire, null);
			
			Query query = new WildcardQuery(new Term(field,value));
			TopDocs tds = searcher.search(query, num);
			System.out.println("一共查询了:"+tds.totalHits);
			for(ScoreDoc sd:tds.scoreDocs){
				Document doc = searcher.doc(sd.doc);
				list.add(Integer.parseInt(doc.get("article_id")));
			}
			return list;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
