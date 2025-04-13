package func.entity;

import java.sql.Timestamp;
import java.util.Date;

public class OrderTableEntity {

    int orderTableId;
    int customerId;
    int capacity;
    String phone;
    String note;
    String status;
    Timestamp reservationTime;
    String customer_name;
    Date createdAt;

    public OrderTableEntity() {
    }

    public OrderTableEntity(int orderTableId, int customerId, int capacity, String phone, String note, String status, Timestamp reservationTime, String customer_name, Date createdAt) {
        this.orderTableId = orderTableId;
        this.customerId = customerId;
        this.capacity = capacity;
        this.phone = phone;
        this.note = note;
        this.status = status;
        this.reservationTime = reservationTime;
        this.customer_name = customer_name;
        this.createdAt = createdAt;
    }

    public Timestamp getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(Timestamp reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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
