package cn.water.cf.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class DynamicNews implements java.io.Serializable{
	
	private long new_id;	//动态表的id字段
	private User user;		//发布的人
	private Date createTime;//发布的时间
	private String content;	//发布的内容
	private String picture;	//图片的路径
	private String vedio;	//视频的url路径
	private int conentNum;	//评论的人数
	private Type type;		//发布动态的类型
	private int shareNum;	//分享的人数
	
	public long getNew_id() {
		return new_id;
	}
	public void setNew_id(long new_id) {
		this.new_id = new_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getVedio() {
		return vedio;
	}
	public void setVedio(String vedio) {
		this.vedio = vedio;
	}
	public int getConentNum() {
		return conentNum;
	}
	public void setConentNum(int conentNum) {
		this.conentNum = conentNum;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public int getShareNum() {
		return shareNum;
	}
	public void setShareNum(int shareNum) {
		this.shareNum = shareNum;
	}
	
}
