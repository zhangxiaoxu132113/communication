<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="cn.water.cf.web.form.Page"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<title>this is blog home page</title>
		<meta charset="UTF-8"/>
		<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/css/blogindex.css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.9.1.min.js"></script>
		<script  src="${pageContext.request.contextPath }/script/page.js"></script>
		<script type="text/javascript">
			window.onload = init;
			function init(){
				arrowdown();//给文章操作的下拉按钮增加事件响应
			}
			function writeBlog(){
				document.writeBlog_form.action="blogAction_writeBlogUI.do";
				alert("提交页面!");
				document.writeBlog_form.submit();
			}
			
			function show_arrow_down(thisValue){
				var nextElement = thisValue.nextElementSibling;
				var $nextElement = $(nextElement);
				$nextElement.slideToggle();//这里使用jquery的滑动效果
				
			}
			function deleteArticle(thisValue){
				var articleId = thisValue.id.substring(4);
				var articleDiv = document.getElementById("article_"+articleId);
				//调用jquery，将该div淡出页面
				var $articleDiv = $(articleDiv);
				$articleDiv.fadeOut();
				//父节点删除子节点
				//articleDiv.parentNode.removeChild(articleDiv);
				//异步删除文章
				deleteArticleWithAjax(articleId);
			}
			function editArticle(thisValue){
				var articleId = thisValue.id.substring(4);
				thisValue.href="blogAction_editArticleUI.do?article_id="+articleId;
			}
			//使用ajax技术删除文章
			function deleteArticleWithAjax(articleId){
				var xhr = ajaxFunction();
				xhr.onreadystatechange = function(){
					if(xhr.readyState==4){
						if(xhr.status==200||xhr.status==304){
							//服务器端没有返回数据
						}else{
							alert("文章删除失败!");
						}
					}
				 };
				xhr.open("post","blogAction_deleteArticle.do",true);
				//如果是POST请求方式，设置请求首部信息
		 		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				xhr.send("article_id="+articleId);
			}
			
			//定义Ajax对象
			function ajaxFunction(){
			   var xmlHttp;
			   try{ // Firefox, Opera 8.0+, Safari
			        xmlHttp=new XMLHttpRequest();
			    }
			    catch (e){
				   try{// Internet Explorer
				         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
				      }
				    catch (e){
				      try{
				         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
				      }
				      catch (e){}
				      }
			    }
			
				return xmlHttp;
			 }
		</script>
	</head>

	<body>
		<div id="container">
			<!-- 页面的顶部信息 -->
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
					<h1>Communication</h1>
				</div>
				<div id="search">
					<form action="" method="post" id="search_form">
						<input type="text" name="search_text" id="search_text" />
						<input type="image" name="search_bt" id="search_bt" src="${pageContext.request.contextPath }/image/blog/search_bt.gif"/>
					</form>
				</div>
			</div>
			<!-- 页面的主体 -->
			<div id="pagebody">
				<div id="pagebody_row">
					<div id="mainbody">
						<div id="mainbody_table">
							<div id="mainbody_table_row">
								<!-- 主体页面的左边内容 -->
								<div id="mainbody_left">
									<div id="touxiang">
										<img alt="touxiang image" src="${pageContext.request.contextPath }/${globle_user.headImage}" width="170" height="190">
									</div>
									<div id="blog_manager">
										<h2>blog manager</h2>
										<ul>
											<li><a href="#"><span class="blog_manager_keyword">All of blog</span>&nbsp;&nbsp;[12]</a></li>
											<li><a href="#"><span class="blog_manager_keyword">blog of java</span>&nbsp;&nbsp;[25]</a></li>
											<li><a href="#"><span class="blog_manager_keyword">blog of asp.net</span>&nbsp;&nbsp;[2]</a></li>
											<li><a href="#"><span class="blog_manager_keyword">blog of php</span>&nbsp;&nbsp;[42]</a></li>
										</ul>
									</div>
									<div id="search_article_topic">
										<h2>search article</h2>
										<form action="" method="post" id="search_article_topic_form">
											<input type="image" id="search_article_topic_bt" name="search_article_topic_bt" src="${pageContext.request.contextPath }/image/blog/search_bt.gif">
											<input type="text" id="searche_article_topic_text" name="search_article_topic_text">
										</form>
									</div>
									<div id="characteristic_article">
										<h2>characteristic article</h2>
										<div id="characteristic_article_table">
											<div class="characteristic_article_table_row">
												<img alt="article_author_image" src="${pageContext.request.contextPath }/image/blog/friend1.png">
												<p>
													<span class="article_tile_span">Action in Hadoop</span><br>
													Using the sprnig framework can make my code more convenient for maintenance, and spring provides
													the functionality for transactional management, and I can use transactions to manage our program
													access database.
												</p>
											</div>
											<div class="characteristic_article_table_row">
												<img alt="article_author_image" src="${pageContext.request.contextPath }/image/blog/friend2.png">
												<p>
													<span class="article_tile_span">How to use webservice </span><br>
													Using the sprnig framework can make my code more convenient for maintenance, and spring provides
													the functionality for transactional management, and I can use transactions to manage our program
													access database.
												</p>
											</div>
											<div class="characteristic_article_table_row">
												<img alt="article_author_image" src="${pageContext.request.contextPath }/image/blog/friend3.png">
												<p>
													<span class="article_tile_span">patterm desgin of java</span><br>
													Using the sprnig framework can make my code more convenient for maintenance, and spring provides
													the functionality for transactional management, and I can use transactions to manage our program
													access database.
												</p>
											</div>
										
										</div>
									</div>	
								</div>
								<!-- 主体页面的右边内容 -->
							<div id="mainbody_right">
								<div id="baseinfo">
									<h2 >MakeZhakeboGe</h2>
									<p>
										welcome to my blog !
									</p>
								</div>
								<div id="write_blog">
									<form action="" method="post" name="writeBlog_form">
										<input type="image" src="${pageContext.request.contextPath }/image/photo/write_bt.png" title="发博文" onclick="writeBlog()">
									</form>
								</div>
								<div id="navgitor_level_two">
									<ul>
										<li><a href="#" id="home">Home</a></li>
										<li><a href="#">Blog</a></li>
										<li><a href="#">Subscribe</a></li>
										<li><a href="blogAction_photoUI.do">Photo</a></li>
										<%-- ${pageContext.request.contextPath }/blog --%>
										<li><a href="#">Contact</a></li>
									</ul>
								</div>
								<div id="showinfo">
									<div id="search_article">
										<form action="" method="get" id="search_article" >
											<input type="image" id="search_article_bt" name="search_article_bt" src="${pageContext.request.contextPath }/image/blog/search_bt.gif">
											<input type="text" id="search_article_text" name="search_article_text" />
										</form>
									</div>
									<div id="show_article">
										<s:if test="#request.articleList!=null && #request.articleList.size()>0">
											<s:iterator value="#request.articleList" id="article_id">
												<div class="show_article_row" id="article_<s:property value="#article_id.article_id"/>">
													<div class="article_author">
														<img alt="touxiang image" src="${pageContext.request.contextPath }/${globle_user.headImage}" class="article_author_image" >
														<span class="author_name_class"><s:property value="#article_id.author"/></span>
													</div>
													<div class="article_show">
														<!-- 文章操作的按钮 -->
														<div class="article_operator">
															<img alt="down image" src="${pageContext.request.contextPath }/image/blog/arrow_down.png" class="arrow_down" onclick="show_arrow_down(this);" onblur="show_arrow_down(this);"/>
															<div class="show_article_operator" >
															
																<ul>
																	<li><a href="blogAction_setTop.do?article_id=<s:property value="#article_id.article_id"/>">置 顶</a></li>
																	<li><a onclick="deleteArticle(this);" id="del_<s:property value="#article_id.article_id"/>">删 除</a></li>
																	<li><a onclick="editArticle(this);" id="edi_<s:property value="#article_id.article_id"/>">编 辑</a></li>
																	
																</ul>
													<%-- href="blogAction_deleteArticle.do?article_id=<s:property value="#article_id.article_id"/>" --%>
															</div>
														</div>
														<h2><a href="blogAction_showOneArticle.do?article_id=<s:property value="#article_id.article_id"/>"> <s:property value="#article_id.title"/></a></h2>
														<br>
														<p class="article_content">
															<s:property value="#article_id.summary"/>...
														</p>
														<p class="article_command">&nbsp;
															<span class="command_class">评论(12)</span><span  class="praise">点赞 (32)</span>
														</p>
													</div>
												</div>
											</s:iterator>
											<!-- 显示分页 -->
											<div id="fenye">
											<p>
											共<s:property value="#request.page.totalPage" />页 &nbsp;&nbsp;
											<s:if test="#request.page.hasPrePage">
												<a href="blogAction_home.do?currentPage=1">首页&nbsp;&nbsp;</a>
												<a href="blogAction_home.do?currentPage=${page.currentPage -1 }">上一页&nbsp;&nbsp;</a>
											</s:if>
											<s:else>
												首页&nbsp;&nbsp;
												上一页
											</s:else>
											<%
												Page page1 = (Page)request.getAttribute("page");
												int currentPage = page1.getCurrentPage();
												int totalPage = page1.getTotalPage();
												int i = 1 + currentPage / 7 * 7;
												int end = i+7;
												for(;i<=totalPage;i++){
													if(i == end)
															break;
													if(currentPage == i){
														out.print("<label style='color:#717EAD;'>"+i+"&nbsp;&nbsp;</label>");
													}else{
														out.print("<a href='blogAction_home.do?currentPage="+i+"'>"+ i+"&nbsp;&nbsp;</a>");
													}
												}
												
											 %>
											<s:if test="#request.page.hasNextPage">
												<a href="blogAction_home.do?currentPage=${page.currentPage +1 }">下一页&nbsp;&nbsp;</a>
												<a href="blogAction_home.do?currentPage=${page.totalPage }"> 尾页</a>
											</s:if>
											<s:else>
												下一页&nbsp;&nbsp;
												 尾页
											</s:else>
											<!-- 显示分页 -->
											</p>
											</div>
										</s:if>
										<!-- 如果没有文章，应该怎么显示呢 -->
										<s:else>
											
										</s:else>
										
										
										<%-- <div class="show_article_row">
											<div class="article_author">
												<img alt="touxiang image" src="${pageContext.request.contextPath }/${globle_user.headImage}" class="article_author_image">
												<span class="author_name_class">author name</span>
											</div>
											<div class="article_show">
												<h2><a href="3">article title</a></h2>
												<p class="article_content">
													Your tools should be as refined as the code you write. STS is our Eclipse-based IDE
													crafted to serve the needs of building applications with Spring. We're always working
												    on new features and performance in our mission to make STS the most productive Eclipse distribution available
													Using the sprnig framework can make my code more convenient for maintenance, and spring provides
													the functionality for transactional management, and I can use transactions to manage our program
													access database.
													Using the sprnig framework can make my code more convenient for maintenance, and spring provides
													the functionality for transactional management, and I can use transactions to manage our program
													access database.
												</p>
												<p class="article_command">&nbsp;
													<span class="command_class">评论(12)</span><span  class="praise">点赞 (32)</span>
												</p>
											</div>
										</div> --%>
										<%-- <div class="show_article_row">
											<div class="article_author">
												<img alt="touxiang image" src="${pageContext.request.contextPath }/${globle_user.headImage}" class="article_author_image">
												<span class="author_name_class">author name</span>
											</div>
											<div class="article_show">
												<h2><a href="3">article title</a></h2>
												<p class="article_content">
													Your tools should be as refined as the code you write. STS is our Eclipse-based IDE
													crafted to serve the needs of building applications with Spring. We're always working
												    on new features and performance in our mission to make STS the most productive Eclipse distribution available
												</p>
												<p class="article_command">&nbsp;
													<span class="command_class">评论(12)</span><span  class="praise">点赞 (32)</span>
												</p>
											</div>
										</div> --%>
										<%-- <div class="show_article_row">
											<div class="article_author">
												<img alt="touxiang image" src="${pageContext.request.contextPath }/${globle_user.headImage}" class="article_author_image">
												<span class="author_name_class">author name</span>
											</div>
											<div class="article_show">
												<h2><a href="3">article title</a></h2>
												<p class="article_content">
													Your tools should be as refined as the code you write. STS is our Eclipse-based IDE
													crafted to serve the needs of building applications with Spring. We're always working
												    on new features and performance in our mission to make STS the most productive Eclipse distribution available
													Using the sprnig framework can make my code more convenient for maintenance, and spring provides
													the functionality for transactional management, and I can use transactions to manage our program
													access database.
													Using the sprnig framework can make my code more convenient for maintenance, and spring provides
													the functionality for transactional management, and I can use transactions to manage our program
													access database.
												</p>
												<p class="article_command">&nbsp;
													<span class="command_class">评论(12)</span><span  class="praise">点赞 (32)</span>
												</p>
											</div>
										</div> --%>
									</div>
						  </div>
							
						</div>
							<!--  -->
							</div>
						</div>
					</div>
					<!-- 边栏的内容 -->
					<div id="sidebar">
						<!-- 时间轴显示模块 -->
						<h3>time axis</h3>
						<div id="time_axis">
							<ul>
								<li class="first_time_axis">now</li>
								<li>June 2014</li>
								<li>December 2015</li>
								<li>January 2020</li>
								<li>January 2020</li>
							</ul>
						</div>
						<!-- 广告模块 -->
						<!-- <div class="adv">
							<h2>this is a adv</h2>
							<img alt="adv image" src="image/blog/adv.jpg">
							<p>
								this is a cone
							</p>
						</div> -->
						<!-- 签到模块 -->
						<div id="qiandao">
							
						</div>
						<!-- 显示日历的一个插件 -->
						<!-- 不过由于暂时还没有找到如何显示这个caleder的js插件，所以使用img来代替 -->
						<div id="calender">
							<!-- <img alt="calender image" src="image/blog/19903.jpg" width="240" height="300"> -->
						</div>
						<!-- 用户可能感兴趣的文章 -->
						<div id="interested_article">
							
						</div>
					</div>
				</div>
			</div>
			<!-- 页面的结尾信息 -->
			<div id="footer">
				<p>copyright@</p>
				<p>welcome to join us,and communication others!</p>
			</div>
		</div>
	
	</body>
</html>
