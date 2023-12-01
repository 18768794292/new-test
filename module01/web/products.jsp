<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="two.domain.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="two.dao.ProductDao" %>
<%@ page import="two.dao.impl.ProductDaoImpl" %>

<%
    // 从数据库获取商品列表
    ProductDao productDao = new ProductDaoImpl();
    List<Product> products = productDao.getAllProducts();
%>

<html>
<head>
    <title>Product List</title>
    <style>
        body {
            text-align: center;
            background-color: #e0f7fa; /* 浅蓝绿背景色 */
            font-family: Arial, sans-serif;
            color: #004d40; /* 深绿文本颜色 */
        }

        h1, h2 {
            color: #004d40; /* 深绿标题颜色 */
        }

        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid #004d40; /* 深绿边框颜色 */
            padding: 10px;
            text-align: left;
        }
    </style>
</head>
<body>

<h1>Product List</h1>

<%
    if (products != null && !products.isEmpty()) {
%>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Description</th>
    </tr>
    </thead>
    <tbody>
    <% for (Product product : products) { %>
    <tr>
        <td><%= product.getId() %></td>
        <td><%= product.getName() %></td>
        <td><%= product.getPrice() %></td>
        <td><%= product.getDescription() %></td>
    </tr>
    <% } %>
    </tbody>
</table>
<%
} else {
%>
<p>No products available.</p>
<%
    }
%>

</body>
</html>
