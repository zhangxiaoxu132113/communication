<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<html>
	<head>
		<title>My Home Page</title>
		<meta charset="UTF-8">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/home.css">
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/bgCanvas.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.9.1.min.js"></script>
	</head>
	<body>
		
		<canvas width="1200" height="98" id="bg_canvas">
			oh ,no!you can't use canvas!
		</canvas>
		<div id="container">
			<!-- 页面的头部信息 -->
			<div id="header">
				<div id="navigtor">
					<ul>
						<li><a href="#"><img alt="home" src="${pageContext.request.contextPath }/image/home/home02.png" width="20" height="20">个人中心</a></li>
						<li><a href="#"><img alt="home" src="${pageContext.request.contextPath }/image/home/message01.png" width="20" height="20">消息</a></li>
						<li><a href="#"><img alt="home" src="${pageContext.request.contextPath }/image/home/friend02.png" width="20" height="20">好友</a></li>
						<li><a href="#"><img alt="home" src="${pageContext.request.contextPath }/image/home/setting.png" width="20" height="20">设置</a></li>
					</ul>
				</div>
				<div id="logo">
					<h1>Communication</h1>
					<div id="search_div" >
					<form action="" method="get" name="search_form" id="search_form" >
						<input type="text" name="userId" id="search" placeholder="搜素内容，找人" onfocus="showSearchTipContent()" onblur="hiddenSearchTipContent()"><!-- onblur="hiddenSearchTipContent()" -->
						
						<!-- 显示用户搜索的内容 -->
						<div class="search_tip">
							<!-- 显示搜索的人物 -->
							<ul id="search_info_show">
								<li><a><span>搜索""相关用户</span></a></li>
								<!-- <li><span>木村拓哉</span> <input type="button" value="添加" onclick="addFriend()"></li> -->
								<!-- <li><span>玉木宏</span> <input type="button" value="添加" onclick="addFriend()"></li> -->
							</ul>
							<!-- 显示搜索的内容 -->
							<ul>
								<li><a id="searchSubmit"><span>搜索""相关内容</span></a></li>
							</ul>
						</div>
						<input type="image" name="search_bt" id="search_bt" src="${pageContext.request.contextPath }/image/home/search_bt.gif"/>
					</form>
					</div>
				</div>
			</div>
			<!-- 页面的主体内容信息 ,将主体内容分为三个部分-->
			<div id="main">
				<div id="main_row">
					<!-- 右边内容 -->
					<div id="main_left">
						<!-- 用户的头像和关注信息 -->
						<div id="head_info">
							<div id="touxiang"><img alt="touxiang image" src="${pageContext.request.contextPath }/${globle_user.headImage}" width="100px" height="100px"></div>
							<div id="profile_info1" align="center">
								<h3>${globle_user.userName}</h3>
								<span>Lv2</span>
							</div>
							
							<div id="attendtion">
								<div id="attendtion_row">
									<div  class="attendtion_cell">
										<p><span class="attendtion_number"><a href="/system/userAction.do">180</a></span><br><span class="attendtion_key">关注</span><p>
									</div>
									<div class="attendtion_cell">
										<p><span class="attendtion_number"><a href="/system/userAction.do">147</a></span><br><span class="attendtion_key">粉丝</span><p>
									</div>
									<div class="attendtion_cell_">
										<p><span class="attendtion_number"><a href="/system/userAction.do">257</a></span><br><span class="attendtion_key">微说</span><p>
									</div>
								</div>
							</div>
						</div>
						<!-- 用户的基本的信息 -->
						<div id="base_info">
							<p class="base_info_row">
								<img alt="address image" src="${pageContext.request.contextPath }/image/home/address.gif" class="base_info_img">
								<span class="base_info_cell">现居地</span>
								<span class="base_info_content_cell">${globle_user.address}</span>
							</p>
							<p class="base_info_row">
								<img alt="university image" src="${pageContext.request.contextPath }/image/home/university.gif" class="base_info_img">
								<span class="base_info_cell">毕业于</span>
								<span class="base_info_content_cell">${globle_user.university}</span>
							</p>
							<p class="base_info_row">
								<img alt="status image" src="${pageContext.request.contextPath }/image/home/status.gif" class="base_info_img">
								<span class="base_info_cell">状态</span>
								<span class="base_info_content_cell">${globle_user.statusVO}</span>
							</p>
							<p class="base_info_row">
								<img alt="birthday image" src="${pageContext.request.contextPath }/image/home/birthday.gif" class="base_info_img">
								<span class="base_info_cell">生日</span>
								<span class="base_info_content_cell">${globle_user.birthday}</span>
							</p>
						</div>
						<!-- 用户的文章标题栏 -->
						<%-- <div id="article_info">
							<h2 class="message_board_title">校园热议榜</h2>
							<div id="article_table">
								<div class="article_info_class">
									<img alt="article icon image" src="${pageContext.request.contextPath }/image/home/article_icon.gif" class="article_image_cell">
									<p class="article_content_cell">
										<span class="article_title">Your Blog Title</span><br>
										<span class="article_content">
											This is article content ,you cna weite your content what 
											you want to express .so we are welcome for you to join plan with 
											apply by our term.
										</span>
									</p>
								</div>
								<div class="article_info_class">
									<img alt="article icon image" src="${pageContext.request.contextPath }/image/home/article_icon.gif" class="article_image_cell">
									<p class="article_content_cell">
										<span class="article_title">I am Tamaki</span><br>
										<span class="article_content">
											16 years old when the Broker's Firm to buy something, after 
											graduating from high school performing arts activities, in 
											1998 to TV drama sad official debut
										</span>
									</p>
								</div>
							</div>
						</div> --%>
						<div id="list_info">
							<h3>校园热议榜</h3>
							<ul>
								<li class="list_info_selected">
									<img alt="" src="${pageContext.request.contextPath }/image/tmp/xiaoyuanbang.jpg" width="70" height="95">
									<span><label>1</label>毕业季旅游</span>
									<p>
										毕业即将来临，如果可以去旅游的话，你最想去哪里玩呢？
									</p>
								
								</li>
								<li><span><label>2</label>校园十大歌手</span></li>
								<li><span><label>3</label>校园里的那些年</span></li>
							</ul>
							<h4>参看更多&gt;</h4>
						</div>
						
						<div id="multual_friend">
							<h3>你可能认识的人</h3>
							<ul>
								<li>
									<img alt="friend image" src="${pageContext.request.contextPath }/image/home/friend1.png" width="75" height="53">
									<span>John</span>
								</li>
							    <li>
							    	<img alt="friend image" src="${pageContext.request.contextPath }/image/home/friend2.png" width="75" height="53">
									<span>Smith</span>
							    </li>
								<li>
									<img alt="friend image" src="${pageContext.request.contextPath }/image/home/friend3.png" width="75" height="53">
									<span>Li Ming</span>
								</li>
							</ul>
							<h4>参看更多好友&gt;</h4>
						</div>
						
						<!-- 发布广告的位子 -->
						<div id="advertise_info">
							<img alt="advertise image" src="${pageContext.request.contextPath }/image/home/advertise_img.png">
						</div>
						
					</div>
					<!-- 中间内容 -->
					<div id="main_center">
						<!-- 个人的简介信息 -->
						<!-- <form action="" method="post" id="search_from1">
							<input type="text" name="search_content_1" id="search_content_1" placeholder="search my content"/>
						</form> -->
						<!-- 二级导航栏 -->
						<%-- <div id="second_navigtor">
							<ul>
								<li id="home"><a href="#">主页</a></li>
								<li id="blog"><a href="${pageContext.request.contextPath }/blog/blogAction_home.do">博客</a></li>
								<li id="journal"><a href="#">日志</a></li>
								<li id="photo"><a href="${pageContext.request.contextPath }/blog/blogAction_photoUI.do">相册</a></li>
								<li id="message_board_"><a href="#">消息</a></li>
							</ul>
						</div> --%>
						<div class="sendWeibo">
							<!-- <h3>有什么新鲜事想告诉大家</h3> -->
							<img alt="分享微博" src="${pageContext.request.contextPath }/image/home/fenxiang.png" width="180" height="20">
							<span></span>
							<div id="sendWeibo_content" class="sendWeibo_content" contenteditable="true" >
							</div>
							<div class="operator_content">
								<input type="button" value="发送" id="sendBtn" onclick="sendStatus()"/>
							</div>
						</div>
						<div id="main_info">
							<div class="info">
								<!-- <span class="info_status">open</span> -->
								<p class="info_user">
									<a href="personhome.html"><img alt="tou xiang image" src="${pageContext.request.contextPath }/image/home/touxiang2.bmp" class="touxiang" title="访问该用户" /></a><br>
									<span class="user_head_info">Amy</span>
								</p>
								<p class="info_content">
									<span>
									一个3.5公顷的花园你能想象嘛？位于日本茨城县日立海滨公园，有一个异常美丽的旅游景点：3.5公顷的花朵公园。在公园内，大量的花朵一年四季都开花，
									并且随季节而改变。在公园里最好的观点叫“Miharashi No Oka”，一座小山，在这里可以俯瞰整个花的海洋。
									</span>
									<span>
										<img alt="" src="${pageContext.request.contextPath }/image/tmp/flower01.jpg" width="80px" height="80px"/>
									    <img alt="" src="${pageContext.request.contextPath }/image/tmp/flower02.jpg" width="80px" height="80px"/>
									    <img alt="" src="${pageContext.request.contextPath }/image/tmp/flower03.jpg" width="80px" height="80px"/>
									<br><img alt="" src="${pageContext.request.contextPath }/image/tmp/flower04.jpg" width="80px" height="80px"/>
										<img alt="" src="${pageContext.request.contextPath }/image/tmp/flower05.jpg" width="80px" height="80px"/>
									</span>
									   11月22日 21:58 
									<span>
									
									</span>
								</p>
								<p class="info_content1">
									<span class="info_command"><a href="/system/userAction.do">收藏</a></span>
									<span class="info_command"><a href="/system/userAction.do">转发</a></span>
									<span class="info_command"><a href="/system/userAction.do">评论</a></span>
									<span class="info_command_extend"><a href="/system/userAction.do">点赞 12</a></span>
								</p>
							</div>
							<div class="info">
								<!-- <span class="info_status">private</span> -->
								<p class="info_user">
									<a href="personhome.html"><img alt="tou xiang image" src="${pageContext.request.contextPath }/image/home/321.jpg" class="touxiang" title="访问该用户" /></a><br>
									<span class="user_head_info">玉木宏</span>
								</p>
								<p class="info_content">
									<span>
									卮言春天 破碎秋千，踟蹰不如停止抱歉，再过秋天 烂了蜿蜒，红灯你搁浅，只是你迟到一千年，
									黄昏后就不会有夜，发间在印象中被蔓延，你说你放弃了八月，其实不需要蜻蜓点水，
									打昏自己食髓知味，吞了你用力一口下餍，捧起碗在倥侗增添。 分享苏打绿的《迟到千年》
									</span>
									<span>
									   11月22日 21:58 
									</span>
								</p>
								<p class="info_content1">
									<span class="info_command"><a href="/system/userAction.do">收藏</a></span>
									<span class="info_command"><a href="/system/userAction.do">转发</a></span>
									<span class="info_command"><a href="/system/userAction.do">评论</a></span>
									<span class="info_command_extend"><a href="/system/userAction.do">点赞 12</a></span>
								</p>
							</div>
							<div class="info">
								<p class="info_user">
									<!-- <span class="info_status">private</span> -->
									<a href="personhome.html"><img alt="tou xiang image" src="${pageContext.request.contextPath }/image/home/touxiang3.jpg" class="touxiang" title="访问该用户" /></a><br>
									<span class="user_head_info">千秋真一</span>
								</p>
								<p class="info_content">
									<span>
									【在东京要做的10件事】：1.去浅草寺求签； 2.学一堂日料课 ；3.去迪斯尼寻找童年；
									 4.到歌舞伎町流连声色； 5.去东京塔感受浪漫； 6.参观古香古色的明治神社； 
									 7.在涩谷街头邂逅时髦男女； 8.坐在路边的居酒屋吃顿螃蟹宴； 9.到三鹰膜拜宫崎骏 ；10.在新宿俯瞰东京夜色。
									 </span>
									 <span>
									 <img alt="" src="${pageContext.request.contextPath }/image/tmp/dongjing01.jpg" width="80px" height="80px"/>
									    <img alt="" src="${pageContext.request.contextPath }/image/tmp/dongjing02.jpg" width="80px" height="80px"/>
									    <img alt="" src="${pageContext.request.contextPath }/image/tmp/dongjing03.jpg" width="80px" height="80px"/>
									<br><img alt="" src="${pageContext.request.contextPath }/image/tmp/dongjing04.jpg" width="80px" height="80px"/>
										<img alt="" src="${pageContext.request.contextPath }/image/tmp/dongjing05.jpg" width="80px" height="80px"/>
									</span>
									<span>
									   11月22日 21:58 
									</span>
								</p>
								<p class="info_content1">
									<span class="info_command"><a href="/system/userAction.do">收藏</a></span>
									<span class="info_command"><a href="/system/userAction.do">转发</a></span>
									<span class="info_command"><a href="/system/userAction.do">评论</a></span>
									<span class="info_command_extend"><a href="/system/userAction.do">点赞 12</a></span>
								</p>
							</div>
							
						</div>
					</div>
					<!-- 左边内容 -->
					<div id="main_right">
						<!-- 导航栏 -->
						<div id="second_navigtor">
							<ul>
								<li id="home"><a href="#">主页</a></li>
								<li id="blog"><a href="${pageContext.request.contextPath }/blog/blogAction_home.do">博客</a></li>
								<li id="journal"><a href="#">日志</a></li>
								<li id="photo"><a href="${pageContext.request.contextPath }/blog/blogAction_photoUI.do">相册</a></li>
								<li id="message_board_"><a href="#">消息</a></li>
							</ul>
						</div>
						<!-- 好友给你的留言 -->
						
						<%-- <h2 class="message_board_title">Message Board</h2>
						<div id="message_board">
							<div class="messageOfFriend">
								<span class="message_friend"><img alt="friend image" src="${pageContext.request.contextPath }/image/home/friend2.png" width="55" height="50">John</span>
								<p class="message_content">
									Hey, my man, are you free tomorrow, going to the gun.
								</p>
							</div>
							<div class="messageOfFriend">
								<span class="message_friend"><img alt="friend image" src="${pageContext.request.contextPath }/image/home/friend3.png" width="55" height="50">John</span>
								<p class="message_content">
									Hey, my man, are you free tomorrow, going to the gun.
								</p>
							</div>
							<div class="messageOfFriend">
								<span class="message_friend"><img alt="friend image" src="${pageContext.request.contextPath }/image/home/friend4.png" width="55" height="50">John</span>
								<p class="message_content">
									Hey, my man, are you free tomorrow, going to the gun.
								</p>
							</div>
						</div> --%>
						<!-- 朋友推荐功能 -->
						<%-- <h2 class="message_board_title">Multual Friend</h2>
						<div id="multual_friend">
							<div class="multual_friend_row">
								<div class="friend_like">
									<img alt="friend image" src="${pageContext.request.contextPath }/image/home/friend1.png"><br>
									<span>John</span>
								</div>
								<div class="friend_like">
									<img alt="friend image" src="${pageContext.request.contextPath }/image/home/friend2.png"><br>
									<span>Smith</span>
								</div>
							</div>
							<div class="multual_friend_row">
								<div class="friend_like">
									<img alt="friend image" src="${pageContext.request.contextPath }/image/home/friend3.png"><br>
									<span>Li Ming</span>
								</div>
							</div>
						</div> --%>
					</div>
				</div>
			</div>
			<div class="messageBoard">
				私信聊天、
			</div>
			<!-- 页面的尾部内容信息 -->
			<div id="footer">
				<p>copyright@</p>
				<p>welcome to join us,and communication others!</p>
			</div>
		</div>
	
	</body>
	<script type="text/javascript">
		var search_url = "";
		var userId = "";
		
		$('.sendWeibo_content').bind('input propertychange',function(){
			/* alert("dfdf"); */
			var content = $('.sendWeibo_content').text();
			
			if(content.length != 0&&content != " "){
				$('#sendBtn').css("backgroundColor","rgba(253,107,50,1.0)");
				//$('#sendBtn').attr("disabled","false");
			}else{
				$('#sendBtn').css("backgroundColor","rgba(253,107,50,0.56)");
				//$('#sendBtn').attr("disabled","true");
			}
		});
		
		function showSearchTipContent(){
			var search_tip = document.getElementsByClassName("search_tip")[0];
			search_tip.style.display = "block";
		}
		function hiddenSearchTipContent(){
			//alert(search_url);
			var search_tip = document.getElementsByClassName("search_tip")[0];
			search_tip.style.display = "none";
			if(search_url != "" && document.getElementById("search").value != ""){
				document.search_form.action = search_url;
				document.getElementById("search").value = userId;
				document.search_form.submit();
				document.getElementById("search").value = "";
			}
	
		}
		function submitInfo(){
			var searchContent = document.getElementById("search").value;
			alert(searchContent);
		}
		/*当鼠标移动到搜索提示内容的上方时调用*/
/* 		function showMyself(){
			document.getElementById("search").onblur = function(){};
		}
		function closeMyself(){
			document.getElementById("search").focus();
			document.getElementById("search").onblur = hiddenSearchTipContent();
		} */
		function getSubmitValue(obj){
			search_url = obj.href;
			userId = obj.id;
			//alert(search_url);
		}
		function cancalValue(){
			search_url = "";
			//alert(search_url);
		}
		function clearSearchInfo(){
			alert("clearSearchInfo");
			$("#search_info_show").empty();
		}
		/**
		请求方式：异步 + POST
		提交数据：status:微博内容
		*/
		function sendStatus(){
			alert("hahh");
			/* $(".sendWeibo_content:first").empty(); */
			$.ajax({
				type:'POST',
				url:'<%=basePath%>system/userAction_sendStatus.do',
				data:{text:$("#sendWeibo_content").text()},				
				dataType:'json',
				timeout:5000,
				success:function(data){
					//判断如果status_code为1则服务器端保存数据成功，如果为0则保存数据失败
					if(data != "" ) {
						$(".sendWeibo_content:first").empty();
						alert("数据保存成功");
						//在界面增加一条数据
						$("#main_info").prepend(
							"<div class='info'>"+
								"<p class='info_user'>"+
									
									"<a href='personhome.html'><img alt='tou xiang image' src='${pageContext.request.contextPath }/"+data.user.headImage+"' class='touxiang' title='访问该用户' /></a><br>"+
									"<span class='user_head_info'>"+data.user.userName+"</span>"+
								"</p>"+
								"<p class='info_content'>"+
									"<span>"+ data.text + "</span>"+
									 "<span>"+
									 "<img alt='' src='${pageContext.request.contextPath }/image/tmp/dongjing01.jpg' width='80px' height='80px'/>"+
									   
									"</span>"+
									"<span>"+ data.created_at.month+1+"月"+data.created_at.day+"日"+data.created_at.hours +":"+data.created_at.minutes+"</span>"+
								"</p>"+
								"<p class='info_content1'>"+
									"<span class='info_command'><a href='/system/userAction.do'>收藏</a></span>"+
									"<span class='info_command'><a href='/system/userAction.do'>转发"+data.reposts_count+"</a></span>"+
									"<span class='info_command'><a href='/system/userAction.do'>评论"+data.contents_count+"</a></span>"+
									"<span class='info_command_extend'><a href='/system/userAction.do'>点赞"+data.attitudes_count+"</a></span>"+
								"</p>"+
							"</div>"
						);
						$("#main_info").first().insertBefore(divObj);
					} else {
						alert("数据保存失败");
					}
				},
				error:function(){
					alert("异常");
				}
			});
		}
		//监听用户的输入
		$('#search').bind('input propertychange',function(){
			//先删除元素下的所有内容
			$("#search_info_show").empty();
			$.ajax({type:'POST',
				    url:'<%=basePath %>system/searchAction_search.do',
				    data:{searchKey:$(this).val()},
				    dataType:'json',
				    timeout:5000,
				    success:function(data){
				    	if(data != "" && data.length>0){
				    		/* alert(data[0].userName); */
					    	for(var i=0; i<data.length; i++){
						        $("#search_info_show").append(
						        	"<a id='"+data[i].id+"' onmouseover='getSubmitValue(this)' onclick='clearSearchInfo()' onmouseout='cancalValue()' href='<%=basePath %>system/userAction_findFriend.do?id="+data[i].id+"'><li><span>"+data[i].userName+"</span> <input type='button' value='添加' /></li></a>"
						        );
						        
					    	}
				    	}
				    }
			});
		});
	
		function getClass(node, classname) {
			if(node.getElementsByClassName) {
				return node.getElementsByClassName(classname);
			} else {
				var elems = node.getElementsByTagName(node),defualt = [];
				for (var i=0; i<elem.length; i++) {
					if(elems[i].className.indexOf(classname)!=-1){
						defualt[defualt.length] = elems[i];
					}
				}
				return defualt;
			}
		}
	</script>
</html>



