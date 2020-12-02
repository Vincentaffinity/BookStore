<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>书城首页</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
	<script>
		$(function () {

			// 跳转servlet进行添加
			$(".book_add button").click(function () {
				var bookId = $(this).attr("name");
				location = "CartServlet?method=addBookToCart&bookId="+bookId;
			});

			$(".book_cond button").click(function () {
				var value = $("#pn_input").val();
				var min = $("#min").val();
				var max = $("#max").val();
				location = "BookClientServlet?method=getBooksByRange&pageNo="+value+"&min="+min+"&max="+max;
			});

			$("#btnSub").click(function () {
				var value = $("#pn_input").val();
				var min = $("#min").val();
				var max = $("#max").val();
				location = "BookClientServlet?method=getBooksByRange&pageNo="+value+"&min="+min+"&max="+max;
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/hit.jpg" height="90" width="110">
			<span class="wel_word">&nbsp;网上书城</span>
			<%@ include file="/WEB-INF/include/welcome.jsp"%>
	</div>
	
	<div id="main">
		<div id="book">
			<div class="book_cond">
				价格：<input type="text" name="min" id="min" value="${param.min}">
					<span class="sp1">元</span> - 
					<input type="text" name="max" id="max" value="${param.max}">
					<span class="sp1">元</span> 
					<button>查询</button>
			</div>
			<div class="cont">
				<c:if test="${!empty sessionScope.cart.totalCount}">
					<span>您的购物车中有<span style="color: red">${sessionScope.cart.totalCount}</span>件商品</span>
				</c:if>
				<c:if test="${!empty sessionScope.bookTitle}">
					您刚刚将<span style="color: red">${sessionScope.bookTitle}</span>加入到了购物车中
					<c:remove var="bookTitle" scope="session"></c:remove>
				</c:if>
				<c:if test="${!empty sessionScope.bookCountMsg}">
					<span style="color: red">&emsp;${sessionScope.bookCountMsg}</span>
					<c:remove var="bookCountMsg" scope="session"></c:remove>
				</c:if>
			</div>
			<c:forEach items="${requestScope.page.list}" var="book">
				<div class="b_list">
					<div class="img_div">
						<img class="book_img" alt="" src="${book.imgPath}" />
					</div>
					<div class="book_info">
						<div class="book_name">
							<span class="sp1">书名:</span>
							<span class="sp2">${book.title}</span>
						</div>
						<div class="book_author">
							<span class="sp1">作者:</span>
							<span class="sp2">${book.author}</span>
						</div>
						<div class="book_price">
							<span class="sp1">价格:</span>
							<span class="sp2">￥${book.price}</span>
						</div>
						<div class="book_sales">
							<span class="sp1">销量:</span>
							<span class="sp2">${book.sales}</span>
						</div>
						<div class="book_amount">
							<span class="sp1">库存:</span>
							<span class="sp2">${book.stock}</span>
						</div>
						<div class="book_add">
							<button name="${book.id}">加入购物车</button>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<div id="page_nav">
			<a href="BookClientServlet?method=getBooksByRange&pageNo=${1}&min=${param.min}&max=${param.max}">首页</a>
			<c:if test="${requestScope.page.pageNo > 1}">
				<a href="BookClientServlet?method=getBooksByRange&pageNo=${requestScope.page.pageNo-1}&min=${param.min}&max=${param.max}">上一页</a>
			</c:if>
			<c:choose>
				<c:when test="${page.totalPageNo<5}">
					<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="${page.totalPageNo}"></c:set>
				</c:when>
				<c:when test="${page.pageNo<=3}">
					<c:set var="begin" value="1"></c:set>
					<c:set var="end" value="5"></c:set>
				</c:when>
				<c:when test="${page.pageNo>3 && page.pageNo<=page.totalPageNo-2}">
					<c:set var="begin" value="${page.pageNo-2}"></c:set>
					<c:set var="end" value="${page.pageNo+2}"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="begin" value="${page.totalPageNo-4}"></c:set>
					<c:set var="end" value="${page.totalPageNo}"></c:set>
				</c:otherwise>
			</c:choose>
			&nbsp;
			<c:forEach var="num" begin="${begin}" end="${end}">
				<c:if test="${page.pageNo == num}">
					【${num}】
				</c:if>
				<c:if test="${page.pageNo != num}">
					<a href="BookClientServlet?method=getBooksByRange&pageNo=${num}&min=${param.min}&max=${param.max}">${num}</a>&nbsp;
				</c:if>
			</c:forEach>
			&nbsp;
			<c:if test="${requestScope.page.pageNo < requestScope.page.totalPageNo}">
				<a href="BookClientServlet?method=getBooksByRange&pageNo=${requestScope.page.pageNo+1}&min=${param.min}&max=${param.max}">下一页</a>
			</c:if>
			<a href="BookClientServlet?method=getBooksByRange&pageNo=${requestScope.page.totalPageNo}&min=${param.min}&max=${param.max}">末页</a>
			共${requestScope.page.totalPageNo}页，${requestScope.page.totalRecord}条记录 到第<input value="4" name="pn" id="pn_input"/>页
			<input id="btnSub" type="button" value="确定">
		</div>
	
	</div>
	
	<div id="bottom">
		<span>
			哈工大图书.Copyright &copy;2020
		</span>
	</div>
</body>
</html>