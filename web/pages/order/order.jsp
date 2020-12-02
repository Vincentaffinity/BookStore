<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
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
			<span class="wel_word">&nbsp;我的订单</span>
			<%@include file="/WEB-INF/include/welcome.jsp"%>
	</div>
	<div id="main" class="box_order">
		<table>
			<tr>
				<td>订单号</td>
				<td>订单日期</td>
				<td>订单金额</td>
				<td>订单数量</td>
				<td>订单状态</td>
				<td>订单详情</td>
			</tr>
			<c:forEach items="${sessionScope.orderList}" var="order">
			<tr>
				<td>${order.id}</td>
				<td>${order.orderTime}2015.04.23</td>
				<td>${order.totalAmount}</td>
				<td>${order.totalCount}</td>
				<td>
					<c:if test="${order.id == 0}">未发货</c:if>
					<c:if test="${order.id == 1}">已发货</c:if>
					<c:if test="${order.id == 2}">交易完成</c:if>
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