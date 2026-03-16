package lesson6;

public class OrderService {

    private DiscountStrategy discount;
    private PaymentMethod payment;
    private NotificationService notification;

    public OrderService(SalesChannelFactory factory) {

        discount = factory.createDiscount();
        payment = factory.createPayment();
        notification = factory.createNotification();
    }

    public void createOrder(String product, double price) {

        System.out.println("Sản phẩm: " + product);

        double finalPrice = discount.applyDiscount(price);

        payment.pay(finalPrice);

        notification.send("Order success");

        System.out.println("Thanh toán thành công: " + finalPrice);
    }
}