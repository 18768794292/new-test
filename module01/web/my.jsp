<!-- my.jsp -->
<%@ page import="two.domain.User" %>
<%@ page import="two.dao.UserDao" %>
<%@ page import="two.dao.impl.UserDaoImpl" %>
<%@ page import="two.service.UserService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  // 获取用户信息
  UserDao userDao = new UserDaoImpl();
  // 用户ID，从登录信息中获取
  int userId = UserService.getLoggedInUserId();
  User user = userDao.getUserById(userId);
%>
<html>
<head>
  <title>我的信息</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 20px;
    }

    h2 {
      color: #004d40;
    }

    table {
      border-collapse: collapse;
      width: 50%;
      margin-top: 20px;
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

    input[type="text"] {
      width: 100%;
      padding: 5px;
    }

    input[type="submit"] {
      background-color: #004d40;
      color: #fff;
      padding: 10px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
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
<h2>My Profile</h2>

<% if (user != null) { %>
<form action="updateProfile" method="post">
  <table>
    <tr>
      <td>用户名:</td>
      <td><%= user.getUsername() %></td>
    </tr>
    <tr>
      <td>邮箱:</td>
      <td><input type="text" name="email" value="<%= user.getEmail() %>"></td>
    </tr>
    <tr>
      <td>电话号码:</td>
      <td><input type="text" name="phoneNumber" value="<%= user.getPhoneNumber() %>"></td>
    </tr>
    <tr>
      <td>地址:</td>
      <td><input type="text" name="address" value="<%= user.getAddress() %>"></td>
    </tr>
  </table>
  <input type="submit" value="提交修改">
</form>
<% } else { %>
<p>用户信息不可用。</p>
<% } %>
<div class="button-container">
  <a href="main.jsp">Back to main</a>
  <a href="orders.jsp">go to orders Page</a>
  <a href="cart.jsp">Back to Cart</a>
</div>
</body>
</html>
