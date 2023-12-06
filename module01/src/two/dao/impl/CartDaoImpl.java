package two.dao.impl;

import two.dao.CartDao;
import two.domain.CartItem;
import two.utils.JdbcUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    @Override
    public void addToCart(int productId, String productName, BigDecimal productPrice, int quantity) throws Exception {
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO shopping_cart (user_id, product_id, quantity) VALUES (?, ?, ?)")) {

            // 假设用户ID为1（你需要根据实际情况获取用户ID）
            //暂时先用这里
            //
            //
            int userId = 1;

            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, quantity);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            handleSQLException(e);
            throw new Exception("Error adding to cart", e);
        }
    }

    @Override
    public List<CartItem> getCartItems() throws Exception {
        List<CartItem> cartItems = new ArrayList<>();

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT c.product_id, p.name AS product_name, p.price, c.quantity " +
                             "FROM shopping_cart c " +
                             "JOIN products p ON c.product_id = p.id " +
                             "WHERE c.user_id = ?")) {

            // 假设用户ID为1（你需要根据实际情况获取用户ID）
            int userId = 1;

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    CartItem cartItem = extractCartItemFromResultSet(resultSet);
                    cartItems.add(cartItem);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
            throw new Exception("Error fetching cart items", e);
        }

        return cartItems;
    }



    private CartItem extractCartItemFromResultSet(ResultSet resultSet) throws SQLException {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(resultSet.getInt("product_id"));
        cartItem.setProductName(resultSet.getString("product_name"));
        cartItem.setPrice(resultSet.getBigDecimal("price"));
        cartItem.setQuantity(resultSet.getInt("quantity"));
        return cartItem;
    }

    private void handleSQLException(SQLException e) {
        e.printStackTrace();
        // Log the exception or handle it as needed
    }
}
