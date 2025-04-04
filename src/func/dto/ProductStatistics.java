package func.dto;

import java.math.BigDecimal;

public class ProductStatistics {

    String productName;
    int quantity;
    BigDecimal total;

    public ProductStatistics() {
    }

    public ProductStatistics(String productName, int quantity, BigDecimal total) {
        this.productName = productName;
        this.quantity = quantity;
        this.total = total;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
