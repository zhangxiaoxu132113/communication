package cn.water.cf.dao;

import java.util.List;

import cn.water.cf.domain.blog.album.Album;
import cn.water.cf.domain.blog.album.Photo;
import cn.water.cf.web.form.Page;

public interface IAlbumDao extends ICommonDao<Album>{
	
	public static final String SERVICE_NAME = "cn.water.cf.dao.impl.AlbumDaoImpl";
	
	List<Integer> getAllTypeOfAlbum(String userId);

	int queryAlbumAllPhotoCount(long parseLong);

	List<Photo> queryByPage(long parseLong, Page page);

}
