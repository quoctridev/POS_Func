/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dto;
import java.util.Date;

/**
 *
 * @author AZ Tech
 */
public class UserConnectOrderDTO {
    int userId,orderId,cashierId,chefId,customerId,discountId,tableId;
    String username, password,role,customerName, status,note;
    Date createdAt,completedAt;

    public UserConnectOrderDTO() {
    }

    public UserConnectOrderDTO(int userId, int orderId, int cashierId, int chefId, int customerId, int discountId, int tableId, String username, String password, String role, String customerName, String status, String note, Date createdAt, Date completedAt) {
        this.userId = userId;
        this.orderId = orderId;
        this.cashierId = cashierId;
        this.chefId = chefId;
        this.customerId = customerId;
        this.discountId = discountId;
        this.tableId = tableId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.customerName = customerName;
        this.status = status;
        this.note = note;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
