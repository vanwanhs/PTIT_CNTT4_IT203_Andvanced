package lesson4;

import java.sql.*;

public class TestPreparedStatement {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3307/session12";
        String user = "root";
        String pass = "123456";

        Connection conn = DriverManager.getConnection(url, user, pass);

        long start = System.currentTimeMillis();

        String sql = "INSERT INTO blood_tests (result) VALUES (?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for (int i = 1; i <= 1000; i++) {
            ps.setDouble(1, 5.6);
            ps.executeUpdate();
        }

        long end = System.currentTimeMillis();

        System.out.println("PreparedStatement time: " + (end - start) + " ms");

        ps.close();
        conn.close();
    }
}