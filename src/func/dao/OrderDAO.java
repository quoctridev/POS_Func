/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.entity.OrderEntity;
//import func.dto.OrderDTO;
import func.utils.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Date;

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
                        + "INSERT INTO Orders (cashier_id, status) "
                        + "OUTPUT INSERTED.order_id INTO @InsertedTable "
                        + "VALUES (?, ?); "
                        + "SELECT order_id FROM @InsertedTable;";
                rs = Database.query(sql, od.getCashierId(), od.getStatus());
            } else {
                // Nếu có order_table_id, insert đầy đủ
                sql = "DECLARE @InsertedTable TABLE (order_id INT); "
                        + "INSERT INTO Orders (cashier_id, status, order_table_id) "
                        + "OUTPUT INSERTED.order_id INTO @InsertedTable "
                        + "VALUES (?, ?, ?); "
                        + "SELECT order_id FROM @InsertedTable;";
                rs = Database.query(sql, od.getCashierId(), od.getStatus(), od.getOrderTableId());
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

//    public List<OrderDTO> readSP() {
//        String sql = "SELECT\n"
//                + "    o.order_id,\n"
//                + "    o.discount_id,\n"
//                + "    o.cashier_id,\n"
//                + "    o.customer_phone,\n"
//                + "    o.order_date,\n"
//                + "    o.customer_name,\n"
//                + "    o.total_price,\n"
//                + "    o.status,\n"
//                + "    o.is_paid,\n"
//                + "    o.order_table_id,\n"
//                + "    p.payment_id,\n"
//                + "    p.payment_time,\n"
//                + "    p.payment_method,\n"
//                + "    p.status AS payment_status,\n"
//                + "    p.total_amount,\n"
//                + "    od.order_detail_id,\n"
//                + "    od.product_id,\n"
//                + "    od.quantity,\n"
//                + "    od.price,\n"
//                + "    od.note,\n"
//                + "    od.status AS order_detail_status,\n"
//                + "    od.created_at\n"
//                + "FROM Orders o\n"
//                + "LEFT JOIN Payments p ON o.order_id = p.order_id\n"
//                + "LEFT JOIN OrderDetails od ON o.order_id = od.order_id";
//        return OrderDTO(sql);
//    }
    public void cancelOrder(OrderEntity od) {
        String sql = "UPDATE Orders\n"
                + "SET status = ?,\n"
                + "note = ?"
                + "WHERE order_id = ?";
        Database.update(sql, od.getStatus(), od.getNote(), od.getOrderId());
    }

    @Override
    public void update(OrderEntity entity) {
        String sql = "UPDATE Orders SET discount_id = ?, cashier_id = ?,"
                + "customer_phone = ?, customer_name = ?,"
                + "total_price = ?, [status] = ?, is_paid =?, order_table_id = ? WHERE order_id = ?";
        Database.update(sql, entity.getDiscountId(), entity.getCashierId(),
                entity.getCustomerPhone(), entity.getCustomerName(), entity.getTotalPrice(),
                entity.getStatus(), entity.isIsPaid(), entity.getOrderTableId());
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

//    protected List<OrderDTO> OrderDTO(String sql, Object... args) {
//        List<OrderDTO> list = new ArrayList<>();
//        try (ResultSet rs = Database.query(sql, args)) { // Sử dụng try-with-resources để tự động đóng ResultSet
//            while (rs.next()) {
//                OrderDTO order = new OrderDTO();
//                order.setOrder_id(rs.getInt("order_id"));
//                order.setDiscount_id(rs.getInt("discount_id"));
//                order.setCashier_id(rs.getInt("cashier_id"));
//                order.setCustomer_name(rs.getString("customer_name")); // Đảm bảo chỉ gọi một lần
//                order.setOrder_date(rs.getTimestamp("order_date"));
//                order.setCustomer_phone(rs.getString("customer_phone"));
//                order.setTotal_price(rs.getBigDecimal("total_price"));
//                order.setStatus(rs.getString("status"));
//                order.setIs_paid(rs.getBoolean("is_paid"));
//                order.setOrder_table_id(rs.getInt("order_table_id"));
//
//                // Lấy thông tin thanh toán liên quan
//                order.setPayment_id(rs.getInt("payment_id"));
//                order.setPayment_time(rs.getTimestamp("payment_time"));
//                order.setPayment_method(rs.getString("payment_method"));
//                order.setTotal_amount(rs.getBigDecimal("total_amount"));
//
//                // Lấy thông tin chi tiết đơn hàng liên quan
//                order.setOrder_detail_id(rs.getInt("order_detail_id"));
//                order.setProduct_id(rs.getInt("product_id"));
//                order.setQuantity(rs.getInt("quantity"));
//                order.setPrice(rs.getBigDecimal("price"));
//                order.setNote(rs.getString("note"));
//                order.setCreated_at(rs.getTimestamp("created_at"));
//
//                list.add(order);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Lỗi khi truy vấn dữ liệu từ cơ sở dữ liệu", e);
//        }
//        return list;
//    }
}
