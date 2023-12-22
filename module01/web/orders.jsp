<%@ page import="two.domain.OrderItem" %>
<%@ page import="java.util.List" %>
<%@ page import="two.dao.OrderDao" %>
<%@ page import="two.dao.impl.OrderDaoImpl" %>
<%@ page import="java.math.BigDecimal" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%

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

    .order-container {
      margin-bottom: 30px;
    }

    .order-summary {
      margin-top: 10px;
      margin-bottom: 20px;
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

<%
  if (orderItems != null && !orderItems.isEmpty()) {
    int currentOrderId = -1; // 用于跟踪当前订单号
    BigDecimal totalOrderPrice = BigDecimal.ZERO;
%>
<h2>您的订单</h2>

<%
  for (OrderItem orderItem : orderItems) {
    // 检查订单号是否变化，如果变化则重新计算总价并显示总价
    if (orderItem.getOrderId() != currentOrderId) {
      // 如果不是第一个订单，关闭前一个订单的表格
      if (currentOrderId != -1) {
%>
<!-- 显示当前订单的总价 -->
<div class="order-summary">
  <strong>该订单总价:</strong> <%= totalOrderPrice %>
</div>
</table>
<!-- 显示当前订单的用户名、电话号码和地址 -->
<div>
  <strong>用户名:</strong> <%= orderItem.getUsername() %><br>
  <strong>电话号码:</strong> <%= orderItem.getPhoneNumber() %><br>
  <strong>地址:</strong> <%= orderItem.getAddress() %>
</div>
</div>
<%
  }
%>
<!-- 打开新的订单容器 -->
<div class="order-container">
  <h2>订单 <%= orderItem.getOrderId() %> </h2>
  <table>
    <tr>
      <th>商品名称</th>
      <th>价格</th>
      <th>数量</th>
      <th>总价</th>
      <th>商品图片</th>
      <th>配送状态</th>
    </tr>
    <%
        totalOrderPrice = BigDecimal.ZERO; // 重置总价
        currentOrderId = orderItem.getOrderId();
      }
    %>
    <tr>
      <td><%= orderItem.getProductName() %></td>
      <td><%= orderItem.getPrice() %></td>
      <td><%= orderItem.getQuantity() %></td>
      <td><%= orderItem.getTotal() %></td>
      <td><img src="<%= orderItem.getProductImage() %>" alt="商品图片" width="50" height="100"></td>
      <td><%= orderItem.getDeliveryStatus() %></td>
    </tr>
    <%
        // 更新当前订单的总价
        totalOrderPrice = totalOrderPrice.add(orderItem.getTotal());
      }
    %>
    <!-- 显示最后一个订单的总价 -->
    <div class="order-summary">
      <strong>该订单总价:</strong> <%= totalOrderPrice %>
    </div>
  </table>
  <!-- 显示最后一个订单的用户名、电话号码和地址 -->
  <div>
    <strong>用户名:</strong> <%= orderItems.get(orderItems.size() - 1).getUsername() %><br>
    <strong>电话号码:</strong> <%= orderItems.get(orderItems.size() - 1).getPhoneNumber() %><br>
    <strong>地址:</strong> <%= orderItems.get(orderItems.size() - 1).getAddress() %>
  </div>
</div>
<%
  } else {
    out.println("<p>您的订单历史为空。</p>");
  }
%>

<div class="button-container">
  <a href="cart.jsp">返回购物车</a>
  <a href="main.jsp">返回主页</a>
</div>

</body>
</html>
