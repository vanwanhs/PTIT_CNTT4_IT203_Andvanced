package lesson3;

import java.sql.*;

public class DischargeService {
    private static final String url = "jdbc:mysql://localhost:3307/session13";
    private static final String user = "root";
    private static final String pass = "123456";

    public static void main(String[] args) {
        DischargeService service = new DischargeService();
        service.xuatVienVaThanhToan(101, 200000); // Test bệnh nhân 101
        service.xuatVienVaThanhToan(102, 200000); // Test bệnh nhân 102 (thiếu tiền -> rollback)
    }

    public void xuatVienVaThanhToan(int maBenhNhan, double tienVienPhi) {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
            conn.setAutoCommit(false);

            // Bẫy 1: Kiểm tra số dư
            String sqlCheckBalance = "SELECT balance, bed_id FROM Patient WHERE patient_id = ?";
            int bedId = -1;
            double balance = 0;
            try (PreparedStatement ps = conn.prepareStatement(sqlCheckBalance)) {
                ps.setInt(1, maBenhNhan);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    balance = rs.getDouble("balance");
                    bedId = rs.getInt("bed_id");
                } else {
                    throw new Exception("Bệnh nhân không tồn tại!");
                }
            }
            if (balance < tienVienPhi) {
                throw new Exception("Bẫy 1: Số dư không đủ để thanh toán!");
            }

            // Bước 1: Trừ tiền viện phí
            String sqlUpdateBalance = "UPDATE Patient SET balance = balance - ? WHERE patient_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlUpdateBalance)) {
                ps.setDouble(1, tienVienPhi);
                ps.setInt(2, maBenhNhan);
                int rows = ps.executeUpdate();
                if (rows == 0) throw new Exception("Bẫy 2: Không có bệnh nhân nào được cập nhật số dư!");
            }

            // Bước 2: Giải phóng giường
            String sqlUpdateBed = "UPDATE Bed SET status = 'Trống' WHERE bed_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlUpdateBed)) {
                ps.setInt(1, bedId);
                int rows = ps.executeUpdate();
                if (rows == 0) throw new Exception("Bẫy 2: Không có giường nào được cập nhật!");
            }

            // Bước 3: Cập nhật trạng thái bệnh nhân
            String sqlUpdatePatient = "UPDATE Patient SET status = 'Đã xuất viện' WHERE patient_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sqlUpdatePatient)) {
                ps.setInt(1, maBenhNhan);
                int rows = ps.executeUpdate();
                if (rows == 0) throw new Exception("Bẫy 2: Không có bệnh nhân nào được cập nhật trạng thái!");
            }

            // Commit nếu tất cả thành công
            conn.commit();
            System.out.println("Xuất viện và thanh toán thành công cho bệnh nhân " + maBenhNhan);

        } catch (Exception e) {
            System.out.println("Có lỗi xảy ra: " + e.getMessage());
            try {
                if (conn != null) conn.rollback();
                System.out.println("Rollback thành công!");
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
}