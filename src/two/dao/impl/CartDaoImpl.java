// two.dao.impl.CartDaoImpl
package two.dao.impl;

import two.dao.CartDao;
import two.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private List<Product> cartItems = new ArrayList<>();

    @Override
    public void addToCart(Product product) {
        cartItems.add(product);
    }

    @Override
    public List<Product> getCartItems() {
        return cartItems;
    }

    @Override
    public List<Integer> getCartItemIds() {
        List<Integer> cartItemIds = new ArrayList<>();
        for (Product product : cartItems) {
            cartItemIds.add(product.getId());
        }
        return cartItemIds;
    }
    @Override
    public Product getProductById(int productId) {
        // 根据 productId 从数据库或其他数据源获取商品对象
        // 在这里你需要实现根据 productId 获取商品对象的逻辑
        // 这里简化为直接创建一个 Product 对象
        Product product = new Product();
        product.setId(productId);
        // 其他属性的设置...

        return product;
    }
    // 其他购物车操作，比如 removeFromCart、clearCart 等
}
