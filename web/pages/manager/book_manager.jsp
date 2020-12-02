<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/WEB-INF/include/base.jsp"%>
	<script>
		$(function () {
			$(".delBook").click(function () {
				var title = $(this).attr("name");
				var torf = confirm("是否删除图书"+title+"？");
				if(!torf){
					return false;
				}
			});

			$("#btnSub").click(function () {
				var value = $("#pn_input").val();
				location = "BookServlet?method=getBooksByPage&pageNo="+value;
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/hit.jpg" height="90" width="110" >
			<span class="wel_word">&nbsp;图书管理系统</span>
		<%@include file="/WEB-INF/include/header.jsp"%>
	</div>
	
	<div id="main" class="box_no">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.list}" var="book">
				<tr>
					<td>${book.title}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="BookServlet?method=getBookById&bookId=${book.id}" class="a_green">修改</a></td>
					<td><a name="${book.title}" class="delBook" href="BookServlet?method=delBook&bookId=${book.id}">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
			</tr>	
		</table>

		<div id="page_nav">
			<a href="BookServlet?method=getBooksByPage&pageNo=${1}">首页</a>
			<c:if test="${requestScope.page.pageNo > 1}">
				<a href="BookServlet?method=getBooksByPage&pageNo=${requestScope.page.pageNo-1}">上一页</a>
				<a href="BookServlet?method=getBooksByPage&pageNo=${requestScope.page.pageNo-1}">${requestScope.page.pageNo-1}</a>
			</c:if>
			【${requestScope.page.pageNo}】
			<c:if test="${requestScope.page.pageNo < requestScope.page.totalPageNo}">
				<a href="BookServlet?method=getBooksByPage&pageNo=${requestScope.page.pageNo+1}">${requestScope.page.pageNo+1}</a>
				<a href="BookServlet?method=getBooksByPage&pageNo=${requestScope.page.pageNo+1}">下一页</a>
			</c:if>
			<a href="BookServlet?method=getBooksByPage&pageNo=${requestScope.page.totalPageNo}">末页</a>
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