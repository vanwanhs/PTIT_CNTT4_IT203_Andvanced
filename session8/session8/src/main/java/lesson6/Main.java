package lesson6;
import factory.*;
import service.OrderService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Website");
        System.out.println("2. Mobile App");
        System.out.println("3. POS");

        int choice = sc.nextInt();

        SalesChannelFactory factory = null;

        switch (choice) {
            case 1:
                System.out.println("Bạn đã chọn Website");
                factory = new WebsiteFactory();
                break;
            case 2:
                System.out.println("Bạn đã chọn Mobile App");
                factory = new MobileAppFactory();
                break;
            case 3:
                System.out.println("Bạn đã chọn POS");
                factory = new POSFactory();
                break;
        }

        System.out.print("Nhập giá sản phẩm: ");
        double price = sc.nextDouble();

        System.out.print("Nhập số lượng: ");
        int qty = sc.nextInt();

        OrderService order = new OrderService(factory);
        order.processOrder(price, qty);
    }
}