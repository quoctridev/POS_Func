/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.entity.CustomerEntity;
import func.utils.Database;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author quoctris.dev
 */
public class CustomerDAO {

    public static CustomerEntity findCustomerByPhone(String phone) {

        String sql = "SELECT * FROM Customers WHERE customer_phone =?";
        try (ResultSet rs = Database.query(sql, phone)) {
            if (rs.next()) {
                int customerId = rs.getInt("customer_id");
                String fullName = rs.getString("full_name");
                String email = rs.getString("email");
                int point = rs.getInt("point");
                return new CustomerEntity(phone, fullName, email, customerId, point);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
