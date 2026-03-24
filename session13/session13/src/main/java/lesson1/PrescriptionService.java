package lesson1;

import java.sql.*;

public class PrescriptionService {
    private static final String url = "jdbc:mysql://localhost:3307/session13";
    private static final String user = "root";
    private static final String pass = "123456";

    public static void main(String[] args) {
        PrescriptionService service = new PrescriptionService();
        service.capPhatThuoc(1, 101);
    }

    public void capPhatThuoc(int medicineId, int patientId) {
        Connection connection = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try {
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false); // Tắt auto-commit

            // Thao tác 1: Trừ số lượng thuốc trong kho
            String sqlUpdateInventory = "UPDATE Medicine_Inventory SET quantity = quantity - 1 WHERE medicine_id = ?";
            ps1 = connection.prepareStatement(sqlUpdateInventory);
            ps1.setInt(1, medicineId);
            ps1.executeUpdate();

            // Thao tác 2: Ghi vào lịch sử bệnh án
            String sqlInsertHistory = "INSERT INTO Prescription_History (patient_id, medicine_id, date) VALUES (?, ?, NOW())";
            ps2 = connection.prepareStatement(sqlInsertHistory);
            ps2.setInt(1, patientId);
            ps2.setInt(2, medicineId);
            ps2.executeUpdate();

            // Commit nếu cả hai thành công
            connection.commit();
            System.out.println("Cấp phát thuốc thành công!");
        } catch (SQLException e) {
            try {
                if (connection != null) connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            System.out.println("Có lỗi xảy ra: " + e.getMessage());
        } finally {
            try {
                if (ps1 != null) ps1.close();
                if (ps2 != null) ps2.close();
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}