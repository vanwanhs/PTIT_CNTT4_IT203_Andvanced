package dao;;

import entity.Accounts;
import utils.DataBaseConnectManager;

import java.sql.*;

public class AccountDAO {

    public Accounts getAccountById(String id) throws SQLException {
        String sql = "SELECT AccountId, fullName, balance FROM Accounts WHERE AccountId = ?";
        try (PreparedStatement ps = DataBaseConnectManager.getInstance().getConnection().prepareStatement(sql)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Accounts(
                        rs.getString("AccountId"),
                        rs.getString("fullName"),
                        rs.getDouble("balance")
                );
            }
        }
        return null;
    }

    public void transfer(String sender, String receiver, double amount) throws SQLException {
        Connection conn = DataBaseConnectManager.getInstance().getConnection();
        try {
            conn.setAutoCommit(false);

            // Kiểm tra số dư
            Accounts accSender = getAccountById(sender);
            if (accSender == null || accSender.getBalance() < amount) {
                throw new SQLException("Số dư không đủ hoặc tài khoản không tồn tại!");
            }

            // Trừ tiền người gửi
            try (CallableStatement cs = conn.prepareCall("{CALL sp_UpdateBalance(?, ?)}")) {
                cs.setString(1, sender);
                cs.setDouble(2, -amount);
                cs.executeUpdate();
            }

            // Cộng tiền người nhận
            try (CallableStatement cs = conn.prepareCall("{CALL sp_UpdateBalance(?, ?)}")) {
                cs.setString(1, receiver);
                cs.setDouble(2, amount);
                cs.executeUpdate();
            }

            conn.commit();
            System.out.println("Chuyển khoản thành công!");

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }
}
