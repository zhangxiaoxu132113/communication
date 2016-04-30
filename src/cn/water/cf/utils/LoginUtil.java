package cn.water.cf.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class LoginUtil {

	/**
	 * 
	 * @param userName	： 用户名
	 * @param password	： 加密过后的密码
	 * @param request	：HttpServletRequest对象
	 * @param response	：HttpServletResponse对象
	 */
	public static void addCookie(String userName, String password,
			HttpServletRequest request, HttpServletResponse response) {
		if(StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password)){
			//创建Cookie对象，并创建Cookie的父路径
			Cookie userNameCookie = new Cookie("userName", userName);
			Cookie pwdCookie = new Cookie("password", password);
			
			userNameCookie.setPath(request.getContextPath()+"/");
			pwdCookie.setPath(request.getContextPath()+"/");
			//根据numberMe的值，来判断cookie的生命周期
			String rememberMe = request.getParameter("rememberMe");
			if(rememberMe!=null && rememberMe.equals("yes")){
				userNameCookie.setMaxAge(7*24*60*60);
				pwdCookie.setMaxAge(7*24*60*60);
			}else{
				userNameCookie.setMaxAge(0);
				pwdCookie.setMaxAge(0);
			}
			response.addCookie(userNameCookie);
			response.addCookie(pwdCookie);
		}
	}

}
