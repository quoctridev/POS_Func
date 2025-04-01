/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.sql.Timestamp;
/**
 *
 * @author quoctris.dev
 */
public class BillDTO {

    int orderId;
    String customerPhone, customerName, status, numberTable;
    BigDecimal total_price;
    Timestamp orderDate;

    public BillDTO() {
    }

    public BillDTO(int orderId, String customerPhone, String customerName, String status, String numberTable, BigDecimal total_price, Timestamp orderDate) {
        this.orderId = orderId;
        this.customerPhone = customerPhone;
        this.customerName = customerName;
        this.status = status;
        this.numberTable = numberTable;
        this.total_price = total_price;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(String numberTable) {
        this.numberTable = numberTable;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
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

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

}
