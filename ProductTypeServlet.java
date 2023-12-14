package two.servlet;

import two.dao.ProductTypeDao;
import two.dao.impl.ProductTypeDaoImpl;
import two.domain.ProductType;
import two.utils.JdbcUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/productType")
public class ProductTypeServlet extends HttpServlet {

    private ProductTypeDao productTypeDao;

    public void init() {
        productTypeDao = new ProductTypeDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应的字符集为 UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应的字符集为 UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "add":
                    addProductType(request, response);
                    break;
                case "edit":
                    editProductType(request, response);
                    break;
                case "delete":
                    deleteProductType(request, response);
                    break;
                default:
                    response.sendRedirect("productTypes.jsp");
            }
        } else {
            response.sendRedirect("productTypes.jsp");
        }
    }

    private void addProductType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String typeName = request.getParameter("typeName");

        try {
            ProductType productType = new ProductType();
            productType.setTypeName(typeName);

            productTypeDao.addProductType(productType);

            response.sendRedirect("productTypes.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error adding product type.");
        }
    }

    private void editProductType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));
        String typeName = request.getParameter("typeName");

        try {
            ProductType productType = productTypeDao.getProductTypeById(typeId);

            if (productType != null) {
                productType.setTypeName(typeName);

                productTypeDao.updateProductType(productType);
            }

            response.sendRedirect("productTypes.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error editing product type.");
        }
    }

    private void deleteProductType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int typeId = Integer.parseInt(request.getParameter("typeId"));

        try {
            productTypeDao.deleteProductType(typeId);

            response.sendRedirect("productTypes.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error deleting product type.");
        }
    }
}
