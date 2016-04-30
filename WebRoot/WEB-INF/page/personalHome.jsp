<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
	<head>
		<title>Friend Home Page</title>
		<meta charset="UTF-8"/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/php.css"/>
	</head>
	<!-- ----------------------内容主体----------------------------- -->
	<body>
		<!-- 主容器 -->
		<div id="container">
			<!-- 将主体的内容分为左右两个部分 -->
			<div id="main">
				<!-- 右边的内容 -->
				<div id="right_body">
					<!-- 头部信息 -->
					<div id="header">
						<div id="p_system">
							<ul>
								<li><a href="home.html">个人中心</a></li>
								<li><a href="#">消息</a></li>
								<li><a href="#">好友</a></li>
								<li><a href="#">设置</a></li>
							</ul>
						</div>
						<div id="p_navigator">
							<ul>
								<li><a href="#">个人简介</a></li>
								<li><a href="#">编辑</a></li>
								<li><a href="#">好友</a></li>
								<li><a href="#">网络</a></li>
								<li><a href="#">收件箱</a></li>
							</ul>
						</div>
					</div>
					<!-- 主体内容 -->
					<div id="main_body">
					
						<div id="main_left">
							<div id="head_image">
								<!-- <img alt="head image" src="image/personhome/touxiang.png"> -->
								<img alt="head image" src="${pageContext.request.contextPath }/image/personhome/321.jpg">
							</div>	
							<div id="contactme">
								<p><a href="#">发送消息给<label class="userName">${request.friend.userName }</label></a></p>
								<p><a href="#">跟<label class="userName">${request.friend.userName }</label>打招呼</a></p>
								<p><a href="#">添加<label class="userName">${request.friend.userName }</label>为好友</a></p>
							</div>
							<div id="add_friend">
								<h3>好友推荐</h3>
								<p id="friend_count"> friends in common.  see All</p>
								<div id="friends">
									<div class="friend">
										<img alt="friend image" src="${pageContext.request.contextPath }/image/personhome/friend1.png">
										<p class="friend_name">John</p>
									</div>
									<div class="friend">
										<img alt="friend image" src="${pageContext.request.contextPath }/image/personhome/friend2.png">
										<p class="friend_name">Mark</p>
									</div>
									<div class="friend">
										<img alt="friend image" src="${pageContext.request.contextPath }/image/personhome/friend3.png">
										<p class="friend_name">Naomi</p>
									</div>
								</div>
							</div>
						</div>
						<div id="main_right">
							<div id="base_info">
								<h3>${request.friend.userName }</h3>
									<p>
										<span class="introduce">is excited about the new redesign</span><br>
										<span class="introduce">Updated one minute ago</span>
									</p>
									<div id="base_textinfo">
										<p class="base_row"><span class="class_info">社交平台:</span><span class="class_myinfo">communication</span></p>
										<p class="base_row"><span class="class_info">性别 :</span><span class="class_myinfo">${request.friend.gender }</span></p>
										<p class="base_row"><span class="class_info">兴趣:</span><span class="class_myinfo">Men</span></p>
										<p class="base_row"><span class="class_info">个人状态:</span><span class="class_myinfo">${request.friend.status }</span></p>
										<p class="base_row"><span class="class_info">正在寻找:</span><span class="class_myinfo">Friendship</span></p>
										<p class="base_row"><span class="class_info">生日:</span><span class="class_myinfo">${request.friend.birthday }</span></p>
										<p class="base_row"><span class="class_info">现居地:</span><span class="class_myinfo">${request.friend.address }</span></p>
									</div>
								
							</div>
							<div id="experience">
								<p class="tableRow">
									<img alt="image" src="${pageContext.request.contextPath }/image/personhome/start_network.gif" class="cell"><!-- image/personhome/add_friends.gif -->
									<span class="font_2">Amy and Andy are now Friends , 1.58pm </span>
									<img class="del_img" alt="delete image" src="${pageContext.request.contextPath }/image/personhome/del.gif">
								</p>
								<p class="tableRow">
									<img alt="image" src="${pageContext.request.contextPath }/image/personhome/start_network.gif" class="cell"><!-- image/personhome/edit_info.gif -->
									<span class="font_2">Amy edited Work info the her profile. 5.55pm</span>
									<img class="del_img" alt="delete image" src="${pageContext.request.contextPath }/image/personhome/del.gif">
								</p>
								<p class="tableRow">
									<img alt="image" src="${pageContext.request.contextPath }/image/personhome/start_network.gif" class="cell"><!-- image/personhome/start_network.gif -->
									<span class="font_2">Amy edited Word Info her profile 3.35pm </span>
									<img class="del_img" alt="delete image" src="${pageContext.request.contextPath }/image/personhome/del.gif">
								</p>
							</div>
							<div id="information">
								<h2>个人动态</h2>
								<h3>个人的动态消息</h3>
								<div id="content">
									<h3>我的学习经历</h3>
									<p>
										之前写的项目，很久没有接触，代码很多都忘却，不过还好自己每一个方法都有doc注释//改善Java文档的理由、建议和技巧
										简历版本一，刚出炉！拿给大哥和大嫂看看，结果被大嫂指出了N多的毛病…[嘻嘻][嘻嘻]继续完善，把项目经验的内容写好，
										并做好细节！到了十二月月底出去面试估计会有很棒的简历啦！哈哈～～
									</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!-- 左边的内容 -->
				<div id="left_body">
					<div id="logo">
						<h1>Commucation</h1>
						<!-- <img alt="logo" src="image/facebook_logo.png"> -->
					</div>
					<div id="attch_info">
						<div id="search">
							<h3>搜索</h3>
							<input type="text" id="search_bar" name="search_bar" />
							<input type="submit" id="search" name="search" value="Submit"/>
						</div>
						<div id="more_info">
							<ul>
								<li><img alt="photos" src="${pageContext.request.contextPath }/image/personhome/photos.png"><span class="font_1">照片</span></li>
								<li><img alt="notes" src="${pageContext.request.contextPath }/image/personhome/Notes.png"><span class="font_1">日记</span></li>
								<li><img alt="Groups" src="${pageContext.request.contextPath }/image/personhome/Groups.png"><span class="font_1">好友组</span></li>
								<li><img alt="Events" src="${pageContext.request.contextPath }/image/personhome/Events.png"><span class="font_1">事件</span></li>
								<li><img alt="PostedItem" src="${pageContext.request.contextPath }/image/personhome/PostedItems.png"><span class="font_1">发布内容</span></li>
							</ul>
						</div>
						<br/>
						<h3>Change Log</h3>
						<div id="adv_info">
							<p>
								Find out about new features just added to 
								Facebook ,<a href="#">Click Here</a>
							</p>
							<!-- 这里放置广告图片 -->
							<p><img id="img_adveri" alt="advertise image" src="${pageContext.request.contextPath }/image/personhome/advertise_img.png"></p>
						</div>
					</div>
				</div>
				
			</div>
			<div id="footer">
				<p>copyright@</p>
				<p>welcome to join us,and communication others!</p>
			</div>
		</div>
		
	</body>
</html>