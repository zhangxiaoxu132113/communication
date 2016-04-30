package cn.water.cf.service;

import cn.water.cf.domain.SystemDDL;

public interface ISystemDDLService {
	
	public static final String SERVICE_NAME = "cn.water.cf.service.impl.SystemDDLServiceImpl";
	/**
	 * @description 添加一条数据
	 * @param systemDDL
	 */
	
	public void addSystemDDL(SystemDDL systemDDL);
	/**
	 * @description 根据添加查看数据
	 * @param systemDDL
	 */
	public SystemDDL findSystemDDLbyCondition(String keyword,int ddlCode);

}
