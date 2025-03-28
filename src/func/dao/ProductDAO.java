/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.entity.ProductEntity;
import func.utils.Database;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author quoctris.dev
 */
public class ProductDAO extends FuncDAO<ProductEntity, String> {

    @Override
    public void insert(ProductEntity entity) {
        String sql = "INSERT INTO Products (product_name, price, is_active, image, category_id) VALUES (?, ?, ?, ?, ?)";
        Database.update(sql, entity.getProductName(), entity.getPrice(), entity.isIsActive(), entity.getImage(), entity.getCategoryId());
    }

    @Override
    public void update(ProductEntity entity) {
        String sql = "UPDATE Products SET product_name=?, price=?, is_active=?, image=?, category_id=? WHERE product_id = ?";
        Database.update(sql, entity.getProductName(), entity.getPrice(), entity.isIsActive(), entity.getImage(), entity.getCategoryId(), entity.getProductId());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Products WHERE product_id = ?";
        Database.update(sql, id);
    }

    @Override
    public ProductEntity selectById(String id) {
        String sql = "SELECT * FROM Products WHERE product_id = ?";
        List<ProductEntity> list = selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<ProductEntity> selectAll() {
        String sql = "SELECT * FROM Products";
        return selectBySql(sql);
    }

    @Override
    protected List<ProductEntity> selectBySql(String sql, Object... args) {
        List<ProductEntity> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    ProductEntity product = new ProductEntity();
                    product.setProductId(rs.getInt("product_id"));
                    product.setProductName(rs.getString("product_name"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setIsActive(rs.getBoolean("is_active"));
                    product.setImage(rs.getString("image"));
                    product.setCategoryId(rs.getInt("category_id"));
                    product.setCreatedAt(rs.getDate("created_at"));
                    list.add(product);
                }
            } finally {
                if (rs != null) {
                    rs.getStatement().getConnection().close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return list;
    }

    // Lấy danh sách sản phẩm theo category_id
    public List<ProductEntity> selectByCategory(int categoryId) {
        String sql = "SELECT * FROM Products WHERE category_id = ?";
        return selectBySql(sql, categoryId);
    }
}
