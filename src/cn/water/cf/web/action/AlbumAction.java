package cn.water.cf.web.action;


import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.water.cf.domain.User;
import cn.water.cf.domain.blog.album.Album;
import cn.water.cf.domain.blog.album.Photo;
import cn.water.cf.service.IAlbumService;
import cn.water.cf.service.IPhotoService;
import cn.water.cf.utils.SessionUtil;
import cn.water.cf.web.form.Page;
import cn.water.cf.web.form.Result;
@Controller("albumAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class AlbumAction extends BaseAction<Album>{
	//创建相册的业务逻辑层对象
	@Resource(name=IAlbumService.SERVICE_NAME)
	private IAlbumService albumService;
	//创建图片的业务逻辑层对象
	@Resource(name=IPhotoService.SERVICE_NAME)
	private IPhotoService photoService;
	
	/**  
	* @Name: createAlbum
	* @Description:转发到博客相册展示页面
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-05（创建日期）
	* @Reture : blog/BlogAction_photoUI
	*/
	public String createAlbum(){
		
		User user = SessionUtil.getGlobelUser(request);
		this.valueObject.setUser(user);
		this.valueObject.setCreateTime(new Date());
		this.valueObject.setLinkAddr("linked adress");
		//调用业务层的方法创建相册
		albumService.createAlbum(this.valueObject);
		
		return "photoUI";
	}
	/**  
	* @Name: showAlbum
	* @Description:展示相册中所有的相片
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-06（创建日期）
	* @Reture : home /WEB-INF/page/blogindex.jsp
	*/
	@SuppressWarnings("unchecked")
	public String showAlbum(){
		//通过分页来显示图片
		String currentPage = request.getParameter("currentPage");
		int currentPageInt = 1;
		if(currentPage != null && currentPage != ""){
			currentPageInt = Integer.parseInt(currentPage);
		}
		//创建page对象
		Page page = new Page();
		page.setCurrentPage(currentPageInt);
		page.setEveryPage(18);
		
		String album_id = request.getParameter("album_id");
		
		//List<Photo> photoList = albumService.findAllPhotoByAlbum_id(Long.parseLong(album_id));
		Result result = albumService.findPhotoByPage(album_id,page);
		List<Photo> photoList = result.getList();
		page = result.getPage();
		request.setAttribute("photoList", photoList);
		request.setAttribute("album_id", album_id);
		request.setAttribute("page", page);
		return "showAllPhoto";
	}
	/**  
	* @Name: getPhoto
	* @Description:获取图片的信息
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-07（创建日期）
	* @Reture : null
	*/
	public String getPhoto() throws Exception{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//获取session中保存的图片对象
		Photo photo = (Photo)request.getSession().getAttribute("photo");

		//设置response响应的数据的编码格式
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		ServletOutputStream outputStream = response.getOutputStream();
		boolean flag = true;
		int waitCount = 0;
		while(flag){
			//判断图片对象是否为空，如果不为空，则跳出循环
			if(photo != null){
				flag = false;
			}
			/*如果等待的时间超过了10秒，则认为访问失效*/
			if(waitCount >=20){
				flag = false;
				outputStream.write("等待时间过长，请重新上传图片！".getBytes("UTF-8"));
				return null;
			}
			try {
				Thread.sleep(1000);
				waitCount++;
				//获取session中保存的图片对象
				photo = (Photo)request.getSession().getAttribute("photo");
				System.out.println(waitCount+"次获取得到的"+photo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//将图片以json格式输出到客户端
		JSONObject json = JSONObject.fromObject(photo);//将java对象转换为json对象
		String str = json.toString();//将json对象转换为字符串
		System.out.println(str);
		outputStream.write(str.getBytes("UTF-8"));
		//outputStream.write(str.getBytes("UTF-8"));
		System.out.println("发送数据完毕");
		request.getSession().removeAttribute("photo");
		
		return null;
	}
	/**  
	* @Name: setFrontCover
	* @Description:修改封面
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-08（创建日期）
	* @Reture : null
	*/
	public String setFrontCover() throws Exception{
		
		String album_id = request.getParameter("album_id");
		String url = request.getParameter("url");
		boolean falg = albumService.updateFrontCover(Long.parseLong(album_id),url);
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		ServletOutputStream outputStream = response.getOutputStream();
		//PrintWriter out = response.getWriter();
		if(falg){
			outputStream.write("删除成功！".getBytes("UTF-8"));
			//out.write("设置成功！");
		}else {
			outputStream.write("删除失败！".getBytes("UTF-8"));
			//out.write("设置失败！");
		}
		return null;
	}
	/**  
	* @Name: deletePhoto
	* @Description:删除照片
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-08（创建日期）
	* @Reture : 重定向到显示照片的页面
	*/
	public String deletePhoto() {
		String photo_id = request.getParameter("photo_id");
		if(photo_id != null && !"".equals(photo_id)){
			photoService.delPhotoById(Long.parseLong(photo_id));
		}
		return this.success;
	}
}
