

package func.entity;

import java.util.Date;


public class MergeTableEntity {
    int orderId;
    int tableId;
    Date createdAt;

    public MergeTableEntity() {
    }

    public MergeTableEntity(int orderId, int tableId, Date createdAt) {
        this.orderId = orderId;
        this.tableId = tableId;
        this.createdAt = createdAt;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    
}
