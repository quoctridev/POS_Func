/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.entity.MergeTableEntity;
import func.utils.Database;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author quoctris.dev
 */
public class MergeTableDAO {

    public void insertMergeTable(MergeTableEntity tb) {
        String sql = "INSERT INTO MergedTables (order_id, table_id) VALUES (?, ?);";
        Database.update(sql, tb.getOrderId(), tb.getTableId());
    }

    public int getActiveOrderIdByTable(int tableId) {
        String sql = "SELECT TOP 1 o.order_id FROM Orders o "
                + "JOIN MergedTables m ON o.order_id = m.order_id "
                + "WHERE m.table_id = ? AND o.status IN ('new', 'processing') "
                + "ORDER BY o.order_date DESC";
        try {
            ResultSet rs = Database.query(sql, tableId);
            if (rs.next()) {
                return rs.getInt("order_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // Không tìm thấy Order
    }
}
