import dao.ProductDAO;
import entity.OrderDetail;
import service.FlashSaleService;
import dao.ReportDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FlashSaleService service = new FlashSaleService();
        ReportDAO reportDAO = new ReportDAO();
        ProductDAO productDAO = new ProductDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== FLASH SALE MENU ===");
            System.out.println("1. Đặt hàng");
            System.out.println("2. Xem Top Buyers");
            System.out.println("3. Xem doanh thu theo Category");
            System.out.println("4. Test Multi-thread Overselling");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập userId: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    List<OrderDetail> details = new ArrayList<>();
                    while (true) {
                        System.out.print("Nhập productId (0 để dừng): ");
                        int pid = sc.nextInt();
                        if (pid == 0) break;

                        // validate productId
                        try {
                            if (!productDAO.exists(pid)) {
                                System.out.println("Sản phẩm không tồn tại!");
                                continue;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            continue;
                        }

                        System.out.print("Số lượng: ");
                        int qty = sc.nextInt();
                        if (qty <= 0) {
                            System.out.println("Số lượng phải > 0!");
                            continue;
                        }

                        // lấy giá từ DB
                        double price = 0;
                        try {
                            price = productDAO.getPrice(pid);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        details.add(new OrderDetail(pid, qty, price));
                    }
                    service.placeOrder(userId, details);
                    break;

                case 2:
                    reportDAO.getTopBuyers();
                    break;

                case 3:
                    System.out.print("Nhập category: ");
                    String cat = sc.nextLine();
                    reportDAO.getRevenueByCategory(cat);
                    break;

                case 4:
                    Runnable task = () -> {
                        List<OrderDetail> d = new ArrayList<>();
                        d.add(new OrderDetail(1, 1, 1000));
                        service.placeOrder(1, d);
                    };
                    for (int i = 0; i < 50; i++) {
                        new Thread(task).start();
                    }
                    break;

                case 5:
                    System.out.println("Thoát chương trình.");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}