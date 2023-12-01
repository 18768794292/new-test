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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 创建用户对象
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // 调用 UserDao 进行注册
        boolean registrationResult = userDao.register(user);

        if (registrationResult) {
            // 注册成功，重定向到注册成功页面
            response.sendRedirect(request.getContextPath() + "/register_success.jsp");
        } else {
            // 注册失败，重定向到错误页面
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
