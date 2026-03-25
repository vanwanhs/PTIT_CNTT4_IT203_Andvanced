package utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectManager {
    private static DataBaseConnectManager instance;
    private Connection connection;
    private static final String url = "jdbc:mysql://localhost:3307/session14";
    private static final String user = "root";
    private static final String pass = "123456";
    private DataBaseConnectManager() throws SQLException {
        connection = DriverManager.getConnection(url,user,pass);
    }
    public static DataBaseConnectManager getInstance() throws SQLException {
        if(instance == null){
            instance = new DataBaseConnectManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
