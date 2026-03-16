package lesson4;

public class Main {

    public static void main(String[] args) {

        System.out.println("Cấu hình 1: File + Email");

        OrderRepository fileRepo = new FileOrderRepository();
        NotificationService email = new EmailService();

        OrderService service1 = new OrderService(fileRepo, email);

        Order order1 = new Order("ORD001");

        System.out.println("Tạo đơn hàng ORD001");

        service1.createOrder(order1);


        System.out.println();
        System.out.println("Cấu hình 2: Database + SMS");


        OrderRepository dbRepo = new DatabaseOrderRepository();
        NotificationService sms = new SMSNotification();

        OrderService service2 = new OrderService(dbRepo, sms);

        Order order2 = new Order("ORD002");

        System.out.println("Tạo đơn hàng ORD002");

        service2.createOrder(order2);
    }
}