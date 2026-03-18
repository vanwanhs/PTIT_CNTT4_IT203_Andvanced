import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();

        while (true) {
            System.out.println("\n----- QUẢN LÝ SẢN PHẨM -----");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Xem danh sách");
            System.out.println("3. Cập nhật");
            System.out.println("4. Xóa");
            System.out.println("5. Thoát");
            System.out.print("Chọn: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Nhập sai!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("1. Physical | 2. Digital");
                    int type = Integer.parseInt(sc.nextLine());
                    Product p = ProductFactory.createProduct(type, sc);
                    db.addProduct(p);
                    break;

                case 2:
                    db.displayAll();
                    break;

                case 3:
                    System.out.print("Nhập ID cần sửa: ");
                    String idUpdate = sc.nextLine();

                    System.out.print("Tên mới: ");
                    String newName = sc.nextLine();

                    double newPrice = 0;
                    try {
                        System.out.print("Giá mới: ");
                        newPrice = Double.parseDouble(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Sai định dạng!");
                        break;
                    }

                    db.updateProduct(idUpdate, newName, newPrice);
                    break;

                case 4:
                    System.out.print("Nhập ID cần xóa: ");
                    db.deleteProduct(sc.nextLine());
                    break;

                case 5:
                    System.out.println("Thoát!");
                    return;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
}