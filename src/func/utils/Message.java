package func.utils;

import java.awt.Component;
import javax.swing.JOptionPane;

public class Message {
// Hiển thị thông báo thông thường

    public static void info(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    // Hiển thị cảnh báo
    public static void warning(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Cảnh báo", JOptionPane.WARNING_MESSAGE);
    }

    // Hiển thị lỗi
    public static void error(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    // Hiển thị hộp xác nhận (OK / Cancel)
    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Xác nhận", JOptionPane.OK_CANCEL_OPTION);
        return result == JOptionPane.OK_OPTION;
    }

    public static String input(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message, "Nhập dữ liệu", JOptionPane.QUESTION_MESSAGE);
    }
}
