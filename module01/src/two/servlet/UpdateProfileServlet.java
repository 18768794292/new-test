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

@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {
    private final UserDao userDao = new UserDaoImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int userId = UserService.getLoggedInUserId();
        User user = userDao.getUserById(userId);
        if (user != null) {

            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");

            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setAddress(address);

            boolean updateResult = userDao.updateUser(user);

            if (updateResult) {

                response.sendRedirect(request.getContextPath() + "/my.jsp");
            } else {

                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } else {

            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
