/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package func.utils;

import java.util.HashMap;
import java.util.Map;

public class StatusConverter {

    private static final Map<String, String> statusMap = new HashMap<>();

    static {
        statusMap.put("processing", "Đang Xử Lý");
        statusMap.put("cooking", "Đang Chế Biến");
        statusMap.put("completed", "Đã Hoàn Thành");
        statusMap.put("canceled", "Huỷ");
    }

    // Hàm chuyển đổi trạng thái
    public static String convertStatus(String status) {
        return statusMap.getOrDefault(status, status); // Nếu không có thì giữ nguyên
    }

    
}
