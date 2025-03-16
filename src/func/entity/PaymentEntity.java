package func.entity;

import java.math.BigDecimal;
import java.util.Date;

public class PaymentEntity {

    int paymentId;
    Date paymentTime;
    int orderId;
    String paymentMethod;
    String status;
    BigDecimal totalAmount;

    public PaymentEntity() {
    }

    public PaymentEntity(int paymentId, Date paymentTime, int orderId, String paymentMethod, String status, BigDecimal totalAmount) {
        this.paymentId = paymentId;
        this.paymentTime = paymentTime;
        this.orderId = orderId;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

}
