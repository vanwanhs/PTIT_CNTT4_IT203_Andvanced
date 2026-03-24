package lesson2;
import java.sql.*;

public class PaymentService {
    private static final String url = "jdbc:mysql://localhost:3307/session13";
    private static final String user = "root";
    private static final String pass = "123456";

    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        // Test case 1: Thanh toán thành công
        System.out.println("=== Test case 1: Thanh toán thành công ===");
        service.thanhToanVienPhi(101, 1, 200000);
        service.checkData(101, 1);

        // Test case 2: Thanh toán lỗi (cố tình sai tên bảng để kiểm chứng rollback)
        System.out.println("\n=== Test case 2: Thanh toán lỗi (rollback) ===");
        service.thanhToanVienPhiLoi(102, 2, 100000);
        service.checkData(102, 2);
    }

    // Hàm thanh toán chuẩn
    public void thanhToanVienPhi(int patientId, int invoiceId, double amount) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            conn.setAutoCommit(false);

            // Trừ tiền trong ví
            String sqlDeductWallet = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            try (PreparedStatement ps1 = conn.prepareStatement(sqlDeductWallet)) {
                ps1.setDouble(1, amount);
                ps1.setInt(2, patientId);
                ps1.executeUpdate();
            }

            // Cập nhật hóa đơn
            String sqlUpdateInvoice = "UPDATE Invoices SET status = 'PAID' WHERE invoice_id = ?";
            try (PreparedStatement ps2 = conn.prepareStatement(sqlUpdateInvoice)) {
                ps2.setInt(1, invoiceId);
                ps2.executeUpdate();
            }

            conn.commit();
            System.out.println("Thanh toán hoàn tất!");
        } catch (SQLException e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
            try {
                if (conn != null) conn.rollback();
                System.out.println("Rollback thành công.");
            } catch (SQLException ex) {
                System.out.println("Rollback thất bại: " + ex.getMessage());
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Hàm thanh toán lỗi để test rollback
    public void thanhToanVienPhiLoi(int patientId, int invoiceId, double amount) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            conn.setAutoCommit(false);

            // Trừ tiền trong ví
            String sqlDeductWallet = "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";
            try (PreparedStatement ps1 = conn.prepareStatement(sqlDeductWallet)) {
                ps1.setDouble(1, amount);
                ps1.setInt(2, patientId);
                ps1.executeUpdate();
            }

            // Cố tình viết sai tên bảng để gây lỗi
            String sqlUpdateInvoice = "UPDATE Invoicess SET status = 'PAID' WHERE invoice_id = ?";
            try (PreparedStatement ps2 = conn.prepareStatement(sqlUpdateInvoice)) {
                ps2.setInt(1, invoiceId);
                ps2.executeUpdate();
            }

            conn.commit();
            System.out.println("Thanh toán hoàn tất!");
        } catch (SQLException e) {
            System.out.println("Lỗi hệ thống (test rollback): " + e.getMessage());
            try {
                if (conn != null) conn.rollback();
                System.out.println("Rollback thành công.");
            } catch (SQLException ex) {
                System.out.println("Rollback thất bại: " + ex.getMessage());
            }
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Hàm kiểm tra dữ liệu
    public void checkData(int patientId, int invoiceId) {
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String sqlWallet = "SELECT balance FROM Patient_Wallet WHERE patient_id = ?";
            try (PreparedStatement ps1 = conn.prepareStatement(sqlWallet)) {
                ps1.setInt(1, patientId);
                ResultSet rs1 = ps1.executeQuery();
                if (rs1.next()) {
                    System.out.println("Số dư ví bệnh nhân " + patientId + ": " + rs1.getDouble("balance"));
                }
            }

            String sqlInvoice = "SELECT status FROM Invoices WHERE invoice_id = ?";
            try (PreparedStatement ps2 = conn.prepareStatement(sqlInvoice)) {
                ps2.setInt(1, invoiceId);
                ResultSet rs2 = ps2.executeQuery();
                if (rs2.next()) {
                    System.out.println("Trạng thái hóa đơn " + invoiceId + ": " + rs2.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}