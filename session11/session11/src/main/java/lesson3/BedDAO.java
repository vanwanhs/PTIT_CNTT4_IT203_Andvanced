package lesson3;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class BedDAO {

    public void updateBedStatus(String bedId) {
        String sql = "UPDATE beds SET bed_status = 'Đang sử dụng' WHERE bed_id = ?";

        try (Connection con = lesson1.DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, bedId);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Cập nhật thành công! Giường " + bedId + " đang được sử dụng.");
            } else {
                System.out.println("LỖI: Mã giường " + bedId + " không tồn tại!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}