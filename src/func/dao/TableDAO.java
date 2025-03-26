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
import java.util.Set;

/**
 *
 * @author HOANG NHI
 */
public class TableDAO extends FuncDAO<TableEntity, String> {

    public void insert(TableEntity entity) {
        String sql = "INSERT INTO Tables (table_number, capacity, [zone_id]) VALUES (?, ?, ?)";
        Database.update(sql, entity.getTableNumber(), entity.getCapacity(), entity.getZone());
    }

    public void update(TableEntity entity) {
        String sql = "UPDATE Tables\n"
                + "SET table_number = ?,\n"
                + "capacity = ?,\n"
                + "[status] = ?\n"
                + "WHERE table_id = ?";
        Database.update(sql, entity.getTableNumber(), entity.getCapacity(), entity.getStatus(), entity.getTableId());
    }

    public void delete(String id) {
        String sql = "DELETE FROM Tables WHERE table_id = ? ";
        Database.update(sql, id);
    }

    public TableEntity selectById(String id) {
        String sql = "SELECT TOP 1 FROM Tables WHERE table_id = ?";
        List<TableEntity> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<TableEntity> selectAll() {
        String sql = "SELECT * FROM Tables";
        return selectBySql(sql);
    }

    protected List<TableEntity> selectBySql(String sql, Object... args) {
        List<TableEntity> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    TableEntity tb = new TableEntity();
                    tb.setTableId(rs.getInt("table_id"));
                    tb.setTableNumber(rs.getString("table_number"));
                    tb.setStatus(rs.getString("status"));
                    tb.setCapacity(rs.getInt("capacity"));
                    tb.setZone(rs.getString("zone_id"));
                    tb.setCreatedAt(rs.getDate("created_at"));
                    list.add(tb);

                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }
//Zone

    public List<TableEntity> selectByZone(String zone) {
        String sql = "SELECT * FROM Tables AS tb JOIN Zones AS z ON tb.zone_id = z.zone_id WHERE zone_name = ?";
        return selectBySql(sql, zone);
    }

    public List<String> selectTableNumberByOrderId(String id) {
        String sql = "SELECT t.table_number\n"
                + "FROM Orders o\n"
                + "JOIN MergedTables m ON o.order_id = m.order_id\n"
                + "JOIN Tables t ON t.table_id = m.table_id\n"
                + "WHERE o.order_id = ?";
        List<String> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            rs = Database.query(sql, id);
            while (rs.next()) {
                System.out.println(rs.getString("table_number"));
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
        try {
            ResultSet rs = null;
            rs = Database.query(sql);
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
        String sql = "UPDATE Zones\n"
                + "SET zone_name = ?\n"
                + "WHERE zone_id = ?";
        Database.update(sql, name_zone, id);
    }

    public void deleteZone(String id) {
        String sql = "DELETE FROM Zones\n"
                + "WHERE table_id = ?";
        Database.update(sql, id);
    }

    public void updateTableStatus(TableEntity tb) {
        String sql = "UPDATE Tables SET status = ? WHERE table_id = ?;";
        Database.update(sql, tb.getStatus(), tb.getTableId());
    }

}
