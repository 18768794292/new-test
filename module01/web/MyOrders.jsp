<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="two.domain.Order" %>
<%@ page import="two.dao.OrderDao" %>
<%@ page import="two.dao.impl.OrderDaoImpl" %>
<%@ page import="java.util.List" %>

<%
    // 获取用户ID，您可能需要在登录时将用户ID存储在 session 中
    int userId = 1; // 示例用户ID

    // 获取用户订单列表
    OrderDao orderDao = new OrderDaoImpl();
    List<Order> orders = orderDao.getOrdersByUserId(userId);
%>

<html>
<head>
    <title>My Orders</title>
    <!-- 添加页面样式或链接到外部样式表 -->
</head>
<body>

<h1>My Orders</h1>

<%
    if (orders != null && !orders.isEmpty()) {
        for (Order order : orders) {
%>
<div>
    <h3>Order ID: <%= order.getOrderId() %></h3>
    <!-- 显示其他订单信息 -->
    <p>Order Date: <%= order.getOrderDate() %></p>
    <!-- 显示订单详情，您可能需要在 Order 类中添加获取订单详情的方法 -->
    <%-- <c:forEach var="orderDetail" items="<%= order.getOrderDetails() %>"> --%>
    <!-- 显示订单详情信息 -->
    <%-- </c:forEach> --%>
</div>
<%
    }
} else {
%>
<p>No orders available.</p>
<%
    }
%>

</body>
</html>
