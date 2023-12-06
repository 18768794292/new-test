package two.servlet;

import two.dao.CartDao;
import two.dao.impl.CartDaoImpl;
import two.domain.CartItem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    private final CartDao cartDao = new CartDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取商品信息
            int productId = Integer.parseInt(request.getParameter("productId"));
            String productName = request.getParameter("productName");
            BigDecimal productPrice = new BigDecimal(request.getParameter("productPrice"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            // 添加商品到购物车
            cartDao.addToCart(productId, productName, productPrice, quantity);

            // 获取更新后的购物车商品列表
            List<CartItem> cartItems = cartDao.getCartItems();
            // 调试语句，输出购物车商品列表的内容
            System.out.println("Cart Items:");
            for (CartItem cartItem : cartItems) {
                System.out.println(cartItem.toString());
            }

            // 将购物车商品列表添加到请求属性中
            request.setAttribute("cartItems", cartItems);

            // 转发到购物车页面
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，发送错误响应
            response.getWriter().write("添加商品到购物车时出错");
        }
    }
}


