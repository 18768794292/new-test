// two.dao.ProductDao
package two.dao;

import java.util.List;
import two.domain.Product;

public interface ProductDao {
    List<Product> getAllProducts();
    Product getProductById(int productId);
}
