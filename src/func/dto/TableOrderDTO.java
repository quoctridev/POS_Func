package func.dto;

public class TableOrderDTO {

    int orderTableId;
    int customerId;
    int tableId;
    String note;
    String status;

    public TableOrderDTO() {
    }

    public TableOrderDTO(int orderTableId, int customerId, int tableId, String note, String status) {
        this.orderTableId = orderTableId;
        this.customerId = customerId;
        this.tableId = tableId;
        this.note = note;
        this.status = status;
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

}
