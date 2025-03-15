package func.dto;

public class TableDTO {

    int tableId;
    int tableNumber;
    String status;
    int capacity;
    int zone;

    public TableDTO() {
    }

    public TableDTO(int tableId, int tableNumber, String status, int capacity, int zone) {
        this.tableId = tableId;
        this.tableNumber = tableNumber;
        this.status = status;
        this.capacity = capacity;
        this.zone = zone;
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

}
