<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="two.domain.Product" %>
<%@ page import="two.domain.ProductType" %>
<%@ page import="java.util.List" %>

<html>
<head>
  <style>
  </style>
</head>
<body>


<%
  Product product = (Product) request.getAttribute("product");
  List<ProductType> productTypes = (List<ProductType>) request.getAttribute("productTypes");
%>

<form action="products" method="post">
  <input type="hidden" name="action" value="updateInfo">
  <input type="hidden" name="productId" value="<%= product.getId() %>">

  <label for="productName">商品名称:</label>
  <input type="text" id="productName" name="productName" value="<%= product.getName() %>" required><br>

  <label for="productPrice">商品价格:</label>
  <input type="text" id="productPrice" name="productPrice" value="<%= product.getPrice() %>" required><br>

  <label for="productDescription">商品描述:</label>
  <textarea id="productDescription" name="productDescription"><%= product.getDescription() %></textarea><br>

  <label for="productImage">商品图片URL:</label>
  <input type="text" id="productImage" name="productImage" value="<%= product.getImage() %>"><br>

  <label for="productStock">商品库存:</label>
  <input type="text" id="productStock" name="productStock" value="<%= product.getStock() %>" required><br>

  <label for="selectProductType">商品类型：</label>
  <select id="selectProductType" name="selectProductType" required>
    <% for (ProductType productType : productTypes) { %>
    <option value="<%= productType.getId() %>" <% if (product.getProductType().getId() == productType.getId()) { %>selected<% } %>><%= productType.getTypeName() %></option>
    <% } %>
  </select><br>

  <input type="submit" value="更新商品信息">
</form>


</body>
</html>
