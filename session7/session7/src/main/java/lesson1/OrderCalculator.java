package lesson1;
import java.util.Map;

public class OrderCalculator {

    public double calculateTotal(Order order) {
        double total = 0;

        for (Map.Entry<Product, Integer> entry : order.getProducts().entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }

        order.setTotal(total);

        System.out.println("Tính tổng tiền");
        System.out.println("Tổng tiền: " + (int) total);

        return total;
    }
}