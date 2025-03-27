/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package func.dto;

import java.util.Date;

/**

 */
public class OrderOrderDetailDTO {
private int orderId;
     Date orderDate;
     String cashierName;
     String zone;
     int tableNumber;
     String status;
     String note;

    public OrderOrderDetailDTO() {
    }

    public OrderOrderDetailDTO(int orderId, Date orderDate, String cashierName, String zone, int tableNumber, String status, String note) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.cashierName = cashierName;
        this.zone = zone;
        this.tableNumber = tableNumber;
        this.status = status;
        this.note = note;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
}
