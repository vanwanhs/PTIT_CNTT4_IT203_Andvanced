package lesson5.presentation;

import lesson5.business.DoctorService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DoctorService service = new DoctorService();

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Xem danh sách bác sĩ");
            System.out.println("2. Thêm bác sĩ");
            System.out.println("3. Thống kê chuyên khoa");
            System.out.println("4. Thoát");

            System.out.print("Chọn: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    service.showAll();
                    break;
                case 2:
                    System.out.print("ID: ");
                    String id = sc.nextLine();

                    System.out.print("Tên: ");
                    String name = sc.nextLine();

                    System.out.print("Chuyên khoa: ");
                    String sp = sc.nextLine();

                    service.addDoctor(id, name, sp);
                    break;
                case 3:
                    service.statistic();
                    break;
                case 4:
                    System.out.println("Thoát...");
                    return;
                default:
                    System.out.println("Sai lựa chọn!");
            }
        }
    }
}