package lesson4;

import java.sql.*;

public class TestBatch {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3307/session12";
        String user = "root";
        String pass = "123456";

        Connection conn = DriverManager.getConnection(url, user, pass);

        String sql = "INSERT INTO blood_tests (id, result) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        long start = System.currentTimeMillis();

        for (int i = 1; i <= 1000; i++) {
            ps.setInt(1, i);
            ps.setDouble(2, 5.6);
            ps.addBatch();
        }

        ps.executeBatch();

        long end = System.currentTimeMillis();

        System.out.println("Batch time: " + (end - start) + " ms");

        ps.close();
        conn.close();
    }
}