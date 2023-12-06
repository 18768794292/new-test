package two.dao;

import two.domain.CartItem;

import java.math.BigDecimal;
import java.util.List;

public interface CartDao {
    void addToCart(int productId, String productName, BigDecimal productPrice, int quantity) throws Exception;

    List<CartItem> getCartItems() throws Exception;
}
