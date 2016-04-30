package cn.water.cf.dao.impl;

import org.springframework.stereotype.Repository;

import cn.water.cf.dao.IEventDao;
import cn.water.cf.domain.sns.Event;
@Repository(IEventDao.SERVICE_NAME)
public class EventDaoImpl extends CommonDaoImpl<Event> implements IEventDao{

}
