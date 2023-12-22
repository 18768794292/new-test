<%@ page import="two.domain.OrderItem" %>
<%@ page import="java.util.List" %>
<%@ page import="two.dao.OrderDao" %>
<%@ page import="two.dao.impl.OrderDaoImpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // 获取订单商品列表
  OrderDao orderDao = new OrderDaoImpl();
  List<OrderItem> orderItems = null;

  try {
    orderItems = orderDao.getOrderItems();
  } catch (Exception e) {
    e.printStackTrace();

    out.println("<p>获取订单商品时发生错误。</p>");
  }
%>
<html>
<head>
  <title>您的订单</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      text-align: center;
    }

    h2 {
      color: #004d40;
    }

    table {
      border-collapse: collapse;
      width: 80%;
      margin: 20px auto;
    }

    th, td {
      border: 1px solid #004d40;
      padding: 8px;
      text-align: left;
    }

    th {
      background-color: #004d40;
      color: #fff;
    }

    p {
      color: #004d40;
    }

    .button-container {
      margin-top: 20px;
    }

    .button-container a {
      display: inline-block;
      margin: 0 10px;
      padding: 10px 20px;
      text-decoration: none;
      background-color: #004d40;
      color: #fff;
      border-radius: 5px;
    }
  </style>
</head>
<body>

<h2>您的订单</h2>

<%
  if (orderItems != null && !orderItems.isEmpty()) {
%>
<table>
  <tr>
    <th>订单号</th>
    <th>商品名称</th>
    <th>价格</th>
    <th>数量</th>
    <th>总价</th>
    <th>商品图片</th>
    <th>配送状态</th>
  </tr>

  <% for (OrderItem orderItem : orderItems) {
    System.out.println("订单号: " + orderItem.getOrderId());
  %>
  <tr>
    <td><%= orderItem.getOrderId() %></td>
    <td><%= orderItem.getProductName() %></td>
    <td><%= orderItem.getPrice() %></td>
    <td><%= orderItem.getQuantity() %></td>
    <td><%= orderItem.getTotal() %></td>
    <td><img src="<%= orderItem.getProductImage() %>" alt="商品图片" width="50" height="100"></td>
    <td><%= orderItem.getDeliveryStatus() %></td>
  </tr>

  <tr>
    <td>用户名:<%= orderItem.getUsername() %></td>
    <td>电话号码:<%= orderItem.getPhoneNumber() %></td>
    <td>地址:<%= orderItem.getAddress() %></td>
  </tr>

  <% } %>

</table>

<div class="button-container">
  <a href="cart.jsp">返回购物车</a>
  <a href="main.jsp">返回主页</a>
</div>

<%
  } else {
    out.println("<p>您的订单历史为空。</p>");
  }
%>

</body>
</html>
