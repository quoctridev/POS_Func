package func.dto;

import java.math.BigDecimal;

public class OrderDetailsDTO {

    int quantity;
    String productName;
    BigDecimal price;
    int productId;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int quantity, String productName, BigDecimal price, int productId) {
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
        this.productId = productId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

}
