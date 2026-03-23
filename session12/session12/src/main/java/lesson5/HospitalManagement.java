package lesson5;

import java.sql.*;
import java.util.Scanner;

public class HospitalManagement {
    // Thông tin kết nối DB
    private static final String URL = "jdbc:mysql://localhost:3307/session12";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Vòng lặp menu chạy liên tục
        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Danh sách bệnh nhân");
            System.out.println("2. Thêm bệnh nhân");
            System.out.println("3. Cập nhật bệnh án");
            System.out.println("4. Xuất viện & tính phí");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");

            int choice;
            try {
                // nhập lựa chọn
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số!");
                continue;
            }

            // điều hướng chức năng
            switch (choice) {
                case 1 -> listPatients();         // xem danh sách
                case 2 -> addPatient(sc);         // thêm
                case 3 -> updateDisease(sc);      // sửa
                case 4 -> discharge(sc);          // tính phí
                case 5 -> {
                    System.out.println("Thoát...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Sai lựa chọn!");
            }
        }
    }

    // ==============================
    // 1. HIỂN THỊ DANH SÁCH
    // ==============================
    public static void listPatients() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            // lấy dữ liệu từ bảng patients5
            String sql = "SELECT * FROM patients5";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // duyệt từng dòng dữ liệu
            while (rs.next()) {
                System.out.println(
                        rs.getInt("patient_id") + " | " +   // id
                                rs.getString("name") + " | " +      // tên
                                rs.getInt("age") + " | " +          // tuổi
                                rs.getString("department")          // khoa
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ==============================
    // 2. THÊM BỆNH NHÂN
    // ==============================
    public static void addPatient(Scanner sc) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            // nhập thông tin
            System.out.print("Tên: ");
            String name = sc.nextLine();

            System.out.print("Tuổi: ");
            int age = Integer.parseInt(sc.nextLine());

            System.out.print("Khoa: ");
            String dept = sc.nextLine();

            System.out.print("Bệnh: ");
            String disease = sc.nextLine();

            System.out.print("Số ngày nhập viện: ");
            int days = Integer.parseInt(sc.nextLine());

            // PreparedStatement giúp chống SQL Injection
            String sql = "INSERT INTO patients5(name, age, department, disease, admission_days) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            // gán dữ liệu vào dấu ?
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, dept);
            ps.setString(4, disease);
            ps.setInt(5, days);

            ps.executeUpdate();

            System.out.println("Thêm thành công!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ==============================
    // 3. CẬP NHẬT BỆNH ÁN
    // ==============================
    public static void updateDisease(Scanner sc) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            System.out.print("Nhập ID bệnh nhân: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Nhập bệnh mới: ");
            String disease = sc.nextLine();

            // cập nhật theo id
            String sql = "UPDATE patients5 SET disease = ? WHERE patient_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, disease);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            // kiểm tra có cập nhật không
            if (rows > 0) {
                System.out.println("Cập nhật thành công!");
            } else {
                System.out.println("Không tìm thấy bệnh nhân!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ==============================
    // 4. XUẤT VIỆN & TÍNH PHÍ
    // ==============================
    public static void discharge(Scanner sc) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {

            System.out.print("Nhập ID bệnh nhân: ");
            int id = Integer.parseInt(sc.nextLine());

            // gọi Stored Procedure
            String sql = "{ call calculate_discharge_fee(?, ?) }";
            CallableStatement cs = conn.prepareCall(sql);

            // truyền IN parameter
            cs.setInt(1, id);

            // đăng ký OUT parameter
            cs.registerOutParameter(2, Types.DOUBLE);

            cs.execute();

            // lấy kết quả từ OUT
            double fee = cs.getDouble(2);

            System.out.println("Tổng viện phí: " + fee);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}