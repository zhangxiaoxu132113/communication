package cn.water.cf.dao;

import cn.water.cf.domain.User;

public interface IUserDao extends ICommonDao<User>{
	
	public final static String SERVICE_NAME = "cn.water.cf.dao.impl.UserDaoImpl";

}
