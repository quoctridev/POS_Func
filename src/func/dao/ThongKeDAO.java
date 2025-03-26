/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.utils.Database;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author quoctris.dev
 */
public class ThongKeDAO {

    //Xem sử dụng phương thức thanh toán nào nhiều
    public static DefaultPieDataset getRevenueByPaymentMethod(String date, String type) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        String sql = "";

        switch (type) {
            case "day":
                sql = "SELECT O.payment_method, O.[status] AS order_status, SUM(O.total_price) AS revenue "
                        + "FROM Orders O "
                        + "WHERE FORMAT(O.order_date, 'yyyy-MM-dd') = ? "
                        + "AND O.[status] IN ('completed', 'canceled') "
                        + "GROUP BY O.payment_method, O.[status] "
                        + "ORDER BY O.[status], O.payment_method;";
                break;
            case "week":
                sql = "SELECT O.payment_method, O.[status] AS order_status, SUM(O.total_price) AS revenue "
                        + "FROM Orders O "
                        + "WHERE DATEPART(YEAR, O.order_date) = ? "
                        + "AND DATEPART(WEEK, O.order_date) = ? "
                        + "AND O.[status] IN ('completed', 'canceled') "
                        + "GROUP BY O.payment_method, O.[status] "
                        + "ORDER BY O.[status], O.payment_method;";
                break;
            case "month":
                sql = "SELECT O.payment_method, O.[status] AS order_status, SUM(O.total_price) AS revenue "
                        + "FROM Orders O "
                        + "WHERE FORMAT(O.order_date, 'yyyy-MM') = ? "
                        + "AND O.[status] IN ('completed', 'canceled') "
                        + "GROUP BY O.payment_method, O.[status] "
                        + "ORDER BY O.[status], O.payment_method;";
                break;
            case "year":
                sql = "SELECT O.payment_method, O.[status] AS order_status, SUM(O.total_price) AS revenue "
                        + "FROM Orders O "
                        + "WHERE FORMAT(O.order_date, 'yyyy') = ? "
                        + "AND O.[status] IN ('completed', 'canceled') "
                        + "GROUP BY O.payment_method, O.[status] "
                        + "ORDER BY O.[status], O.payment_method;";
                break;
            default:
                return dataset;
        }

        try {
            ResultSet rs;
            System.out.println(date);
            if (type.equals("week")) {
                String[] parts = date.split("-W");
                rs = Database.query(sql, parts[0], parts[1]);
            } else {
                rs = Database.query(sql, date);
            }

            while (rs.next()) {
                String method = rs.getString("payment_method").equals("cash") ? "Tiền mặt" : "QR-Code";
                String orderStatus = rs.getString("order_status");
                double revenue = rs.getDouble("revenue");

                // Nhãn chỉ chứa phương thức thanh toán và trạng thái đơn hàng
                String label = method + " (" + (orderStatus.equals("completed") ? "Hoàn thành" : "Đơn huỷ") + ")";
                dataset.setValue(label, revenue);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
    }

    //Lấy doanh thu theo sáng trưa chiều tối
    public static CategoryDataset getRevenueByShift(String date) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String query = "WITH OrderShifts AS (\n"
                + "    SELECT \n"
                + "        CONVERT(DATE, order_date) AS period,\n"
                + "        CASE \n"
                + "            WHEN DATEPART(HOUR, order_date) BETWEEN 6 AND 11 THEN N'Sáng' \n"
                + "            WHEN DATEPART(HOUR, order_date) BETWEEN 12 AND 17 THEN N'Trưa' \n"
                + "            WHEN DATEPART(HOUR, order_date) BETWEEN 18 AND 23 THEN N'Tối' \n"
                + "            ELSE N'Đêm' \n"
                + "        END AS shift,\n"
                + "        [status],\n"
                + "        total_price\n"
                + "    FROM Orders\n"
                + "    WHERE CONVERT(DATE, order_date) = ?\n"
                + ")\n"
                + "SELECT \n"
                + "    period,\n"
                + "    shift,\n"
                + "    [status],\n"
                + "    SUM(total_price) AS revenue\n"
                + "FROM OrderShifts\n"
                + "WHERE [status] IN ('completed', 'canceled')\n"
                + "GROUP BY period, shift, [status]\n"
                + "ORDER BY period, \n"
                + "         CASE shift \n"
                + "             WHEN N'Sáng' THEN 1 \n"
                + "             WHEN N'Trưa' THEN 2 \n"
                + "             WHEN N'Tối' THEN 3 \n"
                + "             ELSE 4 \n"
                + "         END, \n"
                + "         [status];";

        try (ResultSet rs = Database.query(query, date)) {
            System.out.println("Querying data for date: " + date);
            while (rs.next()) {
                String shift = rs.getString("shift");
                String status = rs.getString("status");
                double revenue = rs.getDouble("revenue");

                // Đổi nhãn của 'canceled' thành "Đơn hàng đã huỷ" khi hiển thị
                if ("canceled".equals(status)) {
                    dataset.addValue(revenue, "Đơn hàng đã huỷ", shift);
                } else {
                    dataset.addValue(revenue, "Doanh thu", shift);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    //Lấy doanh thu theo ngày tháng năm
    public static CategoryDataset getRevenueDataset(String type) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String query = "";
        switch (type) {
            case "day":
                query = "SELECT \n"
                        + "    CAST(order_date AS DATE) AS period, \n"
                        + "    [status], \n"
                        + "    SUM(total_price) AS revenue \n"
                        + "FROM Orders \n"
                        + "WHERE [status] IN ('completed', 'canceled') \n"
                        + "GROUP BY CAST(order_date AS DATE), [status] \n"
                        + "ORDER BY period, [status];";
                break;
            case "week":
                query = "SELECT \n"
                        + "    DATEPART(YEAR, order_date) AS year, \n"
                        + "    DATEPART(WEEK, order_date) AS period, \n"
                        + "    [status], \n"
                        + "    SUM(total_price) AS revenue \n"
                        + "FROM Orders \n"
                        + "WHERE [status] IN ('completed', 'canceled') \n"
                        + "GROUP BY DATEPART(YEAR, order_date), DATEPART(WEEK, order_date), [status] \n"
                        + "ORDER BY year, period, [status]";
                break;
            case "month":
                query = "SELECT \n"
                        + "    FORMAT(order_date, 'yyyy-MM') AS period, \n"
                        + "    [status], \n"
                        + "    SUM(total_price) AS revenue \n"
                        + "FROM Orders \n"
                        + "WHERE [status] IN ('completed', 'canceled') \n"
                        + "GROUP BY FORMAT(order_date, 'yyyy-MM'), [status] \n"
                        + "ORDER BY period, [status]";
                break;
            case "year":
                query = "SELECT \n"
                        + "    YEAR(order_date) AS period, \n"
                        + "    [status], \n"
                        + "    SUM(total_price) AS revenue \n"
                        + "FROM Orders \n"
                        + "WHERE [status] IN ('completed', 'canceled') \n"
                        + "GROUP BY YEAR(order_date), [status] \n"
                        + "ORDER BY period, [status];";
                break;
        }
        try (ResultSet rs = Database.query(query)) {
            while (rs.next()) {
                String date;
                if (type.equals("week")) {
                    int year = rs.getInt("year");
                    int week = rs.getInt("period");
                    date = year + "-W" + String.format("%02d", week); // Format thành YYYY-WW
                } else {
                    date = rs.getString("period");
                }
                double revenue = rs.getDouble("revenue");
                String status = rs.getString("status");
                dataset.addValue(revenue, status.equals("completed") ? "Đơn hoàn thành" : "Đơn bị huỷ", date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
    //Mon an duoc mua nhieu nhat
//    public static 
}
