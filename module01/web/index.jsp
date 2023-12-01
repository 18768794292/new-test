<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>登录系统</title>
  <style>
    body {
      text-align: center;
      background-color: #f0fff0;
      font-family: 'Arial', sans-serif;
    }

    h1, h2 {
      color: #333;
    }

    form {
      background-color: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      max-width: 400px;
      margin: 20px auto;
    }

    h3 {
      margin-bottom: 15px;
      color: #555;
    }

    input {
      width: 100%;
      padding: 10px;
      margin-bottom: 15px;
      box-sizing: border-box;
      border: 1px solid #ddd;
      border-radius: 4px;
    }

    input[type="submit"], input[type="reset"] {
      background-color: #3498db;
      color: #fff;
      cursor: pointer;
    }

    input[type="submit"]:hover, input[type="reset"]:hover {
      background-color: #2980b9;
    }

    p {
      color: #888;
      margin-top: 20px;
    }

    a {
      color: #e74c3c;
      text-decoration: none;
    }

    a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<h1 style="color: #3498db;">Welcom</h1>
<h2>欢迎登录本系统</h2>
<hr>
<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
  <h3>用户名：<input type="text" name="username"></h3>
  <h3>密码：<input type="password" name="password"></h3>
  <input type="submit" value="登录">
  <input type="reset" value="重置">
</form>
<p>还没有账号？<a href="${pageContext.request.contextPath}/register.jsp">点击这里进行注册</a></p>
</body>
</html>
