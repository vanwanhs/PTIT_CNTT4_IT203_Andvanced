package dao;
import entity.OrderDetail;
import java.sql.*;
import java.util.List;

public class OrderDetailDAO {
    public void insertOrderDetailsBatch(int orderId, List<OrderDetail> details, Connection conn) throws SQLException {
        String sql = "INSERT INTO Order_Details(order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            for (OrderDetail d : details) {
                ps.setInt(1, orderId);
                ps.setInt(2, d.getProductId());
                ps.setInt(3, d.getQuantity());
                ps.setDouble(4, d.getPrice());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
