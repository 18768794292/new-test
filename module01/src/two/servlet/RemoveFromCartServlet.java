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
            cartDao.removeFromCart(cartId);
            response.sendRedirect(request.getContextPath() + "/cart.jsp");
        } catch (Exception e) {
            e.printStackTrace();

            response.getWriter().write("从购物车中移除商品时出错");
        }
    }
}
