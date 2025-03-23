package func.dao;

import func.entity.CategoriesEntity;
import func.utils.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CategoryDAO extends FuncDAO<CategoriesEntity, String> {

    @Override
    public void insert(CategoriesEntity entity) {
        String sql = "INSERT INTO Categories (category_name, is_active) VALUES (?, ?)";
        Database.update(sql, entity.getCategoryName(), entity.isIsActive());
    }

    @Override
    public void update(CategoriesEntity entity) {
        String sql = "UPDATE Categories SET category_name = ?, is_active = ? WHERE category_id = ?";
        Database.update(sql, entity.getCategoryName(), entity.isIsActive(), entity.getCategoryId());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Categories WHERE category_id = ?";
        Database.update(sql, id);
    }

    @Override
    public CategoriesEntity selectById(String id) {
        String sql = "SELECT TOP 1 FROM Categories WHERE category_id = ?";
        List<CategoriesEntity> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<CategoriesEntity> selectAll() {
        String sql = "SELECT * FROM Categories";
        return selectBySql(sql);
    }

    @Override
    protected List<CategoriesEntity> selectBySql(String sql, Object... args) {
        List<CategoriesEntity> list = new ArrayList<>();
        
        try {
            ResultSet rs = null;
            try{
                rs = Database.query(sql, args);
                while(rs.next()) {
                    CategoriesEntity category = new CategoriesEntity();
                    category.setCategoryId(rs.getInt("category_id"));
                    category.setCategoryName(rs.getString("category_name"));
                    category.setIsActive(rs.getBoolean("is_active"));
                    category.setCreatedAt(rs.getDate("created_at"));
                    list.add(category);
                }
            }
            finally{
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }
    
}
