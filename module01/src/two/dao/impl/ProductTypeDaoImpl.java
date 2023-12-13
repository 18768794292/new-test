package two.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import two.dao.ProductTypeDao;
import two.domain.ProductType;
import two.utils.JdbcUtil;

public class ProductTypeDaoImpl implements ProductTypeDao {

    @Override
    public List<ProductType> getAllProductTypes() throws Exception {
        List<ProductType> productTypes = new ArrayList<>();

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product_types");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ProductType productType = extractProductTypeFromResultSet(resultSet);
                productTypes.add(productType);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return productTypes;
    }

    private ProductType extractProductTypeFromResultSet(ResultSet resultSet) throws SQLException {
        ProductType productType = new ProductType();
        productType.setId(resultSet.getInt("id"));
        productType.setTypeName(resultSet.getString("type_name"));
        return productType;
    }
    @Override
    public ProductType getProductTypeById(int typeId) throws Exception {
        ProductType productType = null;

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM product_types WHERE id = ?");
        ) {
            preparedStatement.setInt(1, typeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    productType = extractProductTypeFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

        return productType;
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace();
        // 处理异常的代码
    }
}
