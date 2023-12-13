package two.domain;

import java.math.BigDecimal;

public class OrderItem {
    private int orderId; // 新增订单ID
    private int productId;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private BigDecimal total;  // 总价
    private String image;  // 商品图片
    // 用户名
    private String username;
   private String phoneNumber;
  private String address;

    public OrderItem() {
        // 默认构造方法
    }

    public OrderItem(int orderId,int productId, String productName, BigDecimal price, String image, int quantity,String username,String phoneNumber,String address) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.username = username;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.total = price.multiply(BigDecimal.valueOf(quantity));  // 计算总价
    }
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
