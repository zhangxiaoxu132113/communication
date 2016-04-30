package cn.water.cf.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.water.cf.dao.IKeyWordDao;
import cn.water.cf.domain.KeyWord;
import cn.water.cf.service.IKeyWordService;
@Service(IKeyWordService.SERVICE_NAME)
@Transactional(readOnly=true)
public class KeyWordServiceImpl implements IKeyWordService{
	
	@Resource(name=IKeyWordDao.SERVICE_NAME)
	private IKeyWordDao keywordDao;
	/**  
	* @Name: addKeyWord
	* @Description: 添加关键字
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-28 （创建日期）
	* @Parameters: KwyWord关键字
	*/
	@Override
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void addKeyWord(KeyWord keyword) {
		if(keyword == null){
			throw new RuntimeException("keyword 不能为空!");
		}
		//调用dao层的方法添加一条数据
		keywordDao.save(keyword);
		
	}
	/**  
	* @Name: getAllKeyWord
	* @Description: 获取所有关键字
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-28 （创建日期）
	* @Return Set<KeyWord> keyword的集合对象
	*/
	@Override
	public List<KeyWord> getAllKeyWord() {
		
		return  (List<KeyWord>) keywordDao.findAll();
	}

}
