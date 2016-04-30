package cn.water.cf.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.ServletActionContext;

import cn.water.cf.domain.User;
import cn.water.cf.web.form.Photo;

public class PhotoUploadUtil {
	
	public static String uploadPhoto(User user,Photo photo){
		
		String username = user.getUserName();

		//设置文件保存目录
		String photoPath = 
			ServletActionContext.getServletContext().getRealPath("/user/photo/" + username);
		File filePhotoPath = new File(photoPath);
		System.out.println(filePhotoPath.getPath());
		if(!filePhotoPath.isDirectory()) {
			filePhotoPath.mkdirs();
		}
		
		//解决中文文件名问题
		String extension = FilenameUtils.getExtension(photo.getMyFileFileName());
		String filename = UUID.randomUUID().toString() + "."+ extension;
		
		//设置目标文件
		File tofile = new File(photoPath,filename);
		//获取上传的头像图片的存放的完整路径
		String imageUrl = photoPath+File.separator+filename;
		String relateImageUrl= "user"+File.separator+"photo"+File.separator+username+File.separator+filename;

		System.out.println("图片的完整的路径："+imageUrl);
		System.out.println("图片的相对的路径："+relateImageUrl);
		imageUrl = imageUrl.replace('\\', '/');
		relateImageUrl = relateImageUrl.replace('\\', '/');
		//将头像图片的路径保存到user用户表中
		/*userService.addHeadImageUrl(relateImageUrl,user.getId());*/
		InputStream is = null;
		OutputStream os = null;
		try {
			//创建一个输入流
			is = new FileInputStream(photo.getMyFile());
			//使用输出流来包装目标文件
			os = new FileOutputStream(tofile);
			byte[] buffer = new byte[1024];
			int length = 0;
			while((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			//当图片传输完毕的时候将图片的url返回
			return relateImageUrl;
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
	
	public static void savePhotoForm(HttpServletRequest request,Photo photo){
		HttpSession session = request.getSession();
		session.setAttribute("photoForm", photo);
	}
	
	public static Photo getPhotoForm(HttpServletRequest request){
		return (Photo)request.getSession().getAttribute("photoForm");
		
	}

}
