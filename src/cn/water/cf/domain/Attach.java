package cn.water.cf.domain;

/**
 * @author 张淼洁
 * @description 附件表，对应于文章，等表而言
 */
@SuppressWarnings("serial")
public class Attach implements java.io.Serializable{
	
	private int attach_id;			//附件的id
	private String attacheName;		//附件的名称
	//TODO 这里要这样设计吗，如果我不一定是所属于文章的附件呢，可能是其他对象的附件
	private String location;		//附件的存放位置
	private Article article_id;		//附件所属的文章
	
	public int getAttach_id() {
		return attach_id;
	}
	public void setAttach_id(int attach_id) {
		this.attach_id = attach_id;
	}
	public String getAttacheName() {
		return attacheName;
	}
	public void setAttacheName(String attacheName) {
		this.attacheName = attacheName;
	}
	public Article getArticle_id() {
		return article_id;
	}
	public void setArticle_id(Article article_id) {
		this.article_id = article_id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	

}
