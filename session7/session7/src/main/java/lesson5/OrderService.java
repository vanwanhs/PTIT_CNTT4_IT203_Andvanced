package lesson5;

public class OrderService {

    private OrderRepository repository;
    private NotificationService notification;

    public OrderService(OrderRepository repository,
                        NotificationService notification) {
        this.repository = repository;
        this.notification = notification;
    }

    public void placeOrder(Order order,
                           DiscountStrategy discount,
                           PaymentMethod payment) {

        double total = order.getTotal();

        double finalAmount = discount.applyDiscount(total);

        order.setFinalAmount(finalAmount);

        payment.pay(finalAmount);

        repository.save(order);

        notification.send("Order created");
    }
}