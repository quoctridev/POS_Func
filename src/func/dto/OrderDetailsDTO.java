package func.dto;

import java.math.BigDecimal;

public class OrderDetailsDTO {

    int quantity;
    String productName;
    BigDecimal price;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(int quantity, String productName, BigDecimal price) {
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
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
