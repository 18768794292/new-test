package two.servlet;

import two.dao.UserDao;
import two.dao.impl.UserDaoImpl;
import two.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/* 我只需要账号和密码，*/
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        // 调用 UserDao 进行登录验证
        User user = userDao.login(username, password);

        if (user != null) {
            // 登录成功，将用户信息存储在 session 中
            request.getSession().setAttribute("user", user);
            // 重定向到系统的主界面
            response.sendRedirect(request.getContextPath() + "/main.jsp");
        } else {
            // 登录失败，重定向到错误页面
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
