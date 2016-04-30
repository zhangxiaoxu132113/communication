package cn.water.cf.web.action;


import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.water.cf.domain.User;
import cn.water.cf.service.IUserService;
import cn.water.cf.utils.MD5Encrypt;
import cn.water.cf.utils.SessionUtil;
@Controller("registerAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class RegisterAction extends BaseAction<User>{
	
	@Resource(name=IUserService.SERVICE_NAME)
	private IUserService userService;
	/**  
	* @Name: register
	* @Description: 用户注册
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-18 （创建日期）
	* @Reture : tipInfo 返回到提示信息页面	
	*/
	public String register(){
		
		//获取前台用户输入的密码，并进行加密。
		String md5Pwd = MD5Encrypt.MD5Encode(this.valueObject.getPassword());
		this.valueObject.setPassword(md5Pwd);
		//调用业务层的方法，注册用户信息
		//如果返回为真，则跳转到完善用户信息的页面，如果错误则返回到登录注册页面
		boolean flag = userService.registerUser(this.valueObject);
		if(flag){
			request.setAttribute("tip_word", "注册成功！");
			request.setAttribute("content", "恭喜你，注册成功，现在返回到完善用户资料页面，<br/>"
					+"来完善你的个人信息吧！");
			request.setAttribute("url", "userAction_editInfoUI.do");
		}else{
			request.setAttribute("tip_word", "注册失败！");
			request.setAttribute("content", "请重新注册一次！");
			request.setAttribute("url", "loginAction_index.do");
		}
		//将用户对象添加到session，方法完善用户信息的页面获取到该用户的相关信息
		SessionUtil.addUserToSession(request, this.valueObject);
		return "tipInfo";
	}
}
