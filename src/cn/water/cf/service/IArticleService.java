package cn.water.cf.service;

import java.util.List;

import cn.water.cf.domain.Article;
import cn.water.cf.web.form.Page;
import cn.water.cf.web.form.Result;

public interface IArticleService {

	public static final String SERVICE_NAME = "cn.water.cf.service.impl.ArticleServiceImpl";
	/**
	 * @description 添加一篇文章
	 * @param article
	 */
	void addArticle(Article article);
	/**
	 * @description 根据用户id字段查询用户的所有文章
	 * @param article
	 */
	List<Article> findAllArticleByUserId(String userId);
	/**
	 * @description 分页显示用户文章
	 * @param article
	 */
	Result showUserArticleByPage(String userId,Page page);
	/**
	 * @description 根据文章id查找一篇文章
	 * @param String article_id 文章的id字段
	 */
	Article findArticleById(String article_id,boolean isUpdateAccess);
	/**
	 * @description 根据文章id删除一篇文章
	 * @param String article_id 文章的id字段
	 */
	void delArticleById(String article_id);
	/**
	 * @description 更新文章
	 * @param String article_id 文章的id字段
	 */
	void updateArticle(Article valueObject);


}
