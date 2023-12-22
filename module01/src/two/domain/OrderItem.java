package two.domain;

import java.math.BigDecimal;
import java.security.Timestamp;

public class OrderItem {
    private int orderId;
    private int productId;
    private String productName;
    private BigDecimal price;
    private int quantity;
    private BigDecimal total;
    private String image;
    private String username;
   private String phoneNumber;
  private String address;
    private Timestamp orderDate;
    private String deliveryStatus;
    public OrderItem() {
    }

    public OrderItem(int orderId,int productId, String productName, BigDecimal price, String image, int quantity,String username,String phoneNumber,String address,String deliveryStatus, Timestamp orderDate) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
        this.username = username;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.deliveryStatus = deliveryStatus;
        this.total = price.multiply(BigDecimal.valueOf(quantity));
        this.orderDate = orderDate;

    }
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
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
        updateTotal();
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void updateTotal() {
        this.total = price.multiply(BigDecimal.valueOf(quantity));
    }
}
