<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<title>this is blog photo </title>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/blogindex.css" type="text/css">
		<link rel="stylesheet" href="${pageContext.request.contextPath }/css/blogphoto.css" type="text/css">
		<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Tangerine"> 
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.9.1.min.js"></script>
		<style type="text/css">
		
			#fullbg {
			    background-color:gray;
			    left: 0px;
			    opacity: 0.5;
			    position: absolute;
			    top: 0px;
			    z-index: 3;
			    filter: alpha(opacity=50); /* IE6 */
			    -moz-opacity: 0.5; /* Mozilla */
			    -khtml-opacity: 0.5; /* Safari */
			}
			#createAlbumDialog {
			    background-color:#fff;
			    border: 1px solid #888;
			    display: none;
			    height: 300px;
			    left: 50%;
			    margin: -100px 0 0 -100px;
			    padding: 12px;
			    position: fixed !important; /* 浮动对话框 */
			    position: absolute;
			    top: 50%;
			    width: 300px;
			    z-index: 5;
			}
			#createAlbumDialog p {
			    margin: 0 0 12px;
			}
			#createAlbumDialog p.close {
			    float:right;
			    margin-right:10px;
			}
		</style>
		<script type="text/javascript">
		    //显示灰色 jQuery 遮罩层
		    function showBg() {
		        var bh = $("body").height();
		        var bw = $("body").width();
		        $("#fullbg").css({
		            height: bh,
		            width: bw,
		            display: "block"
		        });
		        $("#createAlbumDialog").show();
		    }
		    //关闭灰色 jQuery 遮罩
		    function closeBg() {
		        $("#fullbg,#createAlbumDialog").hide();
		    }
		    //创建相册
		    function createAlbum(){
		    	
		    	//提交之后关闭遮罩层
		    	closeBg();
		    	document.createForm.action="albumAction_createAlbum.do";
		    	alert("提交");
		    	document.createForm.submit();
		    	
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
						<li><a href="#"><img alt="image" src="${pageContext.request.contextPath }/${globle_user.headImage}" width="22" height="22" ></a></li>
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
									<!-- 相册操作模块 -->
									<div id="blog_manager">
										<h2>Operation</h2>
										<ul>
											<li><a href="#"><span class="blog_manager_keyword">add classification</span></a></li>
											<li><a href="#"><span class="blog_manager_keyword">add photo</span></a></li>
											<li><a href="#"><span class="blog_manager_keyword">create album</span></a></li>
											<li><a href="#"><span class="blog_manager_keyword"></span></a></li>
										</ul>
									</div>
									<!-- 相册操作模块 -->
									<div id="blog_manager">
										<h2>classification</h2>
										<ul>
											<li><a href="#"><span class="blog_manager_keyword">all of album</span></a></li>
											<li><a href="#"><span class="blog_manager_keyword">cartoon</span></a></li>
											<li><a href="#"><span class="blog_manager_keyword"></span></a></li>
											<li><a href="#"><span class="blog_manager_keyword"></span></a></li>
										</ul>
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
								<div id="navgitor_level_two">
									<ul>
										<li><a href="blogAction_home.do">Home</a></li>
										<li><a href="#" id="blog">Blog</a></li>
										<li><a href="#">Subscribe</a></li>
										<li><a href="blogAction_photoUI.do" id="photo">Photo</a></li>
										<li><a href="#">Contact</a></li>
									</ul>
								</div>
								<div id="showinfo">
									<div id="search_article">
										<form id="search_article" name="createForm" method="post">
											<input type="image" id="search_article_bt" name="search_article_bt" src="${pageContext.request.contextPath }/image/blog/search_bt.gif">
											<input type="text" id="search_article_text" name="search_article_text" />
											<!-- 对话框 -->
											<div id="createAlbumDialog">
											  <p class="close"><a href="#" onclick="closeBg();">关闭</a></p>
											  <h2>创建相册</h2>
											  <table>
											  	<tr>
											  		<td><label>相册名称</label></td>
											  		<td><input type="text" name="albumName" id="albumName"/></td>
											  	</tr>
											  	<tr>
											  		<td><label>相册类型</label></td>
											  		<td>
											  			<select name="type">
													 		<option value="0">动漫</option>
													 		<option value="1">人物</option>
													 		<option value="2">景物</option>
													 	</select>
											  		</td>
											  	</tr>
											  	<tr>
											  		<td><label>相册描述</label></td>
											  		<td><textarea name="description" rows="3" cols="10"></textarea></td>
											  	</tr>
											  	<tr>
											  		<td><input type="button" onclick="createAlbum();" value="创建" /></td>
											  		<td><input type="button" onclick="" value="取消"/></td>
											  	</tr>
											  </table>
											 	
											 	
												
											</div>
											<!-- jQuery 遮罩层上方的对话框 -->
											
											
											
										</form>
									</div>
									<div id="operation_ablum">
										<form action="" method="post">
											<img alt="image" src="${pageContext.request.contextPath }/image/photo/transform_photo.png">
											<img alt="image" src="${pageContext.request.contextPath }/image/photo/create_ablum.png" onclick="showBg();" >
										</form>
									</div>
									<div id="show_ablum">
										<!-- 判断照片是否为空 -->
										<s:if test="#request.albums!=null">
											<!-- 迭代所有的照片的类型 -->
											<s:iterator value="#request.types" var="type">
												
											<h3><s:property value="#type.ddlName"/></h3>
											
												<!-- 一行相冊  -->
													<s:iterator value="#request.albums" var="album" status="num">
														<s:if test="#album.type==#type.ddlCode">
																<div class="show_ablum_cell">
																	<a href="albumAction_showAlbum.do?album_id=<s:property value="#album.album_id"/>"><img alt="ablumimage" src="${pageContext.request.contextPath }/<s:property value="#album.frontCover"/>" width="158" height="150"></a>
																	<p><span class="photo_count"><s:property value="#album.count"/></span> <span class="ablum_name"><a href="albumAction_showAlbum.do?album_id=<s:property value="#album.album_id"/>"><s:property value="#album.albumName"/></a></span></p>
																</div>
														</s:if>
													</s:iterator>
											</s:iterator>
											
										</s:if>
										
											<%-- <h3>Figure</h3>
											<!-- 一行相冊  -->
											<div class="show_ablum_cell">
											<span class="photo_count">12</span> 
												<img alt="ablumimage" src="${pageContext.request.contextPath }/image/photo/1.jpg" width="158" height="150">
												<p><span class="photo_count">12</span> <span class="ablum_name"><a href="">My idol</a></span></p>
											</div>
											<div class="show_ablum_cell">
												<img alt="ablumimage" src="${pageContext.request.contextPath }/image/photo/2.jpg" width="158" height="150">
												<p><span class="photo_count">54</span> <span class="ablum_name"><a href="">jiaoxiang</a></span></p>
											</div>
											<div class="show_ablum_cell">
												<img alt="ablumimage" src="${pageContext.request.contextPath }/image/photo/3.jpg" width="158" height="150">
												<p><span class="photo_count">41</span> <span class="ablum_name"><a href="">My idol</a></span></p>
											</div>
											<div class="show_ablum_cell">
												<img alt="ablumimage" src="${pageContext.request.contextPath }/image/photo/4.jpg" width="158" height="150">
												<p><span class="photo_count">11</span> <span class="ablum_name"><a href="">My idol</a></span></p>
											</div> --%>
											<!-- end -->
											<%-- <h3>Cartoon</h3>
											<!-- 一行相冊  -->
											<div class="show_ablum_cell">
												<img alt="ablumimage" src="${pageContext.request.contextPath }/image/photo/5.jpg" width="158" height="150">
												<p><span class="photo_count">10</span> <span class="ablum_name"><a href="">My idol</a></span></p>
											</div>
											<div class="show_ablum_cell">
												<img alt="ablumimage" src="${pageContext.request.contextPath }/image/photo/6.jpg" width="158" height="150">
												<p><span class="photo_count">8</span> <span class="ablum_name"><a href="">My idol</a></span></p>
											</div>
											<div class="show_ablum_cell">
												<img alt="ablumimage" src="${pageContext.request.contextPath }/image/photo/7.jpg" width="158" height="150">
												<p><span class="photo_count">54</span> <span class="ablum_name"><a href="">My idol</a></span></p>
											</div>
											<div class="show_ablum_cell">
												<img alt="ablumimage" src="${pageContext.request.contextPath }/image/photo/8.jpg" width="158" height="150">
												<p><span class="photo_count">16</span> <span class="ablum_name"><a href="">My idol</a></span></p>
											</div> --%>
										<!-- end -->
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
								this is a content of adv.
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
			<!-- jQuery 遮罩层 -->
			<div id="fullbg"></div>
			<!-- end jQuery 遮罩层 -->
			
			<!-- 页面的结尾信息 -->
			<div id="footer">
				<p>copyright@</p>
				<p>welcome to join us,and communication others!</p>
			</div>
		</div>
	
	</body>
</html>