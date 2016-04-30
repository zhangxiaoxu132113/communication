package cn.water.cf.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.water.cf.dao.IStatusDao;
import cn.water.cf.domain.status.Status;
import cn.water.cf.service.IStatusService;
@Service(IStatusService.SERVICE_NAME)
@Transactional(readOnly=true)

public class StatusServiceImpl implements IStatusService{
	
	@Resource(name=IStatusDao.SERVICE_NAME)
	private IStatusDao statusDao;
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveStatus(Status status) {

		statusDao.save(status);
	}
	

}
