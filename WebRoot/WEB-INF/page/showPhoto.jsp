<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags"  prefix="s"%>
<%@ page import="cn.water.cf.web.form.Page"%>
<%	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<title>show all photo</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/writeBlog.css">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/showPhoto.css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.9.1.min.js"></script>
		<script>
		
			var pathName ;
			var projectName;
			var boarddiv = "<div style='background:white;width:100%;height:100%;z-index:999;position:absolute;top:0;margin-top:100px;'>加载中...</div>"; 
			window.onload=function(){
				pathName = window.document.location.pathname;
				//获取带"/"的项目名，如：/uimcardprj
				projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
			};
		
			function showBox(thisValue){
				var photoId = thisValue.id;
				document.getElementById("operator_"+photoId).style.display="block";
				document.getElementById("operatorBt_"+photoId).style.display="block";
				
			}
			function closeBox(thisValue){
			var photoId = thisValue.id;
				document.getElementById("operator_"+photoId).style.display="none";
				document.getElementById("operatorBt_"+photoId).style.display="none";
				//document.getElementById("operator_list_"+photoId).style.display="none";
				//$("#operator_list_"+photoId).slideToggle();//这里使用jquery的滑动效果
			}
			function showOperator(thisValue){
				var photoId = thisValue.id.substring(11);
				$("#operator_list_"+photoId).slideToggle();//这里使用jquery的滑动效果
			}
			function uploadPhoto(){
				document.form1.action="blog/photoAction_photoUpload.do";
				document.form1.target="hidden_frame";
				document.form1.submit();
				createImage();
			}
			function del(thisValue) {
				var flag = confirm("你确定要删除该照片"); 
				if(!flag){
					thisValue.href="";
				}
			}
			function createImage() {
				$.ajax({type:'POST',
				    url:'<%=basePath %>system/albumAction_getPhoto.do',
				    data:'',
				    dataType:'json',
				    timeout:5000,
				    success:function(data){

				        var photo = document.getElementsByName("count");
				        var photoCount = photo.length;
				        //根据图片的数量，来判断是否需要使用jquery来添加图片
				        if(photoCount<16){
				        $("#photos").append("<div class='photo'  onmouseover='showBox(this);' onmouseout='closeBox(this);' id='"+data.photo_id+" '>"
						        			+"<div class='photoimg'>"
						        			+"<div class='operator'>"
						        			+"<img alt='imgae' src='${pageContext.request.contextPath }/image/photo/operatorBt.png' id='"+data.photo_id+"' onclick='showOperator(this);'>"
						        			+"<ul id='operator_list_'"+data.photo_id+">"
						        			+"<li>设为封面</li>"
						        			+"<li>删除</li>"
						        			+"</ul>"
						        			+"</div>"
						        			+"<div class='showOperator' id='operator_"+data.photo_id+"' >"
						        			+"dfsdf "
						        			+"</div>"
						        			+"<img alt='img' src='${pageContext.request.contextPath }/"+data.url+"' width='170' height='126'>"
						        			+"</div>"
						        			+"<p>"+data.name+"</p>"
						        			+"<input type='hidden' id='photoId' value='"+data.photoId+"'>"
						        			+"</div>"
				        					);
						}
								
				    }
				});
			}
			function createImage1(){
					var xhr = ajaxFunction();
					xhr.onreadystatechange = function(){
						if(xhr.readyState==4){
								if(xhr.status==200||xhr.status==304){
									alert("haha");
									var data = xhr.responseText;
									alert("haha");
									var json = eval("("+data+")");
									alert("json = "+json);
									
								}
							}
					 };
					//var requestUrl = projectName+"/blog/albumAction_getPhoto.do";
					var requestUrl ="<%=basePath %>blog/albumAction_getPhoto.do";
					alert(requestUrl);
					xhr.open("post",requestUrl,true);
					alert("haha");
					//如果是POST请求方式，设置请求首部信息
			 		xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
					alert("haha");
					send(null);
					alert("haha");
				} 
			function setForthCover(thisValue) {
				var action = thisValue.href;
				thisValue.href="";
				alert(action);
				$.ajax({type:'POST',
				    url:action,
				    data:'',
				    timeout:5000,
				    success:function(data){
				    	alert(data);
				    }
				  
				 });
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
					<form action="" method="post">
						<input type="text" id="search_content" name="search_content">
						<input type="image" name="search_bt" id="search_bt" src="${pageContext.request.contextPath }/image/blog/search_bt.gif"/>
					</form>
				</div>
			</div>
			<!-- 显示头部信息和导航栏 -->
			<div id="showHeadInfo">
				<img alt="img" src="${pageContext.request.contextPath }/${globle_user.headImage}" width="140" height="160" id="headImage">
				<div id="nav">
					<h2 style="color:#211551">makezhekaboge</h2>
					<ul>
						<li><a href="">Home</a></li>
						<li><a href="">Blog</a></li>
						<li><a href="">Subscribe</a></li>
						<li><a href="">Photo</a></li>
						<li><a href="">Contact</a></li>
						
					</ul>
				</div>
			</div>
			<div id="shwoAll">
				<div id="showAlbumInfo">
					<img alt="imgage" src="${pageContext.request.contextPath }/image/photo/4.jpg" width="80" id="albumImage">
					<div id="albumInfo">
						<h3>xiangce</h3>
						<!-- <input type="image" src="image/photo/transform_photo.png"> -->
						<form method="post" name="form1" id="form1" enctype ="multipart/form-data">
							<a href="#" class="file">上传图片
								<input type="file" name="myFile" id="myFile" onchange="uploadPhoto();">
							</a>
								<input type="hidden" name="album_id" value="${request.album_id }">	
						</form>
						<iframe name="hidden_frame" id="hidden_frame"></iframe>
					</div>
				</div>
				<div id="photos">
					<s:if test="#request.photoList!=null&& #request.photoList.size()>0">
					<s:iterator value="#request.photoList" var="photo">
						<div class="photo"  onmouseover="showBox(this);" onmouseout="closeBox(this);" id="<s:property value="#photo.photo_id"/>">
							<div class="photoimg">
								<div class="operator">
									<img  alt="imgae" src="${pageContext.request.contextPath }/image/photo/operatorBt.png" id="operatorBt_<s:property value="#photo.photo_id"/>" onclick="showOperator(this);">
									
									<ul id="operator_list_<s:property value="#photo.photo_id"/>">
										<li><a href="albumAction_setFrontCover.do?album_id=${request.album_id }&url=<s:property value="#photo.url"/>" onclick="setForthCover(this);">设为封面</a></li>
										<li><a href="albumAction_deletePhoto.do?album_id=${request.album_id }&photo_id=<s:property value="#photo.photo_id"/>" onclick="del(this);">删除</a></li>
									</ul>
								</div>
								<div class="showOperator" id="operator_<s:property value="#photo.photo_id"/>" >
									dfsdf 
								</div>
								<img alt="img" src="${pageContext.request.contextPath }/<s:property value="#photo.url"/>" width="170" height="126">
							</div>
							<p><s:property value="#photo.name"/></p>
							<input name="count" type="hidden" id="photoId" value="<s:property value="#photo.photo_id"/>">
						</div>
					</s:iterator>
					</s:if>
					
				</div>
				<!-- 显示图片end -->
				<!-- 显示分页 -->
				<div id="fenye">
				<p>
				共<s:property value="#request.page.totalPage" />页 &nbsp;&nbsp;
				<s:if test="#request.page.hasPrePage">
					<a href="albumAction_showAlbum.do?currentPage=1&album_id=${request.album_id }">首页&nbsp;&nbsp;</a>
					<a href="albumAction_showAlbum.do?currentPage=${page.currentPage -1 }&album_id=${request.album_id }">上一页&nbsp;&nbsp;</a>
				</s:if>
				<s:else>
					首页&nbsp;&nbsp;
					上一页
				</s:else>
				<%
					Page page1 = (Page)request.getAttribute("page");
					String album_id = (String)request.getAttribute("album_id");
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
							out.print("<a href='albumAction_showAlbum.do?currentPage="+i+"&album_id="+album_id+"'>"+ i+"&nbsp;&nbsp;</a>");
						}
					}
					
				 %>
				<s:if test="#request.page.hasNextPage">
					<a href="albumAction_showAlbum.do?currentPage=${page.currentPage +1 }&album_id=${request.album_id }">下一页&nbsp;&nbsp;</a>
					<a href="albumAction_showAlbum.do?currentPage=${page.totalPage }&album_id=${request.album_id }"> 尾页</a>
				</s:if>
				<s:else>
					下一页&nbsp;&nbsp;
					 尾页
				</s:else>
				<!-- 显示分页 -->
				</p>
				</div>
			</div>
	
	</body>
</html>