<!DOCTYPE html>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<% 
	String userName = "";
	String password = "";
	String checked = "";
	Cookie[]cookies = request.getCookies();
	//判断cookie是否为null，且长度是否小于1
	if(cookies!=null && cookies.length>0){
		for(int i=0;i<cookies.length;i++){
			Cookie cookie = cookies[i];
			if("userName".equals(cookie.getName())){
				userName = URLDecoder.decode(cookie.getValue(), "UTF-8");
				checked = "checked";
			}
			if("password".equals(cookie.getName())){
				password = cookie.getValue();
			}
		}
	}else{
		checked = "";
	}
%>
<html>
<head>
<title>登录-分享，联系，与交流</title>
<meta charset="utf-8" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/login.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.9.1.min.js"></script>
</head>
<body>
	<div id="container">
		<div id="header">
			<!-- 显示网站的logo -->
			<div id="logo">
				<h1>Communication Platform</h1>
			</div>
			<!-- end -->
			<!-- 填写用户登录信息的div -->
			<div id="login_form">
				<form id="form1" name="form1" method="post">
					<div class="input_info">
						<!-- account:<br>  -->
						<input id="account" name="userName" type="text" value="<%= userName %>"
							placeholder="手机，邮箱地址或用户名" /><br /> <input type="checkbox"
							id="remenber" value="yes" name="rememberMe" checked="<%= checked %>"/> 记住密码

					</div>
					<div class="input_info">
						<!-- password:<br>  -->
						<input id="password" name="password" type="password"
							placeholder="密码"  value="<%= password %>" /> <br /> <span class="color_theme1"><a
							href="www.baidu.com">忘记密码</a> </span>
					</div>
					<div class="input_info">
						<input type="button" id="login" value="登录" onclick="login1()" />
					</div>
				</form>
			</div>
			<!-- end -->
		</div>
		<!-- end页面头部 -->
		<div id="main">
			<div id="show_intro">
				<h2>欢迎使用Communication。</h2>
				<p>
					在大学里发现你的志同道合的朋友，联系你的朋友一起分享与交流。<br>每一次的分享与交流都是你在大学生活中的点点滴滴。
				</p>
			</div>
			<!-- 用户注册信息的div -->
			<div id="register_form">
				<h1>注册</h1>
				<h2>永久免费</h2>
				<form id="form2" name="form2" method="post">
					<!-- 用户输入的信息，使用css表格样式来表现 -->
					<div id="dis_table">
						<div class="dis_row">
							<span class="dis_cell">用户姓名 </span><input type="text"
								id="userName" name="userName" />
						</div>
						<div class="dis_row">
							<span class="dis_cell">QQ账号</span><input type="text" id="qq"
								name="qq"/>
						</div>
						<div class="dis_row">
							<span class="dis_cell">电子邮箱 </span><input type="text" id="email"
								name="email"/>
						</div>
						<div class="dis_row">
							<span class="dis_cell">密码</span><input type="password"
								id="password" name="password" />
						</div>
						<div class="dis_row">
							<span class="dis_cell">性别</span> &nbsp;&nbsp; <input type="radio"
								name="sex" value="male" checked="checked" />男&nbsp;&nbsp; <input
								type="radio" name="sex" value="female" />女
						</div>
					</div>
					<p>
						点击注册即表明你同意我们的<span class="color_theme2">条款</span>且你已阅读过我们的<span
							class="color_theme2">数据<br> 使用政策</span>，包括我们的<span
							class="color_theme2">Cookie使用政策</span>。
					</p>
					<input type="image" id="register" name="register" 
						src="${pageContext.request.contextPath }/image/register_bt.png" onclick="register()" />
				</form>
			</div>
			<div class="clear"></div>
		</div>

		<!-- 页面的尾部信息 -->
		<div id="footer">
			<p>copyright@ mrwater company</p>
			<p>
				<a href="#">关于</a> <a href="#">帮助</a> <a href="#">博客</a> <a href="#">工作机会</a>
				<a href="#">条款</a> <a href="#">隐私</a> <a href="#">Cookies</a> <a
					href="#">广告</a> <a href="#">信息</a> <a href="#">商标</a> <a href="#">广告</a>
				<a href="#">企业</a> <a href="#">媒体</a> <a href="#">开发者</a> <a
					href="#">目录</a> @2015 communication
			</p>
		</div>
	</div>

</body>
<script type="text/javascript">
	var bgImageIndex = 1;
	var isRegister = true;
	/* 切换背景图片  --- 功能实现不了*/ 
	function changeBackgroundImage() {
		//alert("haha");
		var container = document.getElementById("container");
		//var bgImageStr = "login_bg"+bgImageIndex+".jpg";
		//alert(bgImageStr);
		if (bgImageIndex == 1) {
			container.style.background = "url(http://localhost:8080/communication/image/login/login_bg1.jpg)";
			container.style.backgroundRepeat = "no-repeat";
			container.style.backgroundAttachment = "fixed";
			container.style.backgroundPosition="center";
			container.style.backgroundSize = "100%,100%";
			
			
		} else if (bgImageIndex == 2) {
			container.style.background = "url(http://localhost:8080/communication/image/login/2.jpg)";
			container.style.backgroundRepeat = "no-repeat";
			container.style.backgroundAttachment = "fixed";
			container.style.backgroundPosition="center";
			container.style.backgroundSize = "100%,100%";
		} else if (bgImageIndex == 3) {
			container.style.background = "url(http://localhost:8080/communication/image/login/login_bg2.jpg)";
			container.style.backgroundRepeat = "no-repeat";
			container.style.backgroundAttachment = "fixed";
			container.style.backgroundPosition="center";
			container.style.backgroundSize = "100%,100%";
			
		}
		bgImageIndex++;
		if (bgImageIndex > 3) {
			bgImageIndex = 1;
		}
	}
	window.setInterval("changeBackgroundImage();",6000);
	/* 注册 */
	function register() {
		isQQ();
		isEmail();
		if(isRegister){
			document.form2.action = "system/reigsterAction_register.do";
			document.form2.submit();
		}else{
			alert("请填写正确的信息！");
			return;
		}
	}
	/* 登录 */
	function login1() {
		document.form1.action = "system/loginAction_login.do";
		document.form1.submit();
	}
	/*验证邮箱输入是否正确*/
	function isEmail () {
		var email = document.getElementById("email").value;
		if (email.search(/^([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|_|.]?)*[a-zA-Z0-9]+\.(?:com|cn)$/) == -1) {
			//alert("邮箱格式输入不正确");
			isRegister = false;
		}
	}
	/*判断输入的qq号码格式是否正确*/
	function isQQ(){
		var qq = document.getElementById("qq").value;
		if(qq.search(/^[1-9]\d{4,8}$/) == -1){
			//alert("do not pass");
			isRegister = false;
		}
	}
</script>
</html>