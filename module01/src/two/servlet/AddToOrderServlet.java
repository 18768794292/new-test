package two.servlet;

import two.dao.CartDao;
import two.dao.OrderDao;
import two.dao.impl.CartDaoImpl;
import two.dao.impl.OrderDaoImpl;
import two.domain.CartItem;
import two.domain.OrderItem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/addToOrder")
public class AddToOrderServlet extends HttpServlet {
    private final OrderDao orderDao = new OrderDaoImpl();
    private final CartDao cartDao = new CartDaoImpl(); // Assuming you have a CartDao

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取选中的商品信息
            String[] selectedItems = request.getParameterValues("selectedItems");

            if (selectedItems != null && selectedItems.length > 0) {
                for (String itemId : selectedItems) {
                    int productId = Integer.parseInt(itemId);

                    // 根据商品ID从购物车中获取商品信息
                    CartItem cartItem = cartDao.getCartItemById(productId);

                    // 添加商品到订单
                    orderDao.addToOrder(cartItem.getProductId(), cartItem.getProductName(), cartItem.getPrice(), cartItem.getProductImage(), cartItem.getQuantity());

                    // 在此可以选择从购物车中移除对应的商品，根据需要
                    // cartDao.removeFromCart(productId);
                }
            }

            // 获取更新后的订单商品列表
            List<OrderItem> orderItems = orderDao.getOrderItems();

            // 将订单商品列表添加到请求属性中
            request.setAttribute("orderItems", orderItems);

            // 转发到订单页面
            RequestDispatcher dispatcher = request.getRequestDispatcher("/orders.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，发送错误响应
            response.getWriter().write("添加商品到订单时出错");
        }
    }
}

