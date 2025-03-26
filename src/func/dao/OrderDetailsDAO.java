package func.dao;

import func.dto.OrderDetailsDTO;
import func.entity.OrderDetailEntity;
import func.utils.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;

public class OrderDetailsDAO {

    public List<OrderDetailsDTO> selectById(String id) {
        String sql = "         SELECT \n"
                + "                    p.product_name, \n"
                + "                  quantity, \n"
                + "                    od.price, od.product_id\n"
                + "                FROM OrderDetails od\n"
                + "                JOIN Products p ON od.product_id = p.product_id\n"
                + "                JOIN Orders o ON od.order_id = o.order_id\n"
                + "                WHERE od.order_id = ? and od.[status] != 'canceled'";
        return selectBySql(sql, id);
    }

    public List<OrderDetailsDTO> selectByIdOrder(String id) {
        System.out.println(id);
        String sql = "SELECT \n"
                + "    od.order_detail_id,p.product_name, \n"
                + "     od.quantity, \n"
                + "    od.price, od.product_id,od.status\n"
                + "FROM OrderDetails od\n"
                + "JOIN Products p ON od.product_id = p.product_id\n"
                + "JOIN Orders o ON od.order_id = o.order_id\n"
                + "WHERE od.order_id = ?\n";
        return selectBySql(sql, id);
    }

    public OrderDetailsDTO selectStatusProductById(String orderDetailId) {
        String sql = "SELECT TOP 1 od.*,p.product_name FROM OrderDetails AS od JOIN Products AS p ON od.product_id = p.product_id WHERE order_detail_id = ?";
        List<OrderDetailsDTO> ls = selectBySql(sql, orderDetailId);
        return ls.size() > 0 ? ls.get(0) : null;
    }

    public void updateStatusOrderDetails(OrderDetailEntity od) {
        String sql = "UPDATE OrderDetails\n"
                + "SET note = ?,[status] = ?\n"
                + "WHERE order_detail_id = ?";
        Database.update(sql, od.getNote(), od.getStatus(), od.getOrderDetailId());
    }

    public void createOrderDetails(OrderDetailEntity od) {
        // Lấy tổng số lượng sản phẩm đã có trong OrderDetails
        String checkOrderSql = "SELECT COALESCE(SUM(quantity), 0) FROM OrderDetails WHERE order_id = ? AND product_id = ?";
        Integer orderedQuantity = (Integer) Database.value(checkOrderSql, od.getOrderId(), od.getProductId());

        // Nếu tổng số lượng trong OrderDetails nhỏ hơn số lượng cần đặt, thì thêm từng bản ghi
        while (orderedQuantity < od.getQuantity()) {
            String sql = "INSERT INTO OrderDetails (order_id, product_id, quantity, price, note, status, created_at)\n"
                    + "VALUES (?, ?, ?, ?, ?, ?, GETDATE())";

            Database.update(sql, od.getOrderId(), od.getProductId(), 1, od.getPrice(), od.getNote(), "pending");

            // Cập nhật lại tổng số lượng đã đặt
            orderedQuantity++;
        }
    }

    protected List<OrderDetailsDTO> selectBySql(String sql, Object... args) {
        List<OrderDetailsDTO> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    OrderDetailsDTO od = new OrderDetailsDTO();
                    od.setProductName(rs.getNString("product_name"));
                    od.setPrice(rs.getBigDecimal("price"));
                    od.setQuantity(rs.getInt("quantity"));
                    od.setProductId(rs.getInt("product_id"));
                    try {
                        od.setStatus(rs.getString("status"));
                    } catch (SQLException e) {
                    }
                    try {
                        od.setOrderDetailId(rs.getInt("order_detail_id"));
                    } catch (SQLException e) {
                    }
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
