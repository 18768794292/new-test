package two.dao;

import java.util.List;
import two.domain.Product;
import two.domain.ProductType;

public interface ProductDao {
    List<Product> getAllProducts() throws Exception;
    List<Product> getProductsByType(int typeId) throws Exception;
    void addProduct(Product product) throws Exception;
    int getProductStatus(int productId) throws Exception;
    void updateProductInfo(Product product) throws Exception;
    void putProductOnSale(int productId) throws Exception;
    void takeProductOffSale(int productId) throws Exception;
    void changeProductStatus(int productId, int status) throws Exception;
    Product getProductById(int productId);
}