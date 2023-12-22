package two.servlet;

import two.dao.OrderDao;
import two.dao.impl.OrderDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateOrderStatusServlet")
public class UpdateOrderStatusServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String deliveryStatus = "Shipped";

        OrderDao orderDao = new OrderDaoImpl();
        try {
            orderDao.updateOrderStatus(orderId, deliveryStatus);
            orderDao.updateStockOnOrderShipped(orderId);
            response.sendRedirect("ManageOrders.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<p>Error updating order status.</p>");
        }
    }
}
