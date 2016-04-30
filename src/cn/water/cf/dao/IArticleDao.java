package cn.water.cf.dao;

import java.util.List;

import cn.water.cf.domain.Article;
import cn.water.cf.web.form.Page;

public interface IArticleDao extends ICommonDao<Article>{
	
	public static final String SERVICE_NAME = "cn.water.cf.dao.impl.ArticleDaoImpl";
	
	/**
	 * 获得用户文章总记录数
	 * @param userId
	 * @return
	 */
	public int queryUserAllArticleCount(String userId);
	/**
	 * 按分页信息查询记录
	 * @param username
	 * @param page
	 * @return
	 */
	public List<Article> queryByPage(String userId,Page page);




}
