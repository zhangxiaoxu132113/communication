package cn.water.cf.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 张淼洁
 * @description 文章对象
 * 		关联关系：
 * 				1，用户对象（用户表）
 * 				2，附件对象（附件表）
 * 				3，关键字对象（文章关键字表）
 */
@SuppressWarnings("serial")
public class Article implements java.io.Serializable{

	private int article_id;				//文章的id 主键
	private User owner;					//文章所属的用户
	private String title;				//文章的标题
	private String author;				//文章的作者
	private String content;				//文章的内容
	private Date createDate;			//文章的创建日期
	private Date updateDate;			//文章最近修改的日期
	private int accessNumber;			//文章的用户浏览的总数
	private String summary;				//文章的概要内容
	private Set<Attach> attaches = new HashSet<Attach>();		//文章的附件存放的位置
	private String keywords;			//文章的关键字
	private int type;	//文章的分类
	
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public int getAccessNumber() {
		return accessNumber;
	}
	public void setAccessNumber(int accessNumber) {
		this.accessNumber = accessNumber;
	}
	public Set<Attach> getAttaches() {
		return attaches;
	}
	public void setAttaches(Set<Attach> attaches) {
		this.attaches = attaches;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	
	
	
	
}
