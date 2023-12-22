<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="two.domain.ProductType" %>
<%@ page import="java.util.List" %>
<%@ page import="two.dao.ProductTypeDao" %>
<%@ page import="two.dao.impl.ProductTypeDaoImpl" %>

<%
    ProductTypeDao productTypeDao = new ProductTypeDaoImpl();
    List<ProductType> productTypes = productTypeDao.getAllProductTypes();
%>

<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>产品类型管理</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 20px;
            background-color: #f4f4f4;
        }

        h2 {
            color: #004d40;
            text-align: center;
        }

        ul {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        li {
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 10px;
            padding: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        a {
            text-decoration: none;
            color: #004d40;
            margin-left: 10px;
        }

        a:hover {
            text-decoration: underline;
        }

        form {
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            margin-top: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #004d40;
        }

        input[type="text"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            margin-bottom: 10px;
        }

        input[type="submit"], .return-button {
            background-color: #004d40;
            color: #fff;
            padding: 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            width: 100%;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            margin-top: 10px;
        }

        input[type="submit"]:hover, .return-button:hover {
            background-color: #00332e;
        }
    </style>
</head>
<body>
<h2>产品类型</h2>
<ul>
    <% for (ProductType productType : productTypes) { %>
    <li><%= productType.getTypeName() %> - <a href="editProductType.jsp?typeId=<%= productType.getId() %>">编辑</a> | <a href="productType?action=delete&typeId=<%= productType.getId() %>">删除</a></li>
    <% } %>
</ul>

<h2>添加新产品类型</h2>
<form action="productType" method="post">
    <input type="hidden" name="action" value="add">
    <label for="inputTypeName">类型名称：</label>
    <input type="text" id="inputTypeName" name="typeName" required>
    <input type="submit" value="添加类型">
</form>

<a href="manage.jsp" class="return-button">返回到管理页面</a>
</body>
</html>
