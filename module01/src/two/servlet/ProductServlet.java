package two.servlet;

import two.dao.ProductDao;
import two.dao.ProductTypeDao;
import two.dao.impl.ProductDaoImpl;
import two.dao.impl.ProductTypeDaoImpl;
import two.domain.Product;
import two.domain.ProductType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductDao productDao = new ProductDaoImpl();
    private ProductTypeDao productTypeDao = new ProductTypeDaoImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "updateInfo":
                    updateProductInfo(request);
                    break;
                case "putOnSale":
                    putProductOnSale(request);
                    break;
                case "takeOffSale":
                    takeProductOffSale(request);
                    break;
                case "editInfo":
                    editProductInfo(request, response);
                    return;
                default:

                    response.sendRedirect(request.getContextPath() + "/error.jsp");
                    return;
            }
            // 重定向到商品列表页面
            response.sendRedirect(request.getContextPath() + "/products?typeId=0");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    // 处理编辑商品信息的方法
    private void editProductInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = productDao.getProductById(productId);
        List<ProductType> productTypes = productTypeDao.getAllProductTypes();


        request.setAttribute("product", product);
        request.setAttribute("productTypes", productTypes);


        request.getRequestDispatcher("/editProduct.jsp").forward(request, response);
    }

    private Product getProductFromRequest(HttpServletRequest request) {
        Product product = new Product();


        product.setId(Integer.parseInt(request.getParameter("productId")));
        product.setName(request.getParameter("productName"));
        product.setPrice(new BigDecimal(request.getParameter("productPrice")));
        product.setDescription(request.getParameter("productDescription"));
        product.setImage(request.getParameter("productImage"));
        product.setStock(Integer.parseInt(request.getParameter("productStock")));

        // 获取商品类型
        ProductType productType = new ProductType();
        productType.setId(Integer.parseInt(request.getParameter("selectProductType")));
        product.setProductType(productType);

        return product;
    }

    private void updateProductInfo(HttpServletRequest request) throws Exception {
        Product product = getProductFromRequest(request);
        productDao.updateProductInfo(product);
    }

    private void putProductOnSale(HttpServletRequest request) throws Exception {
        int productId = Integer.parseInt(request.getParameter("productId"));
        productDao.putProductOnSale(productId);
    }

    private void takeProductOffSale(HttpServletRequest request) throws Exception {
        int productId = Integer.parseInt(request.getParameter("productId"));
        productDao.takeProductOffSale(productId);
    }
}
