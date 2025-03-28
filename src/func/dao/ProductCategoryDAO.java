/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.dto.ProductDTO;
import func.utils.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quoctris.dev
 */
public class ProductCategoryDAO {

    public List<ProductDTO> selectAll() {
        String sql = "SELECT p.*,c.category_name FROM Products p JOIN Categories c ON p.category_id = C.category_id";
        return selectBySql(sql);
    }

    protected List<ProductDTO> selectBySql(String sql, Object... args) {
        List<ProductDTO> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = Database.query(sql, args);
                while (rs.next()) {
                    ProductDTO product = new ProductDTO();
                    product.setProductId(rs.getInt("product_id"));
                    product.setProductName(rs.getString("product_name"));
                    product.setPrice(rs.getBigDecimal("price"));
                    product.setIsActive(rs.getBoolean("is_active"));
                    product.setImage(rs.getString("image"));
                    product.setCategoryId(rs.getInt("category_id"));
                    product.setCategoryName(rs.getNString("category_name"));
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
}
