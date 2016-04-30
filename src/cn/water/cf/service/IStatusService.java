package cn.water.cf.service;

import cn.water.cf.domain.status.Status;

public interface IStatusService {
	
	public static final String SERVICE_NAME ="cn.water.cf.service.impl.StatusServiceImpl";
	
	public void saveStatus(Status status);

}
