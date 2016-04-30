package cn.water.cf.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public interface ICommonDao<T> {

	/**
	 * <p>保存一条数据 </p>
	 */
	void save(T entity);
	/**
	 * 
	 * <p>保存多条数据 </p>
	 */
	void saveConllection(Collection<T> entities);
	/**
	 * <p>删除一条数据 </p>
	 */
	void deleteById(Serializable id);
	
	/**
	 * <p>根据id数组删除多条数据 </p>
	 */
	void deleteByIds(Collection<T> entities);
	
	/**
	 * <p>更新一条数据 </p>
	 */
	void update(T entity);
	/**
	 * <p>更新一条数据 </p>
	 */
	void partialRenewal(List<Object>keys,List<Object>params ,LinkedHashMap<String,Object> conditions);
	
	/**
	 * <p>根据id查看一条数据 </p>
	 */
	T findEntityById(Serializable id);
	
	/**
	 * <p>根据id数组查找多条数据 </p><br/>
	 * <P>由于数据库里面的实体是不能够重复的，所以这里使用Set集合</p>
	 */
	Set<T> findEntitiesByIds(Serializable ... ids);
	
	/**
	 * 
	 * <p>查找所有对象</p>
	 */
	Collection<T> findAll();
	/**
	 * 
	 *<p>根据多个条件查询数据</p>
	 */
	List<T> findCollectionByConditionNoPage(String condition,
			Object[] params, LinkedHashMap<String, String> hashMap);
	/**
	 * 
	 *<p>根据多个条件查询数据,并且使用了二级缓存</p>
	 */
	List<T> findCollectionByConditionNoPageWithCache(String condition,
			Object[] params, LinkedHashMap<String, String> hashMap);
}
