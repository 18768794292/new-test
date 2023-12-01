<%--
  Created by IntelliJ IDEA.
  User: Weiney
  Date: 2022/12/07
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>登录失败</title>
  <style>
    body {
      text-align: center;
      background-color: #f2f9f4; /* Light green background color */
      font-family: Arial, sans-serif;
      color: #333; /* Dark text color */
    }

    h2 {
      color: #006400; /* Dark green header color */
    }

    a {
      text-decoration: none;
      color: #006400; /* Dark green link color */
      font-weight: bold;
    }

    a:hover {
      color: #008000; /* Light green link color on hover */
    }
  </style>
</head>
<body>

<%-- 使用链接标签，用于跳转到登录界面，此处的&#8203;``【oaicite:0】``&#8203;可写可不写 --%>
<%-- 因为Tomcat服务器会默认打开名为index的文件作为启动页 --%>
<h2><a href="${pageContext.request.contextPath}/index.jsp">用户名密码错误，点击我请重新登录</a></h2>
</body>
</html>
