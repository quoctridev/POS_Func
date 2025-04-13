/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dto;

import java.util.Date;

/**
 *
 */
public class OrderOrderDetailDTO {

    int orderDetailId;
    String productName, tableName, zoneName, status;
    Date createdAt;

    public OrderOrderDetailDTO() {
    }

    public OrderOrderDetailDTO(int orderId, String productName, String tableName, String zoneName, String status, Date createdAt) {
        this.orderDetailId = orderDetailId;
        this.productName = productName;
        this.tableName = tableName;
        this.zoneName = zoneName;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
