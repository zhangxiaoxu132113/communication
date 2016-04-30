package cn.water.cf.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.water.cf.dao.IArticleDao;
import cn.water.cf.domain.Article;
import cn.water.cf.web.form.Page;

@Repository(IArticleDao.SERVICE_NAME)
public class ArticleDaoImpl extends CommonDaoImpl<Article> implements IArticleDao{
	/**  
	* @Name: queryUserAllArticleCount
	* @Description: 根据用户id字段，查询用户的所有的文章
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-01 （创建日期）
	* @Parameters: String userId 用户的id字段
	* @Return: int 用户所有的文章总计数
	*/
	@SuppressWarnings("rawtypes")
	public int queryUserAllArticleCount(String userId) {
		List articleList = this.getHibernateTemplate().find("select count(*) from Article art where art.owner.id = ?",userId);
		return ((Long)articleList.get(0)).intValue();
	}
	/**  
	* @Name: queryByPage
	* @Description: 根据用户id和分页信息查询记录
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-01 （创建日期）
	* @Parameters: String userId 用户id字段
	* 			    Page page	 分页信息
	* @Return: List<Article> 文章的集合对象
	*/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Article> queryByPage(final String userId, final Page page) {
		
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("select art from Article art where art.owner.id = ?");
				//设置参数
				query.setParameter(0, userId);
				//设置每页显示多少个，设置多大结果。
				query.setMaxResults(page.getEveryPage());
				//设置起点
				query.setFirstResult(page.getBeginIndex());
				return query.list();
			}
			
		});

	}


}
