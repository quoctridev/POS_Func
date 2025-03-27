package func.dao;

import func.entity.DiscountEntity;
import func.utils.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DiscountDAO extends FuncDAO<DiscountEntity, String> {

    @Override
    public void insert(DiscountEntity entity) {
        String sql = "INSERT INTO Discounts(code, discount_value, max_value, start_date, end_date) VALUES"
                + "(?,?,?,?,?)";
        Database.update(sql, entity.getCode(), entity.getDiscountValue(), entity.getMaxValue(), entity.getStartDate(), entity.getEndDate());
    }

    @Override
    public void update(DiscountEntity entity) {
        String sql = "UPDATE Discounts SET code=?, discount_value =?, max_value=?, start_date=?, end_date =? WHERE discount_id =?";
        Database.update(sql, entity.getCode(), entity.getDiscountValue(), entity.getMaxValue(), entity.getStartDate(), entity.getEndDate(), entity.getDiscountId());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Discounts WHERE code = ?";
        Database.update(sql, id);
    }

    @Override
    public DiscountEntity selectById(String id) {
        String sql = "SELECT TOP 1 * FROM Discounts WHERE code = ?";
        List<DiscountEntity> ls = selectBySql(sql, id);
        return ls.size() > 0 ? ls.get(0) : null;
    }

    @Override
    public List<DiscountEntity> selectAll() {
        String sql = "SELECT * FROM Discounts";
        return selectBySql(sql);
    }

    @Override
    protected List<DiscountEntity> selectBySql(String sql, Object... args) {
        List<DiscountEntity> ls = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    DiscountEntity dc = new DiscountEntity();
                    dc.setDiscountId(rs.getInt("discount_id"));
                    dc.setCode(rs.getString("code"));
                    dc.setDiscountValue(rs.getBigDecimal("discount_value"));
                    dc.setMaxValue(rs.getBigDecimal("max_value"));
                    dc.setStartDate(rs.getDate("start_date"));
                    dc.setEndDate(rs.getDate("end_date"));
                    dc.setCreatedAt(rs.getDate("created_at"));
                    ls.add(dc);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return ls;
    }

}
