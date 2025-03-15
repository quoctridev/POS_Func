package func.dto;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDTO {

    int orderId;
    int discountId;
    int cashierId;
    String customerPhone;
    Date orderDate;
    String customerName;
    BigDecimal totalPrice;
    String status;
    boolean isPaid;

    public OrderDTO() {
    }

    public OrderDTO(int orderId, int discountId, int cashierId, String customerPhone, Date orderDate, String customerName, BigDecimal totalPrice, String status, boolean isPaid) {
        this.orderId = orderId;
        this.discountId = discountId;
        this.cashierId = cashierId;
        this.customerPhone = customerPhone;
        this.orderDate = orderDate;
        this.customerName = customerName;
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

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
