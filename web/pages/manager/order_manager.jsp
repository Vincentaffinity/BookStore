<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/hit.jpg" height="90" width="110" >
			<span class="wel_word">&nbsp;订单管理系统</span>
			<%@include file="/WEB-INF/include/header.jsp"%>
	</div>
	
	<div id="main" class="box_order">
		<table>
			<tr>
				<td>订单号</td>
				<td>订单日期</td>
				<td>订单金额</td>
				<td>订单数量</td>
				<td>订单详情</td>
				<td>发货状态</td>
			</tr>
			<c:forEach items="${sessionScope.orderList}" var="order">
				<tr>
					<td>${order.id}</td>
					<td>${order.orderTime.toString().substring(0,10)}</td>
					<td>${order.totalAmount}</td>
					<td>${order.totalCount}</td>
					<td>
						<c:if test="${order.state == 0}">未发货</c:if>
						<c:if test="${order.state == 1}">已发货</c:if>
						<c:if test="${order.state == 2}">交易完成</c:if>
					</td>
					<td><a href="#">查看详情</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	<div id="bottom">
		<span>
			哈工大图书.Copyright &copy;2020
		</span>
	</div>
</body>
</html>