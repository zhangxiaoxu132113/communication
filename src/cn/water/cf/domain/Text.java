package cn.water.cf.domain;

import java.util.Date;
/**
 * 
 * @author 		zhangmiaojie
 * @date   		2015-06-05
 * @description 用来测试底层框架是否搭建成功的一个测试对象
 *
 */
@SuppressWarnings("serial")
public class Text implements java.io.Serializable{
	
	//the attribute 
	private String id;			//测试对象的id
	private String testName;	//测试对象的名称
	private Date testDate;		//测试对象的日期
	private String testRemark;	//测试的备注
	
	//setter and getter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public String getTestRemark() {
		return testRemark;
	}
	public void setTestRemark(String testRemark) {
		this.testRemark = testRemark;
	}
}
