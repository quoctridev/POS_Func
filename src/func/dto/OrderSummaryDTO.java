package func.dto;

import java.math.BigDecimal;

public class OrderSummaryDTO {

    int orderId;
    String customerName;
    String customerPhone;
    int tableNumber;
    BigDecimal totalPrice;
    String status;
    boolean isPaid;

    public OrderSummaryDTO() {
    }

    public OrderSummaryDTO(int orderId, String customerName, String customerPhone, int tableNumber, BigDecimal totalPrice, String status, boolean isPaid) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.tableNumber = tableNumber;
        this.totalPrice = totalPrice;
        this.status = status;
        this.isPaid = isPaid;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

}
