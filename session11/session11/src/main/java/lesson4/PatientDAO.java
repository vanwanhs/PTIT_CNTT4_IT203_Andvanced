package lesson4;

import lesson1.DBContext;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PatientDAO {

    public void findPatientByName(String nameInput) {

        //  lọc dữ liệu đầu vào
        String safeInput = sanitizeInput(nameInput);

        String sql = "SELECT * FROM patients WHERE name = '" + safeInput + "'";

        try (Connection con = DBContext.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println("Tên: " + rs.getString("name"));
            }

            if (!found) {
                System.out.println("Không tìm thấy bệnh nhân!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm lọc
    private String sanitizeInput(String input) {
        if (input == null) return "";
        return input.replace("'", "")
                .replace("--", "")
                .replace(";", "");
    }
}