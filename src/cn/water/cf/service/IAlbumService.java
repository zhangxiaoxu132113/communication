package cn.water.cf.service;

import java.util.List;

import cn.water.cf.domain.SystemDDL;
import cn.water.cf.domain.blog.album.Album;
import cn.water.cf.domain.blog.album.Photo;
import cn.water.cf.web.form.Page;
import cn.water.cf.web.form.Result;

public interface IAlbumService {
	
	public static final String SERVICE_NAME="cn.water.cf.service.impl.AlbumServiceImpl";
	/**
	 * @description 创建相册
	 * @param album
	 */
	void createAlbum(Album album);
	/**
	 * @description 根据用户id查找用户的所有相册
	 * @param album
	 */
	List<Album> findAllAlbumsByUserId(String userId);
	/**
	 * @description 根据用户id查找用户的所有相册的类型
	 * @param album
	 */
	List<SystemDDL> findAlbumTypeByUserId(String id);
	/**
	 * @description 根据相册id查找对应的相册的所有图片
	 * @param album
	 */
	List<Photo> findAllPhotoByAlbum_id(long parseLong);
	/**
	 * @description 更新相册的封面
	 * @param album
	 */
	boolean updateFrontCover(long parseLong, String url);
	/**
	 * @description 通过分页查看图片
	 * @param album
	 */
	Result findPhotoByPage(String album_id, Page page);

}
