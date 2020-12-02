<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
	<script type="text/javascript">
		$(function () {

			$("#emptyCart").click(function () {
				var conf = confirm("请问是否要清空购物车？");
				if(!conf){
					return false;
				}
				location = "CartServlet?method=emptyCart";
			});

			$(".delBtn").click(function () {
				var bookId = $(this).attr("id");
				var bookName = $(this).attr("name");
				var conf = confirm("是否确定要删除" + bookName + "？");
				if(!conf){
					return false;
				}
				location = "CartServlet?method=delBookById&bookId=" + bookId;
			});

			$(".bookCountBtn").change(function () {
				var formerCount = this.defaultValue;
				var newCount = $(this).val();
				var bookId = $(this).attr("id");
				var countReg = /^[1-9][0-9]*$/;
				if(!countReg.test(newCount)){
					alert("输入的数量有误，需要重新输入！")
					$(this).val(formerCount);
					return false;
				}

				var stock = $(this).attr("name");
				if(parseInt(newCount) > parseInt(stock)){
					alert("库存不足请重新输入！");
					$(this).val(formerCount);
					return false;
				}
				// location = "CartServlet?method=changeCount&bookId="+bookId+"&count="+newCount;
				var url = "CartServlet?method=changeCount";
				var data = {"bookId": bookId, "count": newCount};
				var $tr = $(this).parent().next().next();

				$.getJSON(url, data, function (msg) {
					var bookAmount = msg.bookAmount;
					var totalCount = msg.totalCount;
					var totalAmount = msg.totalAmount;
					$(".b_count").text(totalCount);
					$(".b_price").text(totalAmount);
					$tr.text(bookAmount);
				});

			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/hit.jpg" height="90" width="110" >
			<span class="wel_word">&nbsp;购物车</span>
			<%@ include file="/WEB-INF/include/welcome.jsp"%>
	</div>
	
	<div id="main" class="box_order">
		<c:if test="${empty sessionScope.cart.cartItems}">
			<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
			<h1 align="center">购物车暂时为空，请前去<a href="index.jsp" style="color: red">购物</a></h1>
		</c:if>
		<c:if test="${!empty sessionScope.cart.cartItems}">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:forEach items="${sessionScope.cart.cartItems}" var="cartItem">
				<tr>
					<td>${cartItem.book.title}</td>
					<td><input name="${cartItem.book.stock}" id="${cartItem.book.id}" class="bookCountBtn" type="text" style="text-align: center" value="${cartItem.count}" size="2"></td>
					<td>${cartItem.book.price}</td>
					<td>${cartItem.book.price*cartItem.count}</td>
					<td><a class="delBtn" id="${cartItem.book.id}" name="${cartItem.book.title}">删除</a></td>
				</tr>
			</c:forEach>
		</table>
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalAmount}</span>元</span>
			<span class="cart_span"><a id="emptyCart">清空购物车</a></span>
			<span class="cart_span"><a href="index.jsp">继续购物</a></span>
			<span class="cart_span"><a href="OrderServlet?method=createOrder">去结账</a></span>
		</div>
		</c:if>
	</div>
	
	<div id="bottom">
		<span>
			哈工大图书.Copyright &copy;2020
		</span>
	</div>
</body>
</html>