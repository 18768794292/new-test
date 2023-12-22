package two.servlet;

import two.dao.CartDao;
import two.dao.OrderDao;
import two.dao.UserDao;
import two.dao.impl.CartDaoImpl;
import two.dao.impl.OrderDaoImpl;
import two.dao.impl.UserDaoImpl;
import two.domain.CartItem;
import two.domain.OrderItem;
import two.domain.User;
import two.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/addToOrder")
public class AddToOrderServlet extends HttpServlet {
    private final OrderDao orderDao = new OrderDaoImpl();
    private final CartDao cartDao = new CartDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String[] selectedItems = request.getParameterValues("selectedItems");

            if (selectedItems != null && selectedItems.length > 0) {
                List<Integer> productIds = new ArrayList<>();
                List<String> productNames = new ArrayList<>();
                List<BigDecimal> productPrices = new ArrayList<>();
                List<String> images = new ArrayList<>();
                List<Integer> quantities = new ArrayList<>();

                for (String itemId : selectedItems) {
                    int productId = Integer.parseInt(itemId);
                    CartItem cartItem = cartDao.getCartItemById(productId);

                    productIds.add(cartItem.getProductId());
                    productNames.add(cartItem.getProductName());
                    productPrices.add(cartItem.getPrice());
                    images.add(cartItem.getProductImage());
                    quantities.add(cartItem.getQuantity());

                    // 从购物车中移除
                    cartDao.removeFromCart(cartItem.getCartId());
                    System.out.println("删除相对应商品");
                }

                UserDao userDao = new UserDaoImpl();
                int userId = UserService.getLoggedInUserId();
                User user = userDao.getUserById(userId);

                // 调用修改后的addToOrder方法
                orderDao.addToOrder(productIds, productNames, productPrices, images, quantities,
                        user.getUsername(), user.getPhoneNumber(), user.getAddress());
            }

            // 获取更新后
            List<OrderItem> orderItems = orderDao.getOrderItems();

            // 将订单商品列表添加到请求属性中
            request.setAttribute("orderItems", orderItems);

            response.sendRedirect(request.getContextPath() + "/orders.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("添加商品到订单时出错");
        }
    }
}
