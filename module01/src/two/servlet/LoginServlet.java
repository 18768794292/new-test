package two.servlet;

import two.dao.UserDao;
import two.dao.impl.UserDaoImpl;
import two.domain.User;
import two.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final UserDao userDao = new UserDaoImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        User user = userDao.login(username, password);
        if (user != null) {
            // 登录成功，将用户信息存储在 session 中
            request.getSession().setAttribute("user", user);
            // 设置已登录用户的ID到 UserService 中
            UserService.setLoggedInUserId(user.getId());
            // 判断用户角色，进行相应的重定向
            if (user.getRole() == 1) {
                // 管理员，重定向到管理界面
                response.sendRedirect(request.getContextPath() + "/manage.jsp");
            } else {
                // 普通用户，重定向到主界面
                response.sendRedirect(request.getContextPath() + "/main.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
