package two.dao;

import two.domain.CartItem;

import java.math.BigDecimal;
import java.util.List;

public interface CartDao {
    void addToCart(int productId, String productName, BigDecimal productPrice, int quantity) throws Exception;

    List<CartItem> getCartItems() throws Exception;

    CartItem getCartItemById(int productId) throws Exception;
    // Remove item from the cart
    void removeFromCart(int cartId) throws Exception;
}
