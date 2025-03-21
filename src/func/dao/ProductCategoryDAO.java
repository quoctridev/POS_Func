/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.dto.ProductCategoryDTO;
import func.entity.ProductEntity;
import func.entity.UserEntity;
import func.utils.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ProductCategoryDAO extends FuncDAO<ProductCategoryDTO, String> {

    @Override
    public void insert(ProductCategoryDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(ProductCategoryDTO entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ProductCategoryDTO selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ProductCategoryDTO> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Lấy danh sách sản phẩm theo ID danh mục
    public List<String> getProductsByCategoryId(int categoryId) {
        List<String> products = new ArrayList<>();
        String sql = "SELECT product_name FROM Products WHERE category_id = ?";
        ResultSet rs = null;
        try { 
            rs = Database.query(sql, categoryId);
            while (rs.next()) {
                products.add(rs.getString("product_name")); // Thêm trực tiếp product_name
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy sản phẩm từ database", e);
        }
        return products;
    }

    public List<ProductCategoryDTO> selectCategory(String category_id) {
        String sql = """
        SELECT p.product_name 
        FROM Products p 
        JOIN Categories c ON p.category_id = c.category_id 
        WHERE c.category_id = ?
    """;

        return selectBySql(sql, category_id);
    }

    @Override
    protected List<ProductCategoryDTO> selectBySql(String sql, Object... args) {
        List<ProductCategoryDTO> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    ProductCategoryDTO proCateDTO = new ProductCategoryDTO();
                    proCateDTO.setProductId(rs.getInt("product_id"));
                    proCateDTO.setProductName(rs.getString("product_name"));
                    proCateDTO.setPrice(rs.getBigDecimal("price"));
                    proCateDTO.setStock(rs.getInt("stock"));
                    proCateDTO.setImage(rs.getString("image"));
                    proCateDTO.setCategoryId(rs.getInt("category_id"));
                    proCateDTO.setCategoryName(rs.getString("category_name"));

                    list.add(proCateDTO);

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
