package two.servlet;

import two.dao.ProductDao;
import two.dao.impl.ProductDaoImpl;
import two.domain.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductDao productDao = new ProductDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = null;

        try {
            String typeIdParam = request.getParameter("typeId");

            if (typeIdParam != null && !typeIdParam.isEmpty()) {
                // 如果存在商品类型参数，则按类型获取产品
                int typeId = Integer.parseInt(typeIdParam);
                products = productDao.getProductsByType(typeId);
            } else {
                // 否则获取所有产品
                products = productDao.getAllProducts();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        request.setAttribute("products", products);
        request.getRequestDispatcher("/main.jsp").forward(request, response);
    }
}
