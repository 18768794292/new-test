package two.domain;

import java.math.BigDecimal;

public class OrderItem {
    private int productId;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private BigDecimal total;  // 总价
    private String image;  // 商品图片

    public OrderItem() {
        // 默认构造方法
    }

    public OrderItem(int productId, String productName, BigDecimal price, String image, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.total = price.multiply(BigDecimal.valueOf(quantity));  // 计算总价
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
        updateTotal();  // 更新总价
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void updateTotal() {
        this.total = price.multiply(BigDecimal.valueOf(quantity));  // 更新总价
    }
}
