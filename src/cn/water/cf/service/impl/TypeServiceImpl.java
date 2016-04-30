package cn.water.cf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.water.cf.dao.ITypeDao;
import cn.water.cf.domain.Type;
import cn.water.cf.service.ITypeService;
@Service(ITypeService.SERVICE_NAME)
@Transactional(readOnly=true)
public class TypeServiceImpl implements ITypeService{

	@Resource(name=ITypeDao.SERVICE_NAME)
	private ITypeDao typeDao;
	/**  
	* @Name: saveType
	* @Description: 保存类型对象
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-27 （创建日期）
	* @Parameters: Type 类型对象
	* @Return: null
	*/
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveType(Type type) {
		if(type == null){
			throw new RuntimeException("type不能为空!");
		}
		typeDao.save(type);
	}
	/**  
	* @Name: findAllType
	* @Description: 获取得到所有的类型
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-27 （创建日期）
	* @Return: List<Type>
	*/
	@Override
	public List<Type> findAllType() {
		
		return (List<Type>) typeDao.findAll();
	}
}
