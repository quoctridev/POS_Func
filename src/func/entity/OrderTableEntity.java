package func.entity;

import java.util.Date;

public class OrderTableEntity {

    int orderTableId;
    int customerId;
    int tableId;
    String phone;
    String note;
    String status;
    Date createdAt;

    public OrderTableEntity() {
    }

    public OrderTableEntity(int orderTableId, int customerId, int tableId, String phone, String note, String status, Date createdAt) {
        this.orderTableId = orderTableId;
        this.customerId = customerId;
        this.tableId = tableId;
        this.phone = phone;
        this.note = note;
        this.status = status;
        this.createdAt = createdAt;
    }


    public int getOrderTableId() {
        return orderTableId;
    }

    public void setOrderTableId(int orderTableId) {
        this.orderTableId = orderTableId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
