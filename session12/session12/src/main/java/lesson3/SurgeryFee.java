package lesson3;

import java.sql.*;
import java.util.Scanner;

public class SurgeryFee {
    private static final String URL = "jdbc:mysql://localhost:3307/session12";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập surgery_id: ");
        int surgeryId = Integer.parseInt(sc.nextLine());

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);

            // Gọi stored procedure
            String sql = "{ call GET_SURGERY_FEE(?, ?) }";
            CallableStatement cstmt = conn.prepareCall(sql);

            // in parameter
            cstmt.setInt(1, surgeryId);

            // out parameter
            cstmt.registerOutParameter(2, Types.DECIMAL);

            // Thực thi
            cstmt.execute();

            // Lấy giá trị out
            double totalCost = cstmt.getDouble(2);

            System.out.println("Chi phí phẫu thuật: " + totalCost);

            cstmt.close();
            conn.close();
            sc.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}