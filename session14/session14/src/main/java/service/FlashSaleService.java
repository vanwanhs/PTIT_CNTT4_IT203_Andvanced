package service;

import dao.*;
import entity.OrderDetail;
import utils.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class FlashSaleService {
    private ProductDAO productDAO = new ProductDAO();
    private OrderDAO orderDAO = new OrderDAO();
    private OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

    public void placeOrder(int userId, List<OrderDetail> details) {
        Connection conn = null;
        try {
            conn = DatabaseConnectionManager.getInstance().getConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            // Kiểm tra tồn kho
            for (OrderDetail d : details) {
                int stock = productDAO.getStockForUpdate(d.getProductId(), conn);
                if (stock < d.getQuantity()) throw new Exception("Hết hàng cho sản phẩm " + d.getProductId());
            }

            // Trừ tồn kho
            for (OrderDetail d : details) {
                productDAO.updateStock(d.getProductId(), d.getQuantity(), conn);
            }

            // Tạo Order
            int orderId = orderDAO.createOrder(userId, conn);

            // Tạo Order_Details (Batch)
            orderDetailDAO.insertOrderDetailsBatch(orderId, details, conn);

            conn.commit();
            System.out.println("Đặt hàng thành công!");
        } catch (Exception e) {
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            System.out.println("Đặt hàng thất bại: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); }
        }
    }
}
