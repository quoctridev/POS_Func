/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.dao.FuncDAO;
import func.entity.OrderTableEntity;
import java.util.ArrayList;
import java.util.List;
import func.utils.Database;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Set;

/**
 *
 * @author HOANG NHI
 */
public class OrderTableDAO extends FuncDAO<OrderTableEntity, String> {

    public void insert(OrderTableEntity entity) {
        String sql = "INSERT INTO Order_Table ( customer_id, table_id, phone, note, [status]) VALUES (?, ?, ?, ?)";
        Database.update(sql, entity.getCustomerId(), entity.getTableId(), entity.getPhone(), entity.getNote(), entity.getStatus());
    }

    public void update(OrderTableEntity entity) {
        String sql = "UPDATE Order_Table SET customer_id=?, table_id=?, phone=?, note=?, [status]=?  WHERE order_table_id = ?";
        Database.update(sql, entity.getCustomerId(), entity.getTableId(), entity.getPhone(), entity.getNote(), entity.getStatus());
    }

    public void delete(String id) {
        String sql = "DELETE FROM Order_Table WHERE order_table_id = ? ";
        Database.update(sql, id);
    }

    public OrderTableEntity selectById(String id) {
        String sql = "SELECT TOP 1 FROM Order_Table WHERE order_table_id = ?";
        List<OrderTableEntity> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<OrderTableEntity> selectAll() {
        String sql = "SELECT * FROM Order_Table";
        return selectBySql(sql);
    }

    protected List<OrderTableEntity> selectBySql(String sql, Object... args) {
        List<OrderTableEntity> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    OrderTableEntity ordertb = new OrderTableEntity();
                    ordertb.setOrderTableId(rs.getInt("order_table_id"));
                    ordertb.setCustomerId(rs.getInt("customer_id"));
                    ordertb.setTableId(rs.getInt("table_id "));
                    ordertb.setPhone(rs.getString("phone"));
                    ordertb.setNote("note");
                    ordertb.setStatus("status");
                    ordertb.setCreatedAt(rs.getDate("created_at"));

                    list.add(ordertb);

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

}
