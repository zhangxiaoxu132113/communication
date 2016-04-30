window.onload=init;

function init(){
	
	var register = document.getElementById("register");
	register.onclick = checkInput;
}
/*判断用户输入的值是否为，，空*/
function checkInput(){
	
	var username = document.getElementById("username");
	var qq = document.getElementById("qq");
	var email = document.getElementById("email");
	var password = document.getElementById("password");
	/*var password = document.get*/
	if(username.value==""){
		
		alert("用户名输入不能为空！");
		return ;
	}else if(qq.value==""){
		alert("qq不能为空，请输入你的qq号码！");
		return;
	}else if(email.value==""){
		alert("电子邮箱不能为空！");
		return;
	}else if(password.value=""){
		alert("密码不能为空，请输入你的用户密码！");
		return;
	}
	alert("提交表单");
	var register = document.getElementById("register_from");
	register.submit;
}

