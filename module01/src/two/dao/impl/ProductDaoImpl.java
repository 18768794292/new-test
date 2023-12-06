package two.dao.impl;

import two.dao.ProductDao;
import two.domain.Product;
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
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products");
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



    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setDescription(resultSet.getString("description"));
        product.setImage(resultSet.getString("image"));
        product.setStock(resultSet.getInt("stock"));
        return product;
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace();
        // Log the exception or handle it as needed
    }


}
