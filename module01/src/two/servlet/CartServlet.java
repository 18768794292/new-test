package two.servlet;
import two.domain.CartItem;
import two.dao.CartDao;
import two.dao.impl.CartDaoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    private final CartDao cartDao = new CartDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<CartItem> cartItems = cartDao.getCartItems();
            request.setAttribute("cartItems", cartItems);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();

            response.getWriter().write("获取购物车数据时出错");
        }
    }
}

