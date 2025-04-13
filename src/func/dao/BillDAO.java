/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.dto.BillDTO;
import func.dto.OrderDetailsDTO;
import func.utils.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quoctris.dev
 */
public class BillDAO {

    public List<OrderDetailsDTO> selectOrderDetailsById(String id) {
        String sql = "SELECT p.product_name, od.price, SUM(od.quantity) as quantity, od.[status], od.order_detail_id, od.product_id "
                + "FROM Orders o JOIN OrderDetails od ON o.order_id = od.order_id "
                + "JOIN Products p ON od.product_id = p.product_id "
                + "WHERE o.order_id = ? "
                + "GROUP BY od.order_detail_id, p.product_name, od.[status], od.price, od.product_id";

        List<OrderDetailsDTO> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, id);
                while (rs.next()) {
                    OrderDetailsDTO details = new OrderDetailsDTO(
                            rs.getInt("quantity"),
                            rs.getString("product_name"),
                            rs.getBigDecimal("price"),
                            rs.getInt("product_id"),
                            rs.getString("status"),
                            rs.getInt("order_detail_id")
                    );
                    list.add(details);
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

    public List<BillDTO> selectAll() {
        String sql = "SELECT o.order_id, o.customer_name,o.customer_phone,STRING_AGG(t.table_number, ', ') AS table_numbers,o.total_price,o.[status],o.order_date FROM Orders o \n"
                + "JOIN MergedTables mt \n"
                + "ON o.order_id = mt.order_id \n"
                + "JOIN Tables t \n"
                + "ON t.table_id = mt.table_id\n"
                + "GROUP BY o.order_id, o.customer_name,o.customer_phone,o.total_price,o.[status],o.order_date";
        return selectBySql(sql);
    }

    protected List<BillDTO> selectBySql(String sql, Object... args) {
        List<BillDTO> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    BillDTO bill = new BillDTO();
                    bill.setOrderId(rs.getInt("order_id"));
                    bill.setCustomerName(rs.getNString("customer_name"));
                    bill.setCustomerPhone(rs.getString("customer_phone"));
                    bill.setOrderDate(rs.getTimestamp("order_date"));
                    bill.setStatus(rs.getString("status"));
                    bill.setNumberTable(rs.getString("table_numbers"));
                    bill.setTotal_price(rs.getBigDecimal("total_price"));
                    list.add(bill);
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
