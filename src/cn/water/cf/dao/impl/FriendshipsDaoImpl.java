package cn.water.cf.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.water.cf.dao.IFriendshipsDao;
import cn.water.cf.domain.sns.Friendships;

@Repository(IFriendshipsDao.SERVICE_NAME)
public class FriendshipsDaoImpl extends CommonDaoImpl<Friendships> implements IFriendshipsDao {

	/**
	 * @description 根据用户的id获取用户的所有好友的id字段
	 * SELECT o.friendId FROM t_friendships AS o WHERE 
	 * o.userId = '8abc8af14e2ff7c1014e2ff812290001'
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object[]> findFriendsByUserId(final String userId) {
		
		final String hql = "select o.friendId from t_friendships as o where o.userId = ?"; 
		System.out.println(hql);
		List<Object[]> friends = (List<Object[]>) this.getHibernateTemplate().execute(new HibernateCallback() {

			@SuppressWarnings("deprecation")
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				Query query = session.createSQLQuery(hql).addScalar("friendId",Hibernate.STRING);
				query.setParameter(0, userId);
				return query.list();
			}
			
		});
		return friends;
	}
	
	

}
