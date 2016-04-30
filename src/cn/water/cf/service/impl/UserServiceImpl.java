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

import cn.water.cf.dao.IFriendshipsDao;
import cn.water.cf.dao.IUserDao;
import cn.water.cf.domain.User;
import cn.water.cf.domain.sns.Event;
import cn.water.cf.service.IEventService;
import cn.water.cf.service.IUserService;
@Service(IUserService.SERVICE_NAME)
@Transactional(readOnly=true)
public class UserServiceImpl implements IUserService{

	@Resource(name=IUserDao.SERVICE_NAME)
	private IUserDao userDao ;
	
	@Resource(name=IFriendshipsDao.SERVICE_NAME)
	private IFriendshipsDao friendshipsDao;
	
	@Resource(name=IEventService.SERVICE_NAME)
	private IEventService eventService;
	
	/**  
	* @Name: saveUser
	* @Description: 保存用户对象
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-10 （创建日期）
	* @Parameters: User 用户对象
	* @Return: null
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveUser(User user) {
		if(user == null){
			throw new RuntimeException("用户对象不能为空");
		}
		userDao.save(user);
		
	}
	/**  
	* @Name: deleteUser
	* @Description: 删除用户
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-25 （创建日期）
	* @Parameters: User : 用户
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void deleteUser(User user) {
		if(user == null){
			throw new RuntimeException("User对象不能为空！");
		}
		userDao.deleteById(user.getId());
	}
	/**  
	* @Name: updateUser
	* @Description: 更新用户信息
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-25 （创建日期）
	* @Parameters: User:用户
	* 
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void updateUser(User user) {
		if(user == null){
			throw new RuntimeException("User对象不能为空！");
		}
		userDao.update(user);
	}
	/**  
	* @Name: findUserById
	* @Description: 根据用户名的id来查找数据库的实体
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-18 （创建日期）
	* @Parameters: userName : 用户名
	* 			   md5Pwd	: 密码
	* @Return: User 用户对象
	*/
	public User findUserById(String id) {
		if(id == null){
			throw new RuntimeException("id 字段不能为空");
		}
		User user = userDao.findEntityById(id);
		return user;
	}
	/**  
	* @Name: findUserByNameAndPwd
	* @Description: 根据用户名和密码获取一条用户数据
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-18 （创建日期）
	* @Parameters: userName : 用户名
	* 			   md5Pwd	: 密码
	* @Return: User 用户对象
	*/
	@Override
	public User findUserByNameAndPwd(String userName, String md5Pwd) {
		//校验传入的数据是否为空值
		if(StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(md5Pwd)){
			//组织好查询条件
			String condition = " and o.userName = ? and o.password = ?";
			Object[]params = {userName,md5Pwd};
			//调用dao的查询方法
			List<User> userList = userDao.findCollectionByConditionNoPage(condition, params, null);
			if(userList!=null && userList.size()==1 ){
				return userList.get(0);
			}
		
		}
		return null;
	}
	/**  
	* @Name: registerUser
	* @Description: 用户注册
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-20 （创建日期）
	* @Parameters: User 用户对象
	* @Return: true 注册成功
	* 		   false 注册失败
	*/
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public boolean registerUser(User valueObject) {
		if(valueObject != null){
			userDao.save(valueObject);
			return true;
		}
		return false;
	}
	/**  
	* @Name: eidtUserInfo
	* @Description: 编辑用户资料
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-20 （创建日期）
	* @Parameters: User 用户对象
	* @Return: true 注册成功
	* 		   false 注册失败
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public void eidtUserInfo(User valueObject) {
		if(valueObject != null){
			userDao.update(valueObject);
		}
		
	}
	/**  
	* @Name: addHeadImageUrl
	* @Description: 将用户的头像的url地址保存到数据库中
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-22 （创建日期）
	* @Parameters: imageUrl 头像的url地址
	* 			   userId   用户的id字段
	*
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	@Override
	public void addHeadImageUrl(String imageUrl,String userId) {
		if(imageUrl == null){
			throw new RuntimeException("用户头像图片存放的url地址不能为空");
		}
		List<Object>keys = new ArrayList<Object>();
		List<Object>params = new ArrayList<Object>();
		LinkedHashMap<String,Object> condition = new LinkedHashMap<String, Object>();
		if(imageUrl != null){
			keys.add("headImage");
			params.add(imageUrl);
		}
		condition.put("id", userId);
		
		userDao.partialRenewal(keys, params, condition);
		System.out.println("业务层的方法，更新局部的数据成功！");
	}
	/**  
	* @Name: perfectionProfile
	* @Description: 完善用户资料
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-26 （创建日期）
	* @Parameters: User 用户对象
	*
	*/
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void perfectionProfile(User user) {
		if(user == null){
			throw new RuntimeException("用户对象不能为空");
		}
		List<Object>keys = new ArrayList<Object>();
		List<Object>params = new ArrayList<Object>();
		LinkedHashMap<String,Object> condition = new LinkedHashMap<String, Object>();
		//组织好
		if(user.getRealName() != null){
			keys.add("realname");
			params.add(user.getRealName());
		}
		if(user.getAddress() != null){
			keys.add("address");
			params.add(user.getAddress());
		}
		if(user.getBirthday() != null){
			keys.add("birthday");
			params.add(user.getBirthday());
		}
		if(user.getProfile() != null){
			keys.add("profile");
			params.add(user.getProfile());
		}
		if(user.getEmail() != null){
			keys.add("email");
			params.add(user.getEmail());
		}
		//TODO 整数类型应该怎么判断呢
		if(user.getQq() < 0){
			keys.add("qq");
			params.add(user.getQq());
		}
		if(user.getHightSchool() != null){
			keys.add("hightschool");
			params.add(user.getHightSchool());
		}
		if(user.getUniversity() != null){
			keys.add("university");
			params.add(user.getUniversity());
		}
		
		//组织更新的条件
		condition.put("id", user.getId());
		userDao.partialRenewal(keys, params, condition);
	}
	/**  
	* @Name: findFriendsById
	* @Description: 根据用户id字段查询用户的所有好友
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-04（创建日期）
	* @Parameters: String userId 用户id字段
	* @return String[] 好友的id数组
	*/
	public String[] findFriendsById(String userId) {
		if(StringUtils.isNotBlank(userId)){
			//调用friendshipsDao投影查询方法查询出一条记录
			List<Object[]> friends = friendshipsDao.findFriendsByUserId(userId);
			Object[] friendsObj = friends.toArray();
			String[] friendsStr = new String[friendsObj.length];
			for(int i=0;i<friendsObj.length;i++){
				friendsStr[i] = (String) friendsObj[i];
			}
			return friendsStr;
		}
		return null;
	}
	/**  
	* @Name: getFriendsDynamic
	* @Description: 根据用户id字段获取好友的动态
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-04（创建日期）
	* @Parameters: String userId 用户id字段
	* @return List<Event> 好友的动态事件
	*/
	public List<Event> getFriendsDynamic(String userId) {
		if(StringUtils.isNotBlank(userId)){
			/*eventService.*/
		}
		return null;
	}
	/**  
	* @Name: searchUserBySearchKey
	* @Description: 根据关键字搜索用户
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-11-19（创建日期）
	* @Parameters: String searchKey 搜索关键字
	* @return List<User> 用户集合
	*/
	public List<User> searchUserBySearchKey(String userName) {
		if(userName == null && "".equals(userName)){
			throw new RuntimeException("搜索的关键字不能为空！");
		}
		String condition = " and o.userName like ?";
		Object[] params = {"%"+userName+"%"};
		List<User> userList = userDao.findCollectionByConditionNoPage(condition, params, null);
		System.out.println(userList.size());
		if(userList != null && userList.size()>0){
			//对获取的集合的长度进行截取,只获取前五个
			if(userList.size()>5)
				userList = userList.subList(0, 5);
			return userList;
		}
		return null;
	}

}
