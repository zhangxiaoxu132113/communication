package cn.water.cf.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.water.cf.dao.IAlbumDao;
import cn.water.cf.dao.IPhotoDao;
import cn.water.cf.domain.SystemDDL;
import cn.water.cf.domain.blog.album.Album;
import cn.water.cf.domain.blog.album.Photo;
import cn.water.cf.service.IAlbumService;
import cn.water.cf.service.ISystemDDLService;
import cn.water.cf.utils.PageUtil;
import cn.water.cf.web.form.Page;
import cn.water.cf.web.form.Result;
@Service(IAlbumService.SERVICE_NAME)
@Transactional(readOnly=true)
public class AlbumServiceImpl implements IAlbumService{
	
	@Resource(name=IAlbumDao.SERVICE_NAME)
	private IAlbumDao albumDao;
	
	@Resource(name=ISystemDDLService.SERVICE_NAME)
	private ISystemDDLService systemDDLService;
	
	@Resource(name=IPhotoDao.SERVICE_NAME)
	private IPhotoDao photoDao;
	
	/**  
	* @Name: createAlbum
	* @Description: 创建相册
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-05 （创建日期）
	* @Parameters: Album 相册对象
	* @Return: null
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void createAlbum(Album album) {
		if(album == null){
			throw new RuntimeException("album 不能为空");
		}
		albumDao.save(album);
		
	}
	/**  
	* @Name: findAllAlbumsByUserId
	* @Description: 根据用户的id查找用户的所有相册
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-05 （创建日期）
	* @Parameters: String 用户id
	* @Return: null
	*/
	public List<Album> findAllAlbumsByUserId(String userId) {
		if(StringUtils.isNotBlank(userId)){
			String condition = " and o.user.id = ?";
			Object[] params = {userId};
			
			return albumDao.findCollectionByConditionNoPage(condition, params, null);
		}
		return null;
	}
	/**  
	* @Name: findAllAlbumsByUserId
	* @Description: 根据用户的id查找用户的所有相册的所有的类型
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-05 （创建日期）
	* @Parameters: String 用户id
	* @Return: null
	*/
	public List<SystemDDL> findAlbumTypeByUserId(String userId) {
		if(StringUtils.isNotBlank(userId)){
			List<Integer> types = albumDao.getAllTypeOfAlbum(userId);
			List<SystemDDL> typeStr = types2Str(types);
			
			return typeStr;
		}
		return null;
	}
	/**  
	* @Name: types2Str
	* @Description: 根据albumType来查找数据字典中对应的数据
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-05 （创建日期）
	* @Parameters: List<Integer> types
	* @Return: List<SystemDDL>
	*/
	private List<SystemDDL> types2Str(List<Integer> types) {
		if(types!=null && types.size()>0){
			List<SystemDDL> typeStr = new ArrayList<SystemDDL>();
			for(int i=0;i<types.size();i++){
				//判断types.get(i)获取得到的值是否为空
				if(types.get(i) != null){
					SystemDDL albumType = systemDDLService.findSystemDDLbyCondition("albumType", types.get(i));
					typeStr.add(albumType);
				}
			}
			return typeStr;
		}
		
		return null;
	}
	/**  
	* @Name: findAllPhotoByAlbum_id
	* @Description: 根据相册的id来查找对应的所有图片
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-06（创建日期）
	* @Parameters: long album_id
	* @Return: List<Photo>
	*/
	public List<Photo> findAllPhotoByAlbum_id(long album_id) {
		
		String condition = " and o.album_id.album_id = ?";
		Object[] params = {album_id};
		List<Photo> photoList = photoDao.findCollectionByConditionNoPage(condition, params, null);
		return photoList;
	}
	/**  
	* @Name: updateFrontCover
	* @Description: 更新相册的封面
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-08（创建日期）
	* @Parameters: long album_id 
	* 			   String url   
	* @Return: null
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public boolean updateFrontCover(long album_id, String url) {
		if(StringUtils.isNotBlank(url) && album_id>0){
			List<Object> keys = new ArrayList<Object>();
			List<Object> params = new ArrayList<Object>();
			
			keys.add("frontCover");
			params.add(url);
			
			LinkedHashMap<String, Object> conditions = new LinkedHashMap<String, Object>();
			conditions.put("album_id", album_id);
			albumDao.partialRenewal(keys, params, conditions);
			
			return true;
		}
		
		return false;
		
	}
	/**  
	* @Name: findPhotoByPage
	* @Description: 通过分页来查看图片
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-08（创建日期）
	* @Parameters: long album_id 
	* 			   Page page
	* @Return: List<Photo>
	*/
	public Result findPhotoByPage(String album_id, Page page) {
		//调用分页的工具类来转换原来page的参数
		page = PageUtil.createPage(page,albumDao.queryAlbumAllPhotoCount(Long.parseLong(album_id)));
		//List<Article> all = articleDao.queryByPage(userId, page);
		List<Photo> all = albumDao.queryByPage(Long.parseLong(album_id),page);
		Result result = new Result();
		result.setPage(page);
		result.setList(all);
		return result;
	}

}
