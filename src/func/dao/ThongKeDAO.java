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

    public static DefaultPieDataset getRevenueByPaymentMethod(String date, String type) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        String sql = "";

        switch (type) {
            case "day":
                sql = "SELECT payment_method, SUM(total_amount) AS revenue "
                        + "FROM Payments "
                        + "WHERE FORMAT(payment_time, 'yyyy-MM-dd') = ? "
                        + "AND [status] = 'completed' "
                        + "GROUP BY payment_method";
                break;
            case "month":
                sql = "SELECT payment_method, SUM(total_amount) AS revenue "
                        + "FROM Payments "
                        + "WHERE FORMAT(payment_time, 'yyyy-MM') = ? "
                        + "AND [status] = 'completed' "
                        + "GROUP BY payment_method";
                break;
            case "year":
                sql = "SELECT payment_method, SUM(total_amount) AS revenue "
                        + "FROM Payments "
                        + "WHERE FORMAT(payment_time, 'yyyy') = ? "
                        + "AND [status] = 'completed' "
                        + "GROUP BY payment_method";
                break;
            case "week":
                sql = "SELECT payment_method, SUM(total_amount) AS revenue "
                        + "FROM Payments "
                        + "WHERE DATEPART(YEAR, payment_time) = ? "
                        + "AND DATEPART(WEEK, payment_time) = ? "
                        + "AND [status] = 'completed' "
                        + "GROUP BY payment_method";
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
                double revenue = rs.getDouble("revenue");
                dataset.setValue(method, revenue);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public static CategoryDataset getRevenueByShift(String date) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String query = "SELECT "
                + "CASE "
                + "WHEN DATEPART(HOUR, order_date) BETWEEN 6 AND 11 THEN 'Sáng' "
                + "WHEN DATEPART(HOUR, order_date) BETWEEN 12 AND 17 THEN 'Trưa' "
                + "WHEN DATEPART(HOUR, order_date) BETWEEN 18 AND 23 THEN 'Tối' "
                + "ELSE 'Đêm' END AS shift, "
                + "SUM(total_price) AS revenue "
                + "FROM Orders WHERE CAST(order_date AS DATE) = ? "
                + "AND [status] = 'completed' "
                + "GROUP BY "
                + "CASE "
                + "WHEN DATEPART(HOUR, order_date) BETWEEN 6 AND 11 THEN 'Sáng' "
                + "WHEN DATEPART(HOUR, order_date) BETWEEN 12 AND 17 THEN 'Trưa' "
                + "WHEN DATEPART(HOUR, order_date) BETWEEN 18 AND 23 THEN 'Tối' "
                + "ELSE 'Đêm' END "
                + "ORDER BY shift;";

        try (ResultSet rs = Database.query(query, date)) {
            while (rs.next()) {
                String shift = rs.getString("shift");
                double revenue = rs.getDouble("revenue");
                dataset.addValue(revenue, "Doanh thu", shift);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    public static CategoryDataset getRevenueDataset(String type) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String query = "";
        switch (type) {
            case "day":
                query = "SELECT CAST(order_date AS DATE) AS period, SUM(total_price) AS revenue "
                        + "FROM Orders WHERE [status] = 'completed' "
                        + "GROUP BY CAST(order_date AS DATE) ORDER BY period";
                break;
            case "week":
                query = "SELECT DATEPART(YEAR, order_date) AS year, DATEPART(WEEK, order_date) AS period, SUM(total_price) AS revenue "
                        + "FROM Orders WHERE [status] = 'completed' "
                        + "GROUP BY DATEPART(YEAR, order_date), DATEPART(WEEK, order_date) "
                        + "ORDER BY year, period";
                break;
            case "month":
                query = "SELECT FORMAT(order_date, 'yyyy-MM') AS period, SUM(total_price) AS revenue "
                        + "FROM Orders WHERE [status] = 'completed' "
                        + "GROUP BY FORMAT(order_date, 'yyyy-MM') ORDER BY period";
                break;
            case "year":
                query = "SELECT YEAR(order_date) AS period, SUM(total_price) AS revenue "
                        + "FROM Orders WHERE [status] = 'completed' "
                        + "GROUP BY YEAR(order_date) ORDER BY period";
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
                dataset.addValue(revenue, "Doanh thu", date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }
}
