package two.servlet;

import two.dao.CartDao;
import two.dao.impl.CartDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removeFromCart")
public class RemoveFromCartServlet extends HttpServlet {
    private final CartDao cartDao = new CartDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int cartId = Integer.parseInt(request.getParameter("cartId"));

            // 移除购物车中的商品
            cartDao.removeFromCart(cartId);

            // 重定向回购物车页面
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，发送错误响应
            response.getWriter().write("从购物车中移除商品时出错");
        }
    }
}
