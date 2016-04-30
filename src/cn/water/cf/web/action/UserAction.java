package cn.water.cf.web.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.water.cf.domain.SystemDDL;
import cn.water.cf.domain.User;
import cn.water.cf.domain.status.Status;
import cn.water.cf.service.IStatusService;
import cn.water.cf.service.ISystemDDLService;
import cn.water.cf.service.IUserService;
import cn.water.cf.utils.SessionUtil;
@Controller("userAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class UserAction extends BaseAction<User>{
	
	public static String fail_code = "{falg:0}";
	public static String success_code = "{falg:1}";
	
	public String FAIL_CODE = fail_code;
	public String SUCCESS_CODE = success_code;
	
	
	//注入用户对象的service业务层
	@Resource(name=IUserService.SERVICE_NAME)
	private IUserService userService;
	//创建数据字典业务对象
	@Resource(name=ISystemDDLService.SERVICE_NAME)
	private ISystemDDLService systemDDLService;
	//注入微博操作的service业务层
	@Resource(name=IStatusService.SERVICE_NAME)
	private IStatusService statusService;
	
	/**  
	* @Name: editInfoUI
	* @Description: 跳转到编辑用户信息的页面
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-20 （创建日期）
	* @Reture : editInfo 返回到完善个人信息的页面
	*/
	public String editInfoUI(){
		User user = (User)request.getSession().getAttribute("globle_user");
		user = userService.findUserByNameAndPwd(user.getUserName(), user.getPassword());
		SessionUtil.removeGlobelUser(request);
		//查询数据字段，对相应的数据进行转换
		SystemDDL genderDDL = systemDDLService.findSystemDDLbyCondition("gender", user.getGender());
		user.setGenderVO(genderDDL.getDdlName());
		SessionUtil.addUserToSession(request, user);
		return "editInfo";
	}
	/**  
	* @Name: editUserInfo
	* @Description: 完善用户的个人的资料
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-20 （创建日期）
	* @Reture : home 返回用户个人中心的主页	
	*/
	public String editUserInfo(){
		//调用业务层的方法更新用户信息Perfection Profile
		//调用业务层的方法，完善用户资料页面
		userService.perfectionProfile(this.valueObject);
		//将session中旧的用户对象移除，然后添加信息的用户对象
		SessionUtil.removeGlobelUser(request);
		SessionUtil.addUserToSession(request, this.valueObject);
		System.out.println("完善用户资料！");
		return "returnHome";
	}
	/**  
	* @throws IOException 
	 * @Name: getHeadImage
	* @Description: 获取用户的头像图片的存放位置
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-23 （创建日期）
	* @Reture : null
	*/
	public String getHeadImage() throws IOException{
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//获取用户的头像图片的url地址
		String headImageUrl = (String)request.getSession().getAttribute("imageUrl");

		//设置response响应的数据的编码格式
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		ServletOutputStream outputStream = response.getOutputStream();
		boolean flag = true;
		int waitCount = 0;
		while(flag){
			//判断用户头像的url地址是否为空，如果不为空，则跳出循环
			if(headImageUrl != null && !headImageUrl.equals("")){
				flag = false;
			}
			/*如果等待的时间超过了10秒，则认为访问失效*/
			if(waitCount >=10){
				flag = false;
				outputStream.write("等待时间过长，请重新上传图片！".getBytes("UTF-8"));
				return null;
			}
			try {
				Thread.sleep(1000);
				waitCount++;
				//从session中获取得到用户头像的url地址
				headImageUrl = (String)request.getSession().getAttribute("imageUrl");
				System.out.println(waitCount+"次获取得到的"+headImageUrl);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//将图片的url地址输出到客户端
		outputStream.write(headImageUrl.getBytes("UTF-8"));
		/**
		 * 当服务器将用户头像的url的地址发送到了客户端的时候，
		 * 讲session的imageUrl的属性的值删除掉，
		 * 避免第二次上传图片时请求的是上次一次的session保存的图片url地址
		 */
		request.getSession().removeAttribute("imageUrl");
		
		return null;
	}
	/**  
	* @throws IOException 
	* @Name: findFriend
	* @Description: 根据id跳转到好友的home页面
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-11-11 （创建日期）
	* @Reture : friendHome 好友的home页面
	*/
	public String findFriend(){
		System.out.println("findFriend");
		String userId = (String)request.getParameter("userId");
		User user = userService.findUserById(userId);
		
		request.setAttribute("friend", user);
		//return null;
		return "friendHome";
	}
	/**  
	* @throws IOException 
	* @Name: sendStatus
	* @Description: 发表用户微博
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2016-02-04 （创建日期）
	* @Reture : json 发送状态码 
	* 			null 不跳转页面
	*/
	public String sendStatus() throws Exception{
		//获取用户发表的微博内容
		String text = request.getParameter("text");
		System.out.println("sendStataus --- "+text);
		User user = SessionUtil.getGlobelUser(request);
		Status status = new Status();
		status.setText(text);
		status.setUser(user);
		System.out.println("userid ===  "+user.getId());
		status.setAttitudes_count(0);
		status.setContents_count(0);
		status.setReposts_count(0);
		status.setThumbnail_pic("dfsdkf,jpg");
		Date created_at = new Date();
		status.setCreated_at(created_at);
		
		//TODO 暂时不实现图片上传的功能
		statusService.saveStatus(status);
		
		// 创建JSON对象,将json发送到客户端
		JSONObject json = JSONObject.fromObject(status);
		System.out.println(json.toString());
		response.getOutputStream().write(json.toString().getBytes("utf-8"));
		return null;
	}
}
