/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.entity.UserEntity;
import func.utils.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 */
public class UserDAO extends FuncDAO<UserEntity, String> {

    public UserEntity selectById(String username) {
        String sql = "SELECT TOP 1 * FROM Users WHERE = ?";
        List<UserEntity> list = this.selectBySql(sql, username);
        return list.size() > 0 ? list.get(0) : null;
    }

    
    public void insert(UserEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public void update(UserEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<UserEntity> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected List<UserEntity> selectBySql(String sql, Object... args) {
        List<UserEntity> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    UserEntity user = new UserEntity();
                    user.setUsername(rs.getString("username"));
                    user.setUsername(rs.getString("user_id"));
                    user.setUsername(rs.getString("password"));
                    user.setUsername(rs.getString("role"));
                    list.add(user);

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
