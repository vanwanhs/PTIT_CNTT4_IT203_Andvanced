package lesson5;

public class HolidayDiscount implements DiscountStrategy {

    public double applyDiscount(double total) {
        return total * 0.85;
    }
}