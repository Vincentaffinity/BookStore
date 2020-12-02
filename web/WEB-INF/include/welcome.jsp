<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:if test="${empty sessionScope.user}">
    <div class="user">
        <a href="pages/user/login.jsp">登录</a> |
        <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="index.jsp">返回</a>
    </div>
</c:if>
<c:if test="${!empty sessionScope.user}">
    <div>
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临哈工大书城</span>
        <a href="pages/order/order.jsp">我的订单</a>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
        <a href="UserServlet?method=logout">注销</a>&nbsp;&nbsp;
        <a href="index.jsp">返回</a>
    </div>
</c:if>