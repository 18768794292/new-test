package two.dao;

import java.util.List;
import two.domain.Product;
import two.domain.ProductType;

public interface ProductDao {
    List<Product> getAllProducts() throws Exception;
    List<Product> getProductsByType(int typeId) throws Exception;

}