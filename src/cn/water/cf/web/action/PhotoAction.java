package cn.water.cf.web.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.water.cf.domain.User;
import cn.water.cf.domain.blog.album.Album;
import cn.water.cf.domain.blog.album.Photo;
import cn.water.cf.service.IPhotoService;
import cn.water.cf.utils.SessionUtil;

@Controller("photoAction")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class PhotoAction extends BaseAction<cn.water.cf.web.form.Photo>{

	@Resource(name=IPhotoService.SERVICE_NAME)
	private IPhotoService photoService;
	
	/**  
	* @Name: photoUpload
	* @Description:展示相册中所有的相片
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-06（创建日期）
	* @Reture : home /WEB-INF/page/blogindex.jsp
	*/
	public String photoUpload(){
		//获得username
		HttpServletRequest request = ServletActionContext.getRequest();
		User user = (User)SessionUtil.getGlobelUser(request);
		String username = user.getUserName();
		
		//设置文件保存目录
		String photoPath = 
			ServletActionContext.getServletContext().getRealPath("/user/photo/" + username);
		File filePhotoPath = new File(photoPath);
		//创建文件夹
		if(!filePhotoPath.isDirectory()) {
			filePhotoPath.mkdirs();
		}
		
		//解决中文文件名问题
		String extension = FilenameUtils.getExtension(this.valueObject.getMyFileFileName());
		String filename = UUID.randomUUID().toString() + "."+ extension;
		
		//设置目标文件
		File tofile = new File(photoPath,filename);
		//获取上传的头像图片的存放的完整路径
		String imageUrl = photoPath+File.separator+filename;
		String relateImageUrl= "user"+File.separator+"photo"+File.separator+username+File.separator+filename;

		System.out.println("图片的相对的路径："+relateImageUrl);
		//替换斜杠，从而符合服务器的规范
		imageUrl = imageUrl.replace('\\', '/');
		relateImageUrl = relateImageUrl.replace('\\', '/');

		//定义输出流和输入流
		InputStream is = null;
		OutputStream os = null;
		try {
			//创建一个输入流
			is = new BufferedInputStream( new FileInputStream(this.valueObject.getMyFile()));
			//使用输出流来包装目标文件
			os = new BufferedOutputStream(new FileOutputStream(tofile));
			byte[] buffer = new byte[1024];
			int length = 0;
			while((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			
			//创建相片
			Photo photo = new Photo();
			String album_id = request.getParameter("album_id");
			Album album = new Album();
			album.setAlbum_id(Long.parseLong(album_id));
			photo.setAlbum_id(album);
			photo.setDescription("photo");
			photo.setUploadDate(new Date());
			photo.setUrl(relateImageUrl);
			photo.setUser_id(user.getId());
			//调用业务层的方法保存photo信息
			photoService.savePhoto(photo);
			request.getSession().setAttribute("photo", photo);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					//关闭输出流
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(is != null){
						try {
							//关闭输入流
							is.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}//关闭流的操作
		}
		return null;
	}
	
	/**  
	* @Name: getPhoto
	* @Description:获取图片的信息
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-07-07（创建日期）
	* @Reture : null
	*/
	/*public String getPhoto() throws Exception{
		
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
			如果等待的时间超过了10秒，则认为访问失效
			if(waitCount >=10){
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

		request.getSession().removeAttribute("photo");
		
		return null;
	}*/
}
