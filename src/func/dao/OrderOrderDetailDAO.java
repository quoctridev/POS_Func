/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.entity.OrderDetailEntity;
import func.dto.OrderOrderDetailDTO;
import func.entity.PaymentEntity;
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
        String sql = "SELECT \n"
                + "    od.order_detail_id, \n"
                + "    p.product_name, \n"
                + "    STRING_AGG(t.table_number, ', ') AS table_numbers,\n"
                + "    z.zone_name,\n"
                + "    od.created_at, \n"
                + "    od.status\n"
                + "FROM OrderDetails od \n"
                + "JOIN Products p ON od.product_id = p.product_id\n"
                + "JOIN Orders o ON od.order_id = o.order_id\n"
                + "JOIN MergedTables mt ON o.order_id = mt.order_id\n"
                + "JOIN Tables t ON mt.table_id = t.table_id\n"
                + "JOIN Zones z ON t.zone   _id = z.zone_id\n"
                + "WHERE CAST(od.created_at AS DATE) = CAST(GETDATE() AS DATE)\n"
                + "AND od.order_detail_id = 33\n"
                + "GROUP BY od.order_detail_id, p.product_name, z.zone_name, od.created_at, od.status\n"
                + "ORDER BY od.created_at ASC";
        return selectBySql(sql, orderId);
    }

    public static List<OrderOrderDetailDTO> selectAllInDay() {
        String sql = "SELECT od.order_detail_id, p.product_name, STRING_AGG(t.table_number, ', ')as table_numbers, z.zone_name,od.created_at,od.status\n"
                + "                FROM OrderDetails od JOIN Products p \n"
                + "                ON od.product_id = p.product_id JOIN Orders o \n"
                + "                ON od.order_id = o.order_id\n"
                + "                JOIN MergedTables mt\n"
                + "                ON o.order_id = mt.order_id\n"
                + "                JOIN Tables t\n"
                + "                ON mt.table_id = t.table_id\n"
                + "                JOIN Zones z\n"
                + "                ON t.zone_id = z.zone_id\n"
                + "                WHERE DAY(od.created_at) = DAY(GETDATE()) AND  od.[status] NOT IN ('completed', 'canceled','ready')\n"
                + "                GROUP BY od.order_detail_id, p.product_name, z.zone_name, od.created_at, od.status\n"
                + "                ORDER BY od.created_at ASC";
        return selectBySql(sql);

    }

    public static void updateStatusById(OrderOrderDetailDTO od, String note) {
        String sql = "UPDATE OrderDetails\n"
                + "SET note = ?,[status] = ?\n"
                + "WHERE order_detail_id = ?";
        Database.update(sql, note, od.getStatus(), od.getOrderDetailId());
    }

    protected static List<OrderOrderDetailDTO> selectBySql(String sql, Object... args) {
        List<OrderOrderDetailDTO> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    OrderOrderDetailDTO od = new OrderOrderDetailDTO();
                    od.setOrderDetailId(rs.getInt("order_detail_id"));
                    od.setProductName(rs.getNString("product_name"));
                    od.setTableName(rs.getNString("table_numbers"));
                    od.setZoneName(rs.getString("zone_name"));
                    od.setStatus(rs.getString("status"));
                    od.setCreatedAt(rs.getDate("created_at"));
                    list.add(od);
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
