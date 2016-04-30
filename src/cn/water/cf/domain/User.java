package cn.water.cf.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class User implements java.io.Serializable{

	private String id;					//用户的id
	private String userName;			//用户的网站登录名
	private String realName;			//用户的真实姓名
	private String password;			//用户的密码
	private int gender;					//用户的性别
	private int status;					//用户的状态【单身，恋爱中，结婚】
	private String email;				//用户的电子邮件
	private int qq;						//用户的qq号码
	private int isJoinCommunity;		//用户是否有加入社团
	private int communityId;			//用户参加的社团的id，【外键】
	private Date birthday;				//用户的出身日期
	private String placeOfOrigin;		//用户的籍贯
	private String address;				//用户的现居地
	private String hightSchool;			//用户的毕业院校
	private String university;			//用户的所在的高等院校
	private String profile;				//用户的简介
	private String experience;			//用户的经历
	private int blogInfo;				//用户的博客相关信息的id
	private String questionOne;			//用户忘记密码的问题一
	private String answerOne;			//用户忘记密码的回答一
	private String questionTwo;			//用户忘记密码的问题二
	private String answerTwo;			//用户忘记密码的回答二
	
	//后期添加的字段
	private String headImage;			//用户头像图片存放的url地址
	
	
	
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	//User 对象的静态变量
	public static final int STATUS_SINGLETON = 0;	//单身
	public static final int STATUS_IN_LOVE = 1;		//恋爱中	
	public static final int STATUS_MARRY = 2;		//已婚
	
	public static final int MALE = 0;				//男性
	public static final int FEMALE = 1;				//女性
	
	public static final int HAS_JOIN = 0;
	public static final int HAS_NOT_JOIN = 1;
	
	
	//后期添加的属性
	
	
	//getter and setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getStatus() {
		return status;
	}
	/**
	 * 	<p>该status变量赋值的时候建议使用User类的静态变量</p>
	 * 	<ul>
	 * 		<li>STATUS_SINGLETON 单身</li>
	 * 		<li>STATUS_IN_LOVE   恋爱中</li>
	 * 		<li>STATUS_MARRY     已婚</li>
	 * 	</ul>
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getQq() {
		return qq;
	}
	public void setQq(int qq) {
		this.qq = qq;
	}
	public void setIsJoinCommunity(int isJoinCommunity) {
		this.isJoinCommunity = isJoinCommunity;
	}
	public int getIsJoinCommunity() {
		return isJoinCommunity;
	}
	public int getCommunityId() {
		return communityId;
	}
	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPlaceOfOrigin() {
		return placeOfOrigin;
	}
	public void setPlaceOfOrigin(String placeOfOrigin) {
		this.placeOfOrigin = placeOfOrigin;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHightSchool() {
		return hightSchool;
	}
	public void setHightSchool(String hightSchool) {
		this.hightSchool = hightSchool;
	}
	public String getUniversity() {
		return university;
	}
	public void setUniversity(String university) {
		this.university = university;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public int getBlogInfo() {
		return blogInfo;
	}
	public void setBlogInfo(int blogInfo) {
		this.blogInfo = blogInfo;
	}
	public String getQuestionOne() {
		return questionOne;
	}
	public void setQuestionOne(String questionOne) {
		this.questionOne = questionOne;
	}
	public String getAnswerOne() {
		return answerOne;
	}
	public void setAnswerOne(String answerOne) {
		this.answerOne = answerOne;
	}
	public String getQuestionTwo() {
		return questionTwo;
	}
	public void setQuestionTwo(String questionTwo) {
		this.questionTwo = questionTwo;
	}
	public String getAnswerTwo() {
		return answerTwo;
	}
	public void setAnswerTwo(String answerTwo) {
		this.answerTwo = answerTwo;
	}
	/*------------------增加不需要持久化的属性,不需要进行页面的回显-----------------*/
	private String genderVO;	//性别
	private String statusVO;	//状态

	public String getGenderVO() {
		return genderVO;
	}
	public void setGenderVO(String genderVO) {
		this.genderVO = genderVO;
	}
	public String getStatusVO() {
		return statusVO;
	}
	public void setStatusVO(String statusVO) {
		this.statusVO = statusVO;
	}
	
	
	
	
}
