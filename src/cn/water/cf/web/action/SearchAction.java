package cn.water.cf.web.action;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.water.cf.domain.User;
import cn.water.cf.service.IUserService;
import cn.water.cf.utils.CharUtil;
import cn.water.cf.web.form.SearchForm;;
/**
 * 
 * @author mrwater
 * @description 专门处理用户搜索的内容的
 */
@SuppressWarnings("serial")
@Controller("searchAction")
@Scope(value="prototype")
public class SearchAction extends BaseAction<SearchForm> {
	
	@Resource(name=IUserService.SERVICE_NAME)
	private IUserService userService;
	/**  
	* @throws Exception 
	 * @Name: search
	* @Description: 根据内容来搜索内容
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-11-19 （创建日期）
	* @Reture : null
	*/
	public String search() throws Exception{
		//获取得到用户输入的关键字
		String searchKey = request.getParameter("searchKey");
		//判断一下字符串中是否有中文字符，
		if(CharUtil.isChinese(searchKey)){//如果有则先到mysql数据库中查询
			System.out.println("有中文字符"+searchKey);
			//根据关键字得到搜索的内容
			List<User> userList = userService.searchUserBySearchKey(searchKey);
			if(userList != null){
				//将数据以json的格式发送到浏览器
				//HttpServletResponse response = ServletActionContext.getResponse();
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				ServletOutputStream outputStream = response.getOutputStream();
				//将图片以json格式输出到客户端
				//JSONObject json = JSONObject.fromObject(userList);//将java对象转换为json对象
				JSONArray json = JSONArray.fromObject(userList);
				String str = json.toString();//将json对象转换为字符串
				System.out.println(str);
				outputStream.write(str.getBytes("UTF-8"));
				System.out.println("发送数据完毕");
			}
			return null;
			
		}else{//如果没有则到redis中查询
			System.out.println("没有中文字符"+searchKey);
		}
		return null;
	}

}
