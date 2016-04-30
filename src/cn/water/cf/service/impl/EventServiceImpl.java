package cn.water.cf.service.impl;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.water.cf.dao.IEventDao;
import cn.water.cf.dao.IEventUserDao;
import cn.water.cf.domain.sns.Event;
import cn.water.cf.domain.sns.EventUser;
import cn.water.cf.service.IEventService;
@Service(IEventService.SERVICE_NAME)
@Transactional(readOnly=true)
public class EventServiceImpl implements IEventService{

	@Resource(name=IEventDao.SERVICE_NAME)
	private IEventDao eventDao;
	
	@Resource(name=IEventUserDao.SERVICE_NAME)
	private IEventUserDao eventUserDao;
	
	/**  
	* @Name: addEvent
	* @Description: 添加一条事件记录,在保存事件表的同时保存用户用户事件表
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-02 （创建日期）
	* @Parameters: Event 事件对象
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void addEvent(Event event,Set<String> friendIds) {
		if(event == null ){
			throw new RuntimeException("event对象不能为空");
		}
		Set<EventUser> events = new HashSet<EventUser>();
		
		Iterator<String> iterator = friendIds.iterator();
		while(iterator.hasNext()){
			String friendId = iterator.next();
			
			EventUser eventUser = new EventUser();
			eventUser.setCreateDate(event.getCreateTime());
			eventUser.setEvent_id(event);
			eventUser.setFriendsUserId(friendId);
			
			events.add(eventUser);
		}
		eventDao.save(event);
		eventUserDao.saveConllection(events);
		/*
		 * 由于这个方法会经常被调用，会产生很对分配给set的内存，
		 * 出于性能方面的考虑，还是将events=null
		 */
		events = null;
		
	}
	/**  
	* @Name: addEvent
	* @Description: 添加一条事件记录
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-02 （创建日期）
	* @Parameters: Event 事件对象
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void addEvent(Event event) {
		if(event == null ){
			throw new RuntimeException("event对象不能为空");
		}
		eventDao.save(event);
		
	}
	/**  
	* @Name: addEventUser
	* @Description: 添加一条用户事件记录
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-04 （创建日期）
	* @Parameters: EventUser 用户事件对象
	*/
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void addEventUser(EventUser eventUser) {
		if(eventUser == null ){
			throw new RuntimeException("event对象不能为空");
		}
		eventUserDao.save(eventUser);
	}
	/**  
	* @Name: findFriendDynamicByUserId
	* @Description: 根据用户id字段查询得到好友的动态事件
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-04 （创建日期）
	* @Parameters: EventUser 用户事件对象
	*/
	public Set<Event> findFriendDynamicByUserId(String userId) {
		Long[] friendsDynamicIds = eventUserDao.findMyFriendsDynamicIdByUserId(userId);
		//TODO action
		//获取得到数据后要转换为json格式
		//Event[id=46,owner=cn.water.cf.domain.User.. ,messageType="createAblum" eventMsg="test" ]
		return eventDao.findEntitiesByIds((Serializable[])friendsDynamicIds);
	}
	
	
	
}
