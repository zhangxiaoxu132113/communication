package cn.water.cf.service;

import java.util.List;

import cn.water.cf.domain.Type;

public interface ITypeService {
	
	public final static String SERVICE_NAME = "cn.water.cf.service.impl.ITypeServiceImpl";
	/**
	 * @description 保存类型对象
	 * @param type
	 */
	public void saveType(Type type);
	/**
	 * @description 获取所有的类型
	 * @param type
	 */
	public List<Type> findAllType();
	
	

}
