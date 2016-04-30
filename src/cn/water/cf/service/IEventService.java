package cn.water.cf.service;

import java.util.Set;

import cn.water.cf.domain.sns.Event;
import cn.water.cf.domain.sns.EventUser;

public interface IEventService {
	
	public static final String SERVICE_NAME = "cn.water.cf.service.impl.EventServiceImpl";
	/**
	 * 添加一条事件记录
	 * @param event
	 */
	void addEvent(Event event);
	/**
	 * 添加一条用户事件记录
	 * @param event
	 */
	void addEventUser(EventUser eventUser);
	/**
	 * 添加一条事件记录的同时插入多个用户事件记录表
	 * @param event
	 */
	void addEvent(Event event,Set<String> friendIds);
	
	Set<Event> findFriendDynamicByUserId(String userId);
}
