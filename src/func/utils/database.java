package func.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class database {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String dburl = "jdbc:sqlserver://db.quoctri.dev,1433;database=POS_FUNC";
    private static String username = "sa";
    private static String password = "FuncDev@";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection con = DriverManager.getConnection(dburl, username, password);
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = con.prepareCall(sql);
        } else {
            pstmt = con.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    public static int update(String sql, Object... args) {
        try {
            PreparedStatement pstmt = database.getStmt(sql, args);
            try {
                return pstmt.executeUpdate();
            } finally {
                pstmt.getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet read(String sql, Object... args) {
        try {
            PreparedStatement pstmt = database.getStmt(sql, args);
            return pstmt.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object value(String sql, Object... args) {
        try (ResultSet rs = database.read(sql, args)) {
            if (rs.next()) {
                return rs.getObject(1);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
