// CartDao.java

package two.dao;

import two.domain.Product;

import java.util.List;

public interface CartDao {
    void addToCart(Product product);
    List<Product> getCartItems();

    List<Integer> getCartItemIds();
    Product getProductById(int productId); // 新增方法
    // 其他购物车操作，比如 removeFromCart、clearCart 等
}
