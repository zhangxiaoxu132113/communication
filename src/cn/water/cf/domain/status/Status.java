package cn.water.cf.domain.status;

import java.util.Date;

import cn.water.cf.domain.User;

@SuppressWarnings("serial")
public class Status implements java.io.Serializable{

	public void setId(long id) {
		this.id = id;
	}
	private long id;				//id
	private User user;				//作者
	private String text;			//微博内容
	private Date created_at; 		//创建时间
	private int reposts_count;		//转发数
	private int contents_count;		//评论数
	private int attitudes_count;	//表态数
	private String thumbnail_pic;   //配图（注：多张配图以分号隔开）
	//TODO 暂时不实现这个功能
	private Status retweeted_status;//被转发的微博
	
	// getting and setting ...
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public int getReposts_count() {
		return reposts_count;
	}
	public void setReposts_count(int reposts_count) {
		this.reposts_count = reposts_count;
	}
	public int getContents_count() {
		return contents_count;
	}
	public void setContents_count(int contents_count) {
		this.contents_count = contents_count;
	}
	public int getAttitudes_count() {
		return attitudes_count;
	}
	public void setAttitudes_count(int attitudes_count) {
		this.attitudes_count = attitudes_count;
	}
	public String getThumbnail_pic() {
		return thumbnail_pic;
	}
	public void setThumbnail_pic(String thumbnail_pic) {
		this.thumbnail_pic = thumbnail_pic;
	}
	public Status getRetweeted_status() {
		return retweeted_status;
	}
	public void setRetweeted_status(Status retweeted_status) {
		this.retweeted_status = retweeted_status;
	}
	public long getId() {
		return id;
	}
	
	

}
