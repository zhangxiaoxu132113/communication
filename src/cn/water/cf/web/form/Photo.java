package cn.water.cf.web.form;

import java.io.File;

@SuppressWarnings("serial")
public class Photo implements java.io.Serializable {

	private File myFile;				//上传的文件
	private String myFileContentType;	//上传的文件类型
	private String myFileFileName;		//上传的文件名称
	
	public File getMyFile() {
		return myFile;
	}
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	public String getMyFileContentType() {
		return myFileContentType;
	}
	public void setMyFileContentType(String myFileContentType) {
		this.myFileContentType = myFileContentType;
	}
	public String getMyFileFileName() {
		return myFileFileName;
	}
	public void setMyFileFileName(String myFileFileName) {
		this.myFileFileName = myFileFileName;
	}
	
}
