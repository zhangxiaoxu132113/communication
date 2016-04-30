package cn.water.cf.domain;
/**
 * 
 * @author 张淼洁
 * @description 文章收藏表
 *
 */
@SuppressWarnings("serial")
public class Collect implements java.io.Serializable{

	private int collect_id;			//文章收藏表的主键
	private User user;				//收藏人
	private String title;			//文章收藏的标题
	private String linkUrl;			//文章的链接地址
	private String description;		//收藏文章的描述
	private Type type;//收藏文章的类型
	
	
	public int getCollect_id() {
		return collect_id;
	}
	public void setCollect_id(int collect_id) {
		this.collect_id = collect_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
