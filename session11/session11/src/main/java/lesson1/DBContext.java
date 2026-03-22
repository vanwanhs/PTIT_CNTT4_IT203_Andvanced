package lesson1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    //  Hằng số cấu hình
    private static final String URL = "jdbc:mysql://localhost:3307/Hospital_DB";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    //  Lấy connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    //  Đóng connection an toàn
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}