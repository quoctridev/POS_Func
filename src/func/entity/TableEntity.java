package func.entity;

import java.util.Date;

public class TableEntity {

    int tableId;
    int tableNumber;
    String status;
    int capacity;
    int zone;
    Date createdAt;

    public TableEntity() {
    }

    public TableEntity(int tableId, int tableNumber, String status, int capacity, int zone, Date createdAt) {
        this.tableId = tableId;
        this.tableNumber = tableNumber;
        this.status = status;
        this.capacity = capacity;
        this.zone = zone;
        this.createdAt = createdAt;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

}
