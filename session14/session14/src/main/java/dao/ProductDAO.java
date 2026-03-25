package dao;
import utils.DatabaseConnectionManager;

import java.sql.*;

public class ProductDAO {
    public int getStockForUpdate(int productId, Connection conn) throws SQLException {
        String sql = "SELECT stock FROM Products WHERE product_id = ? FOR UPDATE";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getInt("stock");
        }
        return 0;
    }
    public void updateStock(int productId, int quantity, Connection conn) throws SQLException {
        String sql = "UPDATE Products SET stock = stock - ? WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);
            ps.setInt(2, productId);
            ps.executeUpdate();
        }
    }
    public boolean exists(int productId) throws SQLException {
        String sql = "SELECT 1 FROM Products WHERE product_id = ?";
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    public double getPrice(int productId) throws SQLException {
        String sql = "SELECT price FROM Products WHERE product_id = ?";
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getDouble("price");
        }
        return 0;
    }

}