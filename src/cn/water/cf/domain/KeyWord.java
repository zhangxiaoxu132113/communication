package cn.water.cf.domain;

/**
 * @author 张淼洁
 * @description 关键字表，存放文件的关键字，方便用户快速搜索查找到文章
 *
 */
@SuppressWarnings("serial")
public class KeyWord implements java.io.Serializable{
	
	private int keyword_id;			//关键字表的id字段
	private String keyWordName;		//关键字表的名称字段
	
	public int getKeyword_id() {
		return keyword_id;
	}
	public void setKeyword_id(int keyword_id) {
		this.keyword_id = keyword_id;
	}
	public String getKeyWordName() {
		return keyWordName;
	}
	public void setKeyWordName(String keyWordName) {
		this.keyWordName = keyWordName;
	}
	
	

}
