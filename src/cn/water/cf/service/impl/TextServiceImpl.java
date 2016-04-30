package cn.water.cf.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.water.cf.dao.ITextDao;
import cn.water.cf.domain.Text;
import cn.water.cf.service.ITextService;
@Transactional(readOnly=true)
@Service(ITextService.SERVICE_NAME)
public class TextServiceImpl implements ITextService{

	@Resource(name=ITextDao.SERVICE_NAME)
	private ITextDao dao ;
	
	@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveText(Text text) {
		dao.save(text);
	}

	public void updateText(Text text) {
		dao.update(text);
	}

	public void deleteText(Text text) {
		dao.deleteById(text.getId());
	}

}
