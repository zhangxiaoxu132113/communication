package cn.water.cf.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class Reply implements java.io.Serializable{
	
	private long reply_id;		//回复表的主键
	private long comment_id;	//评论的id主键
	private String content;		//评论的内容
	private Date replyTime;		//回复的时间
	
	public long getReply_id() {
		return reply_id;
	}
	public void setReply_id(long reply_id) {
		this.reply_id = reply_id;
	}
	public long getComment_id() {
		return comment_id;
	}
	public void setComment_id(long comment_id) {
		this.comment_id = comment_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	
	
}
