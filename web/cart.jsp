<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="two.dao.CartDao" %>
<%@ page import="two.dao.impl.CartDaoImpl" %>
<%@ page import="two.domain.Product" %>
<%@ page import="two.dao.ProductDao" %>
<%@ page import="two.dao.impl.ProductDaoImpl" %>
<%@ page import="java.util.List" %>
<%
    // 获取购物车Dao
    CartDao cartDao = new CartDaoImpl();
    // 获取购物车中的商品ID列表
    List<Integer> cartItemIds = cartDao.getCartItemIds();
    List<Product> cartItems = cartDao.getCartItems();

    // 获取商品Dao
    ProductDao productDao = new ProductDaoImpl();
%>
<html>
<head>
    <title>DJ冰淇淋 - 我的购物车</title>
    <!-- ... (样式部分保持不变) ... -->
</head>
<body>

<header>
    <h1>DJ冰淇淋 - 我的购物车</h1>
    <div class="button-container">
        <button class="button" onclick="redirectToCart()">我的购物车(<%= cartItemIds.size() %>)</button>
        <button class="button" onclick="redirectToOrders()">我的订单</button>
    </div>
</header>

<h2>购物车内容</h2>

<%
    if (!cartItemIds.isEmpty()) {
        for (Integer productId : cartItemIds) {
            // 根据商品ID获取商品对象
            Product product = cartDao.getProductById(productId);
%>
<div class="product-container">
    <div class="product-card">
        <h3><%= product.getName() %></h3>
        <img src="<%= product.getImage() %>" alt="<%= product.getName() %> 图片" width="150" height="200">
        <p><strong>价格：</strong> <%= product.getPrice() %></p>
        <p><strong>描述：</strong> <%= product.getDescription() %></p>
        <p><strong>库存：</strong> <%= product.getStock() %></p>
    </div>
</div>
<%
    }
} else {
%>
<p>购物车为空。</p>
<%
    }
%>



<script>
    function redirectToCart() {
        console.log("Redirecting to cart.jsp");
        window.location.href = "cart.jsp";
    }

    function redirectToOrders() {
        window.location.href = "orders.jsp";
    }
</script>

</body>
</html>
