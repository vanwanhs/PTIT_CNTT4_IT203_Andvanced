package lesson6;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Chọn kênh bán");
        System.out.println("1. Website");
        System.out.println("2. Mobile App");
        System.out.println("3. POS");

        int choice = sc.nextInt();

        SalesChannelFactory factory = null;

        if(choice == 1) {

            System.out.println("Bạn đã chọn kênh Website");
            factory = new WebsiteFactory();
        }

        else if(choice == 2) {

            System.out.println("Bạn đã chọn kênh Mobile App");
            factory = new MobileAppFactory();
        }

        else if(choice == 3) {

            System.out.println("Bạn đã chọn kênh POS");
            factory = new StorePOSFactory();
        }

        OrderService service = new OrderService(factory);

        service.createOrder("Laptop",15000000);
    }
}