package cn.water.cf.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.water.cf.dao.ICommonDao;
import cn.water.cf.utils.GenericSuperClass;
import cn.water.cf.utils.HibernateUtil;

public class CommonDaoImpl<T> extends HibernateDaoSupport implements ICommonDao<T> {

	@SuppressWarnings("rawtypes")
	private Class entityClass = GenericSuperClass.getActualTypeClass(this.getClass()) ;
	
	//通过依赖注入SessionFactory类，获取hibernateTempalte对象
	@Resource(name="sessionFactory")
	public final void setSessionFactoryDI(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	/**  
	* @Name: 		save
	* @Description: 保存
	* @Author: 		张淼洁（作者）
	* @Version: 	V1.00 （版本号）
	* @Create 		Date: 2015-06-20 （创建日期）
	* @Param 		T entity 保存对象
	*/
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}
	/**  
	* @Name: 		deleteById
	* @Description: 根据实体的id删除数据
	* @Author: 		张淼洁（作者）
	* @Version: 	V1.00 （版本号）
	* @Create 		Date: 2015-06-20 （创建日期）
	* @Param 		Serializable id 实体的id字段
	*/
	public void deleteById(Serializable id) {
		T entity = findEntityById(id);
		this.getHibernateTemplate().delete(entity);
		
	}
	/**  
	* @Name: 		deleteByIds
	* @Description: 根据实体的id删除数据
	* @Author: 		张淼洁（作者）
	* @Version: 	V1.00 （版本号）
	* @Create 		Date: 2015-06-20 （创建日期）
	* @Param 		Serializable id 实体的id字段
	*/
	public void deleteByIds(Collection<T> entities) {
		this.getHibernateTemplate().deleteAll(entities);
		
	}
	public void update(T entity) {

		this.getHibernateTemplate().update(entity);
	}

	@SuppressWarnings("unchecked")
	public T findEntityById(Serializable id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	public Set<T> findEntitiesByIds(Serializable... ids) {
		
		Set<T> set = new HashSet<T>();
	
		for(int id=0; id<ids.length; id++){
			set.add((T)this.getHibernateTemplate().get(entityClass, ids[id]));
		}

		return set;
	}

	@SuppressWarnings("unchecked")
	public Collection<T> findAll() {
		
		return this.getHibernateTemplate().find("from " + entityClass.getName());
	}

	/**根据条件进行模糊查询*/
	@SuppressWarnings("rawtypes")
	@Override
	public List<T> findCollectionByConditionNoPage(String condition,
			final Object[] params, LinkedHashMap<String, String> hashMap) {
		/*
		 * web界面查询用户的信息，where默认为1=1，这样用户即使不选择任何条件,sql查询也不会出错。
		 */
		String hql = "from "+entityClass.getSimpleName() +" o where 1=1";
		String orderby = "";
		//判断是否需要排序
		if(hashMap !=null){
			
			orderby = orderByHql(hashMap);
		}
		//from SystemGroupUser o where 1=1 and o.name like ? and o.principal = ? order by o.id desc 
		final String finalHql = hql + condition + orderby;
		System.out.println("hql : "+hql);
		@SuppressWarnings({ "unchecked" })
		List<T> list = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(finalHql);
				for(int i=0;params!=null && i<params.length;i++){
					query.setParameter(i, params[i]);
				}
				return  query.list();
			}
		});
		
		return list;
	}
	/**通过传递的排序集合语句（Map），获取对应的排序条件（String）*/
	/**ORDER BY o.textDate ASC,o.textName DESC*/
	private String orderByHql(LinkedHashMap<String, String> hashMap) {
		StringBuffer order = new StringBuffer("");
		if(hashMap != null && !hashMap.isEmpty()){
			order.append(" order by ");
			for(Map.Entry<String, String> map : hashMap.entrySet()){
				order.append(map.getKey()+" "+map.getValue()+" ,");
			}
			order.deleteCharAt(order.length()-1);//去掉最后一个逗号
		}	
		return order.toString();
	}
	/**  
	* @Name: partialRenewal
	* @Description: 根据业务层传递的条件和要更新的字段进行局部的更新
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-20 （创建日期）
	* @Param params		要更新的字段
	* 		 condition	条件
	*/
	@Override
	public void partialRenewal(List<Object>keys,List<Object>params ,LinkedHashMap<String,Object> conditions) {
		//字段和实际的值数量要一致
		if(keys.size()!=params.size()){
			throw new RuntimeException("字段的数量和更新的值的数量不一致！");
		}
		//将集合对象转换为数组对象
		Object[]keyArray = keys.toArray();
		Object[]paramArray = params.toArray();
		HibernateUtil hibernateUtil = HibernateUtil.getHibernateUtil();
		Session session = HibernateUtil.getSession();
		//定义查询的语句
		/*String hql = "update "+getTableName4ClassName()+" set ";*/
		String hql = "update "+ hibernateUtil.getTableName4ClassName(entityClass)+" set ";
		//组织查询语句的关键字段
		for(int i=0; i<keys.size(); i++){
			if(i==0){
				hql += ""+keyArray[i]+"= "+modifiedField(paramArray[i]) +"";
				
			}else{
				hql +=","+keyArray[i]+"= "+modifiedField(paramArray[i]) +"";
			}
		}
		hql +=updateConditionByHql(conditions);
		final String executeSQL = hql;
		System.out.println("hql :"+hql);
				
		session.beginTransaction();
				
		SQLQuery query = session.createSQLQuery(executeSQL);
		int result = query.executeUpdate();
		
		System.out.println("一共有"+result+"条数据被影响！");
		
		session.getTransaction().commit();
		HibernateUtil.closeSession(session);
				
		/*return result;	*/
		
	}
	/**
	 * @description:判断表字段的类型，从而是否进行字段的修饰
	 * @param type
	 * @return
	 */
	private String modifiedField(Object type){
		if(type == null){
			throw new RuntimeException("type 不能为空!");
		}
		StringBuffer sb = new StringBuffer();
		if(type instanceof String){
			String stringType = (String)type;
			sb.append("'");
			sb.append(stringType);
			sb.append("'");
				
			return new String(sb);
		}else if(type instanceof Date){
			String dateType =new SimpleDateFormat("yyyy-MM-dd").format(type); 
			sb.append("'");
			System.out.println("date  ==" +dateType);
			sb.append(dateType);
			sb.append("'");
			
			return new String(sb);
		}
		return type+"";
	}
	/**
	 * @description 组织好更新的条件
	 * @param conditions
	 * @return
	 */
	private String updateConditionByHql(LinkedHashMap<String,Object> conditions){
		if(conditions == null || conditions.keySet().size()<=0){
			throw new RuntimeException("更新条件不能为空!");
		}
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");
		for(Map.Entry<String, Object> entry:conditions.entrySet()){
			sb.append(" "+entry.getKey()+"="+modifiedField(entry.getValue()));
		}
		System.out.println("更新条件为 "+new String(sb));
		return new String(sb);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<T> findCollectionByConditionNoPageWithCache(String condition,
			final Object[] params, LinkedHashMap<String, String> hashMap) {
		/*
		 * web界面查询用户的信息，where默认为1=1，这样用户即使不选择任何条件,sql查询也不会出错。
		 */
		String hql = "from "+entityClass.getSimpleName() +" o where 1=1";
		String orderby = "";
		//判断是否需要排序
		if(hashMap !=null){
			
			orderby = orderByHql(hashMap);
		}
		//from SystemGroupUser o where 1=1 and o.name like ? and o.principal = ? order by o.id desc 
		final String finalHql = hql + condition + orderby;
		System.out.println("hql : "+hql);
		@SuppressWarnings({ "unchecked" })
		List<T> list = (List<T>) this.getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(finalHql);
				for(int i=0;params!=null && i<params.length;i++){
					query.setParameter(i, params[i]);
				}
				//启用二级缓存存储数据
				query.setCacheable(true);
				return  query.list();
			}
		});
		
		return list;
	}

	@Override
	public void saveConllection(Collection<T> entities) {
		
		this.getHibernateTemplate().saveOrUpdateAll(entities);
		
	}
}
