package cn.water.cf.dao.impl;

import org.springframework.stereotype.Repository;

import cn.water.cf.dao.IStatusDao;
import cn.water.cf.dao.impl.CommonDaoImpl;
import cn.water.cf.domain.status.Status;
@Repository(IStatusDao.SERVICE_NAME)
public class StatusDaoImpl extends CommonDaoImpl<Status> implements IStatusDao {

}
