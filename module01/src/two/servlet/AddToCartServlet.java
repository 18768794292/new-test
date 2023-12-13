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

            // 重定向到购物车页面
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，发送错误响应
            response.getWriter().write("添加商品到购物车时出错");
        }
    }
}



