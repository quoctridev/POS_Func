/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.dao;

import func.utils.Database;

/**
 *
 */
public class NotificationDAO {

    public static void ThongBaoQuenMatKhau(String user) {
        String sql = "INSERT INTO Notifications (user_id, message, isRead, createdAt)\n"
                + "SELECT user_id, CONCAT(?, ' Quen mat khau'), 0, GETDATE()\n"
                + "FROM Users\n"
                + "WHERE role = 'admin';";
        Database.update(sql, user);
    }

}
