package cn.water.cf.dao.impl;

import org.springframework.stereotype.Repository;

import cn.water.cf.dao.IPhotoDao;
import cn.water.cf.domain.blog.album.Photo;

@Repository(IPhotoDao.SERVICE_NAME)
public class PhotoDaoImpl extends CommonDaoImpl<Photo> implements IPhotoDao{
	
	

}
