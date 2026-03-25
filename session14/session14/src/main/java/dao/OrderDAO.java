package dao;
import java.sql.*;

public class OrderDAO {
    public int createOrder(int userId, Connection conn) throws SQLException {
        String sql = "INSERT INTO Orders(user_id, order_date, status) VALUES (?, NOW(), 'CONFIRMED')";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, userId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            return rs.getInt(1);
        }
    }
}
