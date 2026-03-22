package lesson2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MedicineDAO {

    public void printMedicineList() {
        String sql = "SELECT name, quantity FROM medicine";

        try (Connection con = lesson1.DBContext.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("=== DANH SÁCH THUỐC ===");

            boolean isEmpty = true;

            while (rs.next()) {
                isEmpty = false;

                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");

                System.out.println("Tên thuốc: " + name + " | Số lượng: " + quantity);
            }

            if (isEmpty) {
                System.out.println("Kho không có thuốc!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}