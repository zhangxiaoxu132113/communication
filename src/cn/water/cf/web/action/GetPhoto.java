package cn.water.cf.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.water.cf.domain.User;
import cn.water.cf.service.IUserService;
@Controller("getPhoto")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class GetPhoto extends BaseAction<User>{

	@Resource(name=IUserService.SERVICE_NAME)
	private IUserService userService;
	
	public String execute() throws Exception{
		//根据用户的id查询用户信息
		String userId = (String) request.getParameter("id");
		System.out.println("user id = " + userId);
		User user = userService.findUserById(userId);
		// 获取用户的头像图片的url地址
		String headImageUrl = user.getHeadImage();
		System.out.println("图片的url = " + headImageUrl);
		// 设置response响应信息的乱码问题，并输出到前台中
		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(headImageUrl);
		return null;
		
	}
}
