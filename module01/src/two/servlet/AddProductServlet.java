package two.servlet;

import two.dao.ProductDao;
import two.dao.ProductTypeDao;
import two.dao.impl.ProductDaoImpl;
import two.dao.impl.ProductTypeDaoImpl;
import two.domain.Product;
import two.domain.ProductType;
import two.utils.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {

    private ProductDao productDao;
    private ProductTypeDao productTypeDao;

    public void init() {
        productDao = new ProductDaoImpl();
        productTypeDao = new ProductTypeDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String productName = request.getParameter("productName");
        String productPrice = request.getParameter("productPrice");
        String productDescription = request.getParameter("productDescription");
        String productImage = request.getParameter("productImage");
        String productStock = request.getParameter("productStock");
        String productTypeId = request.getParameter("productType");
        try {
            BigDecimal price = new BigDecimal(productPrice);
            int stock = Integer.parseInt(productStock);
            ProductType productType = productTypeDao.getProductTypeById(Integer.parseInt(productTypeId));
            Product product = new Product();
            product.setName(productName);
            product.setPrice(price);
            product.setDescription(productDescription);
            product.setImage(productImage);
            product.setStock(stock);
            product.setProductType(productType);
            productDao.addProduct(product);
            response.sendRedirect("main.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error adding product.");
        }
    }
}
