<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册成功</title>
    <style>
        body {
            text-align: center;
            background-color: #c8e6c9; /* Light green background color */
            font-family: Arial, sans-serif;
            color: #1b5e20; /* Dark green text color */
            margin: 0;
            padding: 0;
        }
        h1, p {
            color: #1b5e20; /* Dark green header and paragraph color */
        }
        a {
            text-decoration: none;
            color: #1b5e20; /* Dark green link color */
            font-weight: bold;
        }
        a:hover {
            color: #33691e; /* Light green link color on hover */
        }
    </style>
</head>
<body>
<div>
    <h1>注册成功</h1>
    <p>恭喜你，注册成功！</p>
    <p>现在你可以 <a href="${pageContext.request.contextPath}/main.jsp">进入系统</a>，或者 <a href="${pageContext.request.contextPath}/index.jsp">重新登录</a></p>
</div>
</body>
</html>
