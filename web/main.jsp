<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="two.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="two.dao.ProductDao" %>
<%@ page import="two.dao.impl.ProductDaoImpl" %>
<%@ page import="two.dao.CartDao" %>
<%@ page import="two.dao.impl.CartDaoImpl" %>
<%
  // 从数据库获取商品列表
  ProductDao productDao = new ProductDaoImpl();
  List<Product> products = productDao.getAllProducts();
  // 获取购物车Dao
  CartDao cartDao = new CartDaoImpl();
%>
<html>
<head>
  <title>DJ冰淇淋 - Product List</title>
  <style>
    body {
      text-align: center;
      background-color: #f2f9f4; /* 浅蓝绿背景色 */
      font-family: Arial, sans-serif;
      color: #004d40; /* 深绿文本颜色 */
      margin: 0;
      padding: 0;
    }

    header {
      background-color: #004d40;
      padding: 10px;
      color: #fff;
      display: flex;
      justify-content: space-between;
      align-items: center;
      position: relative;
    }

    h1, h2 {
      color: #004d40; /* 深绿标题颜色 */
    }

    .product-container {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-around;
      margin: 20px;
    }

    .product-card {
      width: 200px;
      margin: 10px;
      padding: 10px;
      border: 1px solid #004d40; /* 深绿边框颜色 */
      text-align: left;
    }

    img {
      max-width: 100%;
      height: auto;
    }

    .button-container {
      position: absolute;
      top: 10px;
      left: 10px;
      display: flex;
      flex-direction: column;
      gap: 10px;
    }

    .button {
      background-color: #004d40;
      color: #fff;
      padding: 10px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    .button:hover {
      background-color: #00352e; /* 深绿色 */
    }
  </style>
</head>
<body>

<header>
  <h1>DJ冰淇淋 - 欢迎您选购</h1>
  <div class="button-container">
    <button class="button" onclick="redirectToCart()">我的购物车</button>
    <button class="button" onclick="redirectToOrders()">我的订单</button>
  </div>
</header>

<h2>DJ冰淇淋 - 欢迎您选购</h2>

<%
  if (products != null && !products.isEmpty()) {
    for (Product product : products) {
%>
<div class="product-container">
  <div class="product-card">
    <h3><%= product.getName() %></h3>
    <img src="<%= product.getImage() %>" alt="<%= product.getName() %> Image" width="150" height="200">
    <p><strong>价格:</strong> <%= product.getPrice() %></p>
    <p><strong>描述:</strong> <%= product.getDescription() %></p>
    <p><strong>库存:</strong> <%= product.getStock() %></p>
    <!-- 添加到购物车按钮 -->
    <form action="AddToCartServlet" method="post">
      <input type="hidden" name="productId" value="<%= product.getId() %>">
      <button type="submit" class="button">添加到购物车</button>
    </form>
  </div>
</div>
<%
  }
} else {
%>
<p>No products available.</p>
<%
  }
%>

<script>
  function redirectToCart() {
    window.location.href = "cart.jsp";
  }

  function redirectToOrders() {
    window.location.href = "orders.jsp";
  }
</script>
</body>
</html>
