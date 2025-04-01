package func.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Database {

    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:sqlserver://103.118.28.181:1433;databaseName=POS_FUNC;user=sa;password=FuncDev@;encrypt=true;trustServerCertificate=true");
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = connection.prepareCall(sql);
        } else {
            pstmt = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    public static void batchUpdate(String sql, List<Object[]> batchArgs) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlserver://103.118.28.181:1433;databaseName=POS_FUNC;user=sa;password=FuncDev@;encrypt=true;trustServerCertificate=true")) {
            PreparedStatement stmt = connection.prepareStatement(sql);

            // Thêm tất cả các batch vào PreparedStatement
            for (Object[] args : batchArgs) {
                for (int i = 0; i < args.length; i++) {
                    stmt.setObject(i + 1, args[i]);
                }
                stmt.addBatch();  // Thêm vào batch
            }

            // Thực hiện batch update
            stmt.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void update(String sql, Object... args) {
        try {
            PreparedStatement stmt = Database.getStmt(sql, args);
            try {
                stmt.executeUpdate();
            } finally {
                stmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet query(String sql, Object... args) {
        try {
            PreparedStatement stmt = Database.getStmt(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Object value(String sql, Object... args) {
        try {
            ResultSet rs = Database.query(sql, args);
            if (rs.next()) {
                return rs.getObject(1);
            }
            rs.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
