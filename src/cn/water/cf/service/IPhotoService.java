package cn.water.cf.service;

import cn.water.cf.domain.blog.album.Photo;

public interface IPhotoService {
	
	public static final String SERVICE_NAME = "cn.water.cf.service.impl.PhotoServiceImpl";
	
	void savePhoto(Photo photo);

	void delPhotoById(long parseLong);

}
