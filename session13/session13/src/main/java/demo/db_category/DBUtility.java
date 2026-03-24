package demo.db_category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {
    private static DBUtility instance;
    private static final String url = "jdbc:mysql://localhost:3307/DB_Cate_Pro";
    private static final String user  = "root";
    private static final String pass = "123456";

    private DBUtility(){};
    public static DBUtility getInstance()  {
        if(instance == null){
            instance = new DBUtility();
        }
        return instance;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,pass);
    }
}
