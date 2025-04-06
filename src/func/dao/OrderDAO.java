/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.entity.OrderEntity;
import func.utils.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author This PC
 */
public class OrderDAO extends FuncDAO<OrderEntity, String> {

    @Override
    public void insert(OrderEntity entity) {
        String sql = "INSERT INTO Orders (discount_id, cashier_id,customer_phone,"
                + "customer_name, total_price, [status], is_paid, order_table_id)"
                + "VALUES (?,?,?,?,?,?,?,?)";
        Database.update(sql, entity.getDiscountId(), entity.getCashierId(),
                entity.getCustomerPhone(), entity.getCustomerName(), entity.getTotalPrice(),
                entity.getStatus(), entity.isIsPaid(), entity.getOrderTableId());
    }

    public int createOrder(OrderEntity od) {
        String sql;
        ResultSet rs;

        try {
            if (od.getOrderTableId() == 0) {
                // Nếu không có order_table_id, bỏ cột này khỏi INSERT
                sql = "DECLARE @InsertedTable TABLE (order_id INT); "
                        + "INSERT INTO Orders (cashier_id, status, customer_phone, customer_name) "
                        + "OUTPUT INSERTED.order_id  INTO @InsertedTable "
                        + "VALUES (?, ?,?,?); "
                        + "SELECT order_id FROM @InsertedTable;";
                rs = Database.query(sql, od.getCashierId(), od.getStatus(), od.getCustomerPhone(), od.getCustomerName());
            } else {
                // Nếu có order_table_id, insert đầy đủ
                sql = "DECLARE @InsertedTable TABLE (order_id INT); "
                        + "INSERT INTO Orders (cashier_id, status, order_table_id,customer_phone, customer_name) "
                        + "OUTPUT INSERTED.order_id INTO @InsertedTable "
                        + "VALUES (?, ?, ?,?,?); "
                        + "SELECT order_id FROM @InsertedTable;";
                rs = Database.query(sql, od.getCashierId(), od.getStatus(), od.getOrderTableId(), od.getCustomerPhone(), od.getCustomerName());
            }

            if (rs.next()) {
                return rs.getInt("order_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi tạo Order", e);
        }
        return -1; // Trả về -1 nếu thất bại
    }

    public void cancelOrder(OrderEntity od) {
        String sql = "UPDATE Orders\n"
                + "SET status = ?,\n"
                + "note = ?"
                + "WHERE order_id = ?";
        Database.update(sql, od.getStatus(), od.getNote(), od.getOrderId());
    }

    @Override
    public void update(OrderEntity entity) {
        String sql = "";
        if (entity.getDiscountId() == 0) {
            sql = "UPDATE Orders SET "
                    + "total_price = ?, [status] = ?, is_paid =? WHERE order_id = ?";
            Database.update(sql, entity.getTotalPrice(),
                    entity.getStatus(), entity.isIsPaid(), entity.getOrderId());
        } else {
            sql = "UPDATE Orders SET discount_id = ?,"
                    + "total_price = ?, [status] = ?, is_paid =? WHERE order_id = ?";
            Database.update(sql, entity.getDiscountId(), entity.getTotalPrice(),
                    entity.getStatus(), entity.isIsPaid(), entity.getOrderId());
        }

    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Orders WHERE order_id = ?";
        Database.update(sql, id);
    }

    @Override
    public OrderEntity selectById(String id) {
        String sql = "SELECT * FROM Orders WHERE order_id = ?";
        List<OrderEntity> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<OrderEntity> selectAll() {
        String sql = "SELECT * FROM Orders";
        return selectBySql(sql);
    }

    @Override
    protected List<OrderEntity> selectBySql(String sql, Object... args) {
        List<OrderEntity> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    OrderEntity entity = new OrderEntity();
                    entity.setOrderId(rs.getInt("order_id"));
                    entity.setDiscountId(rs.getInt("discount_id"));
                    entity.setCashierId(rs.getInt("cashier_id"));
                    entity.setCustomerPhone(rs.getString("customer_phone"));
                    entity.setOrderDate(rs.getDate("order_date"));
                    entity.setCustomerName(rs.getString("customer_name"));
                    entity.setTotalPrice(rs.getBigDecimal("total_price"));
                    entity.setStatus(rs.getString("status"));
                    entity.setIsPaid(rs.getBoolean("is_paid"));
                    entity.setOrderTableId(rs.getInt("order_table_id"));
                    entity.setNote(rs.getNString("note"));
                    list.add(entity);
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
