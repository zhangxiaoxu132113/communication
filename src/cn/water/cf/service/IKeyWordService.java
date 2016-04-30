package cn.water.cf.service;

import java.util.List;

import cn.water.cf.domain.KeyWord;

public interface IKeyWordService {

	public static final String SERVICE_NAME="cn.water.cf.service.impl.KeyWordServiceImpl";
	
	/**
	 * @description 添加关键字
	 * @param keyword
	 */
	public void addKeyWord(KeyWord keyword);
	
	public List<KeyWord> getAllKeyWord();
}
