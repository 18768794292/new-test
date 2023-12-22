<%@ page import="two.domain.OrderItem" %>
<%@ page import="java.util.List" %>
<%@ page import="two.dao.OrderDao" %>
<%@ page import="two.dao.impl.OrderDaoImpl" %>
<%@ page import="two.domain.User" %>
<%@ page import="two.service.UserService" %>
<!-- ManageOrders.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // 获取所有用户订单商品列表
  OrderDao orderDao = new OrderDaoImpl();
  List<OrderItem> orderItems = null;

  try {
    orderItems = orderDao.getAllOrders(); // 修改这里
  } catch (Exception e) {
    e.printStackTrace();
    // 处理异常，例如日志记录或向用户显示错误信息
    out.println("<p>Error fetching order items.</p>");
  }

  // 获取当前登录的用户信息
  User loggedInUser = UserService.getLoggedInUser();
  // 在适当的位置添加以下语句，用于调试
  System.out.println("loggedInUser: " + loggedInUser);
%>
<html>
<head>
  <title>Orders</title>
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

<h2>Manage Orders</h2>

<%
  if (orderItems != null && !orderItems.isEmpty()) {
%>
<table>
  <tr>
    <th>OrderId</th>
    <th>Product Name</th>
    <th>Price</th>
    <th>Quantity</th>
    <th>Total Price</th>
    <th>Product Image</th>
    <th>Delivery Status</th>
  </tr>

  <% for (OrderItem orderItem : orderItems) { %>
  <tr>
    <td><%= orderItem.getOrderId() %></td>
    <td><%= orderItem.getProductName() %></td>
    <td><%= orderItem.getPrice() %></td>
    <td><%= orderItem.getQuantity() %></td>
    <td><%= orderItem.getTotal() %></td>
    <td><img src="<%= orderItem.getProductImage() %>" alt="Product Image" width="50" height="100"></td>
    <td><%= orderItem.getDeliveryStatus() %></td>
  </tr>

  <tr>
    <td>用户名:<%= orderItem.getUsername() %></td>
    <td>电话号码:<%= orderItem.getPhoneNumber() %></td>
    <td>地址:<%= orderItem.getAddress() %></td>
  </tr>
  <% System.out.println("orderItem.getDeliveryStatus(): " + orderItem.getDeliveryStatus()); %>
  <% if (loggedInUser != null && loggedInUser.getRole() == 1 && "Pending".equals(orderItem.getDeliveryStatus())) { %>
  <!-- 显示发货按钮 -->
  <tr>
    <td colspan="7">
      <form action="UpdateOrderStatusServlet" method="post">
        <input type="hidden" name="orderId" value="<%= orderItem.getOrderId() %>">
        <input type="hidden" name="deliveryStatus" value="Shipped">
        <button type="submit">制作成功</button>
      </form>
    </td>
  </tr>
  <% } %>

  <% } %>

</table>

<div class="button-container">
  <a href="cart.jsp">Back to Cart</a>
  <a href="main.jsp">Back to Main Page</a>
</div>

<%
  } else {
    out.println("<p> order history is empty.</p>");
  }
%>

</body>
</html>
