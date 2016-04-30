package cn.water.cf.dao.impl;

import org.springframework.stereotype.Repository;

import cn.water.cf.dao.ITextDao;
import cn.water.cf.domain.Text;

@Repository(ITextDao.SERVICE_NAME)
public class TextDaoImpl extends CommonDaoImpl<Text> implements ITextDao{

	
	
}
