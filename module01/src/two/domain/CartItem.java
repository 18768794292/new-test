package two.domain;

import java.math.BigDecimal;

public class CartItem {
    private int productId;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private String image;
    private int cartId;
    public CartItem() {
    }

    public CartItem(int productId, String productName, BigDecimal price, String image,int quantity,int cartId) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.image=image;
        this.quantity = quantity;
        this.cartId=cartId;
    }

    public String getProductImage() {
        return image;
    }

    public void setProductImage(String image) {
        this.image = image;
    }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

}
