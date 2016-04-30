package cn.water.cf.domain.sns;

import java.util.Date;

@SuppressWarnings("serial")
public class RecentVisitor implements java.io.Serializable{

	private long recentVisitor_id;	//最近访客表的主键
	private String User_id;			//被访问的用户端的主键
	private String visitor_id;		//访问者的主键
	private Date time;				//访问时间
	
	public long getRecentVisitor_id() {
		return recentVisitor_id;
	}
	public void setRecentVisitor_id(long recentVisitor_id) {
		this.recentVisitor_id = recentVisitor_id;
	}
	public String getUser_id() {
		return User_id;
	}
	public void setUser_id(String user_id) {
		User_id = user_id;
	}
	public String getVisitor_id() {
		return visitor_id;
	}
	public void setVisitor_id(String visitor_id) {
		this.visitor_id = visitor_id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	

}
