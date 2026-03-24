package lesson5;

import java.util.Scanner;

public class ReceptionView {
    private BenhNhanController controller = new BenhNhanController();

    public void showMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=== MENU LỄ TÂN ===");
            System.out.println("1. Xem giường trống");
            System.out.println("2. Tiếp nhận bệnh nhân");
            System.out.println("3. Thoát");
            System.out.print("Chọn: ");
            int choice = sc.nextInt();
            sc.nextLine(); // bỏ dòng

            switch (choice) {
                case 1:
                    controller.xemGiuongTrong();
                    break;
                case 2:
                    System.out.print("Tên bệnh nhân: ");
                    String name = sc.nextLine();
                    System.out.print("Tuổi: ");
                    int age = sc.nextInt();
                    System.out.print("Mã giường: ");
                    int bedId = sc.nextInt();
                    System.out.print("Số tiền tạm ứng: ");
                    double tamUng = sc.nextDouble();
                    controller.tiepNhanBenhNhan(name, age, bedId, tamUng);
                    break;
                case 3:
                    System.out.println("Thoát chương trình.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }
    public static void main(String[] args) {
        new ReceptionView().showMenu();
    }
}

