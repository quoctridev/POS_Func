/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.entity.CategoriesEntity;
import java.util.*;
import java.sql.SQLException;
import func.entity.ProductEntity;
import java.sql.*;
import func.utils.Database;
import func.entity.CategoriesEntity;

/**
 *
 * @author AZ Tech
 */
public class ProductDAO extends FuncDAO<ProductEntity, String> {

    @Override
    public void insert(ProductEntity entity) {
        String sql = "INSERT INTO Products(product_name,price,stock,[image],category_id) "
                + " VALUES(?, ?, ?, ?, ?)";
        Database.update(sql, entity.getProductName(), entity.getPrice(), entity.getStock(), entity.getImage(), entity.getCategoryId());
    }

    @Override
    public void update(ProductEntity entity) {
        String sql = "UPDATE products SET product_name = ?, price = ?, stock = ?, [image] = ? WHERE product_id = ?";
        Database.update(sql, entity.getProductName(), entity.getPrice(), entity.getStock(), entity.getImage(), entity.getProductId());
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM Products WHERE product_id =? ";
        Database.update(sql, id);
    }

    @Override
    public ProductEntity selectById(String id) {
        String sql = "SELECT TOP 1 FROM Products WHERE product_id =? ";
        List<ProductEntity> list = selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
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
                    ProductEntity enity = new ProductEntity();
                    enity.setProductId(rs.getInt("product_id"));
                    enity.setProductName(rs.getNString("product_name"));
                    enity.setPrice(rs.getBigDecimal("price"));
                    enity.setStock(rs.getInt("stock"));
                    enity.setImage(rs.getString("image"));
                    enity.setCategoryId(rs.getInt("category_id"));
                    enity.setCreatedAt(rs.getDate("created_at"));
                    list.add(enity);
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

    public List<CategoriesEntity> readCategories() {
        List<CategoriesEntity> list = new ArrayList<>();
        String sql = "SELECT category_name FROM Categories";

        try {
            ResultSet rs = Database.query(sql);
            while (rs.next()) {
                CategoriesEntity categoriesEntity = new CategoriesEntity();
                categoriesEntity.setCategoryName(rs.getString("category_name")); // Fix lỗi truy vấn
                list.add(categoriesEntity);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi khi lấy danh sách danh mục!", e);
        }
        return list;
    }
}
