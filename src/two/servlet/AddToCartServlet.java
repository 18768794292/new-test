package two.servlet;

import two.dao.CartDao;
import two.dao.ProductDao;
import two.dao.impl.CartDaoImpl;
import two.dao.impl.ProductDaoImpl;
import two.domain.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取商品ID参数
        String productIdStr = request.getParameter("productId");

        // 转换商品ID为整数类型
        int productId = Integer.parseInt(productIdStr);

        // 获取商品Dao
        ProductDao productDao = new ProductDaoImpl();

        // 根据商品ID获取商品对象
        Product product = productDao.getProductById(productId);

        // 获取购物车Dao
        CartDao cartDao = new CartDaoImpl();

        // 将商品添加到购物车
        cartDao.addToCart(product);

        // 更新购物车信息到Session
        request.getSession().setAttribute("cart", cartDao.getCartItems());
        System.out.println("Cart updated: " + cartDao.getCartItems());


        // 重定向回商品列表页面
        response.sendRedirect("main.jsp");
    }
}
