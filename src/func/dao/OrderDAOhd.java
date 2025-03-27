/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;
import func.dto.OrderOrderDetailsDTO;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.Date;
import func.entity.OrderDetailEntity;



/**
 *
 * @author This PC
 */
public class OrderDAOhd {
    public static Connection getConnection() throws SQLException{
        String connectionUrl = "jdbc:sqlserver://localhost:1433;"
                + "databaseName=POS_FUNC;user=sa;password=1234;"
                + "instanceName=SQLEXPRESS;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(connectionUrl);
    }
    public List<OrderOrderDetailsDTO> readOrder(){
        String sql = "SELECT Orders.order_id, Orders.order_date, Orders.customer_name, \n" +
"       Orders.total_price, Orders.status, Orders.note\n" +
"FROM Orders\n" +
"LEFT JOIN OrderDetails ON Orders.order_id = OrderDetails.order_id";
        List<OrderOrderDetailsDTO> OrderLst = new ArrayList<>();
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int orderId = rs.getInt("order_id");
                Date orderDate = rs.getDate("order_date");
                String customerName = rs.getString("customer_name");
                BigDecimal totalPrice = rs.getBigDecimal("total_price");
                String status = rs.getString("status");
                String note = rs.getString("note");

                // Tạo đối tượng DTO với dữ liệu vừa lấy
                OrderOrderDetailsDTO orDTO = new OrderOrderDetailsDTO();
                orDTO.setOrder_id(orderId);
                orDTO.setOrder_date(orderDate);
                orDTO.setCustomer_name(customerName);
                orDTO.setTotal_price(totalPrice);
                orDTO.setStatus(status);
                orDTO.setNote(note);
                OrderLst.add(orDTO);
            }
            return OrderLst;
        } catch (Exception e) {
            return OrderLst;
        }
    }
//    public List<OrderDetailEntity> readORD(){
//        String sql = "SELECT * FROM OrderDetails";
//        List<OrderDetailEntity> orderLst = new ArrayList<>();
//        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql);){
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                int orderId = rs.getInt("order_id");
//                OrderDetailEntity Order = new OrderDetailEntity(orderId, "");
//                Or
//            }
//        } catch (Exception e) {
//        }
//    }
}
