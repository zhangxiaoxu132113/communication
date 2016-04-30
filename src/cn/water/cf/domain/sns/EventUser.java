package cn.water.cf.domain.sns;

import java.util.Date;

@SuppressWarnings("serial")
public class EventUser implements java.io.Serializable{

	private long id;				//用户事件表的id主键
	private Event event_id;			//事件的id
	private String friendsUserId;	//好友的id主键
	private Date createDate;		//事件创建的时间
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Event getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Event event_id) {
		this.event_id = event_id;
	}
	public String getFriendsUserId() {
		return friendsUserId;
	}
	public void setFriendsUserId(String friendsUserId) {
		this.friendsUserId = friendsUserId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
