package cn.water.cf.domain.sns;

import java.util.Date;

import cn.water.cf.domain.User;

@SuppressWarnings("serial")
public class Event implements java.io.Serializable{
	
	private long id; 			//消息id
	private User owner;			//事件的所属者
	private String message_type;//事件的类型
	private String eventMsg;	//事件的内容
	private Date createTime;	//事件创建的事件
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getEventMsg() {
		return eventMsg;
	}
	public void setEventMsg(String eventMsg) {
		this.eventMsg = eventMsg;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", owner=" + owner + ", message_type="
				+ message_type + ", eventMsg=" + eventMsg + ", createTime="
				+ createTime + "]";
	}
	
	
}
