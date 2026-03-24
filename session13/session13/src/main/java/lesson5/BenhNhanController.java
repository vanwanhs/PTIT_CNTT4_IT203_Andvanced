package lesson5;

import java.sql.*;

public class BenhNhanController {
    public void xemGiuongTrong() {
        String sql = "SELECT bed_id FROM Bed WHERE status = 'Trống'";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("Danh sách giường trống:");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(" - Giường số " + rs.getInt("bed_id"));
            }
            if (!found) {
                System.out.println("Không có giường nào trống!");
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi khi lấy danh sách giường: " + e.getMessage());
        }
    }
    public void tiepNhanBenhNhan(String name, int age, int bedId, double tamUng) {
        Connection conn = null;
        try {
            conn = DatabaseHelper.getConnection();
            conn.setAutoCommit(false);

            // 1. Thêm bệnh nhân
            String sqlInsertPatient = "INSERT INTO BenhNhan(name, age, bed_id, status) VALUES (?, ?, ?, 'Đang điều trị')";
            try (PreparedStatement ps = conn.prepareStatement(sqlInsertPatient)) {
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setInt(3, bedId);
                int rows = ps.executeUpdate();
                if (rows == 0) throw new Exception("Không thêm được bệnh nhân!");
            }

            // 2. Cập nhật giường
            String sqlUpdateBed = "UPDATE Bed SET status = 'Đã có người' WHERE bed_id = ? AND status = 'Trống'";
            try (PreparedStatement ps = conn.prepareStatement(sqlUpdateBed)) {
                ps.setInt(1, bedId);
                int rows = ps.executeUpdate();
                if (rows == 0) throw new Exception("Giường không tồn tại hoặc đã có người!");
            }

            // 3. Ghi nhận tài chính
            String sqlInsertFinance = "INSERT INTO TaiChinh(patient_id, amount, date) VALUES ((SELECT MAX(patient_id) FROM BenhNhan), ?, NOW())";
            try (PreparedStatement ps = conn.prepareStatement(sqlInsertFinance)) {
                ps.setDouble(1, tamUng);
                int rows = ps.executeUpdate();
                if (rows == 0) throw new Exception("Không ghi nhận được tài chính!");
            }

            conn.commit();
            System.out.println("Tiếp nhận bệnh nhân thành công!");

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
