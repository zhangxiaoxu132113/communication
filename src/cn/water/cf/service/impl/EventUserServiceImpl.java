package cn.water.cf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.water.cf.dao.IEventUserDao;
import cn.water.cf.domain.sns.EventUser;
import cn.water.cf.service.IEventUserService;
@Service(IEventUserService.SERVICE_NAME)
@Transactional(readOnly=true)
public class EventUserServiceImpl implements IEventUserService{
	
	@Resource(name=IEventUserDao.SERVICE_NAME)
	private IEventUserDao eventUserDao;

	@Override
	public List<EventUser> findMyFriendsDynamicByUserId(String userId) {
		String condition = " and o.friendsUserId = ? ";
		Object[] params = {userId};
		
		return eventUserDao.findCollectionByConditionNoPage(condition, params, null);
	}

}
