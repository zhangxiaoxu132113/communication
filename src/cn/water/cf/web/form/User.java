package cn.water.cf.web.form;

import java.util.Date;

@SuppressWarnings("serial")
public class User implements java.io.Serializable{

	private String id;					//用户的id
	private String userName;			//用户的网站登录名
	private String realName;			//用户的真实姓名
	private String password;			//用户的密码
	private String gender;					//用户的性别
	private String status;					//用户的状态【单身，恋爱中，结婚】
	private String email;				//用户的电子邮件
	private String qq;						//用户的qq号码
	private String isJoinCommunity;		//用户是否有加入社团
	private String communityId;			//用户参加的社团的id，【外键】
	private Date birthday;				//用户的出身日期
	private String placeOfOrigin;		//用户的籍贯
	private String address;				//用户的现居地
	private String hightSchool;			//用户的毕业院校
	private String university;			//用户的所在的高等院校
	private String profile;				//用户的简介
	private String experience;			//用户的经历
	private String blogInfo;				//用户的博客相关信息的id
	private String questionOne;			//用户忘记密码的问题一
	private String answerOne;			//用户忘记密码的回答一
	private String questionTwo;			//用户忘记密码的问题二
	private String answerTwo;			//用户忘记密码的回答二
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getIsJoinCommunity() {
		return isJoinCommunity;
	}
	public void setIsJoinCommunity(String isJoinCommunity) {
		this.isJoinCommunity = isJoinCommunity;
	}
	public String getCommunityId() {
		return communityId;
	}
	public void setCommunityId(String communityId) {
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
	public String getBlogInfo() {
		return blogInfo;
	}
	public void setBlogInfo(String blogInfo) {
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
	
	
}
