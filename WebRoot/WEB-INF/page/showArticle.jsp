<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>this is a showArticle page</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/showArticle.css" >
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/writeBlog.css">
	</head>
	
	<body>
		<div id="header">
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
		</div>
		
		<div id="container">
			<div id="article_header">
				<h2>${request.article.title }</h2>
				
			</div>
			<div id="article_info">
				发表于${request.article.createDate }&nbsp;&nbsp; |&nbsp;&nbsp; 阅读数量 ： ${request.article.accessNumber } &nbsp;&nbsp;|&nbsp;&nbsp; 作者 ：${request.article.author }&nbsp;&nbsp;
			</div>
			<div id="content">
				${request.article.content }
			</div>
		</div>
	</body>
</html>
