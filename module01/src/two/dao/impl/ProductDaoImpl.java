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
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT p.*, pt.type_name, p.status FROM products p JOIN product_types pt ON p.type = pt.id");
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

    @Override
    public List<Product> getProductsByType(int typeId) throws Exception {
        List<Product> products = new ArrayList<>();

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.*, pt.type_name, p.status FROM products p JOIN product_types pt ON p.type = pt.id WHERE ? = 0 OR p.type = ?")) {

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
    @Override
    public Product getProductById(int productId) {
        Product product = null;

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.*, pt.type_name FROM products p JOIN product_types pt ON p.type = pt.id WHERE p.id = ?")) {

            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    product = extractProductFromResultSet(resultSet);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("id"));
        product.setName(resultSet.getString("name"));
        product.setPrice(resultSet.getBigDecimal("price"));
        product.setDescription(resultSet.getString("description"));
        product.setImage(resultSet.getString("image"));
        product.setStock(resultSet.getInt("stock"));
        product.setStatus(resultSet.getInt("status")); // 添加获取status字段

        // 从结果集中获取商品类型的相关信息
        ProductType productType = new ProductType();
        productType.setId(resultSet.getInt("type"));
        productType.setTypeName(resultSet.getString("type_name"));
        product.setProductType(productType);

        return product;
    }

    @Override
    public void addProduct(Product product) throws Exception {
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products (name, price, description, image, stock, type) VALUES (?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getImage());
            preparedStatement.setInt(5, product.getStock());
            preparedStatement.setInt(6, product.getProductType().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public void updateProductInfo(Product product) throws Exception {
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE products SET name = ?, price = ?, description = ?, image = ?, stock = ?, type = ? WHERE id = ?")) {

            preparedStatement.setString(1, product.getName());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setString(4, product.getImage());
            preparedStatement.setInt(5, product.getStock());
            preparedStatement.setInt(6, product.getProductType().getId());
            preparedStatement.setInt(7, product.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    @Override
    public void putProductOnSale(int productId) throws Exception {
        changeProductStatus(productId, 1);
    }

    @Override
    public void takeProductOffSale(int productId) throws Exception {
        changeProductStatus(productId, 0);
    }

    @Override
    public void changeProductStatus(int productId, int status) throws Exception {
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE products SET status = ? WHERE id = ?")) {

            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, productId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
    @Override
    public int getProductStatus(int productId) throws Exception {
        int status = -1;

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT status FROM products WHERE id = ?")) {

            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    status = resultSet.getInt("status");
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }

        return status;
    }
    private void handleSQLException(SQLException e) {
        e.printStackTrace();

    }
}
