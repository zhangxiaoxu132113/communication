package cn.water.cf.domain.blog.album;

import java.util.Date;


import cn.water.cf.domain.User;

@SuppressWarnings("serial")
public class Album implements java.io.Serializable{

	private Long album_id;		//相册表的主键
	private User user;			//相册所属用户的主键
	private String albumName;	//相册的名称
	private Date createTime;	//相册创建时间
	private String linkAddr;	//相册的连接地址
	private Integer type;		//相册的类型
	private String description;	//相册的描述
	
	private Integer count;		//相册的总数
	private String frontCover;	//相册的封面
	
	public Long getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(Long album_id) {
		this.album_id = album_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLinkAddr() {
		return linkAddr;
	}
	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getFrontCover() {
		return frontCover;
	}
	public void setFrontCover(String frontCover) {
		this.frontCover = frontCover;
	}
	
	

}
