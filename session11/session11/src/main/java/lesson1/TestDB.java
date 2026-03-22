package lesson1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestDB {

    public static void main(String[] args) {
        Connection conn = null;

        try {
            conn = DBContext.getConnection();

            String sql = "SELECT * FROM Patients";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBContext.closeConnection(conn);
        }
    }
}