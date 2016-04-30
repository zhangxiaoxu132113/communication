package cn.water.cf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.water.cf.dao.ISystemDDLDao;
import cn.water.cf.domain.SystemDDL;
import cn.water.cf.service.ISystemDDLService;
@Service(ISystemDDLService.SERVICE_NAME)
@Transactional(readOnly=true)
public class SystemDDLServiceImpl implements ISystemDDLService{
	
	@Resource(name=ISystemDDLDao.SERVICE_NAME)
	private ISystemDDLDao systemDDLDao;
	/**  
	* @Name: addSystemDDL
	* @Description: 添加一条数据字典数据
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-29 （创建日期）
	* @Parameters: SystemDDL
	*/
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void addSystemDDL(SystemDDL systemDDL) {
		if(systemDDL == null){
			throw new RuntimeException("systemDDL 不能为空");
		}
		//调用业务层的方法添加一条数据
		systemDDLDao.save(systemDDL);
		
	}
	/**  
	* @Name: findSystemDDLbyCondition
	* @Description:获取一条数据字典数据
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-29 （创建日期）
	* @Parameters: SystemDDL
	*/
	@Override
	public SystemDDL findSystemDDLbyCondition(String keyword,int ddlCode) {
		if(keyword==null && ddlCode <0){
			throw new RuntimeException("参数不合法!");
		}
		//组织查询条件
		String condition = "and o.keyword = ? and o.ddlCode = ? ";
		Object[]params = {keyword,ddlCode};
		List<SystemDDL> systemDDLList = systemDDLDao.findCollectionByConditionNoPageWithCache(condition, params, null);
		return systemDDLList.get(0);
	}

}
