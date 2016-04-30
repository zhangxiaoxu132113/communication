<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
	<head>
		<title>this is info page</title>
		<meta charset="UTF-8">
		<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/editInfo.css"> --%>
		<script type="text/javascript"  src="${pageContext.request.contextPath }/script/jquery-1.9.1.min.js"></script>
		<script>
			function changeNum(){
				var s = document.getElementById("countNum");
				s.innerHTML = s.innerHTML * 1 -1;
			}
			function countNum(){
				var s = document.getElementById("countNum");
						
					window.setInterval("changeNum();",1000);
				
			}
			countNum();
		</script>
		<style type="text/css">
			body{background-color:#F5F6FA;}
			#container{width:500px;height:340px; margin:50px auto 90px;}
			#tip{
				border:0px;
				width:500px;height:30px;
				background-color:#24498D;color:#ffffff;
				font-size:24px;font-weight:bold;text-align:center;
				padding-top:10px;
			}
			#content{
				line-height: 2em;font-size:14px; font-weight:bold;text-align:center;
				padding-top:50px;padding-bottom:30px; background-color:#ffffff;
			}
			#countNum{
				font-weight:bold;
				font-size:24px;
				color:#FCD208;
			}
		</style>
		
	</head>
	<body>
		<%-- <div id="header">
				<!-- 一级导航栏 -->
				<div id="navgitor_level_one">
					<ul>
						<li><a href="#"><img alt="image" src="${pageContext.request.contextPath }/image/blog/touxiang.png" width="22" height="22" ></a></li>
						<li class="navgitor_level_one_text"><a href="#">center</a></li>
						<li><a href="#"><img alt="image" src="${pageContext.request.contextPath }/image/blog/groupfriend.jpg" width="22" height="22"></a></li>
						<li class="navgitor_level_one_text"><a href="#">group</a></li>
						<li><a href="#"><img alt="image" src="${pageContext.request.contextPath }/image/blog/personal.jpg" width="22" height="22"></a></li>
						<li class="navgitor_level_one_text"><a href="#">message</a></li>
						<li><a href="#"><img alt="image" src="${pageContext.request.contextPath }/image/blog/groupfriend.jpg" width="22" height="22"></a></li>
						<li class="navgitor_level_one_text"><a href="#">logout</a></li>
					</ul>
				</div>
				<div id="logo">
					<h1>communication</h1>
				</div>
				<div id="search">
					<form action="" method="post">
						<input type="text" id="search_content" name="search_content">
						<input type="image" name="search_bt" id="search_bt" src="${pageContext.request.contextPath }/image/blog/search_bt.gif"/>
					</form>
				</div>
			</div> --%>
		<div id="container">
			<div id="tip">
				<span class="tip_word">${request.tip_word }</span>
			</div>
			<div id="content">
				<span id="countNum"> 3 </span>
				<P>${request.content }<P>
			</div>
		</div>
		
		<%--三秒后，跳到指定的页面 --%>
		<%
			String url = (String)request.getAttribute("url");
			response.setHeader("Refresh","3;url=" + url);
		 %>
	</body>
</html>
