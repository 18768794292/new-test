<%@ page import="two.domain.ProductType" %>
<%@ page import="two.dao.ProductTypeDao" %>
<%@ page import="two.dao.impl.ProductTypeDaoImpl" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    // 获取typeId参数
    String typeIdParam = request.getParameter("typeId");
    int typeId = Integer.parseInt(typeIdParam);

    // 从数据库获取商品类型信息
    ProductTypeDao productTypeDao = new ProductTypeDaoImpl();
    ProductType productType = productTypeDao.getProductTypeById(typeId);
%>

<!-- 在表单中显示商品类型信息 -->
<form action="productType" method="post">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="typeId" value="<%= productType.getId() %>">
    <label for="inputTypeName">Type Name:</label>
    <input type="text" id="inputTypeName" name="typeName" value="<%= productType.getTypeName() %>" required>
    <input type="submit" value="Save Changes">
</form>
