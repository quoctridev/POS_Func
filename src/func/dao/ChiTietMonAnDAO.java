/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.dto.OrderDetailExpandedDTO;
import func.entity.UserEntity;
import func.utils.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ChiTietMonAnDAO {

    public void readChiTiet() {
        String sql = "SELECT od.order_detail_id, od.order_id, od.product_id, p.product_name, "
                + "od.quantity, od.price, od.note, od.status, od.created_at "
                + "FROM OrderDetails od "
                + "JOIN Products p ON od.product_id = p.product_id";
        List<OrderDetailExpandedDTO> orderDetailLst = new ArrayList<>();

    }

    protected List<OrderDetailExpandedDTO> selectBySql(String sql, Object... args) {
        List<OrderDetailExpandedDTO> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    OrderDetailExpandedDTO orderDetail = new OrderDetailExpandedDTO();
                    orderDetail.setOrderDetailId(rs.getInt("order_detail_id"));
                    orderDetail.setOrderId(rs.getInt("order_id"));
                    orderDetail.setProductName(rs.getString("product_name")); // lấy tên sản phẩm
                    orderDetail.setQuantity(rs.getInt("quantity"));
                    orderDetail.setPrice(rs.getBigDecimal("price"));
                    orderDetail.setNote(rs.getString("note"));
                    orderDetail.setStatus(rs.getString("status"));
                    list.add(orderDetail);

                }

            } finally {
                rs.getStatement().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return list;
    }
}
