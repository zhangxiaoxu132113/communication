package cn.water.cf.dao.impl;

import org.springframework.stereotype.Repository;

import cn.water.cf.dao.IUserDao;
import cn.water.cf.domain.User;
@Repository(IUserDao.SERVICE_NAME)
public class UserDaoImpl extends CommonDaoImpl<User> implements IUserDao{

}
