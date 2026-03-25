import dao.AccountDAO;
import entity.Accounts;

public class Main {
    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        try {
            dao.transfer("ACC01", "ACC02", 1000);

            Accounts acc1 = dao.getAccountById("ACC01");
            Accounts acc2 = dao.getAccountById("ACC02");

            System.out.println("Kết quả sau chuyển khoản:");
            acc1.display();
            acc2.display();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
