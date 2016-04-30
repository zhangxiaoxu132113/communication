package cn.water.cf.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.water.cf.dao.IPhotoDao;
import cn.water.cf.domain.blog.album.Photo;
import cn.water.cf.service.IPhotoService;

@Service(IPhotoService.SERVICE_NAME)
@Transactional(readOnly=true)
public class PhotoServiceImpl implements IPhotoService{
	
	@Resource(name=IPhotoDao.SERVICE_NAME)
	private IPhotoDao photoDao;
	/**  
	* @Name: savePhoto
	* @Description: 保存图片信息到数据库
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-23 （创建日期）
	* @Parameters: Photo 照片对象
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void savePhoto(Photo photo){
		if(photo == null){
			throw new RuntimeException("用户对象不能为空");
		}
		photoDao.save(photo);
		
	}
	/**  
	* @Name: delPhotoById
	* @Description: 根据照片id删除照片
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-08 （创建日期）
	* @Parameters: Photo 照片对象
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void delPhotoById(long photoId) {
		// TODO 这回涉及到了一个安全的问题，必需从session中取出userId作为一个条件进行删除
		if(photoId>0){
			photoDao.deleteById(photoId);
		}
		
	}

}
