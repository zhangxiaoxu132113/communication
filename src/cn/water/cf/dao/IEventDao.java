package cn.water.cf.dao;


import cn.water.cf.domain.sns.Event;
public interface IEventDao extends ICommonDao<Event>{

	public static final String SERVICE_NAME = "cn.water.cf.dao.impl.EventDaoImpl";
}
