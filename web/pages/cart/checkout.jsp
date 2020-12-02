<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>结算页面</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/hit.jpg" height="90" width="110" >
			<span class="wel_word">&nbsp;结算</span>
			<%@ include file="/WEB-INF/include/welcome.jsp"%>
	</div>
	
	<div id="main">
		
		<h1>你的订单已结算，订单号为<span style="color: red">${sessionScope.order}</span></h1>
		
	
	</div>
	
	<div id="bottom">
		<span>
			哈工大图书.Copyright &copy;2020
		</span>
	</div>
</body>
</html>