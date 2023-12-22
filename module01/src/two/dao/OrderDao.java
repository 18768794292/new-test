package two.dao;

import two.domain.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDao {
    void addToOrder(List<Integer> productIds, List<String> productNames, List<BigDecimal> productPrices,
                    List<String> images, List<Integer> quantities, String username, String phoneNumber, String address) throws Exception;

    List<OrderItem> getOrderItems() throws Exception;
    OrderItem getOrderItemById(int productId) throws Exception;
    void updateOrderStatus(int orderId, String deliveryStatus) throws Exception;
    void updateStockOnOrderShipped(int orderId) throws Exception;
    List<OrderItem> getAllOrders() throws Exception;

}
