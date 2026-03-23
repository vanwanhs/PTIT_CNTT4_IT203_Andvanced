package lesson2;

import java.sql.*;
import java.util.Scanner;

public class UpdateVitalSigns {
    private static final String Url = "jdbc:mysql://localhost:3307/session12";
    private static final String User = "root";
    private static final String Pass = "123456";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập id bệnh nhân: ");
        String patientId = sc.nextLine();

        System.out.print("Nhập nhiệt độ: ");
        double temperature = Double.parseDouble(sc.nextLine());

        System.out.print("Nhập nhịp tim: ");
        int heartRate = Integer.parseInt(sc.nextLine());

        try {
            Connection connection = DriverManager.getConnection(Url, User, Pass);

            String sql = "UPDATE patients SET temperature = ?, heartRate = ? WHERE patient_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setDouble(1, temperature);
            ps.setInt(2, heartRate);
            ps.setString(3, patientId);

            int rows = ps.executeUpdate();

            System.out.println("Rows affected = " + rows);

            if (rows > 0) {
                System.out.println("Cập nhật thành công");
            } else {
                System.out.println("Không tìm thấy bệnh nhân");
            }

            ps.close();
            connection.close();
            sc.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}