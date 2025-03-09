/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dto;
import java.util.Date;
import java.math.BigDecimal;
/**
 *
 * @author AZ Tech
 */
public class OrderDetailStatusConnectPayments {
    int  status_id,chef_id,order_detail_id, payment_id,order_id;
    String status;
    Date created_at, payment_time;
    BigDecimal amount;

    public OrderDetailStatusConnectPayments() {
    }

    public OrderDetailStatusConnectPayments(int status_id, int chef_id, int order_detail_id, int payment_id, int order_id, String status, Date created_at, Date payment_time, BigDecimal amount) {
        this.status_id = status_id;
        this.chef_id = chef_id;
        this.order_detail_id = order_detail_id;
        this.payment_id = payment_id;
        this.order_id = order_id;
        this.status = status;
        this.created_at = created_at;
        this.payment_time = payment_time;
        this.amount = amount;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public int getChef_id() {
        return chef_id;
    }

    public void setChef_id(int chef_id) {
        this.chef_id = chef_id;
    }

    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getPayment_time() {
        return payment_time;
    }

    public void setPayment_time(Date payment_time) {
        this.payment_time = payment_time;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
}
