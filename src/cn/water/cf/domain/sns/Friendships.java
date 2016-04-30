package cn.water.cf.domain.sns;

import cn.water.cf.domain.User;

@SuppressWarnings("serial")
public class Friendships implements java.io.Serializable{

	private Long id;		//好友关系表的id主键
	private String userId;	//用户的id主键
	private User friendId;	//好友的id主键
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public User getFriendId() {
		return friendId;
	}
	public void setFriendId(User friendId) {
		this.friendId = friendId;
	}
	
	
}
