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
        List<Product> products = productDao.getAllProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("/main.jsp").forward(request, response);
    }
}
