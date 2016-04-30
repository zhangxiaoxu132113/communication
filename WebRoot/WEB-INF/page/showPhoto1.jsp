<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<!DOCTYPE html>
<html>
	<head>
		<title>show all photo</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/writeBlog.css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.9.1.min.js"></script>
		<style type="text/css">
			#myFile{
				margin-top:20px;
				margin-left:20px;
			}
			.photo{
				float:left;
				margin-right:20px;
			}
			#hidden_frame{
				display: none;
				border:0px;
			}
		</style>
		<script type="text/javascript">
			function uploadPhoto(){
					document.form1.action="blog/photoAction_photoUpload.do";
					document.form1.target="hidden_frame";
					document.form1.submit();
					//setHeadImage();
			}
				/* function setHeadImage(){
					var xhr = ajaxFunction();
					
					xhr.onreadystatechange = function(){
					 	//alert(xhr.readyState);
						//alert(xhr.status);
					if(xhr.readyState==4){
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
						}
					 };
					
					xhr.open("post","blog/userAction_getHeadImage.do",true);
					//如果是POST请求方式，设置请求首部信息
			 		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
					var idValue = document.getElementById("id").value;
					
					xhr.send("id="+idValue);
				
				} */
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
		<div id="header" style="height: 433px; ">
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
					<h1>communication</h1>
				</div>
				<div id="search">
					<form action="" method="post" name="form2">
						<input type="text" id="search_content" name="search_content">
						<input type="image" name="search_bt" id="search_bt" src="${pageContext.request.contextPath }/image/blog/search_bt.gif"/>
					</form>
				</div>
			</div>
			<div id="container">
			<form method="post" name="form1" id="form1" enctype ="multipart/form-data">
				<input type="file" name="myFile" id="myFile" onchange="uploadPhoto();">
				<input type="hidden" name="album_id" value="${request.album_id }">			
			</form>
			<iframe name="hidden_frame" id="hidden_frame"></iframe>
				<div id="showPhoto">
					<s:if test="#request.photoList!=null&& #request.photoList.size()>0">
						<s:iterator value="#request.photoList" var="photo">
							<div id="photo">
								
								<img class="photo" alt="image" src="${pageContext.request.contextPath }/<s:property value="#photo.url"/>">
								
							</div>
							
						</s:iterator>
					</s:if>
				</div>
				
			</div>
	</body>
</html>
