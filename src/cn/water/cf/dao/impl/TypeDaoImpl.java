package cn.water.cf.dao.impl;

import org.springframework.stereotype.Repository;

import cn.water.cf.dao.ITypeDao;
import cn.water.cf.domain.Type;

@Repository(ITypeDao.SERVICE_NAME)
public class TypeDaoImpl extends CommonDaoImpl<Type> implements ITypeDao{

}
