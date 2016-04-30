package cn.water.cf.utils.lucene;
import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;



public class LuceneUtil {
	
	/*private static Directory directory;
	
	private static IndexWriter writer;
	
	private static IndexReader reader;
	
	private static IndexSearcher searcher;*/
	
	//private static String STORE_PATH;
	
	//私有化构造函数
	private LuceneUtil(){}
	/**
	 *得到IndexWriter对象
	 */
	public static IndexWriter getIndexWriter(String storePath){
		IndexWriter writer = null;
		Directory directory = null;
		try {
			
			directory = getDirectory(storePath);
			//writer = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_35, new StandardAnalyzer(Version.LUCENE_35)));
			//采用中文的分词器
			String data = "D:"+File.separator+"data";
			System.out.println(data);
			writer = new IndexWriter(directory, new IndexWriterConfig(Version.LUCENE_35, new MMSegAnalyzer()));
			return writer;
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 关闭IndexWriter对象
	 */
	public static void closeIndexWriter(IndexWriter writer){
		if(writer != null){
			try {
				writer.close();
			} catch (CorruptIndexException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @throws IOException 
	 * 获取得到Directory对象
	 */
	public static Directory getDirectory(String storePath) throws IOException{
		return FSDirectory.open(new File(storePath));
	}
	/**
	 * 获取得到IndexSearcher对象
	 */
	public static IndexSearcher getIndexSearcher(Directory dire,IndexReader reader){
		try {
			if(reader==null) {
				reader = IndexReader.open(dire,false);
			} else {
				IndexReader tr = IndexReader.openIfChanged(reader);
				if(tr!=null) {
					reader.close();
					reader = tr;
				}
			}
			return new IndexSearcher(reader);
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
