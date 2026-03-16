package lesson5;
import java.util.*;

public class Order {

    private String orderId;
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();
    private double finalAmount;

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double getTotal() {
        return items.stream().mapToDouble(OrderItem::getTotal).sum();
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getOrderId() { return orderId; }

    public Customer getCustomer() { return customer; }

    public void setFinalAmount(double amount) {
        this.finalAmount = amount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }
}
