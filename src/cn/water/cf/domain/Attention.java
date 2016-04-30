package cn.water.cf.domain;

@SuppressWarnings("serial")
public class Attention implements java.io.Serializable{
	
	private long attention_id;			//关注表的主键
	private String user_id;				//被关注的用户的主键
	private String attention_user_id;	//点击关注的用户主键
	
	public long getAttention_id() {
		return attention_id;
	}
	public void setAttention_id(long attention_id) {
		this.attention_id = attention_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAttention_user_id() {
		return attention_user_id;
	}
	public void setAttention_user_id(String attention_user_id) {
		this.attention_user_id = attention_user_id;
	}
	
	

}
