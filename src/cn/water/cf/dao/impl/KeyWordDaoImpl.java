package cn.water.cf.dao.impl;

import org.springframework.stereotype.Repository;

import cn.water.cf.dao.IKeyWordDao;
import cn.water.cf.domain.KeyWord;

@Repository(IKeyWordDao.SERVICE_NAME)
public class KeyWordDaoImpl extends CommonDaoImpl<KeyWord> implements IKeyWordDao {

}
