package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private Connection connection;
    private final String url = "jdbc:mysql://localhost:3307/flashsale";
    private final String user = "root";
    private final String pass = "123456";

    private DatabaseConnectionManager() throws SQLException {
        connection = DriverManager.getConnection(url, user, pass);
    }

    public static DatabaseConnectionManager getInstance() throws SQLException {
        if (instance == null || instance.connection.isClosed()) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
