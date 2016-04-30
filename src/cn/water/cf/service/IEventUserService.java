package cn.water.cf.service;

import java.util.List;

import cn.water.cf.domain.sns.EventUser;

public interface IEventUserService {
	
	public static final String SERVICE_NAME ="cn.water.cf.service.impl.EventUserServiceImpl";

	List<EventUser> findMyFriendsDynamicByUserId(String userId);
}
