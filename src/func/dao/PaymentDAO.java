package func.dao;

import func.entity.PaymentEntity;
import func.utils.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;

public class PaymentDAO extends FuncDAO<PaymentEntity, String> {

    @Override
    public void insert(PaymentEntity entity) {
        String sql = "INSERT INTO Payments(order_id, payment_method, status, total_amount)";
        Database.update(sql, entity.getOrderId(), entity.getPaymentMethod(), entity.getStatus(), entity.getTotalAmount());
    }

    @Override
    public void update(PaymentEntity entity) {
        String sql = "UPDATE Payments SET payment_method=?, status =?, total_amount=? WHERE payment_id =?";
        Database.update(sql, entity.getPaymentMethod(), entity.getStatus(), entity.getTotalAmount(), entity.getPaymentId());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Payments WHERE payment_id=?";
        Database.update(sql, id);
    }

    @Override
    public PaymentEntity selectById(String id) {
        String sql = "SELECT TOP 1 * FROM Payments WHERE payment_id=?";
        List<PaymentEntity> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<PaymentEntity> selectAll() {
        String sql = "SELECT * FROM Payments";
        return selectBySql(sql);
    }

    @Override
    protected List<PaymentEntity> selectBySql(String sql, Object... args) {
        List<PaymentEntity> list = new ArrayList<>();

        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    PaymentEntity pay = new PaymentEntity();
                    pay.setOrderId(rs.getInt("order_id"));
                    pay.setPaymentId(rs.getInt("payment_id"));
                    pay.setPaymentMethod(rs.getString("payment_method"));
                    pay.setPaymentTime(rs.getDate("payment_time"));
                    pay.setStatus(rs.getString("status"));
                    pay.setTotalAmount(rs.getBigDecimal("total_amount"));
                    list.add(pay);
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
