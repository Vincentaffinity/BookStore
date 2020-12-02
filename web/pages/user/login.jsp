<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录页面</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/hit.jpg" height="90" width="110" >
			<span class="wel_word">&nbsp;登录</span>
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>管理登录</h1>
							</div>
							<div class="msg_cont">
								<b></b>
								<c:if test="${!empty requestScope.loginmsg}">${requestScope.loginmsg}</span></c:if>
								<c:if test="${empty requestScope.loginmsg}"><span class="errorMsg">${empty requestScope.msg? "请输入用户名和密码": requestScope.msg}</span></c:if>
							</div>
							<div class="form">
								<form action="LoginServlet?method=login" method="post">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" value="${sessionScope.userSession.username}" id="username" />
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" value="${sessionScope.userSession.password}" id="password" />
									<br />
									<br/><label>七天免登录&nbsp;</label><input type="checkbox" name="checkbox"><br/>
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
								<div class="tit">
									<a href="pages/user/regist.jsp">立即注册</a>
								</div>
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