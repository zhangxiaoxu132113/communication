package cn.water.cf.utils;

import java.lang.reflect.ParameterizedType;
/**
 * 
 * @author zhangmiaojie
 *
 */
public class GenericSuperClass {
	/**
	 * 
	 * @param clazz
	 * @return class对象
	 * @description 将泛型转换为class类对象
	 */
	@SuppressWarnings("rawtypes")
	public static Class getActualTypeClass(Class clazz){
		ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
		Class entityClass = (Class) type.getActualTypeArguments()[0];
		return entityClass;
	}

}
