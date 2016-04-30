package cn.water.cf.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
	
	/*private static Class clazz = GenericSuperClass.getActualTypeClass(this.getClass());*/
	private HibernateUtils(){}
	
	
	public static int partialRenewal(Object[]keys,final Object[] params, Object[] condition) {
		//字段和实际的值数量要一致
		if(keys.length!=params.length){
			throw new RuntimeException("字段的数量和更新的值的数量不一致！");
		}
		//定义查询的语句
		/*String hql = "update "+getTableName4ClassName()+" set ";*/
		String hql = "";
		//组织查询语句的关键字段
		for(int i=0; i<keys.length; i++){
			if(i==0){
				hql += ""+keys[i]+"= ' "+params[i] +"'";
			}else{
				hql +=","+keys[i]+"= ' "+params[i] +"'";
			}
		}
		hql +=" where id="+condition[0];
		final String executeSQL = hql;
		System.out.println("hql :"+hql);
		
		getSession().beginTransaction();
		
		SQLQuery query = getSession().createSQLQuery(executeSQL);
		int result = query.executeUpdate();
		
		/*getSession().getTransaction().commit();*/
		getSession().close();
		
		return result;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	private static String getTableName4ClassName(Class clazz){
		String className = clazz.getSimpleName();
		StringBuffer sb = new StringBuffer();
		sb.append("t_");
		if(className.length()<=0){
			throw new RuntimeException("字段的长度不能为零");
		}
		
		String[] split = className.split("");
		for(int i=0;i<split.length;i++){
			if(i==0){
				split[0].toLowerCase();
				sb.append(split[i]);
			}else{
				sb.append(split[i]);
			}
		}
		
		return new String(sb);
	}
	
	public static Session getSession(){
		Configuration conf = new Configuration();
		conf.configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		
		return session;
	}
}
