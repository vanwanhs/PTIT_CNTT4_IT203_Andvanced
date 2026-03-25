package dao;
import java.sql.*;
import utils.DatabaseConnectionManager;

public class ReportDAO {
    public void getTopBuyers() {
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall("{CALL SP_GetTopBuyers()}")) {
            ResultSet rs = cs.executeQuery();
            System.out.println("Top Buyers:");
            while (rs.next()) {
                System.out.println(rs.getString("username") + " - " + rs.getInt("total_buy"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void getRevenueByCategory(String category) {
        try (Connection conn = DatabaseConnectionManager.getInstance().getConnection();
             CallableStatement cs = conn.prepareCall("{? = CALL FUNC_CalculateCategoryRevenue(?)}")) {
            cs.registerOutParameter(1, Types.DOUBLE);
            cs.setString(2, category);
            cs.execute();
            double revenue = cs.getDouble(1);
            System.out.println("Doanh thu cho category " + category + ": " + revenue);
        } catch (SQLException e) { e.printStackTrace(); }
    }
}