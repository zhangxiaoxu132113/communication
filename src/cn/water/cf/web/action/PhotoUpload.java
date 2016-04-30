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
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import cn.water.cf.domain.User;
import cn.water.cf.service.IUserService;
import cn.water.cf.web.form.Photo;
@Controller("photoUpload")
@Scope(value="prototype")
@SuppressWarnings("serial")
public class PhotoUpload extends BaseAction<Photo>{
	
	//注入用户操作的业务对象
	@Resource(name=IUserService.SERVICE_NAME)
	private IUserService userService ;
	
	
	/**  
	* @Name: execute
	* @Description: 上传用户头像的图片到本地文件系统
	* @Author: 张淼洁（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2015-06-23 （创建日期）
	* @Reture : null
	*/
	public String execute(){
		
		HttpSession session = request.getSession();
		System.out.println("execute photo upload!");
		//获得username
		User user = (User)session.getAttribute("globle_user");
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
		//将头像图片的路径保存到user用户表中
		/*userService.addHeadImageUrl(relateImageUrl,user.getId());*/
		user.setHeadImage(relateImageUrl);
		userService.addHeadImageUrl(relateImageUrl, user.getId());
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
			//当图片上传到本地文件夹之后，将用户头像图片添加到session中
			session.setAttribute("imageUrl", relateImageUrl);
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
}
