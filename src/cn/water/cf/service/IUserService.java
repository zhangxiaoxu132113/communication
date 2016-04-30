package cn.water.cf.service;


import java.util.ArrayList;
import java.util.List;

import cn.water.cf.domain.User;
import cn.water.cf.domain.sns.Event;

public interface IUserService {
	public static final String SERVICE_NAME = "cn.water.cf.service.impl.UserServiceImpl";
	
	void saveUser(User user);
	
	void deleteUser(User user);
	
	void updateUser(User user);
	
	User findUserById(String id);

	User findUserByNameAndPwd(String userName, String md5Pwd);

	boolean registerUser(User valueObject);

	void eidtUserInfo(User valueObject);

	void addHeadImageUrl(String imageUrl,String userId);

	void perfectionProfile(User valueObject);
	
	String[] findFriendsById(String userId);
	
	List<Event> getFriendsDynamic(String userId);
	/**
	 * @description 根据用户输入的关键字查找用户信息
	 * @param searchKey 搜索的关键字
	 * @return 用户集合
	 */
	List<User> searchUserBySearchKey(String searchKey);
}
