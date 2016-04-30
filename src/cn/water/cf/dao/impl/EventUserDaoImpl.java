package cn.water.cf.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.water.cf.dao.IEventUserDao;
import cn.water.cf.domain.sns.EventUser;
@Repository(IEventUserDao.SERVICE_NAME)
public class EventUserDaoImpl extends CommonDaoImpl<EventUser> implements IEventUserDao{


	@Override
	public List<EventUser> findMyFriendsDynamicByUserId(String userId) {
		String condition = " and o.friendsUserId = ? ";
		Object[] params = {userId};
		
		return findCollectionByConditionNoPage(condition, params, null);
	}

	@Override
	public Long[] findMyFriendsDynamicIdByUserId(String userId) {
		String condition = " and o.friendsUserId = ? ";
		Object[] params = {userId};
		
		List<EventUser> friendsDynamics = findCollectionByConditionNoPage(condition, params, null);
		
		return EventUser2EventIds(friendsDynamics);
	}
	
	
	private Long[] EventUser2EventIds(List<EventUser> eventUserList) {
		
		if(eventUserList != null && eventUserList.size()>0){
			Long[] friendEventIds = new Long[eventUserList.size()];
			for(int i=0;i<eventUserList.size();i++){
				friendEventIds[i] = eventUserList.get(i).getEvent_id().getId();
			}
			return friendEventIds;
		}
		return null;
	}
}
