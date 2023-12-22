package two.servlet;

import two.dao.OrderDao;
import two.dao.impl.OrderDaoImpl;
import two.domain.OrderItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ManageOrdersServlet")
public class ManageOrdersServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            OrderDao orderDao = new OrderDaoImpl();
            List<OrderItem> orderItems = orderDao.getAllOrders();
            request.setAttribute("orderItems", orderItems);
            request.getRequestDispatcher("/ManageOrders.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();

            response.getWriter().println("<p>Error fetching order items.</p>");
        }
    }
}
