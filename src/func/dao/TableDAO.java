/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.dao.FuncDAO;
import func.entity.TableEntity;
import func.utils.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author HOANG NHI
 */
public class TableDAO extends FuncDAO<TableEntity, String> {

    public void insert(TableEntity entity) {
        String sql = "INSERT INTO Tables (table_number, [status],capacity, [zone_id]) VALUES (?, ?, ?,?)";
        Database.update(sql, entity.getTableNumber(), entity.getStatus(), entity.getCapacity(), entity.getZoneId());
    }

    public void update(TableEntity entity) {
        String sql = "UPDATE Tables SET capacity = ?, status = ? WHERE table_id = ?";
        Database.update(sql, entity.getCapacity(), entity.getStatus(), entity.getTableId());
    }

    public void delete(String id) {
        String sql = "DELETE FROM Tables WHERE table_id = ?";
        Database.update(sql, id);
    }

    public TableEntity selectById(String id) {
        String sql = "SELECT TOP 1 * FROM Tables WHERE table_id = ?";
        List<TableEntity> list = selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<TableEntity> selectAll() {
        String sql = "SELECT * FROM Tables AS tb JOIN Zones AS z ON tb.zone_id = z.zone_id";
        return selectBySql(sql);
    }

    protected List<TableEntity> selectBySql(String sql, Object... args) {
        List<TableEntity> list = new ArrayList<>();
        try (ResultSet rs = Database.query(sql, args)) {
            while (rs.next()) {
                TableEntity tb = new TableEntity();
                tb.setTableId(rs.getInt("table_id"));
                tb.setTableNumber(rs.getString("table_number"));
                tb.setStatus(rs.getString("status"));
                tb.setCapacity(rs.getInt("capacity"));
                tb.setZoneId(rs.getInt("zone_id"));
                tb.setCreatedAt(rs.getDate("created_at"));
                tb.setZone_name(rs.getNString("zone_name"));
                list.add(tb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // Zone
    public List<TableEntity> selectByZone(String zone) {
        String sql = "SELECT * FROM Tables AS tb JOIN Zones AS z ON tb.zone_id = z.zone_id WHERE zone_name = ?";
        return selectBySql(sql, zone);
    }

    public List<String> selectTableNumberByOrderId(String id) {
        String sql = "SELECT t.table_number FROM Orders o JOIN MergedTables m ON o.order_id = m.order_id JOIN Tables t ON t.table_id = m.table_id WHERE o.order_id = ?";
        List<String> list = new ArrayList<>();
        try (ResultSet rs = Database.query(sql, id)) {
            while (rs.next()) {
                list.add(rs.getString("table_number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<String> selectZone() {
        String sql = "SELECT zone_name FROM Zones";
        List<String> list = new ArrayList<>();
        try (ResultSet rs = Database.query(sql)) {
            while (rs.next()) {
                list.add(rs.getNString("zone_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    public void updateZone(String id, String name_zone) {
        String sql = "UPDATE Zones SET zone_name = ? WHERE zone_id = ?";
        Database.update(sql, name_zone, id);
    }

    public void deleteZone(String id) {
        String sql = "DELETE FROM Zones WHERE zone_id = ?";
        Database.update(sql, id);
    }

    public void insertZone(String name) {
        String sql = "INSERT INTO Zones(zone_name) VALUES (?)";
        Database.update(sql, name);
    }

    public int findZoneIdByName(String name) {
        String sql = "SELECT zone_id FROM Zones WHERE zone_name = ?";
        try (ResultSet rs = Database.query(sql, name)) {
            if (rs.next()) {
                return rs.getInt("zone_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return -1;
    }

    public void updateTableStatus(TableEntity tb) {
        String sql = "UPDATE Tables SET status = ? WHERE table_id = ?";
        Database.update(sql, tb.getStatus(), tb.getTableId());
    }

    public boolean isMergedTable(int tableId) {
        String sql = "SELECT COUNT(*) FROM MergedTables WHERE table_id = ?";
        try (ResultSet rs = Database.query(sql, tableId)) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return false;
    }

    public int getSeatsUsed(int tableId) {
        String sql = " SELECT * FROM Tables t \n"
                + " JOIN MergedTables mt ON t.table_id = mt.table_id \n"
                + " JOIN Orders o ON mt.order_id = o.order_id \n"
                + " WHERE t.table_id = ? and t.[status] = 'occupied'";
        try (ResultSet rs = Database.query(sql, tableId)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return 0;
    }

    public List<String> getMergedTables(int tableId) {
        String sql = "SELECT table_id FROM MergedTables WHERE order_id = (SELECT order_id FROM MergedTables WHERE table_id = ?)";
        List<String> mergedTables = new ArrayList<>();
        try (ResultSet rs = Database.query(sql, tableId)) {
            while (rs.next()) {
                mergedTables.add(rs.getString("table_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return mergedTables;
    }

    public boolean chuyenBan(List<String> sourceTables, List<String> targetTables) {
        try {
            // Lấy order_id của bàn nguồn (chỉ cần lấy 1 bàn đầu tiên)
            String getOrderIdSql = "SELECT order_id FROM MergedTables WHERE table_id = ?";
            int orderId = -1;
            try (ResultSet rs = Database.query(getOrderIdSql, sourceTables.get(0))) {
                if (rs.next()) {
                    orderId = rs.getInt("order_id");
                }
            }

            if (orderId == -1) {
                System.out.println("Không tìm thấy order_id cho bàn nguồn!");
                return false;
            }

            // Xóa tất cả bàn cũ khỏi MergedTables
            String deleteOldTablesSql = "DELETE FROM MergedTables WHERE table_id = ?";
            for (String sourceTable : sourceTables) {
                Database.update(deleteOldTablesSql, sourceTable);
            }

            // Thêm bàn mới vào MergedTables
            String insertNewTablesSql = "INSERT INTO MergedTables (order_id, table_id) VALUES (?, ?)";
            for (String targetTable : targetTables) {
                Database.update(insertNewTablesSql, orderId, targetTable);
            }

            // Cập nhật trạng thái bàn (bàn cũ → available, bàn mới → occupied)
            String updateTableStatusSql = "UPDATE Tables SET status = ? WHERE table_id = ?";
            for (String sourceTable : sourceTables) {
                Database.update(updateTableStatusSql, "available", sourceTable);
            }
            for (String targetTable : targetTables) {
                Database.update(updateTableStatusSql, "occupied", targetTable);
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getTableStatus(int tableId) {
        String sql = "SELECT status FROM Tables WHERE table_id = ?";
        try (ResultSet rs = Database.query(sql, tableId)) {
            if (rs.next()) {
                return rs.getString("status");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return "unknown";
    }
}
