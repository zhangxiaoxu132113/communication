package cn.water.cf.web.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.water.cf.utils.GenericSuperClass;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 * @description:该类封装了ActionSupport和提供了模型驱动和request，reponse对象的获取的接口
 * 				web/action的action对象可以通过集成该对象，实现了继承和多态的效果。
 * @author 张淼洁
 * @version 1.0
 */
@SuppressWarnings("serial")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> ,ServletRequestAware,ServletResponseAware{

	protected String home = HOME;
	protected String edit = EDIT;
	protected String success = SUCCESS;
	protected String faile = FAILE;
	
	private static final String HOME   	= "home";
	private static final String EDIT  	= "edit";
	private static final String SUCCESS 	= "success";
	private static final String FAILE 	= "faile";
	
	
	@SuppressWarnings("rawtypes")
	private Class clazz;
	public T valueObject;
	
	//定义HttpServletQuest和HttpServletResponse对象
	protected HttpServletResponse response;
	protected HttpServletRequest request;
	
	//在构造函数中实现泛型对象的初始化
	@SuppressWarnings("unchecked")
	public BaseAction(){
		try {
			this.clazz = GenericSuperClass.getActualTypeClass(this.getClass());
			this.valueObject = (T) this.clazz.newInstance();
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public T getModel() {
		return valueObject;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

}
