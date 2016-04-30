package cn.water.cf.web.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.water.cf.domain.SystemDDL;
import cn.water.cf.domain.User;
import cn.water.cf.service.ISystemDDLService;
import cn.water.cf.service.IUserService;
import cn.water.cf.utils.LoginUtil;
import cn.water.cf.utils.MD5Encrypt;
import cn.water.cf.utils.SessionUtil;
@Controller("loginAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class LoginAction extends BaseAction<User>{
	//创建用户业务对象
	@Resource(name=IUserService.SERVICE_NAME)
	private IUserService userService;
	//创建数据字典业务对象
	@Resource(name=ISystemDDLService.SERVICE_NAME)
	private ISystemDDLService systemDDLService;
	/**  
	* @Name: login
	* @Description: 处理用户登录
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-18 （创建日期）
	* @Reture : login 登录失败，返回到登录页面	
	* 			home  登录成功，跳转到home主页面	
	*/
	public String login(){
		
		//获取页面用户输入的用户名和密码，并对密码进行加密处理
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("password");
		String md5Pwd = MD5Encrypt.MD5Encode(pwd);
		//调用业务层的方法查找数据库是否存在一条数据
		User user = userService.findUserByNameAndPwd(userName,md5Pwd);
		if(user==null) {
			this.addFieldError("errorInfo", "用户名或密码输入错误，请再输入一遍！");
			return "login";
		}
		
		//处理用是否记住密码，增加cookie功能
		//String rememberMe = request.getParameter("rememberMe");
		//System.out.println("是否记住我："+rememberMe);
		try {
			userName = URLEncoder.encode(userName, "UTF-8");
			LoginUtil.addCookie(userName,pwd,request,response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//查询数据字段，对相应的数据进行转换
		SystemDDL genderDDL = systemDDLService.findSystemDDLbyCondition("gender", user.getGender());
		user.setGenderVO(genderDDL.getDdlName());
		SystemDDL statusDDL = systemDDLService.findSystemDDLbyCondition("status", user.getStatus());
		user.setStatusVO(statusDDL.getDdlName());
		
		//登录成功，并将对象添加到session中
		SessionUtil.addUserToSession(request,user);
		
		
		
		
		return "home";
		
	}
	public String index(){
		return "login";
	}
}
