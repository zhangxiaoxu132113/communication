package cn.water.cf.domain;

@SuppressWarnings("serial")
public class ShortMessage implements java.io.Serializable{


	private long message_id;	//短信的id字段
	private String from_id;		//短信发送者的id字段
	private String to_id;		//短信接收者的id字段
	private String content;		//短信的内容
	private Type type;			//短信的类型
	
	public long getMessage_id() {
		return message_id;
	}
	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}
	public String getFrom_id() {
		return from_id;
	}
	public void setFrom_id(String from_id) {
		this.from_id = from_id;
	}
	public String getTo_id() {
		return to_id;
	}
	public void setTo_id(String to_id) {
		this.to_id = to_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	
	
}
