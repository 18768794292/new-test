<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
    <style>
        body {
            text-align: center;
            background-color: #f0fff0; /* 浅绿色背景 */
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        h2 {
            color: #2e8b57; /* 深绿色标题 */
            text-transform: uppercase; /* 将文字转为大写 */
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 400px;
            margin-top: 20px;
            margin: 0 auto; /* Center the form */
        }

        h3 {
            margin-bottom: 15px;
            color: #2e8b57; /* 深绿色文本 */
        }

        input {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            box-sizing: border-box;
            border: 1px solid #2e8b57;
            border-radius: 4px;
        }

        input[type="submit"], input[type="reset"] {
            width: 100%;
            background-color: #2e8b57; /* 深绿色按钮 */
            color: #fff;
            cursor: pointer;
        }

        input[type="submit"]:hover, input[type="reset"]:hover {
            background-color: #228b22; /* 深绿悬停效果 */
        }

        p {
            color: #888;
            margin-top: 20px;
        }

        a {
            color: #3cb371; /* 中绿色链接颜色 */
            text-decoration: none;
            border-bottom: 1px solid #3cb371; /* 中绿色下划线 */
        }

        a:hover {
            text-decoration: none;
            border-bottom: 1px solid #2e8b57; /* 深绿色下划线 */
        }
    </style>
</head>
<body>
<h2>用户注册</h2>
<hr>
<p>Welcome~~~</p>
<form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
    <h3>用户名：<input type="text" name="username"></h3>
    <h3>密码：<input type="password" name="password"></h3>
    <h3>Email：<input type="text" name="email"></h3>
    <h3>电话号码：<input type="text" name="phoneNumber"></h3>
    <h3>地址：<input type="text" name="address"></h3>
    <input type="submit" value="注册">
    <input type="reset" value="重置">
</form>
</body>
</html>
