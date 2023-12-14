package two.servlet;

import two.dao.CartDao;
import two.dao.ProductDao;
import two.dao.impl.CartDaoImpl;
import two.dao.impl.ProductDaoImpl;
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
    private final ProductDao productDao = new ProductDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 获取商品信息
            int productId = Integer.parseInt(request.getParameter("productId"));

            // 检查商品是否上架
            int productStatus = productDao.getProductStatus(productId);
            if (productStatus == 1) {
                String productName = request.getParameter("productName");
                BigDecimal productPrice = new BigDecimal(request.getParameter("productPrice"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));

                // 添加商品到购物车
                cartDao.addToCart(productId, productName, productPrice, quantity);

                // 重定向到购物车页面
                response.sendRedirect(request.getContextPath() + "/cart.jsp");
            } else {
                // 商品未上架，可以选择跳转到错误页面或其他逻辑
                response.sendRedirect(request.getContextPath() + "/kong.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，发送错误响应
            response.getWriter().write("添加商品到购物车时出错");
        }
    }
}
