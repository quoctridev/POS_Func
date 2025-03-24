package func.dao;

import func.dto.OrderDetailsDTO;
import func.utils.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;

public class OrderDetailsDAO {

    public List<OrderDetailsDTO> selectById(String id) {
        String sql = "    SELECT \n"
                + "    p.product_name,\n"
                + "    od.quantity,\n"
                + "    od.price\n"
                + "    FROM OrderDetails od\n"
                + "JOIN Products p ON od.product_id = p.product_id\n"
                + "JOIN Orders o ON od.order_id = o.order_id\n"
                + "WHERE od.order_id = ?";
        return selectBySql(sql, id);
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
