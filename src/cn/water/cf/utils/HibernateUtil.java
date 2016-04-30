package cn.water.cf.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	private  static final HibernateUtil util = new HibernateUtil();
	
	//将构造函数设置为私有的
	private HibernateUtil(){
		
	}
	public static HibernateUtil getHibernateUtil(){
		return util;
	}

	/**
	 * @description 将对象转换为数据库表的名称
	 * @param clazz 需要
	 * @return
	 */
	public String getTableName4ClassName(@SuppressWarnings("rawtypes") Class clazz){
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
	/**
	 * @description:获取session对象
	 * @return
	 */
	public static Session getSession(){
		Configuration conf = new Configuration();
		conf.configure();
		SessionFactory sf = conf.buildSessionFactory();
		Session session = sf.openSession();
		
		return session;
	}
	/**
	 * @description:关闭session对象
	 * @return
	 */
	public static void closeSession(Session session){
		if(session != null){
			session.close();
		}
	}
}
