package two.dao;

import java.util.List;
import two.domain.ProductType;

public interface ProductTypeDao {
    List<ProductType> getAllProductTypes() throws Exception;
    ProductType getProductTypeById(int typeId) throws Exception;
    void addProductType(ProductType productType) throws Exception;
    void updateProductType(ProductType productType) throws Exception;
    void deleteProductType(int typeId) throws Exception;
}
