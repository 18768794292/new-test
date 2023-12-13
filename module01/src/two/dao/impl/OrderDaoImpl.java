package two.dao.impl;

import two.dao.OrderDao;
import two.domain.OrderItem;
import two.service.UserService;
import two.utils.JdbcUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public void addToOrder(int productId, String productName, BigDecimal productPrice, String image, int quantity,String username,String phoneNumber,String address) throws Exception {
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);  // 关闭自动提交

            int userId = UserService.getLoggedInUserId();

// 先插入订单表，获取生成的订单ID
            int orderId;
            try (PreparedStatement orderStatement = connection.prepareStatement(
                    "INSERT INTO orders (user_id) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {

                orderStatement.setInt(1, userId);
                orderStatement.executeUpdate();

                // 获取生成的订单ID
                try (ResultSet generatedKeys = orderStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderId = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("Creating order failed, no ID obtained.");
                    }
                }
            }

// 输出订单信息
            System.out.println("User ID: " + userId + ", Order ID: " + orderId);

// 在订单明细表中插入订单明细信息
            try (PreparedStatement detailsStatement = connection.prepareStatement(
                    "INSERT INTO order_details (order_id, product_id, quantity, total_price) VALUES (?, ?, ?, ?)")) {

                detailsStatement.setInt(1, orderId);
                detailsStatement.setInt(2, productId);
                detailsStatement.setInt(3, quantity);
                detailsStatement.setBigDecimal(4, productPrice.multiply(BigDecimal.valueOf(quantity))); // 计算总价

                detailsStatement.executeUpdate();
            }


            connection.commit();  // 手动提交事务
        } catch (SQLException e) {
            handleSQLException(e);
            if (connection != null) {
                connection.rollback();  // 发生异常时回滚事务
            }
            throw new Exception("Error adding to order", e);
        } finally {
            if (connection != null) {
                connection.setAutoCommit(true);  // 恢复自动提交状态
                connection.close();  // 关闭连接
            }
        }
    }

// 其他方法保持不变


    public List<OrderItem> getOrderItems() throws Exception {
        List<OrderItem> orderItems = new ArrayList<>();

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT o.product_id, p.name AS product_name, p.price, o.quantity, o.total_price, p.image, " +
                             "u.username, u.phoneNumber, u.address, ord.order_id " +
                             "FROM order_details o " +
                             "JOIN products p ON o.product_id = p.id " +
                             "JOIN orders ord ON o.order_id = ord.order_id " +
                             "JOIN javaweb_user u ON ord.user_id = u.id " +
                             "WHERE ord.user_id = ?"
             )) {

            int userId = UserService.getLoggedInUserId();
            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    OrderItem orderItem = extractOrderItemFromResultSet(resultSet);
                    orderItems.add(orderItem);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
            throw new Exception("Error fetching order items", e);
        }

        return orderItems;
    }

    public OrderItem getOrderItemById(int productId) throws Exception {
        OrderItem orderItem = null;

        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT o.product_id, p.name AS product_name, p.price, o.quantity, o.total_price, p.image, ord.order_id " +
                             "FROM order_details o " +
                             "JOIN products p ON o.product_id = p.id " +
                             "JOIN orders ord ON o.order_id = ord.order_id " +
                             "WHERE ord.user_id = ? AND o.product_id = ?"
             )) {

            int userId = UserService.getLoggedInUserId();
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    orderItem = extractOrderItemFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
            throw new Exception("Error fetching order item by ID", e);
        }

        return orderItem;
    }

    private OrderItem extractOrderItemFromResultSet(ResultSet resultSet) throws SQLException {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(resultSet.getInt("product_id"));
        orderItem.setProductName(resultSet.getString("product_name"));
        orderItem.setPrice(resultSet.getBigDecimal("price"));
        orderItem.setQuantity(resultSet.getInt("quantity"));
        orderItem.updateTotal();
        orderItem.setProductImage(resultSet.getString("image"));
        orderItem.setUsername(resultSet.getString("username"));
        orderItem.setPhoneNumber(resultSet.getString("phoneNumber"));
        orderItem.setAddress(resultSet.getString("address"));
        orderItem.setOrderId(resultSet.getInt("order_id"));

        return orderItem;
    }


    private void handleSQLException(SQLException e) {
        e.printStackTrace();
        // Log the exception or handle it as needed
    }

    private int getLatestOrderId(int userId) throws Exception {
        try (Connection connection = JdbcUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT MAX(order_id) FROM orders WHERE user_id = ?")) {

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        }

        // 如果没有找到订单ID，可以返回一个默认值或者抛出异常，具体根据业务需求来定
        throw new SQLException("Unable to retrieve the latest order ID");
    }

}
