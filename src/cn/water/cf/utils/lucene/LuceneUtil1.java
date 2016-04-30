package cn.water.cf.utils.lucene;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

import cn.water.cf.domain.Article;

public class LuceneUtil1 {
	
	private static Directory directory;			//定义Directory对象
	private static IndexWriter indexWriter;		//定义IndexWriter对象
	private static IndexReader indexReader;		//定义IndexReader对象
	private static IndexSearcher indexSearcher;  //定义IndexSearcher对象
	
	/**
	 * 获取得到directory对象
	 * @throws IOException 
	 */
	public static Directory getDirectory(String storePath) throws IOException{
		return FSDirectory.open(new File(storePath));
	}
	
	/**
	 * 获取得到IndexSearcher对象
	 * @return
	 */
	public static IndexSearcher getIndexSearcher(Directory dire){
		try {
			if(indexReader==null) {
				indexReader = IndexReader.open(dire,false);
			} else {
				IndexReader tr = IndexReader.openIfChanged(indexReader);
				if(tr!=null) {
					indexReader.close();
					indexReader = tr;
				}
			}
			return new IndexSearcher(indexReader);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 创建索引
	 */
	public static void createIndex(String storePath,boolean isDelIndex,List<Article> articleList){
		
		try {
			directory = getDirectory(storePath);
			indexWriter = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
			//是否执行删除所有的索引
			if(isDelIndex){
				indexWriter.deleteAll();
			}
			//创建索引
			if(articleList != null && articleList.size()>0){
				Iterator<Article> iterator = articleList.iterator();
				while(iterator.hasNext()){
					Document document = null;
					Article article = iterator.next();
					document = Article2Document(article);
					indexWriter.addDocument(document);
					document = null;//释放内存
				}
			}else{
				System.out.println("article集合对象为空!");
			}
			
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(indexWriter != null){
				try {
					indexWriter.close();
				} catch (CorruptIndexException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 将文章对象转换为document文档
	 */
	public static Document Article2Document(Article article){
		Document document = new Document();
		document.add(new Field("title",article.getTitle(),Field.Store.YES,Index.ANALYZED));
		document.add(new Field("author",article.getAuthor(),Field.Store.YES,Index.NOT_ANALYZED_NO_NORMS));
		document.add(new NumericField("article_id",Field.Store.NO,false).setIntValue(article.getArticle_id()));
		document.add(new Field("content",article.getContent(),Field.Store.NO,Index.ANALYZED));
		document.add(new NumericField("createDate",Field.Store.YES,true).setLongValue(article.getCreateDate().getTime()));
		return document;
	}
	
	/**
	 * 使用indexReader对象对索引数据进行恢复
	 */
	public static void unDelete(){
		
		IndexReader reader = null;
		try {
			//恢复时，必须把IndexReader的只读(readOnly)设置为false
			reader = IndexReader.open(directory, false);
			//对所有的数据进行恢复
			reader.undeleteAll();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 
	 */
	public static void meger(String storePath){
		try {
			if(indexWriter == null){
				directory = getDirectory(storePath);
				indexWriter = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
				//会将索引合并为2段，这两段中的被删除的数据会被清空
				//特别注意：此处Lucene在3.5之后不建议使用，因为会消耗大量的开销，
				//Lucene会根据情况自动处理的
			}
			indexWriter.forceMerge(2);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(indexWriter != null){
				try {
					indexWriter.close();
				} catch (CorruptIndexException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	/**
	 * 强制删除
	 */
	public static void forceDeletes(String storePath){
		try {
			if(indexWriter == null){
				directory = getDirectory(storePath);
				indexWriter = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
				//会将索引合并为2段，这两段中的被删除的数据会被清空
				//特别注意：此处Lucene在3.5之后不建议使用，因为会消耗大量的开销，
				//Lucene会根据情况自动处理的
			}
			indexWriter.forceMergeDeletes();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(indexWriter != null){
				try {
					indexWriter.close();
				} catch (CorruptIndexException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
	
}
