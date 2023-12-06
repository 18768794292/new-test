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
            // 获取购物车商品列表
            List<CartItem> cartItems = cartDao.getCartItems();

            // 将购物车数据放入request属性
            request.setAttribute("cartItems", cartItems);

            // 转发到cart.jsp页面
            RequestDispatcher dispatcher = request.getRequestDispatcher("/cart.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，发送错误响应
            response.getWriter().write("获取购物车数据时出错");
        }
    }
}

