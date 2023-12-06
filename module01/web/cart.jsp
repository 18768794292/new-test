<%@ page import="two.domain.CartItem" %>
<%@ page import="java.util.List" %>
<!-- cart.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Shopping Cart</title>
</head>
<body>

<h2>Your Shopping Cart</h2>

<%
  // 获取购物车商品列表
  List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
  // 调试语句，输出购物车商品列表的内容
  System.out.println("cartItems in cart.jsp: " + cartItems);
  if (cartItems.isEmpty()) {
    out.println("<p>Your cart is empty.</p>");
  } else {
    out.println("<table border=\"1\">");
    out.println("<tr><th>Product Name</th><th>Price</th><th>Quantity</th></tr>");

    for (CartItem cartItem : cartItems) {
      out.println("<tr>");
      out.println("<td>" + cartItem.getProductName() + "</td>");
      out.println("<td>" + cartItem.getPrice() + "</td>");
      out.println("<td>" + cartItem.getQuantity() + "</td>");
      out.println("</tr>");
    }

    out.println("</table>");
  }
%>

</body>
</html>
