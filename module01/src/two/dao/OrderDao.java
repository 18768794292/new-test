package two.dao;

import two.domain.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDao {
    void addToOrder(int productId, String productName, BigDecimal productPrice, String image, int quantity,String username,String phoneNumber,String address) throws Exception;
    List<OrderItem> getOrderItems() throws Exception;
    OrderItem getOrderItemById(int productId) throws Exception;
    void updateOrderStatus(int orderId, String deliveryStatus) throws Exception;
    void updateStockOnOrderShipped(int orderId) throws Exception;
    List<OrderItem> getAllOrders() throws Exception;
}
