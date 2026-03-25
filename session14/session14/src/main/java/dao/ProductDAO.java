package dao;

import entity.Product;
import utils.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    // Thêm sản phẩm
    public void addProduct(Product p) throws SQLException {
        String sql = "INSERT INTO Products(name, category, price, stock) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.executeUpdate();
        }
    }

    // Sửa sản phẩm
    public void updateProduct(Product p) throws SQLException {
        String sql = "UPDATE Products SET name=?, category=?, price=?, stock=? WHERE product_id=?";
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getProductId());
            ps.executeUpdate();
        }
    }

    // Xóa sản phẩm
    public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM Products WHERE product_id=?";
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ps.executeUpdate();
        }
    }

    // Lấy danh sách sản phẩm
    public List<Product> getAllProducts() throws SQLException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("product_id"));
                p.setName(rs.getString("name"));
                p.setCategory(rs.getString("category"));
                p.setPrice(rs.getDouble("price"));
                p.setStock(rs.getInt("stock"));
                list.add(p);
            }
        }
        return list;
    }

    // Kiểm tra tồn tại sản phẩm
    public boolean exists(int productId) throws SQLException {
        String sql = "SELECT 1 FROM Products WHERE product_id = ?";
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    // Lấy giá sản phẩm
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
    public int getStockForUpdate(int productId, Connection conn) throws SQLException {
        String sql = "SELECT stock FROM Products WHERE product_id = ? FOR UPDATE";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("stock");
            }
        }
        return 0;
    }
    public void updateStock(int productId, int quantity, Connection conn) throws SQLException {
        String sql = "UPDATE Products SET stock = stock - ? WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, quantity);       // số lượng cần trừ
            ps.setInt(2, productId);      // id sản phẩm
            ps.executeUpdate();           // thực thi update
        }
    }


}