package lesson1;

import java.sql.*;
import java.util.Scanner;

public class DoctorLogin {
    private static final String URL = "jdbc:mysql://localhost:3307/session12";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap tai khoan cua ban");
        String userName = sc.nextLine();
        System.out.println("Nhap mat khau");
        String passWord = sc.nextLine();
        try{
            Connection connection = DriverManager.getConnection(URL,USER,PASS);
            String sql = "Select * from Dortors where doctorCode = ? and doctorPass = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,userName);
            ps.setString(2,passWord);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("dang nhap thanh cong");
            } else {
                System.out.println("Dang nhap that bai");
            }
            rs.close();
            ps.close();
            connection.close();
            sc.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}