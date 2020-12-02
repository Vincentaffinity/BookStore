<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
<style type="text/css">
	.login_form{
		height:450px;
		margin-top: 13px;
	}
</style>
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
<script type="text/javascript">
 		$(function(){
			//验证注册中的数据，是否合法
			$("#sub_btn").click(function(){
				//定义用户名&密码的验证规则
				var uapReg = /^\w{6,18}$/;
				//定义邮箱正则 规则  sdfsdfsd.sd@sdf5-sdf4.com
				var emailReg = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.[a-z]+)+$/;
				
				//验证用户名是否合法
				var unValue = $("#username").val();
				if(!uapReg.test(unValue)){
					alert("用户名输入有误，请重新输入！【用户名只能是字母（大小写）、数字、_。6-18位。】");
					//取消submit按钮的默认行为
					return false;
				}
				//验证密码是否合法
				var pwdValue = $("#password").val();
				if(uapReg.test(pwdValue) == false){
					alert("密码输入有误，请重新输入！【密码只能是字母（大小写）、数字、_。6-18位。】");
					return false;
				}
				//验证两次密码是否一致
				var rePwdValue = $("#repwd").val();
				if(rePwdValue != pwdValue){
					alert("两次密码输入不一致，请重新输入！");
					return false;
				}
				//验证邮箱是否合法
				var emailValue = $("#email").val();
				if(emailReg.test(emailValue)==false){
					alert("邮箱格式不正确，请重新输入！");
					return false;
				}
				//验证【验证码】是否为空
				var codeValue = $("#code").val();
				if(codeValue==""){
					alert("验证码不能为空，请重新输入！");
					return false;
				}
			});

			$("form img").click(function () {
				$(this).prop("src", "KaptchaServlet?random=" + Math.random());
			});

			$("#username").change(function () {
				var user = $(this).val();
				var url = "RegistServlet?method=checkUser";
				var data = {"name": user};
				$.get(url, data, function (msg) {
					if(msg == "true"){
						$(".msg_cont .errorMsg").text("用户名可用").css("color", "green");
					}else {
						$(".msg_cont .errorMsg").text("用户名已存在，请重新输入!").css("color", "red");
					}
				},"text");
			});
		});

</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/hit.jpg" height="90" width="110" >
			<span class="wel_word">&nbsp;注册</span>
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册用户</h1>
								<span class="errorMsg"></span>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">${empty requestScope.msg? "请输入用户名和密码": requestScope.msg}</span>
							</div>
							<div class="form">
								<form action="RegistServlet?method=regist" method="post">
									<label>用户名称：</label>
									<input value="${param.username}" class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input value="${param.email}" class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" />
									<br />
									<br />
									<label>验证码：</label>
									<input name="vcode" class="itxt inp" type="text" style="width: 155px;" id="code"/>
									<img alt="" src="KaptchaServlet" style="float: right; margin-r:60px; width: 85px; height: 40px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<div id="bottom">
			<span>
				哈工大图书.Copyright &copy;2020
			</span>
		</div>
</body>
</html>