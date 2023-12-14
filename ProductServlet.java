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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

                // 添加其他操作的case...
                default:
                    // 处理未知的操作
                    response.sendRedirect(request.getContextPath() + "/error.jsp");
            }

            // 重定向到商品列表页面或其他适当的页面
            response.sendRedirect(request.getContextPath() + "/products?typeId=0"); // 修改为你的实际URL
        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常，可以重定向到错误页面
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    // 从请求中获取商品信息并更新
    private void updateProductInfo(HttpServletRequest request) throws Exception {
        Product product = getProductFromRequest(request);
        productDao.updateProductInfo(product);
    }

    // 从请求中获取商品ID并执行上架操作
    private void putProductOnSale(HttpServletRequest request) throws Exception {
        int productId = Integer.parseInt(request.getParameter("productId"));
        productDao.putProductOnSale(productId);
    }

    // 从请求中获取商品ID并执行下架操作
    private void takeProductOffSale(HttpServletRequest request) throws Exception {
        int productId = Integer.parseInt(request.getParameter("productId"));
        productDao.takeProductOffSale(productId);
    }



    // 根据实际情况，从请求中获取商品信息的方法
    private Product getProductFromRequest(HttpServletRequest request) {
        Product product = new Product();
        // 从request中获取参数并设置到product对象中
        return product;
    }
}
