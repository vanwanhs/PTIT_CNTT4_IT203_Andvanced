package lesson4;
import java.sql.*;
import java.util.*;
public class DashboardService {
    private static final String url = "jdbc:mysql://localhost:3307/session13";
    private static final String user = "root";
    private static final String pass = "123456";

    public List<BenhNhanDTO> layDanhSachBenhNhanCapCuu() {
        Map<Integer, BenhNhanDTO> map = new HashMap<>();
        String sql = "SELECT b.maBenhNhan, b.tenBenhNhan, d.maDichVu, d.tenDichVu " +
                "FROM BenhNhan b " +
                "LEFT JOIN DichVuSuDung d ON b.maBenhNhan = d.maBenhNhan " +
                "WHERE b.khoa = 'Cap cuu' AND b.ngayNhapVien = CURDATE()";

        try (Connection conn = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int maBN = rs.getInt("maBenhNhan");
                String tenBN = rs.getString("tenBenhNhan");

                // Nếu bệnh nhân chưa có trong map thì tạo mới
                BenhNhanDTO bn = map.get(maBN);
                if (bn == null) {
                    bn = new BenhNhanDTO(maBN, tenBN);
                    map.put(maBN, bn);
                }

                // Bẫy 2: Nếu bệnh nhân chưa có dịch vụ (LEFT JOIN trả về null)
                int maDV = rs.getInt("maDichVu");
                String tenDV = rs.getString("tenDichVu");
                if (tenDV != null) { // chỉ thêm nếu có dịch vụ
                    bn.dsDichVu.add(new DichVu(maDV, tenDV));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        DashboardService service = new DashboardService();
        List<BenhNhanDTO> ds = service.layDanhSachBenhNhanCapCuu();
        for (BenhNhanDTO bn : ds) {
            System.out.println("Bệnh nhân: " + bn.tenBenhNhan);
            if (bn.dsDichVu.isEmpty()) {
                System.out.println("  (Chưa có dịch vụ nào)"); // xử lý Bẫy 2
            } else {
                for (DichVu dv : bn.dsDichVu) {
                    System.out.println("  - " + dv.tenDichVu);
                }
            }
        }
    }
}
