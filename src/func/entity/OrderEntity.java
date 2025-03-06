/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author quoctris.dev
 */
public class OrderEntity {

    private int orderId;
    private int cashierId;
    private int chefId;
    private int customerId;
    private String customerName;
    private String status;
    private BigDecimal totalPrice;
    private int discountId;
    private int tableId;
    private String note;
    private Date createdAt;
    private Date completedAt;

    public OrderEntity() {
    }

    public OrderEntity(int orderId, int cashierId, int chefId, int customerId, String customerName, String status, BigDecimal totalPrice, int discountId, int tableId, String note, Date createdAt, Date completedAt) {
        this.orderId = orderId;
        this.cashierId = cashierId;
        this.chefId = chefId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.status = status;
        this.totalPrice = totalPrice;
        this.discountId = discountId;
        this.tableId = tableId;
        this.note = note;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCashierId() {
        return cashierId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public int getChefId() {
        return chefId;
    }

    public void setChefId(int chefId) {
        this.chefId = chefId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Date completedAt) {
        this.completedAt = completedAt;
    }

}
