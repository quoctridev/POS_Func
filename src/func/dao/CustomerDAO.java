/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import func.entity.CustomerEntity;
import func.utils.Database;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author quoctris.dev
 */
public class CustomerDAO {

    private static final String pass = "123456";

    public static void insert(String fullName, String phone) {
        String sql = "INSERT INTO Customer(customer_phone, full_name, password) VALUES(?,?,?)";
        String password = BCrypt.withDefaults().hashToString(10, pass.toCharArray());
        Database.update(sql, fullName, phone, password);
    }

    public static List<CustomerEntity> selectAllCustomers() {
        List<CustomerEntity> ls = new ArrayList();
        String sql = "SELECT * FROM Customer";
        ResultSet rs = null;
        try {
            rs = Database.query(sql);
            while (rs.next()) {
                int customerId = rs.getInt("customer_id");
                String fullName = rs.getNString("full_name");
                String email = rs.getString("email");
                BigDecimal point = rs.getBigDecimal("point");
                String phone = rs.getString("customer_phone");
                ls.add(new CustomerEntity(phone, fullName, email, customerId, point));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ls;
    }

    public static void updatePoint(BigDecimal point, int id) {
        String sql = "UPDATE Customer SET point = ? WHERE customer_id = ?";
        Database.update(sql, point, id);
    }

    public static CustomerEntity findCustomerByPhone(String phone) {
        String sql = "SELECT * FROM Customer WHERE customer_phone =?";
        ResultSet rs = null;
        try {
            rs = Database.query(sql, phone);
            if (rs.next()) {
                int customerId = rs.getInt("customer_id");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                BigDecimal point = rs.getBigDecimal("point");
                return new CustomerEntity(phone, fullName, email, customerId, point);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
