package cn.water.cf.dao;


import java.util.List;

import cn.water.cf.domain.sns.EventUser;

public interface IEventUserDao extends ICommonDao<EventUser>{
	
	public static final String SERVICE_NAME="cn.water.cf.dao.impl.EventUserDaoImpl";

	List<EventUser> findMyFriendsDynamicByUserId(String userId);
	
	Long[] findMyFriendsDynamicIdByUserId(String userId);
}
