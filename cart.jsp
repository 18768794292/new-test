<%@ page import="two.domain.CartItem" %>
<%@ page import="java.util.List" %>
<%@ page import="two.dao.CartDao" %>
<%@ page import="two.dao.impl.CartDaoImpl" %>
<!-- cart.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // 获取购物车商品列表
  CartDao cartDao = new CartDaoImpl();
  List<CartItem> cartItems = null;

  try {
    cartItems = cartDao.getCartItems();
  } catch (Exception e) {
    e.printStackTrace();
    // 处理异常，例如日志记录或向用户显示错误信息
    out.println("<p>Error fetching cart items.</p>");
  }
%>
<html>
<head>
  <title>Shopping Cart</title>
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

<h2>Your Shopping Cart</h2>

<%
  if (cartItems != null && !cartItems.isEmpty()) {
%>

<form action="addToOrder" method="post">
  <table>
    <tr>
      <th>CartId</th>
      <th>Product Name</th>
      <th>Price</th>
      <th>Image</th>
      <th>Quantity</th>
      <th>Action</th> <!-- New column for remove button -->
    </tr>

    <% for (CartItem cartItem : cartItems) {%>
    <tr>
      <td><%=cartItem.getCartId() %></td>
      <td><%= cartItem.getProductName() %></td>
      <td><%= cartItem.getPrice() %></td>
      <td><img src="<%= cartItem.getProductImage() %>" alt="<%= cartItem.getProductName() %> Image" width="100"
               height="150"></td>
      <td><%= cartItem.getQuantity() %></td>
      <td>
        <form action="removeFromCart" method="post">
          <input type="hidden" name="cartId" value="<%= cartItem.getCartId() %>">
          <input type="submit" value="Remove">
        </form>
      </td>
    </tr>
    <% } %>
  </table>

  <input type="submit" value="Add to Order">
</form>

<div class="button-container">
  <a href="main.jsp">Back to Main Page</a>
  <a href="orders.jsp">Go to Order Page</a>
</div>

<%
  } else {
    out.println("<p>Your cart is empty.</p>");
  }
%>

</body>
</html>
