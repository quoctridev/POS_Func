/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.entity.OrderTableEntity;
import func.utils.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quoctris.dev
 */
public class OrderTableDAO {

    public void createOrderTable(OrderTableEntity od) {
        String sql = "INSERT INTO [dbo].[Order_Table] \n"
                + "([customer_id], [capacity], [phone], [note], [reservation_time], [customer_name])  \n"
                + "VALUES (?,?,?,?,?,?)";
        Database.update(sql, od.getCustomerId(), od.getCapacity(), od.getPhone(), od.getNote(), od.getReservationTime(), od.getCustomer_name());
    }

    public void updateOrderTable(String status, String id) {
        String sql = "UPDATE Order_Table SET [status] = ? WHERE order_table_id = ?";
        Database.update(sql, status, id);
    }

    public List<OrderTableEntity> selectAll() {
        String sql = "SELECT * FROM Order_Table WHERE [status] NOT IN('completed', 'canceled') ORDER BY reservation_time ASC ";
        return selectBySql(sql);
    }

    private List<OrderTableEntity> selectBySql(String sql, Object... args) {
        List<OrderTableEntity> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    OrderTableEntity od = new OrderTableEntity();
                    od.setCapacity(rs.getInt("capacity"));
                    od.setReservationTime(rs.getTimestamp("reservation_time"));
                    od.setPhone(rs.getString("phone"));
                    od.setOrderTableId(rs.getInt("order_table_id"));
                    od.setCustomer_name(rs.getNString("customer_name"));
                    od.setCustomerId(rs.getInt("customer_id"));
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
