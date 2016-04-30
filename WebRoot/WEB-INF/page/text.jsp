<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'text.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
		function submit(){
			document.form1.action="system/textAction_add.do";
			alert("点击提交页面");
			document.form1.submit();	
		}
	</script>

  </head>
  
  <body>
    <form name="form1" id="form1" method="post" action="system/textAction_add.do"> 
    	testName:<input name="testName" type="text" ><br>
    	testDate:<input name="testDate" type="text" ><br>
    	testRemark:<input name="testRemark" type="text" ><br>
    	<input type="submit" value="add" name="test">
    	<!-- <input type="button" value="submit" onclick="submit()" name="add"> -->
    </form>
  </body>
</html>
