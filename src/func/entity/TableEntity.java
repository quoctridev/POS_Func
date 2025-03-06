/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.entity;

import java.util.Date;

/**
 *
 * @author quoctris.dev
 */
public class TableEntity {

    private int tableId;
    private String tableNumber;
    private String status;
    private Date createdAt;

    public TableEntity() {
    }

    public TableEntity(int tableId, String tableNumber, String status, Date createdAt) {
        this.tableId = tableId;
        this.tableNumber = tableNumber;
        this.status = status;
        this.createdAt = createdAt;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
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
