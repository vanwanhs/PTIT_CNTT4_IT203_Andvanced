import dao.ProductDAO;
import dao.UserDAO;
import dao.ReportDAO;
import entity.OrderDetail;
import entity.Product;
import entity.User;
import service.FlashSaleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FlashSaleService service = new FlashSaleService();
        ReportDAO reportDAO = new ReportDAO();
        ProductDAO productDAO = new ProductDAO();
        UserDAO userDAO = new UserDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== FLASH SALE MENU ===");
            System.out.println("1. Đặt hàng");
            System.out.println("2. Xem Top Buyers");
            System.out.println("3. Xem doanh thu theo Category");
            System.out.println("4. Test Multi-thread Overselling");
            System.out.println("5. Quản lý sản phẩm");
            System.out.println("6. Quản lý người dùng");
            System.out.println("7. Thoát");
            System.out.print("Chọn: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: // Đặt hàng
                    System.out.print("Nhập userId: ");
                    int userId = sc.nextInt();
                    sc.nextLine();
                    List<OrderDetail> details = new ArrayList<>();
                    while (true) {
                        System.out.print("Nhập productId (0 để dừng): ");
                        int pid = sc.nextInt();
                        if (pid == 0) break;

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

                case 2: // Top Buyers
                    reportDAO.getTopBuyers();
                    break;

                case 3: // Doanh thu theo Category
                    System.out.print("Nhập category: ");
                    String cat = sc.nextLine();
                    reportDAO.getRevenueByCategory(cat);
                    break;

                case 4: // Test Overselling
                    Runnable task = () -> {
                        List<OrderDetail> d = new ArrayList<>();
                        d.add(new OrderDetail(1, 1, 1000));
                        service.placeOrder(1, d);
                    };
                    for (int i = 0; i < 50; i++) {
                        new Thread(task).start();
                    }
                    break;

                case 5: // Quản lý sản phẩm
                    System.out.println("=== Quản lý sản phẩm ===");
                    System.out.println("1. Thêm sản phẩm");
                    System.out.println("2. Sửa sản phẩm");
                    System.out.println("3. Xóa sản phẩm");
                    System.out.println("4. Xem tất cả sản phẩm");
                    int pChoice = sc.nextInt();
                    sc.nextLine();
                    try {
                        switch (pChoice) {
                            case 1:
                                System.out.print("Tên: ");
                                String name = sc.nextLine();
                                System.out.print("Category: ");
                                String category = sc.nextLine();
                                System.out.print("Giá: ");
                                double price = sc.nextDouble();
                                System.out.print("Stock: ");
                                int stock = sc.nextInt();
                                productDAO.addProduct(new Product(0, name, category, price, stock));
                                break;
                            case 2:
                                System.out.print("Nhập productId: ");
                                int pid = sc.nextInt(); sc.nextLine();
                                System.out.print("Tên mới: ");
                                String newName = sc.nextLine();
                                System.out.print("Category mới: ");
                                String newCat = sc.nextLine();
                                System.out.print("Giá mới: ");
                                double newPrice = sc.nextDouble();
                                System.out.print("Stock mới: ");
                                int newStock = sc.nextInt();
                                productDAO.updateProduct(new Product(pid, newName, newCat, newPrice, newStock));
                                break;
                            case 3:
                                System.out.print("Nhập productId: ");
                                int delPid = sc.nextInt();
                                productDAO.deleteProduct(delPid);
                                break;
                            case 4:
                                for (Product p : productDAO.getAllProducts()) {
                                    System.out.println(p.getProductId() + " - " + p.getName() + " - " + p.getCategory() + " - " + p.getPrice() + " - stock=" + p.getStock());
                                }
                                break;
                        }
                    } catch (Exception e) { e.printStackTrace(); }
                    break;

                case 6: // Quản lý người dùng
                    System.out.println("=== Quản lý người dùng ===");
                    System.out.println("1. Thêm user");
                    System.out.println("2. Sửa user");
                    System.out.println("3. Xóa user");
                    System.out.println("4. Xem tất cả user");
                    int uChoice = sc.nextInt();
                    sc.nextLine();
                    try {
                        switch (uChoice) {
                            case 1:
                                System.out.print("Username: ");
                                String uname = sc.nextLine();
                                System.out.print("Email: ");
                                String email = sc.nextLine();
                                userDAO.addUser(new User(0, uname, email));
                                break;
                            case 2:
                                System.out.print("Nhập userId: ");
                                int uid = sc.nextInt(); sc.nextLine();
                                System.out.print("Username mới: ");
                                String newUname = sc.nextLine();
                                System.out.print("Email mới: ");
                                String newEmail = sc.nextLine();
                                userDAO.updateUser(new User(uid, newUname, newEmail));
                                break;
                            case 3:
                                System.out.print("Nhập userId: ");
                                int delUid = sc.nextInt();
                                userDAO.deleteUser(delUid);
                                break;
                            case 4:
                                for (User u : userDAO.getAllUsers()) {
                                    System.out.println(u.getUserId() + " - " + u.getUsername() + " - " + u.getEmail());
                                }
                                break;
                        }
                    } catch (Exception e) { e.printStackTrace(); }
                    break;

                case 7: // Thoát
                    System.out.println("Thoát chương trình.");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}