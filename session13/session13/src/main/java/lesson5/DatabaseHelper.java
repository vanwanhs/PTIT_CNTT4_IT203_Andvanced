package lesson5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String url = "jdbc:mysql://localhost:3307/session13";
    private static final String user = "root";
    private static final String pass = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
}
