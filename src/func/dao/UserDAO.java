package func.dao;

import func.entity.UserEntity;
import func.utils.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;




public class UserDAO extends FuncDAO<UserEntity, String>{

    @Override
    public void insert(UserEntity entity) {
        String sql  = "INSERT INTO Users (username, [password], full_name, phone, [role] VALUES (?, ?, ?, ?, ?))";
        Database.update(sql, entity.getUsername(), entity.getPassword(), entity.getFullName(), entity.getPhone(), entity.getRole());
    }

    @Override
    public void update(UserEntity entity) {
        String sql = "UPDATE Users SET username = ?, [password] = ?, full_name = ?, phone = ?, [role] = ? WHERE user_id = ?";
        Database.update(sql, entity.getUsername(), entity.getPassword(), entity.getFullName(), entity.getPhone(), entity.getRole(), entity.getUserId());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        Database.update(sql, id);
    }

    @Override
    public UserEntity selectById(String id) {
        String sql = "SELECT TOP 1 FROM Users WHERE user_id = ?";
        List<UserEntity> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<UserEntity> selectAll() {
        String sql = "SELECT * FROM Users";
        return selectBySql(sql);
    }
    
    @Override
    protected List<UserEntity> selectBySql(String sql, Object... args) {
        List<UserEntity> list = new ArrayList<>();
        
        try {
            ResultSet rs = null;
            try{
                rs = Database.query(sql, args);
                while(rs.next()) {
                    UserEntity user = new UserEntity();
                    user.setUserId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("[password]"));
                    user.setFullName(rs.getString("full_name"));
                    user.setPhone(rs.getString("phone"));
                    user.setRole(rs.getString("[role]"));
                    user.setCreatedAt(rs.getDate("created_at"));
                    list.add(user);                    
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
