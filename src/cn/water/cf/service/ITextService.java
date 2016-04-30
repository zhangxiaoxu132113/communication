package cn.water.cf.service;

import cn.water.cf.domain.Text;

public interface ITextService {
	
	public final static String SERVICE_NAME = "cn.water.cf.service.impl.TextServiceImpl";

	/**
	 * 保存测试对象
	 * @param text
	 */
	void saveText(Text text);
	
	/**
	 * 更新测试对象
	 * @param text
	 */
	void updateText(Text text);
	
	/**
	 * 删除测试对象
	 * @param text
	 */
	void deleteText(Text text);
}
