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

        // Assuming you have a method to get the logged-in user ID
        int userId = UserService.getLoggedInUserId();

        // Retrieve the user based on the user ID
        User user = userDao.getUserById(userId);

        if (user != null) {
            // Retrieve updated information from the form
            String email = request.getParameter("email");
            String phoneNumber = request.getParameter("phoneNumber");
            String address = request.getParameter("address");

            // Update the user object
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setAddress(address);

            // Call UserDao to update the user information
            boolean updateResult = userDao.updateUser(user);

            if (updateResult) {
                // Update successful, you may want to redirect to a success page
                response.sendRedirect(request.getContextPath() + "/my.jsp");
            } else {
                // Update failed, redirect to an error page
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } else {
            // User not found, redirect to an error page
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
