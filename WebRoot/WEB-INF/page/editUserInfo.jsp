<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
	<head>
		<title>this is edit info page</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/editInfo.css">
		<script type="text/javascript"  src="${pageContext.request.contextPath }/script/jquery-1.9.1.min.js"></script>
		<script type="text/javascript">
		var pathName = window.document.location.pathname;
		//获取带"/"的项目名，如：/uimcardprj
		var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
			function sub1(){
				alert("ha");
				document.form1.action="system/userAction_editUserInfo.do";
				document.form1.submit();
			}

			//上传图片并调用ajax异步请求图片，立刻会先头像
			function uploadPhoto(){
				
				document.form1.action="system/photoUpload.do";
				document.form1.target="hidden_frame";
				document.form1.submit();
				setHeadImage();
			}
			//线程睡眠时间
			function sleep(milliseconds){
				setTimeout(function(){
					var start = new Date().getTime();
					while((new Date().getTime()-start)<milliseconds){
						//do someThing
					}
				},0);
			}
			function setHeadImage(){
				var xhr = ajaxFunction();
				
				xhr.onreadystatechange = function(){
				 	//alert(xhr.readyState);
					//alert(xhr.status);
					if(xhr.readyState==1){
						document.getElementById("head_info_image").style.background="#FFFFFF";
						document.getElementById("head_info_image").style.filter="alpha(opacity=80)";
						document.getElementById("head_info_image").style.opacity="0.5";
						document.getElementById("divcheck").innerHTML = "<img src='"+projectName+"/image/loading.gif'></img>";
					}else if(xhr.readyState==2){
						document.getElementById("divcheck").innerHTML = "<img src='"+projectName+"/image/loading.gif'></img>";
					}else if(xhr.readyState==3){
						document.getElementById("divcheck").innerHTML = "<img src='"+projectName+"/image/loading.gif'></img>";
					}else if(xhr.readyState==4){
						if(xhr.status==200||xhr.status==304){
							var data = xhr.responseText;
							
							var project = document.getElementById("project").value;
							imageUrl = project +"/"+ data;
							var headImage = document.getElementById("head_image");
							//更换图片之前的一些操作
							document.getElementById("head_info_image").style.background="";
							document.getElementById("head_info_image").style.filter="";
							document.getElementById("head_info_image").style.opacity="";
							document.getElementById("divcheck").innerHTML = "";
							headImage.src=encodeURI(imageUrl);
							
						}
					}else{
						document.getElementById("divcheck").innerHTML = "视频页面加载失败";
					}
					/* if(xhr.readyState==4){
						if(xhr.status==200||xhr.status==304){
							var data = xhr.responseText;
							
							var project = document.getElementById("project").value;
							imageUrl = project +"/"+ data;
							var headImage = document.getElementById("head_image");
							headImage.src=encodeURI(imageUrl);
							
						}
					} */
				 };	
				
				xhr.open("post","system/userAction_getHeadImage.do",true);
				//如果是POST请求方式，设置请求首部信息
		 		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
				var idValue = document.getElementById("id").value;
				
				xhr.send("id="+idValue);
			
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
						<input type="text" id="search_content" name="search_content">
						<input type="image" name="search_bt" id="search_bt" src="${pageContext.request.contextPath }/image/blog/search_bt.gif"/>
				</div>
			</div>
		<div id="container">
		<form method="post" id="form1" name="form1" enctype="multipart/form-data" >
			<h1 id="base_info_h1">编辑你的信息</h1>
			<h2 id="base_info_h2">基本信息</h2>
			<div id="baseInfo">
				<div id="baseInfo_row">
					<div id="head_info">
					<div id="head_info_image">
						<img alt="head image" src="${pageContext.request.contextPath }/image/personhome/321.jpg" id="head_image" width="185" height="230">
						<input type="file" name="myFile" id="myFile" onchange="uploadPhoto();" />
						<!-- divcheck 显示加载的进度 -->
						<div id="divcheck"></div>
					</div>
						<div id="head_info_table">
							<div class="head_info_row">
								<span class="head_info_cell_key">昵称</span>
								<span class="head_info_cell_value">
									${globle_user.userName}
								</span>
							</div>
							<div class="head_info_row">
								<span class="head_info_cell_key">性别</span>
								<span class="head_info_cell_value">
									${globle_user.genderVO}
								</span>
							</div>
							<div class="head_info_row">
								<span class="head_info_cell_key">年龄</span>
								<span class="head_info_cell_value">
									35
								</span>
							</div>
						</div>
					</div>
					<div id="edit_base_info">  
						<div id="edit_base_info_table">
							<div class="edit_base_row">
									<span class="edit_base_cell_key">感情状态</span>
									<div class="styled-select">
									<span class="edit_base_cell_value">
										
										<select name="status" >
											<option value="0">单身</option>
											<option value="1">恋爱中</option>
											<option value="2">已婚</option>
										</select>
										
									</span>
									</div>
								</div>
								<div class="edit_base_row">
									<span class="edit_base_cell_key">真实姓名</span>
									<span class="edit_base_cell_value">
										<input type="text" id="realName" name="realName">
									</span>
								</div>
								<div class="edit_base_row">
									<span class="edit_base_cell_key">现居地</span>
									<span class="edit_base_cell_value">
										<input type="text" id="address" name="address">
									</span>
								</div>
								<div class="edit_base_row">
									<span class="edit_base_cell_key">生日</span>
									<span class="edit_base_cell_value">
										<input type="text" id="birthday" name="birthday">
									</span>
								</div>
								<div class="edit_base_row">
									<span class="edit_base_cell_key">个人简介</span>
									<span class="edit_base_cell_value">
										<textarea rows="10" cols="65" name="profile"></textarea><br>
										you can write your profile in here!<br>
										you can write your profile in here,so other can learn about you!
									</span>
								</div>
						</div>
					</div>
				</div>
			</div>
			<h2 id="connection_info_h2">联系信息</h2>
			<div id="connection_info">
				<div class="more_info_row">
					<span class="more_info_cell_key">电子邮件</span>
					<span class="more_info_cell_value">
						<input type="text" id="email" name="email">&nbsp;write your email here!
					</span>
				</div>
				<div class="more_info_row">
					<span class="more_info_cell_key">QQ</span>
					<span class="more_info_cell_value">
						<input type="text" id="qq" name="qq">&nbsp;write your qq here!
					</span>
				</div>
				<div class="more_info_row">
					<span class="more_info_cell_key">MSN</span>
					<span class="more_info_cell_value">
						<input type="text" id="msn" name="msn">&nbsp; write your msn here!
					</span>
				</div>
			</div>
			<h2 id="connection_info_h2">教育经历</h2>
			<div id="connection_info">
				<div class="more_info_row">
					<span class="more_info_cell_key">高中</span>
					<span class="more_info_cell_value">
						<input type="text" id="highSchool" name="hightSchool">
					</span>
				</div>
				<div class="more_info_row">
					<span class="more_info_cell_key">大学</span>
					<span class="more_info_cell_value">
						<input type="text" id="university" name="university">
					</span>
				</div>
				<div class="more_info_row">
					<span class="more_info_cell_key">MSN</span>
					<span class="more_info_cell_value">
						<input type="text" id="msn" name="msn">
					</span>
				</div>
			</div>

			<div id="sub_form">
				<input type="button" id="sub" name="sub" value="提交" class="sub_form" onclick="sub1()" />
				<input type="button" id="cancel" name="cancel" value="取消" class="sub_form" />
			</div>
			<!-- onchange="uploadPhoto()" -->
			<input type="hidden" id="project" name="project" value="${pageContext.request.contextPath }">
			<input type="hidden" id="userName" name="userName" value="${globle_user.userName }">
			<input type="hidden" id="gender" name="gender" value="${globle_user.gender }">
			<input type="hidden" id="password" name="password" value="${globle_user.password }">
			<input type="hidden" id="id" name="id" value="${globle_user.id }">
		</form>
		<iframe name="hidden_frame" id="hidden_frame"></iframe>
		</div>
	</body>
</html>