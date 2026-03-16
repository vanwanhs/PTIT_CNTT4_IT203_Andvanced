package lesson1;

public class Main {
    public static void main(String[] args) {

        Product p1 = new Product("SP01", "Laptop", 15000000);
        Product p2 = new Product("SP02", "Chuột", 300000);

        System.out.println("Đã thêm sản phẩm SP01, SP02");

        Customer customer = new Customer(
                "Nguyễn Văn A",
                "a@example.com",
                "Hà Nội"
        );

        System.out.println("Tạo đơn hàng: SP01 (1 cái), SP02 (2 cái)");

        Order order = new Order("ORD001", customer);
        order.addProduct(p1, 1);
        order.addProduct(p2, 2);

        OrderCalculator calculator = new OrderCalculator();
        calculator.calculateTotal(order);

        OrderRepository repository = new OrderRepository();
        repository.save(order);

        EmailService emailService = new EmailService();
        emailService.sendEmail(order);
    }
}
