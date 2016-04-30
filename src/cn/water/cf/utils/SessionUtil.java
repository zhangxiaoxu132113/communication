package cn.water.cf.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.water.cf.domain.User;

public class SessionUtil {

	/**  
	* @Name: addUserToSession
	* @Description: 登录成功后，将用户User对象添加到session中
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-18 （创建日期）
	* @Parameters: request : HttpServletRequest
	* 			   user	   : 用户对象
	*/
	public static void addUserToSession(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
		System.out.println("把user添加到session中");
		session.setAttribute("globle_user", user);
		
	}
	/**  
	* @Name: removeGlobelUser
	* @Description:移除session的用户对象
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-20 （创建日期）
	* @Parameters: request : HttpServletRequest
	*/
	public static void removeGlobelUser(HttpServletRequest request){
		request.getSession().removeAttribute("globle_user");
		System.out.println("清空了session的用户对象");
	}
	/**  
	* @Name: getGlobelUser
	* @Description:获取session的用户对象
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-27（创建日期）
	* @Parameters: request : HttpServletRequest
	*/
	public static User getGlobelUser(HttpServletRequest request){
		return (User) request.getSession().getAttribute("globle_user");
	}
	/**  
	* @Name: getSession
	* @Description:获取HttpSession对象
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-21 （创建日期）
	* @Parameters: request : HttpServletRequest
	*/
	public static HttpSession getSession(HttpServletRequest request){
		return request.getSession();
	}

}
