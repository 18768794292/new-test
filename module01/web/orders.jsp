<%@ page import="two.domain.OrderItem" %>
<%@ page import="java.util.List" %>
<%@ page import="two.dao.OrderDao" %>
<%@ page import="two.dao.impl.OrderDaoImpl" %>
<!-- orders.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // 获取订单商品列表
  OrderDao orderDao = new OrderDaoImpl();
  List<OrderItem> orderItems = null;

  try {
    orderItems = orderDao.getOrderItems();
  } catch (Exception e) {
    e.printStackTrace();
    // 处理异常，例如日志记录或向用户显示错误信息
    out.println("<p>Error fetching order items.</p>");
  }
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
  </style>
</head>
<body>

<h2>Your Orders</h2>

<%
  if (orderItems != null && !orderItems.isEmpty()) {
%>
<table>
  <tr>
    <th>Product Name</th>
    <th>Price</th>
    <th>Quantity</th>
  </tr>

  <% for (OrderItem orderItem : orderItems) { %>
  <tr>
    <td><%= orderItem.getProductName() %></td>
    <td><%= orderItem.getPrice() %></td>
    <td><%= orderItem.getQuantity() %></td>
  </tr>
  <% } %>

</table>
<%
  } else {
    out.println("<p>Your order history is empty.</p>");
  }
%>

</body>
</html>
