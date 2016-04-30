<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://ckeditor.com" prefix="ck" %>
<!DOCTYPE html>
<html>
	<head>
		<title>this is write blog page</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/writeBlog.css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/ckeditor4.1/ckeditor.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.9.1.min.js"></script>
		<script type="text/javascript">
	    var editor = null;
	    var isShowTag = false;
	    window.onload = function(){
	    	//参数‘content’是textarea元素的name属性值，而非id属性值
	        /* editor = CKEDITOR.replace('content',{ height: '500px'});  */
	        setCreateTime();//给创建时间赋值
	       	sendBlog();
	       	showTag();//显示标签的div
	        closeTag();//关闭标签的div
	        getTags();//获取标签的值
	    }
	    function add0(m){return m<10?'0'+m:m }
		function format(shijianchuo){
			//shijianchuo是整数，否则要parseInt转换
			var time = new Date(shijianchuo);
			var y = time.getFullYear();
			var m = time.getMonth()+1;
			var d = time.getDate();
			var h = time.getHours();
			var mm = time.getMinutes();
			var s = time.getSeconds();
			return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
		}
		function setCreateTime(){
			//给时间赋值
	        /* var currentTime = new Date(); */
	        var currentTime = format(new Date());
	        document.getElementById("createTime").value=currentTime;
		}
		function sendBlog(){
			document.getElementById("sendBlog").onclick=function(){
				document.form2.action="blog/blogAction_addBlog.do";
				alert("add bLOG");
				document.form2.submit();
			}
			
		}
		/*  显示标签的div */
	    function showTag(){
	    	document.getElementById("tags").onclick=function(){
		    	if(!isShowTag){
	    			isShowTag = true;
	    			/* document.getElementById("showTag").style.display='block'; */
	    			 $("#showTag").slideDown("slow");//这里使用jquery的滑动效果
	    		}else if(isShowTag){
	    			isShowTag = false;
		    		/* document.getElementById("showTag").style.display='none'; */
		    		 $("#showTag").slideUp("slow");//这里使用jquery的滑动效果
	    		}
	    	}
	    }
	    /*  关闭标签的div */
	    function closeTag(){
	    	document.getElementById("closeTag").onclick=function(){
		    	/* document.getElementById("showTag").style.display='none'; */
		    	$("#showTag").slideUp("slow");//这里使用jquery的滑动效果
	    	}
	    	/* document.getElementById("tags").onblur=function(){
		    	$("#showTag").slideUp("slow");//这里使用jquery的滑动效果
	    	} */
	    }
	    
	    function getTags(){
	    	var showTag = document.getElementById("showTag");
	    	var tags = showTag.getElementsByTagName("label");
	    	var tag_input = document.getElementById("tags");
	    	for(var i=0;i<tags.length;i++){
	    		tags[i].onclick=function(){
	    			if(tag_input.value == ""){
	    				tag_input.value+=this.innerHTML;
	    			}else{
	    				tag_input.value+=","+this.innerHTML;
	    			}
	    				
	    		}
	    	}
	    }
	    
	</script>
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
	        $("#dialog").show();
	    }
	    //关闭灰色 jQuery 遮罩
	    function closeBg() {
	        $("#fullbg,#dialog").hide();
	    }
	</script>
	<script type="text/javascript">
	    // 浮动对话框
	    $(document).ready(function() {
	        var domThis = $('#dialog')[0];
	        var wh = $(window).height() / 2;
	        $("body").css({
	            /* "background-attachment": "fixed", */
	            /* "background-image": "url(about:blank)" */
	        });
	        domThis.style.setExpression('top', 'eval((document.documentElement).scrollTop + ' + wh + ') + "px"');
	    });
	</script>
	</head>
	<body>
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
					<h1>communication</h1>
				</div>
				<div id="search">
					<form action="" method="post">
						<input type="text" id="search_content" name="search_content">
						<input type="image" name="search_bt" id="search_bt" src="${pageContext.request.contextPath }/image/blog/search_bt.gif"/>
					</form>
				</div>
			</div>
			<!-- 主体内容 -->
			<div id="container">
			<form name="form2" id="form2" method="post">
					<h2 id="writeblog_title">写博文</h2>
					
					<div id="article_top">
						<label class="article_top_keyword">标题</label><input type="text" name="title" id="title"/>
						<label class="article_top_keyword">创建时间</label><input type="text" name="createTime" id="createTime" readonly="readonly"/>
						<input type="button" id="setTime" name="setTime" value="Set Timing" />
					</div>
					<!-- 编辑文章内容 -->
					<div id="article_content">
						<ck:editor   editor="content" basePath="/ckeditor4.1"  ></ck:editor>
						<!-- <textarea id="content" name="content"></textarea> -->
						<%-- <ck:replace replace="my_txt" basePath="/ckeditor4.1"></ck:replace> --%>
					</div>
					<div id="more_table">
						<div id="classification_div">
						<label class="more_table_cell">权限</label>
						<div class="more_table_cell">
							<!-- 遍历获取类型对象 -->
							<s:if test="#request.types!=null">
								<s:select list="#request.types" name="type" id="type" 
										  listKey="type_id" listValue="typeName"
										  ></s:select>
							</s:if>
							
						</div>
					</div>
					<div id="tag_div">
						<label class="more_table_cell">分类</label>
						<div class="more_table_cell" id="fenlei">
							<select id="Classification" name="Classification">
								<option value="1" selected="selected">Select classifiaction</option>
								<option value="1">private blog</option>
								<option value="1">public blog</option>
							</select>
							&nbsp;&nbsp;&nbsp;<a id="createClass" onclick="showBg();">创建分类</a>
						</div>
					</div>
					<div id="tag_div">
						<label class="more_table_cell">标签</label>
						<div class="more_table_cell" id="tag_content">
							<input type="text" name="keywords" id="tags" /><label id="addTags" onclick="showBg();" >add tags</label>
							<div id="showTag">
								<img alt="closeTag" src="${pageContext.request.contextPath }/image/blog/menu_bullet.gif" id="closeTag"/>
								<!-- <label class="tag">jsp</label><label class="tag">java</label><label class="tag">Hadoop</label>
								<label class="tag">asp.net</label><label class="tag">lucene</label>
								<label class="tag">nutch</label><label class="tag">jbpm</label><label class="tag">php</label>
								<label class="tag">mysql</label><label class="tag">oracle</label> -->
								<s:if test="#request.keyWords!=null">
									<s:iterator value="#request.keyWords" id="keyword_id">
										<label class="tag"><s:property value="#keyword_id.keyWordName"/></label>
									</s:iterator>
								</s:if>
							</div>
							
						</div>
					</div>
					<div id="setting">
						<label class="more_table_cell">设置</label>
						<div class="more_table_cell">
							<input type="radio" name="" value="1"/>follow all command&nbsp;&nbsp;
							<input type="radio" name="" value="1"/>follow all command&nbsp;&nbsp;
							<input type="radio" name="" value="1"/>not follow all command&nbsp;&nbsp;<br><br>
							<input type="checkbox" name=""/>visible for self&nbsp;&nbsp;
							<input type="checkbox" name=""/>visible for everyone&nbsp;&nbsp;
						</div>
						
						
					</div>
					</div>
					<div id="article_form">
						<input type="button" id="sendBlog" name="sendBlog" value="提交" />
						<input type="button" id="save" name="save" value="保存到草稿箱"/>
					</div>
			</form>
			</div>
			<!--  -->
			<div id="toolSider">
				<ul>
					<li><a href="#"><img alt="send image" src="${pageContext.request.contextPath }/image/blog/send.png" title="Send Blog"></a></li>
					<li><a href="#"><img alt="save image" src="${pageContext.request.contextPath }/image/blog/save.png" title="Save Blog"></a></li>
					<li><a href="#"><img alt="home image" src="${pageContext.request.contextPath }/image/blog/home.png" title="return home"></a></li>
					<li><a href="#"><img alt="return top image" src="${pageContext.request.contextPath }/image/blog/return_top.png" title="return top"/></a></li>
				</ul>
			</div>
			<!-- jQuery 遮罩层 -->
			<div id="fullbg"></div>
			<!-- end jQuery 遮罩层 -->
			<!-- 对话框 -->
			<div id="dialog">
			  <p class="close"><a href="#" onclick="closeBg();">关闭</a></p>
			  <p>正在加载，请稍后...</p>
			</div>
			<!-- jQuery 遮罩层上方的对话框 -->

	</body>
	
	
</html>
