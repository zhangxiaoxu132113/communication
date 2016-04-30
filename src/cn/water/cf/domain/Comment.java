package cn.water.cf.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class Comment implements java.io.Serializable{

	private long comment_id;	//评论表的主键
	private Article article;	//被评论的文章的主键
	private User user;			//评论人的主键
	private String content;		//评论的内容
	private Date commentDate;	//评论的时间
	
	public long getComment_id() {
		return comment_id;
	}
	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
 
}
