package lesson6;

public class FirstTimeDiscount implements DiscountStrategy {
    public double applyDiscount(double amount) {
        double discount = amount * 0.15;
        System.out.println("Áp dụng giảm giá 15%: " + discount);
        return amount - discount;
    }
}