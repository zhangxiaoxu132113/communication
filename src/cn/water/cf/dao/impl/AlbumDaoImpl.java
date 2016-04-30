package cn.water.cf.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.water.cf.dao.IAlbumDao;
import cn.water.cf.domain.blog.album.Album;
import cn.water.cf.domain.blog.album.Photo;
import cn.water.cf.web.form.Page;
@Repository(IAlbumDao.SERVICE_NAME)
public class AlbumDaoImpl extends CommonDaoImpl<Album> implements IAlbumDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Integer> getAllTypeOfAlbum(final String userId) {
		final String hql = "select distinct o.type from t_album as o where o.user_id = ?";
		List<Integer> types = this.getHibernateTemplate().execute(new HibernateCallback() {
			
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(hql);
				query.setParameter(0, userId);
				
				return query.list();
			}
		});
		
		return types;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int queryAlbumAllPhotoCount(long album_id) {
		List albumList = this.getHibernateTemplate().find("select count(*) from Photo o where o.album_id.album_id = ?",album_id);
		return ((Long)albumList.get(0)).intValue();
		
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Photo> queryByPage(final long album_id, final Page page) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery("select photo from Photo photo where photo.album_id.album_id = ?");
				//设置参数
				query.setParameter(0, album_id);
				//设置每页显示多少个，设置多大结果。
				query.setMaxResults(page.getEveryPage());
				//设置起点
				query.setFirstResult(page.getBeginIndex());
				return query.list();
			}
			
		});
	}
	
	

}
