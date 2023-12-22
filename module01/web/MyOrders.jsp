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
        out.println("<p>获取购物车商品时出错。</p>");
    }
%>
<html>
<head>
    <title>购物车</title>
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
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
        $(document).ready(function () {
            // 移除商品的函数
            function removeFromCart(cartId) {
                $.ajax({
                    type: 'POST',
                    url: 'removeFromCart',
                    data: { cartId: cartId },
                    success: function (data) {
                        alert('商品移除成功！');
                        location.reload();
                    },
                    error: function (error) {
                        alert('移除购物车商品时出错。');
                    }
                });
            }

            // 点击 Remove 按钮时的事件处理
            $('.remove-btn').click(function () {
                var cartId = $(this).data('cart-id');
                removeFromCart(cartId);
            });
        });
    </script>
</head>
<body>

<h2>您的购物车</h2>

<%
    if (cartItems != null && !cartItems.isEmpty()) {
%>

<form action="addToOrder" method="post">
    <table>
        <tr>
            <th>选择</th>
            <th>商品名称</th>
            <th>价格</th>
            <th>图片</th>
            <th>数量</th>
            <th>操作</th>
        </tr>

        <% for (CartItem cartItem : cartItems) { %>
        <tr>
            <td><input type="checkbox" name="selectedItems" value="<%= cartItem.getProductId() %>"></td>
            <td><%= cartItem.getProductName() %></td>
            <td><%= cartItem.getPrice() %></td>
            <td><img src="<%= cartItem.getProductImage() %>" alt="<%= cartItem.getProductName() %> 图片" width="160" height="150"></td>
            <td><%= cartItem.getQuantity() %></td>
            <td>
                <button type="button" class="remove-btn" data-cart-id="<%= cartItem.getCartId() %>">移除</button>
            </td>
        </tr>
        <% } %>
    </table>

    <input type="submit" value="将选中的商品加入订单">
</form>

<%
    } else {
        out.println("<p>您的购物车是空的。</p>");
    }
%>
<div class="button-container">
    <a href="main.jsp">返回首页</a>
    <a href="orders.jsp">前往订单页面</a>
</div>
</body>
</html>
