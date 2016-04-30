package cn.water.cf.domain.blog.album;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 张淼洁
 * @description 照片对象
 */
@SuppressWarnings("serial")
public class Photo implements Serializable{

	private long photo_id;		//图片的id
	private String name;		//图片的真实名称
	private String url;			//图片的存放的位置
	private Album album_id;		//所属的相册的id
	private String user_id;		//图片所属的用户
	private Date uploadDate;	//图片上传的日期
	private String description;	//图片信息的描述
	
	public long getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(long photo_id) {
		this.photo_id = photo_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Album getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(Album album_id) {
		this.album_id = album_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
