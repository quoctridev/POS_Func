/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.dto.ProductCategoryDTO;
import func.entity.CategoriesEntity;
import func.utils.Database;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CategoryDAO {

     public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        
        String sql = "SELECT category_name  FROM Categories"; // Cột trong database

        try {
            ResultSet rs = Database.query(sql);
            while (rs.next()) {
                categories.add(rs.getString("category_name"));
                
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy danh mục từ database", e);
        }
        return categories;
    }
    
    public Map<Integer, String> getAllCategoriesWithID() {
        Map<Integer, String> categories = new LinkedHashMap<>();
        String sql = "SELECT category_id, category_name FROM Categories";

        try {
            ResultSet rs = Database.query(sql);
            while (rs.next()) {
                categories.put(rs.getInt("category_id"), rs.getString("category_name"));
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy danh mục từ database", e);
        }
        return categories;
    }
    

    

}
