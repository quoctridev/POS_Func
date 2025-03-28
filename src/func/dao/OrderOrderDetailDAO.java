/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.entity.OrderDetailEntity;
import func.dto.OrderOrderDetailDTO;
import func.utils.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

/**
 *
 */
public class OrderOrderDetailDAO {

    public static List<OrderOrderDetailDTO> getOrderDetailsById(int orderId) {
        List<OrderOrderDetailDTO> orderDetails = new ArrayList<>();

        String sql = "SELECT Top 1\n"
                + "    o.order_id AS orderId, \n"
                + "    o.order_date AS orderDate, \n"
                + "    u.full_name AS cashierName, \n"
                + "    z.zone_name AS zone, \n"
                + "    t.table_number AS tableNumber, \n"
                + "    o.status AS status, \n"
                + "    od.note AS note \n"
                + "FROM Orders o \n"
                + "JOIN Users u ON o.cashier_id = u.user_id \n"
                + "JOIN MergedTables mt ON o.order_id = mt.order_id \n"
                + "JOIN Tables t ON mt.table_id = t.table_id \n"
                + "JOIN Zones z on t.zone_id = z.zone_id\n"
                + "LEFT JOIN OrderDetails od ON od.order_id = o.order_id\n"
                + "WHERE o.order_id = ?;";

        try (ResultSet rs = Database.query(sql, orderId)) {
            while (rs.next()) {
                OrderOrderDetailDTO orderDetail = new OrderOrderDetailDTO();
                orderDetail.setOrderId(rs.getInt("orderId"));
                orderDetail.setOrderDate(rs.getTimestamp("orderDate"));
                orderDetail.setCashierName(rs.getString("cashierName"));
                orderDetail.setZone(rs.getString("zone"));
                orderDetail.setTableNumber(rs.getInt("tableNumber"));
                orderDetail.setStatus(rs.getString("status"));
                orderDetail.setNote(rs.getString("note"));
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy chi tiết đơn hàng từ database", e);
        }
        return orderDetails;
    }

    public static void loadOrderDetailsToTable(JTable tableDanhSach, int orderId) {
        List<OrderOrderDetailDTO> orderDetails = getOrderDetailsById(orderId);
        DefaultTableModel model = (DefaultTableModel) tableDanhSach.getModel();
        model.setRowCount(0);

        for (OrderOrderDetailDTO order : orderDetails) {
            model.addRow(new Object[]{
                order.getOrderId(),
                order.getOrderDate(),
                order.getCashierName(),
                order.getZone(),
                order.getTableNumber(),
                order.getStatus(),
                order.getNote()
            });
        }
    }

    public static List<OrderOrderDetailDTO> loadAllOrdersToTable() {
         List<OrderOrderDetailDTO> orders = new ArrayList<>();

        String sql = "SELECT \n"
                + "o.order_id AS orderId, \n"
                + "o.order_date AS orderDate, \n"
                + "u.full_name AS cashierName, \n"
                + "z.zone_name AS zone, \n"
                + "t.table_number AS tableNumber, \n"
                + "o.status AS status\n"
                + "FROM Orders o \n"
                + "JOIN Users u ON o.cashier_id = u.user_id \n"
                + "JOIN MergedTables ot ON o.order_id = ot.order_id \n"
                + "JOIN Tables t ON ot.table_id = t.table_id \n"
                + "JOIN Zones z on t.zone_id = z.zone_id\n"
                + "ORDER BY o.order_date DESC";

        try (ResultSet rs = Database.query(sql)) {
            while (rs.next()) {
                OrderOrderDetailDTO order = new OrderOrderDetailDTO();
                order.setOrderId(rs.getInt("orderId"));
                order.setOrderDate(rs.getTimestamp("orderDate"));
                order.setCashierName(rs.getString("cashierName"));
                order.setZone(rs.getString("zone"));
                order.setTableNumber(rs.getInt("tableNumber"));
                order.setStatus(rs.getString("status"));

                orders.add(order);
            }

            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy danh sách đơn hàng từ database", e);
        }
        return orders;
    }
}
