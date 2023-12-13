package two.dao.impl;

import two.dao.ProductDao;
import two.domain.Product;
import two.domain.ProductType;
import two.utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    @Override
    public List<Product> getAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT p.*, pt.type_name FROM products p JOIN product_types pt ON p.type = pt.id");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                products.add(product);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return products;
    }

    // 其他方法...
    @Override
    public List<Product> getProductsByType(int typeId) throws Exception {
        List<Product> products = new ArrayList<>();

        try (Connection connection = JdbcUtil.getConnection();//注意sql语句
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.*, pt.type_name FROM products p JOIN product_types pt ON p.type = pt.id WHERE ? = 0 OR p.type = ?")) {

            preparedStatement.setInt(1, typeId);
            preparedStatement.setInt(2, typeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = extractProductFromResultSet(resultSet);
                    products.add(product);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return products;
    }


    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setDescription(resultSet.getString("description"));
        product.setImage(resultSet.getString("image"));
        product.setStock(resultSet.getInt("stock"));

        // 从结果集中获取商品类型的相关信息
        ProductType productType = new ProductType();
        productType.setId(resultSet.getInt("type"));
        productType.setTypeName(resultSet.getString("type_name")); // 使用正确的列名
        product.setProductType(productType);

        return product;
    }


    // 其他方法...


    private void handleSQLException(SQLException e) {
        e.printStackTrace();
        // Log the exception or handle it as needed
    }


}
