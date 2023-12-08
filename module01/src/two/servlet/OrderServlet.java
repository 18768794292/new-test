package two.servlet;

import two.domain.OrderItem;
import two.dao.OrderDao;
import two.dao.impl.OrderDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderServlet extends HttpServlet {
    private final OrderDao orderDao = new OrderDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取订单列表
            List<OrderItem> orderItems = orderDao.getOrderItems();

            // 将订单数据放入request属性
            request.setAttribute("orderItems", orderItems);

            // 转发到orders.jsp页面
            RequestDispatcher dispatcher = request.getRequestDispatcher("/orders.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，发送错误响应
            response.getWriter().write("获取订单数据时出错");
        }
    }
}
